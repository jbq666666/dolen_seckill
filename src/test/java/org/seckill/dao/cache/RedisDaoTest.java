package org.seckill.dao.cache;

import junit.runner.BaseTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/* Created with IntelliJ IDEA.
* User: Dolen
* Date: 2017/11/1
* Time: 21:40
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {

    private long id = 1;

    @Autowired
    private  RedisDao redisDao;

    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void testSeckill() throws Exception {
        //get and put
        Seckill seckill = redisDao.getSeckill(id);
        if(seckill == null){
            seckill = seckillDao.queryById(id);
            if(seckill != null){
                //存放redis
                String result = redisDao.putSeckill(seckill);
                System.out.println("result :"+result);
                //取出redis
                Seckill seckill1 = redisDao.getSeckill(id);
                System.out.println("seckill :"+seckill);
            }
        }
    }
}