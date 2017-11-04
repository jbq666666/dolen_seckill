package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessSeckilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-dao.xml")
public class SuccessSeckilledDaoTest {

    @Resource
    SuccessSeckilledDao successSeckilledDao;

    @Test
    public void insertSeckilled() throws Exception {
        //配置 insert ignore into 主键冲突不报错
        long seckillId=2L;
        long user_phnoe=18600505375L;
       int insertCnt = successSeckilledDao.insertSeckilled(seckillId,user_phnoe);
        System.out.println("***************成功插入**************** "+insertCnt );
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        long seckillId=1L;
        long user_phone=18600505375L;
        SuccessSeckilled successSeckilled = successSeckilledDao.queryByIdWithSeckill(seckillId,user_phone);
        System.out.println(successSeckilled);
    }

}