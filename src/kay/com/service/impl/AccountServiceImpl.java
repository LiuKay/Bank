package kay.com.service.impl;

import kay.com.dao.AccountDAO;
import kay.com.dao.BankWaterDAO;
import kay.com.dao.impl.AccountDAOImpl;
import kay.com.dao.impl.BankWaterDAOImpl;
import kay.com.dto.Pager;
import kay.com.entity.Account;
import kay.com.entity.BankWater;
import kay.com.exception.PasswordCheckException;
import kay.com.service.AccountService;
import kay.com.utils.SecurityUtil;

import java.util.List;

public class AccountServiceImpl implements AccountService{

	private AccountDAO accountDAO;
	
	private BankWaterDAO bankWaterDAO;	
		
	public AccountServiceImpl() {
		accountDAO=new AccountDAOImpl();
		bankWaterDAO=new BankWaterDAOImpl();
	}

	@Override
	public Account getAccountById(String accountId){
		return accountDAO.queryAccountById(accountId);
	}

	@Override
	public Account checkPassword(String inputId, String inputPassword) throws PasswordCheckException{
		Account account=accountDAO.queryAccountById(inputId);
		//没有找到用户直接返回false
		if(account==null){
			return null;
		}else {
			//验证密码
			try {
				if(SecurityUtil.checkPassword(inputPassword, account.getPassword())){
					return account;
				}else {
					return null;
				}
			} catch (Exception e) {
				throw new PasswordCheckException("check password error", e);
			}
		}
		
	}

	@Override
	public List<BankWater> queryBankWaters(String accountId) {
		return bankWaterDAO.queryBankWaters(accountId);
	}

	@Override
	public Pager<BankWater> queryBankWaters(String accountId, int pageNum, int pageSize) {
		return bankWaterDAO.querySubBankWaters(accountId,pageNum,pageSize);
	}


}
