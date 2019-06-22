package com.jeesd.job.enums;

public enum JobOperateEnum {

    START(1, "启动"),
    PAUSE(2, "暂停"),
    DELETE(3, "删除");

    private int value;
    private String describe;

    private JobOperateEnum(int value, String describe) {
        this.value = value;
        this.describe = describe;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public String getDescribe() {
        return describe;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
