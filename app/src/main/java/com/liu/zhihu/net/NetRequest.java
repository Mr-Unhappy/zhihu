package com.liu.zhihu.net;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Request.Priority;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.liu.zhihu.entity.BaseEntity;

import java.util.Collections;
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

//    public <T> void get(String url, Class<? extends BaseEntity> clazz, Map<String, String> params, RequestListener listener) {
//        get(url, clazz, params, listener, new DefaultRetryPolicy(10 * 1000, 0, 1));
//    }

    public <T> void get(String url, Class<? extends BaseEntity> clazz, Map<String, String> params, RequestListener listener) {

        get(url, clazz, params, listener, new DefaultRetryPolicy(10 * 1000, 0, 1), Priority.NORMAL);
    }

    private <T> void get(String url, final Class<? extends BaseEntity> clazz, final Map<String, String> params, final RequestListener callback, RetryPolicy retryPolicy, final Priority priority) {
        StringGetRequest getRequest = new StringGetRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    BaseEntity entity = new Gson().fromJson(response, clazz);
                    if (entity == null) {
                        callback.onFailed(new NullPointerException(), "data :" + response);
                        return;
                    }

                    callback.onSuccess(entity);
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
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if (params != null) {
                    return checkNull(params);
                } else {
                    return Collections.emptyMap();
                }
            }

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

    public interface RequestListener {
        public void onSuccess(BaseEntity entity);

        public void onFailed(Exception exception, String msg);
    }
}
