package kay.com.entity;

import java.math.BigDecimal;
import java.util.Date;

import kay.com.enums.CheckTypeEnum;
/**
 * 
 * @author kay
 *	银行流水
 */
public class BankWater {
	
	private String checkId;  //流水号	
	
	private String accountId; //账户ID

	private Date checkTime; //操作日期
		
	private BigDecimal checkCount;	//发生额
	
	private String checkType;	//操作类型
	
	private BigDecimal balance; //余额
	
	private String checkPlace;   //交易地点

	
		
	public BankWater() {
		super();
	}

	public BankWater(String accountId, BigDecimal checkCount,
			CheckTypeEnum checkType, BigDecimal balance) {
		this.accountId = accountId;
		this.checkCount = checkCount;
		this.checkType = checkType.getIndex();
		this.balance = balance;
	}

	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public BigDecimal getCheckCount() {
		return checkCount;
	}

	public void setCheckCount(BigDecimal chechCount) {
		this.checkCount = chechCount;
	}

	public String getCheckType() {
		return this.checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType =checkType;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getCheckPlace() {
		return checkPlace;
	}

	public void setCheckPlace(String checkPlace) {
		this.checkPlace = checkPlace;
	}

	@Override
	public String toString() {
		return "BankWater [checkId=" + checkId + ", accountId=" + accountId
				+ ", checkTime=" + checkTime + ", chechCount=" + checkCount
				+ ", checkType=" + checkType + ", balance=" + balance
				+ ", checkPlace=" + checkPlace + "]";
	}
	
	
	
}
