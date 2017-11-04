package org.seckill.exception;

/* *
*秒杀关闭异常：运行时候异常
* User: Dolen
* Date: 2017/10/22
* Time: 19:37
*/
public class SeckillCloseException extends SeckillException  {
    //以下两个构造方法
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
