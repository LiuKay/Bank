package kay.com.enums;

/**
 * 
 * @author kay
 * 账户状态
 */
public enum AccountStateEnum {	
	INIT("初始状态","00"),
    NORMAL("账户正常","01"),
    LOST("已挂失","02"),
    CANCELLED("已注销","03");
	
	private String name;

    private String index;

    private AccountStateEnum(String name,String index){
        this.name=name;
        this.index=index;
    }

    public static String getName(String index) {
        for (AccountStateEnum t : AccountStateEnum.values()) {
            if (t.getIndex().equals(index)) {
                return t.name;
            }
        }
        return null;
    }


     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
