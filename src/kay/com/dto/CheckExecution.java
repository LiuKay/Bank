package kay.com.dto;

import kay.com.entity.Account;
import kay.com.enums.CheckResultEnum;

/**
 * Created by kay on 2017/7/22.
 * 封装操作的结果对象，便于在service层与web层传输
 */
public class CheckExecution {

    //账户号
    private String accountId;

    //被转账户姓名
    private String accountName;

    //操作状态
    private int state;

    //操作状态描述
    private String stateinfo;

    public CheckExecution(Account account, CheckResultEnum resultEnum) {
        this.accountId = account.getAccountId();
        this.accountName = account.getAccountName();
        this.state = resultEnum.getState();
        this.stateinfo = resultEnum.getStateInfo();
    }

    @Override
    public String toString() {
        return "CheckExecution{" +
                "accountId='" + accountId + '\'' +
                ", accountName='" + accountName + '\'' +
                ", state=" + state +
                ", stateinfo='" + stateinfo + '\'' +
                '}';
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateinfo() {
        return stateinfo;
    }

    public void setStateinfo(String stateinfo) {
        this.stateinfo = stateinfo;
    }
}
