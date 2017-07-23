package kay.com.enums;

/**
 * Created by kay on 2017/7/22.
 * 执行结果类型
 */
public enum CheckResultEnum {
    SUCCESS(1,"操作成功"),
    LESS_MONEY(0,"您的余额不足，操作失败"),
    ACCOUNT_ILLEGAL(-1,"账户不存在或账户状态非法"),
    INNER_ERROR(-2,"内部错误");

    private int state;

    private String stateInfo;

    CheckResultEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public static CheckResultEnum stateOf(int index){
        for(CheckResultEnum state : values()){
            if(state.getState()==index){
                return state;
            }
        }
        return null;
    }
}
