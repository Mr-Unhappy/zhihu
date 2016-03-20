package com.liu.zhihu.net;

import android.content.Context;

import com.liu.zhihu.entity.BaseEntity;

import java.util.Map;

/**
 * Created by Ming on 2016/3/13.
 */
public class RequestService {

	private static final RequestService requestService = new RequestService();

	private RequestService() {
	};

	public static RequestService getInstance() {
		return requestService;
	}

	public void register(Context context, String phone, String pwd, Class<? extends BaseEntity> clazz, NetRequest.RequestListener listener) {
		Map<String, String> paramsMap = NetConfig.getRegisterParams(phone, pwd);
		new NetRequest(context).get(NetConfig.REGISTE, clazz, paramsMap, listener);
	}

	public void startImage(Context context, Class<? extends BaseEntity> clazz, NetRequest.RequestListener listener) {
		Map<String, String> paramsMap = NetConfig.getDefaultParams(false);
		new NetRequest(context).get(NetConfig.START_IMAGE, clazz, paramsMap, listener);
	}

	public void getNews(Context context, Class<? extends BaseEntity> clazz, NetRequest.RequestListener listener) {
		Map<String, String> paramsMap = NetConfig.getDefaultParams(false);
		new NetRequest(context).get(NetConfig.GET_NEWS, clazz, paramsMap, listener);
	}

}
