package kay.com.service;

import kay.com.dto.CheckExecution;
import kay.com.enums.CheckTypeEnum;
import kay.com.exception.CheckInOutException;
import kay.com.exception.IllegalAccountStateException;
import kay.com.exception.LessMoneyException;

import java.math.BigDecimal;

/**
 * 交易服务
 * @author kay
 *
 */
public interface CheckService {

	/**
	 * 存取款业务
	 * @param accountId 账户号
	 * @param checkType 操作类型
	 * @param checkMoney 变动金额
	 */
	CheckExecution checkInOut(String accountId, CheckTypeEnum checkType, BigDecimal checkMoney)
			throws CheckInOutException,LessMoneyException,IllegalAccountStateException;
	
	/**
	 * 转账业务
	 * @param mainAccountId  主账户
	 * @param outAccountId   对方账户
	 * @param checkMoney     转账金额
	 */
	CheckExecution transferCheckInOut(String mainAccountId, String outAccountId, BigDecimal checkMoney)
			throws CheckInOutException,LessMoneyException,IllegalAccountStateException;
	
	
}
