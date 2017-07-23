package kay.com.service;

import kay.com.dto.Pager;
import kay.com.entity.Account;
import kay.com.entity.BankWater;
import kay.com.exception.PasswordCheckException;

import java.sql.SQLException;
import java.util.List;


/**
 * 
 * @author kay 
 */
public interface AccountService {

	/**
	 * 获取账户信息
	 * @param accountId
	 * @return
	 * @throws SQLException 
	 */
	Account getAccountById(String accountId) throws SQLException;

	/**
	 * 通过输入的id和密码进行验证
	 * @param inputId
	 * @param inputPassword
	 * @return
	 */
	Account checkPassword(String inputId, String inputPassword) throws PasswordCheckException;
	
		
	
	/**
	 * 查询用户流水单
	 * @param accountId 用户ID
	 * @return
	 */
	List<BankWater> queryBankWaters(String accountId);

	/**
	 * 分页查询流水
	 * @param accountId
	 * @param pageNum  第几页
	 * @param pageSize 每页大小
	 * @return
	 */
	Pager<BankWater> queryBankWaters(String accountId, int pageNum, int pageSize);
}
