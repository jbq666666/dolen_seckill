package org.seckill.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-dao.xml")
public class SeckillDaoTest {

    @Resource
    private SeckillDao seckillDao;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void reduceNumber() throws Exception {
        Date killTime = new Date();
        long seckillId=1;
        int updateCnt = seckillDao.reduceNumber(seckillId,killTime);
        System.out.println("updateCnt=" +updateCnt);
    }

    @Test
    public void queryById() throws Exception {
        long num=1;
        Seckill seckill=seckillDao.queryById(num);

        System.out.println("*********"+seckill.getName());
        //*********Seckill{seckillId=1, name='1000元秒杀iPhone6', number=100, startTime=2017-10-01, endTime=2017-10-02, createTime=2017-10-18}
        System.out.println("*********"+seckill);
    }

    @Test
    public void queryAll() throws Exception {
        int offset=1;
        int limit=100;
        ArrayList<Seckill> seckills = (ArrayList) seckillDao.queryAll(offset,limit);
        for (int i = 0; i < seckills.size(); i++) {
            System.out.println(seckills.get(i));
        }
    }

}