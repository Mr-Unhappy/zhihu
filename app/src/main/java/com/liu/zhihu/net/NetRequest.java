package com.liu.zhihu.net;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Priority;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.liu.zhihu.entity.BaseEntity;
import com.liu.zhihu.utils.DeviceHelper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * Created by Ming on 2016/3/14.
 */
public class NetRequest {

	private Context mContext;

	public NetRequest(Context context) {
		this.mContext = context;
	}

	public <T> void get(String url, Class<? extends BaseEntity> clazz, Map<String, String> params, RequestListener listener) {
		if (!DeviceHelper.checkNetwork(mContext)) {
			listener.onFailed(new Exception(), "无网络连接");
			return;
		}
		String requestUrl = NetConfig.getRequestUrl(url);

		// TODO: 2016/3/15 接着往下写

		if (params != null && !params.isEmpty()) {
			checkNull(params);
			requestUrl = requestUrl + encodeParams(params);
		}
			Log.e("url", requestUrl);

		get(requestUrl, clazz, listener, new DefaultRetryPolicy(10 * 1000, 0, 1), Priority.NORMAL);
	}

	private <T> void get(String url, final Class<? extends BaseEntity> clazz, final RequestListener callback, RetryPolicy retryPolicy, final Priority priority) {
		StringGetRequest getRequest = new StringGetRequest(url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.e("response", response);
				try {
					BaseEntity result = new Gson().fromJson(response, clazz);
					if (result == null) {
						callback.onFailed(new NullPointerException(), "data :" + response);
						return;
					}

					callback.onSuccess(result);
				} catch (Exception e) {
					callback.onFailed(e, e.getClass().toString());
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				callback.onFailed(error, error.getClass().toString());
			}
		}) {
//         此处不必复写getHeader方法，该方法可用于向请求中添加默认参数
//			@Override
//			public Map<String, String> getHeaders() throws AuthFailureError {
//				if (params != null) {
//					return checkNull(params);
//				} else {
//					return Collections.emptyMap();
//				}
//			}

			@Override
			public Priority getPriority() {
				if (priority == Priority.LOW) {
					return Priority.LOW;
				} else if (priority == Priority.NORMAL) {
					return Priority.NORMAL;
				} else if (priority == Priority.IMMEDIATE) {
					return Priority.IMMEDIATE;
				} else if (priority == Priority.HIGH) {
					return Priority.HIGH;
				}
				return Priority.NORMAL;
			}
		};

		if (retryPolicy != null) {
			getRequest.setRetryPolicy(retryPolicy);
		}

		Volley.newRequestQueue(mContext).add(getRequest);
	}

	private Map<String, String> checkNull(Map<String, String> params) {
		if (params != null && !params.isEmpty()) {
			LinkedList<String> list = new LinkedList<>();
			Set<String> set = params.keySet();
			for (Iterator<String> it = set.iterator(); it.hasNext(); ) {
				String value = params.get(it.next());
				if (TextUtils.isEmpty(value)) {
					list.add(value);
				}
			}
			for (String value : list) {
				params.remove(value);
			}
		}
		return params;
	}


	private String encodeParams(Map<String, String> params) {
		StringBuffer buffer = new StringBuffer("?");
		try {
			for (String key : params.keySet()) {
				buffer.append(URLEncoder.encode(key, "UTF-8"));
				buffer.append(NetConfig.FLAG);
				buffer.append(URLEncoder.encode(params.get(key), "UTF-8"));
				buffer.append("&");
			}
			return buffer.toString();
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Encoding not supported:" + "UTF-8", e);
		}

	}

	public interface RequestListener {
		void onSuccess(BaseEntity result);

		void onFailed(Exception exception, String msg);
	}
}
