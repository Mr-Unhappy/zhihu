package com.liu.zhihu.entity;

/**
 * Created by Ming on 2016/3/13.
 */
public class BaseEntity {
	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	public int getError_type() {
		return error_type;
	}

	public void setError_type(int error_type) {
		this.error_type = error_type;
	}

	public boolean isOk(){
		if(error_type == 0){
			return true;
		}
		return false;
	}

	private int error_type;
	private String error_msg;


}
