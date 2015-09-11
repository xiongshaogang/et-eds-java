package com.edaisong.core.enums;

/**
 *
 * @author  pengyi 
 * @date 2015年9月11日 上午10:07:42
 * @version 1.0
 * @parameter
 * @since
 */
public enum CardModifyC {
	Success(0,"成功"),
	NotExists(1,"该骑士未绑定过金融账号"),
	AccountNotSame(2,"两次录入的金融账号不一致"),
	NameError(3,"帐户名不能为空"),
	NoClienter(4,"骑士不存在"),
	NoAccount(5,"账户不存在"),
	NoModify(6,"信息没有任何更改"),
	SystemError(-1,"系统错误"),
	ParamError(-2,"未传参或者参数错误,参数错误时返回所有的错误信息文本");
	
	private int value = 0;
	private String desc;
	private CardModifyC(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static CardModifyC getEnum(int index) {
		for (CardModifyC c : CardModifyC.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}