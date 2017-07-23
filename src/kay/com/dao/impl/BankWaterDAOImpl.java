package kay.com.dao.impl;

import kay.com.dao.BankWaterDAO;
import kay.com.dto.Pager;
import kay.com.entity.BankWater;
import kay.com.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankWaterDAOImpl implements BankWaterDAO {

	/**
	 * 查询某个账户的流水
	 */
	@Override
	public List<BankWater> queryBankWaters(String accountId) {
		Connection connection=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		List<BankWater> bankWaters=new ArrayList<BankWater>();
		String sql="SELECT * from bankwater WHERE account_id=?";	
		try {	
			connection=DBUtils.getConnection();
			psmt=connection.prepareStatement(sql);
			psmt.setString(1, accountId);
			rs= psmt.executeQuery();			
			while(rs.next()){
				BankWater bankWater=new BankWater();
				bankWater.setCheckId(rs.getInt(1)+"");
				bankWater.setAccountId(rs.getString(2));
				bankWater.setCheckCount(rs.getBigDecimal(3));
				bankWater.setCheckTime(rs.getDate(4));
				bankWater.setCheckType(rs.getString(5));
				bankWater.setBalance(rs.getBigDecimal(6));
				bankWater.setCheckPlace(rs.getString(7));
				bankWaters.add(bankWater);
			}
			return bankWaters;
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			DBUtils.release(connection, psmt, rs);
		}		
		return bankWaters;
	}

    /**
     * 分页查询
     * @param accountId
     * @param pageNum  第几页
     * @param pageSize  每页大小
     * @return
     */
	@Override
	public Pager<BankWater> querySubBankWaters(String accountId, int pageNum, int pageSize) {
		Connection connection=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		Pager<BankWater> pager=null;
        List<BankWater> bankWaters=new ArrayList<>();

		StringBuilder sql=new StringBuilder("SELECT * from bankwater WHERE account_id=?");
        // 起始索引
        int fromIndex	= pageSize * (pageNum -1);
        sql.append(" limit "+fromIndex+", "+pageSize);  //使用mysql的limit关键字分页

        //查总记录数
        StringBuilder countSql = new StringBuilder("SELECT count(check_id) from bankwater WHERE account_id=? ");
		try {
			connection=DBUtils.getConnection();
			psmt=connection.prepareStatement(countSql.toString());
            psmt.setString(1, accountId);
            rs=psmt.executeQuery();
            int totalRecord=-1;
            while (rs.next()){
                //总记录数
                totalRecord=rs.getInt(1);
            }

			psmt=connection.prepareStatement(sql.toString());
			psmt.setString(1, accountId);
			rs= psmt.executeQuery();
			while(rs.next()){
				BankWater bankWater=new BankWater();
				bankWater.setCheckId(rs.getInt(1)+"");
				bankWater.setAccountId(rs.getString(2));
				bankWater.setCheckCount(rs.getBigDecimal(3));
				bankWater.setCheckTime(rs.getDate(4));
				bankWater.setCheckType(rs.getString(5));
				bankWater.setBalance(rs.getBigDecimal(6));
				bankWater.setCheckPlace(rs.getString(7));
				bankWaters.add(bankWater);
			}

            //获取总页数
            int totalPage = totalRecord / pageSize;
            if(totalRecord % pageSize !=0){
                totalPage++;
            }
            //创建Pager对象
            pager=new Pager<BankWater>(pageSize,pageNum,totalRecord,totalPage,bankWaters);

		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			DBUtils.release(connection, psmt, rs);
		}
		return pager;
	}


	/**
	 * 添加流水
	 * @throws SQLException 
	 */
	@Override
	public int insertBankWater(BankWater bankWater,Connection connection) throws SQLException {
		PreparedStatement psmt=null;
		ResultSet rs=null;
		int count=0;
		String sql="INSERT INTO bankwater(account_id,check_count,check_type,balance)VALUES(?,?,?,?)";	
			
		psmt=connection.prepareStatement(sql);
		psmt.setString(1, bankWater.getAccountId());
		psmt.setBigDecimal(2, bankWater.getCheckCount());
		psmt.setString(3,bankWater.getCheckType());
		psmt.setBigDecimal(4, bankWater.getBalance());
		count=psmt.executeUpdate();			
		return count;
		
	}

}
