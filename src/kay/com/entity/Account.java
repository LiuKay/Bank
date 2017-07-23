package kay.com.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Account {
	
	private String accountId;  //账户卡号

    private String accountName; //账户姓名

    private String currency;  //币种

    private BigDecimal balance; //余额

    private Date openTime; //开户时间

    private String openPlace; //开户地点

    private String accountState; //账户状态

    private String password;   //账户密码
    
    
    
	public Account() {
		
	}

	
	
	public Account(String accountId, String accountName, String currency,
			BigDecimal balance, Date openTime, String openPlace,
			String accountState, String password) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.currency = currency;
		this.balance = balance;
		this.openTime = openTime;
		this.openPlace = openPlace;
		this.accountState = accountState;
		this.password = password;
	}



	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public String getOpenPlace() {
		return openPlace;
	}

	public void setOpenPlace(String openPlace) {
		this.openPlace = openPlace;
	}

	public String getAccountState() {
		return accountState;
	}

	public void setAccountState(String accountState) {
		this.accountState = accountState;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountName="
				+ accountName + ", currency=" + currency + ", balance="
				+ balance + ", openTime=" + openTime + ", openPlace="
				+ openPlace + ", accountState=" + accountState + ", password="
				+ password + "]";
	}
    
	
    
}
