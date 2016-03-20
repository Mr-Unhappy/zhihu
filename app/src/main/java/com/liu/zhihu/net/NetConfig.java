package com.liu.zhihu.net;

import com.liu.zhihu.application.MyApplication;
import com.liu.zhihu.utils.DeviceHelper;
import com.liu.zhihu.utils.StringHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ming on 2016/3/13.
 */
public class NetConfig {

    public static final String FLAG = "=";
    public static final String BASE_URL = "http://news-at.zhihu.com/api/" + DeviceHelper.getVersionCode(MyApplication.getContext()) + "/";

    public static final String REGISTE = "registe";
    public static final String START_IMAGE = "start-image/1080*1776";
    public static final String GET_NEWS = "news/latest";

    public static String getRequestUrl(String url) {

        return BASE_URL + url;
    }

    public static class Params {
        public static final String token = "token";
        public static final String phone = "phone";
        public static final String pwd = "password";
        public static final String sign = "sign";
    }


    public static Map<String, String> getDefaultParams(boolean isNeedToken) {
        Map<String, String> defaultParams = new HashMap<>();
        if (isNeedToken) {
            defaultParams.put(Params.token, "");
        }
        return defaultParams;
    }

    public static Map<String, String> getRegisterParams(String phone, String pwd) {
        Map<String, String> paramsMap = getBaseParams(false, formatParams(Params.phone, phone), formatParams(Params.pwd, pwd));
        paramsMap.put(Params.phone, phone);
        paramsMap.put(Params.pwd, pwd);
        return paramsMap;
    }

    public static Map<String, String> getBaseParams(boolean isNeedToken, String... strs) {
        Map<String, String> paramsMap = new HashMap<>();
        String token = null;
        if (isNeedToken) {
            // TODO token赋值；
            paramsMap.put(Params.token, token);
        }
        String sign = getCheckToken(isNeedToken, formatParams(Params.token, token), strs);

        paramsMap.put(Params.sign, sign);
        return paramsMap;
    }


    /**
     * @param isNeedToken
     * @param token
     * @param strs
     * @return MD5 加密后的参数值
     * <p/>
     * 此过程为MD5加密过程是计算了 所有参数放在一起的MD5值
     */
    public static String getCheckToken(boolean isNeedToken, String token, String... strs) {
        String[] array = null;
        if (isNeedToken) {
            array = new String[strs.length + 1];
            array[strs.length] = token;
        } else {
            array = new String[strs.length];
        }
        int i = 0;
        for (String s : strs) {
            array[i] = s;
            i++;
        }
        Arrays.sort(array);
        String checkToken = "";
        for (String s : array) {
            checkToken = checkToken + s.split(s.substring(s.indexOf(FLAG) + 1));
        }

        // TODO 测试不加密情况如何

        checkToken = StringHelper.toMD5(checkToken);
        return checkToken;
    }

    public static String formatParams(String name, String value) {
        return name + FLAG + value;
    }
}
