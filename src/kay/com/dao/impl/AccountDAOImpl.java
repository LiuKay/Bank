package kay.com.dao.impl;

import kay.com.dao.AccountDAO;
import kay.com.dao.BankWaterDAO;
import kay.com.entity.Account;
import kay.com.entity.BankWater;
import kay.com.enums.CheckTypeEnum;
import kay.com.utils.DBUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAOImpl implements AccountDAO{
	
	private BankWaterDAO bankWaterDAO;   //存取款需要添加流水记录
	
	public AccountDAOImpl() {
		bankWaterDAO=new BankWaterDAOImpl();
	}


	@Override
	public Account queryAccountById(String accountId){		
		Connection connection=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		Account account=null;
		String sql="select * from account where account_id = ?";	
		try {	
			connection=DBUtils.getConnection();
			psmt=connection.prepareStatement(sql);
			psmt.setString(1, accountId);
			rs= psmt.executeQuery();			
			while(rs.next()){
				account=new Account();
				account.setAccountId(rs.getString(1));
				account.setAccountName(rs.getString(2));
				account.setCurrency(rs.getString(3));
				account.setBalance(rs.getBigDecimal(4));
				account.setOpenTime(rs.getDate(5));
				account.setOpenPlace(rs.getString(6));
				account.setAccountState(rs.getString(7));
				account.setPassword(rs.getString(8));
			}
			return account;
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			DBUtils.release(connection, psmt, rs);
		}		
		return account;
	}

	/**
	 * 事务：存取款+记录流水
	 */
	@Override
	public int checkInOut(String accountId,BigDecimal checkMoney,CheckTypeEnum checkType){
		Connection connection=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		BankWater bankWater=null;
		int count=0;   //更新行数
		try {	
			Account account= queryAccountById(accountId);
			BigDecimal balance=account.getBalance();   //获取原有余额
			if(checkType == CheckTypeEnum.CHECK_IN){
				balance=balance.add(checkMoney);		//更新余额
			}else if(checkType == CheckTypeEnum.CHECK_OUT){
				balance=balance.subtract(checkMoney);
			}
			//新建流水
			bankWater=new BankWater(accountId, checkMoney, checkType, balance);
		    String sql="UPDATE account SET balance=? where account_id=?";
			connection=DBUtils.getConnection();
			connection.setAutoCommit(false); //开启事务
			psmt=connection.prepareStatement(sql);			
			psmt.setBigDecimal(1, balance);			
			psmt.setString(2, accountId);
			count=psmt.executeUpdate();
			count+=bankWaterDAO.insertBankWater(bankWater, connection); //添加流水
			connection.commit();  //提交事务
			return count;
		} catch (Exception e) {	
			try {
				connection.rollback();//事物回滚
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
			e.printStackTrace();
		}finally{
			DBUtils.release(connection, psmt, rs);
		}
		return count;
	}


	/**
	 * 一个事务：
	 * 1.转账
	 * 2.添加流水记录
	 * 
	 */
	@Override
	public int transferCheckInOut(String mainAccountId, String outAccountId,
			BigDecimal checkMoney){		
		Connection connection=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		BankWater mainBankWater=null;
		BankWater outBankWater=null;
		int count=0;  //更新行数
		
		try {			
			Account outAccount=queryAccountById(outAccountId);
			BigDecimal oldOutBalance= outAccount.getBalance();
			Account mainAccount=queryAccountById(mainAccountId);
			BigDecimal oldMainBalance=mainAccount.getBalance();
			connection=DBUtils.getConnection();
			connection.setAutoCommit(false);
			String sql="UPDATE account SET balance=? where account_id=?";
			psmt=connection.prepareStatement(sql);
			//更新主账户  减钱
			oldMainBalance=oldMainBalance.subtract(checkMoney);
			psmt.setBigDecimal(1,oldMainBalance);
			psmt.setString(2, mainAccountId);
			count= psmt.executeUpdate();	
			//记录主账户流水
			mainBankWater=new BankWater(mainAccountId,checkMoney,CheckTypeEnum.TRANSFER_CHECK_OUT,oldMainBalance);
										
			//更新被转入账户 金额 加钱
			oldOutBalance=oldOutBalance.add(checkMoney);
			psmt.setBigDecimal(1, oldOutBalance);
			psmt.setString(2, outAccountId);
			count+=psmt.executeUpdate();
			//记录被转入账户流水
			outBankWater=new BankWater(outAccountId,checkMoney,CheckTypeEnum.TRANSFER_CHECK_IN,oldOutBalance);				
						
			//记录流水			
			count+=bankWaterDAO.insertBankWater(mainBankWater,connection);
			count+=bankWaterDAO.insertBankWater(outBankWater,connection);			
			connection.commit();			//提交事物
			
		} catch (Exception e) {
			try {
				connection.rollback();   //事务回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			DBUtils.release(connection, psmt, rs);
		}
				
		return count;
	}
	
}
