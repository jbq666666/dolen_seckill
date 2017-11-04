package org.seckill.service.impl;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessSeckilledDao;
import org.seckill.dao.cache.RedisDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessSeckilled;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;


/* Created with IntelliJ IDEA.
* User: Dolen
* Date: 2017/10/22
* Time: 20:03
*/
@Service
public class SeckillServiceImpl implements SeckillService {
    //日志采用lf4j
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //注入Service 依赖
    @Autowired
    private SeckillDao seckillDao;

    //注入redisDao操作
    @Autowired
    private RedisDao redisDao;


    @Autowired
    private SuccessSeckilledDao successSeckilledDao;
    //加入混淆概念,用于生成md5
    private final String slat = "sadfasdff^%sadfasd";

    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 5);
    }

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        //优化点：缓存优化，一致性维护在超时的基础上
        //1、访问redis
        Seckill seckill = redisDao.getSeckill(seckillId);
        if (seckill == null) {
            //2、访问数据库
            seckill = seckillDao.queryById(seckillId);
            //秒杀对象为null,不能秒杀
            if (seckill == null) {
                return new Exposer(false, seckillId);
            } else {
                //3、放入redis
                redisDao.putSeckill(seckill);
            }

        }
        //2、秒杀已经结束
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        //系统时间
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(),
                    startTime.getTime(), endTime.getTime());

        }
        //3.开始秒杀
        //转换md5,
        String md5 = getMd5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    private String getMd5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());//利用工具类生成md5
        return md5;
    }

    /**
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillException
     * @throws RepeatKillException
     * @throws SeckillCloseException 使用注解控制事物方法有点：
     *                               1：开发团队达成一致预定，明确标注事物放方法编程风格
     *                               2：保证事物方法执行时间尽可能短，不要穿插其他网络操作RPC/HTTP请求或者剥离出来
     *                               3：不是所有方法都需要事物，如只有一条修改操作，只读操作不需要事物控制
     */
    @Transactional
    public SeckillExcution executeSeckill(long seckillId, long userPhone, String md5) throws RepeatKillException, SeckillCloseException, SeckillException {
        //1、用户信息不匹配，直接抛出异常
        if (md5 == null || !md5.equals(getMd5(seckillId))) {
            throw new SeckillException("seckill is rewrite");
        }
        //执行秒杀逻辑：减库存 + 记录购买行为
        Date nowTime = new Date();
        try {
            //记录购买行为
            int insertCount = successSeckilledDao.insertSeckilled(seckillId, userPhone);
            //唯一验证：seckillId,userPhone
            if (insertCount <= 0) {
                //重复秒杀
                throw new RepeatKillException("seckill repeated");
            } else {
                //减库存，热点商品竞争
                int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
                if (updateCount <= 0) {
                    //没有更新到记录，秒杀结束
                    throw new SeckillCloseException("seckill is close");
                } else {
                    //秒杀成功
                    SuccessSeckilled successSeckilled = successSeckilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    logger.info("*********************" + successSeckilled);
                    return new SeckillExcution(seckillId, SeckillStatEnum.SUCCESS, successSeckilled);
                }
            }
        } catch (SeckillCloseException e1) {
            throw e1;//关闭时候异常
        } catch (RepeatKillException e2) {
            throw e2;//重复秒杀异常
        } catch (Exception e) {
            e.getStackTrace();
            //所有编译异常转换成运行异常
            throw new SeckillException("seckill inner error" + e.getStackTrace());
        }
    }
}
