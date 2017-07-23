package kay.com.dao;

import java.math.BigDecimal;
import java.sql.SQLException;

import kay.com.entity.Account;
import kay.com.enums.CheckTypeEnum;

public interface AccountDAO {
	
	/**
	 * 根据卡号查用户
	 * @param accountId
	 * @return
	 */
	Account queryAccountById(String accountId);

	
	
	/**
	 * 存取款
	 * @param balance
	 * @return
	 * @throws SQLException 
	 */
	int checkInOut(String accountId, BigDecimal checkMoney, CheckTypeEnum checkType);
	
	/**
	 * 转账
	 * @param mainAccountId 主账户
	 * @param outAccountId 对方账户
	 * @param checkMoney 变动额
	 * @return
	 */
	int transferCheckInOut(String mainAccountId, String outAccountId, BigDecimal checkMoney);
}
