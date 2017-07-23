package kay.com.enums;

public enum CheckTypeEnum {
	CHECK_IN("现存","01"),
	CHECK_OUT("现取","02"),
	TRANSFER_CHECK_IN("转入","03"),
	TRANSFER_CHECK_OUT("转出","04");

    private String name;

    private String index;

    private CheckTypeEnum(String name,String index){
        this.name=name;
        this.index=index;
    }

    public static String getName(String index) {
        for (CheckTypeEnum t : CheckTypeEnum.values()) {
            if (t.getIndex().equals(index)) {
                return t.name;
            }
        }
        return null;
    }

    public static CheckTypeEnum getCheckType(String name) {
		for (CheckTypeEnum t : CheckTypeEnum.values()) {
			if(t.getName().equals(name)){
				return t;
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
