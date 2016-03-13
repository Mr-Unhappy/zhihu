package com.liu.zhihu.net;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;

/**
 * Created by Ming on 2016/3/13.
 */
public class StringPostRequest extends Request<String> {

	private Response.Listener<String> mListener;

	public StringPostRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
		super(method, url, errorListener);
		this.mListener = listener;
	}

	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		try {
			String result = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
			return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		}
	}

	@Override
	protected void deliverResponse(String response) {
		mListener.onResponse(response);
	}
}
