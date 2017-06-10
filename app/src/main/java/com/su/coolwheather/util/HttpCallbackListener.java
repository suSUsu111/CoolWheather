package com.su.coolwheather.util;

/**
 * Created by su on 2017/6/10.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
