package com.liu.zhihu.net;

import com.liu.zhihu.entity.BaseEntity;

import java.util.Map;

/**
 * Created by Ming on 2016/3/13.
 */
public class RequestService {

	private static final RequestService requestService = new RequestService();

	private RequestService() {
	};

	public static RequestService getInstance(){
		return requestService;
	}

	private void register(String phone,String pwd, Class<? extends BaseEntity> clazz, NetRequest.RequestListener listener){
		Map<String,String> paramsMap = NetConfig.getRegisterParams(phone,pwd);

	}
}
