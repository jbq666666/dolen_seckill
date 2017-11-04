package org.seckill.dto;

/* Created with IntelliJ IDEA.
* User: Dolen
* Date: 2017/10/26
* Time: 21:13
*/
//所有的ajax请求返回类型，封装json结果
public class SeckillResult<T> {

    private boolean success;

    private T data;

    private String error;
    //构造方法：成功并返回数据
    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }
    //构造方法：失败返回错误结果
    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }
    //getter setter
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
