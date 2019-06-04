package com.yezhihun.demo.vo.response;

import java.io.Serializable;

/**
 * 基础返回实体
 */
public class BaseJsonVo implements Serializable {
	private static final long serialVersionUID = 1467926070559792155L;

	public static final int SUCCESS_CODE = 200;
	public static final String SUCCESS_MSG = "OK";
	public static final int FAILED_CODE = 500;
	public static final String FAILED_MSG = "Internal Server Error";
	public static final int EXPIRED_CODE = 103;
	public static final String EXPIRED_MSG = "No login or login expiration";
	public static final int ERROR_CODE = 101;
	public static final String ERROR_MSG = "In system maintenance, please try again later.";

	private int code;
	private String msg;
	private Object data;

	/**
	 * 登录失效
	 * 
	 * @return
	 */
	public static BaseJsonVo expired() {
		return new BaseJsonVo(EXPIRED_CODE, EXPIRED_MSG);
	}

	public static BaseJsonVo success() {
		return new BaseJsonVo(SUCCESS_CODE, SUCCESS_MSG);
	}

	public static BaseJsonVo success(Object data) {
		if (data == null) {
			return new BaseJsonVo(SUCCESS_CODE, SUCCESS_MSG);
		}
		return new BaseJsonVo(SUCCESS_CODE, SUCCESS_MSG, data);
	}

	public static BaseJsonVo error() {
		return new BaseJsonVo(ERROR_CODE, ERROR_MSG);
	}

	public static BaseJsonVo error(String retMsg) {
		return new BaseJsonVo(ERROR_CODE, retMsg);
	}

	/**
	 * 自定义
	 */
	public static BaseJsonVo customError(int retCode, String retMsg) {
		return new BaseJsonVo(retCode, retMsg);
	}

	public BaseJsonVo() {
		super();
	}

	public BaseJsonVo(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public BaseJsonVo(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getdata() {
		return data;
	}

	public void setdata(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "BaseJsonVo [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

}
