package com.jeesd.common.enums;

/**
 * 通用类型
 */
public enum CommonEnum {

	STATE_0(0,"可用状态"),
	STATE_1(1,"不可用状态");

	private int value;
	private String describe;
	private CommonEnum(int value, String describe) {
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
