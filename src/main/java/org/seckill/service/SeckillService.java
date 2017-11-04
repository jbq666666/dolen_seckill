package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

import java.util.List;

/* Created with IntelliJ IDEA.
* User: Dolen
* Date: 2017/10/22
* Time: 11:12
* 站在使用者角度设计接口：三个方面：方法定义粒度，参数，返回类型(return 类型/异常)
*/public interface SeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀接口
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启时候输出秒杀接口地址,
     * 否则输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId) ;

    /**
     * 执行秒杀
     * @param seckillId
     * @param userPhone
     * @param md5
        */
        SeckillExcution executeSeckill(long seckillId, long userPhone, String md5)
        throws RepeatKillException,SeckillCloseException,SeckillException;
        }
