package org.seckill.exception;

/*  秒杀相关业务异常
* User: Dolen
* Date: 2017/10/22
* Time: 19:40
*/public class SeckillException extends  RuntimeException{

    //以下两个构造方法
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
