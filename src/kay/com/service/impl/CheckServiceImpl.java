package kay.com.service.impl;

import kay.com.dao.AccountDAO;
import kay.com.dao.impl.AccountDAOImpl;
import kay.com.dto.CheckExecution;
import kay.com.entity.Account;
import kay.com.enums.AccountStateEnum;
import kay.com.enums.CheckResultEnum;
import kay.com.enums.CheckTypeEnum;
import kay.com.exception.CheckInOutException;
import kay.com.exception.IllegalAccountStateException;
import kay.com.exception.LessMoneyException;
import kay.com.service.CheckService;

import java.math.BigDecimal;

public class CheckServiceImpl implements CheckService{

	private AccountDAO accountDAO;
	
	public CheckServiceImpl(){
		accountDAO=new AccountDAOImpl();
	}
	
	/**
	 * 存取款业务
	 * 1.判断账户状态   抛出异常
	 * 2.判断取款与余额是否不足   抛出异常
	 */
	@Override
	public CheckExecution checkInOut(String accountId, CheckTypeEnum checkType, BigDecimal checkMoney)
			throws CheckInOutException,LessMoneyException,IllegalAccountStateException{

		try {
			//账户状态检查
			Account account= checkStateOfAccount(accountId);
			//如果是取款，则判断余额是否不足
			if(checkType ==CheckTypeEnum.CHECK_OUT){
				if(account.getBalance().compareTo(checkMoney)<0){
					//抛出余额不足异常
					throw new LessMoneyException("balance is not enough");
				}
			}

            int count = accountDAO.checkInOut(accountId, checkMoney, checkType);
            //正常存取款更新2条记录（账户+流水）
            if (count < 2) {
                //抛出异常，可能是数据库或者系统问题
                throw new CheckInOutException("check error");
            }else {
                //操作成功
                return new CheckExecution(account, CheckResultEnum.SUCCESS);
            }

		}catch (IllegalAccountStateException e1){
			throw e1;
		}catch (LessMoneyException e2){
			throw e2;
		}catch (CheckInOutException e){
            System.out.println("log----："+e.getMessage());
            throw new CheckInOutException("inner error"+e.getMessage());
		}

	}

	/**
	 * 转账业务
	 */
	@Override
	public CheckExecution transferCheckInOut(String mainAccountId, String outAccountId,BigDecimal checkMoney)
			throws CheckInOutException,LessMoneyException,IllegalAccountStateException{
	    try {
	        if(mainAccountId.equals(outAccountId)){
	            //不能自己给自己转账
	            throw new IllegalAccountStateException("Illegal operator");
            }
	        //检查账户状态，并返回出outAccount，上层需要用到
            Account mainAccount=checkStateOfAccount(mainAccountId);
            Account outAccount= checkStateOfAccount(outAccountId);
            if (mainAccount.getBalance().compareTo(checkMoney) < 0) {
                //余额不足
                throw new LessMoneyException("balance is not enough");
            }else {
                int count=accountDAO.transferCheckInOut(mainAccountId, outAccountId, checkMoney);
                if(count < 4){    //更新2个账户+2个流水记录
                    //转账异常
                    throw new CheckInOutException("transfer error");
                }else {
                    //转账成功
                    return new CheckExecution(outAccount,CheckResultEnum.SUCCESS);
                }
            }
        }catch (LessMoneyException e1){
	        throw e1;
        }catch (IllegalAccountStateException e2){
            throw e2;
        }catch (CheckInOutException e){
            System.out.println("log----:"+e.getMessage());
            throw new CheckInOutException("inner error:"+e.getMessage());
        }
	}

	/**
	 * 检查账户状态
	 * @param accountId 账户ID
	 * @return
	 * @throws IllegalAccountStateException
	 */
	private Account checkStateOfAccount(String accountId) throws IllegalAccountStateException{
		Account account	= accountDAO.queryAccountById(accountId);
		if(account ==null){
		    //账户不存在
			throw new IllegalAccountStateException("account not exists");
		}
		String accountState=account.getAccountState();
		
		if(accountState.equals(AccountStateEnum.CANCELLED.getIndex())
				|| accountState.equals(AccountStateEnum.LOST.getIndex())){
		    //账户状态非法
			throw new IllegalAccountStateException("account is already lost or cancelled！");
		}
		return account;
	}
	

}
