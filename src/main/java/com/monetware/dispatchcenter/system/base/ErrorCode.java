package com.monetware.dispatchcenter.system.base;


public enum ErrorCode {

	//Token验证
	USER_NOT_LOGIN(10001, "用户未登陆"),
	TOKEN_WITHOUT(10002, "登陆超时,请重新登陆"),
	USER_NOT_EXIT(10003,"用户不存在"),
	USER_IS_DISABLE(10004,"用户已被禁用"),



	EMAIL_SEND_FAIL(20001, "邮件发送失败"),

	//任务相关
	TASK_CANCEL_WRONG(30002,"任务取消失败"),
	TASK_FINISH_WRONG(30003,"结束任务失败"),
	NODE_EXIT(30004,"节点已存在"),
	TASK_TYPE_EXIT(30005,"项目类型下此任务类型已经存在"),


	TASK_ADD_WRONG(30001,"任务新增失败");


	private String msg;
    private int code;

    ErrorCode(int code,String msg)
    {
    	this.code=code;
        this.msg=msg;
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}

