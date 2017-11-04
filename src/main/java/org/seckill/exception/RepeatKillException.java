package org.seckill.exception;

/**
*重复秒杀异常(运行期异常)
* User: Dolen
* Date: 2017/10/22
* Time: 19:34
*/public class RepeatKillException extends SeckillException {

    //以下两个构造方法
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
