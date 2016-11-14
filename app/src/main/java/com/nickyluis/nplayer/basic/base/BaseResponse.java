package com.nickyluis.nplayer.basic.base;

/**
 * 公共响应参数
 *
 * @author Ht
 */
public interface BaseResponse {

    //失败
    int RESULT_FAILED = 0;
    //超时
    int RESULT_TIME_OUT = 1;

    ////////////////////////回调函数/////////////////////////////
    //失败，返回失败码
    void onFailed(int code);

    //成功
    void onSucceed(BaseModel result);
}
