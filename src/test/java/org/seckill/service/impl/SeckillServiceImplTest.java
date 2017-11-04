package org.seckill.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/* Created with IntelliJ IDEA.
* User: Dolen
* Date: 2017/10/23
* Time: 0:09
*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml",
                        "classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {

    private final Logger logger  = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}",list);
    }

    @Test
    public void getById() throws Exception {
        long id = 1L;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        long id = 1L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}",exposer);
//        Exposer{exposed=true, md5='7f552bf00707b88fec0c2e805d297d39', seckillId=1, now=0, start=0, end=0}
    }

    @Test
    public void executeSeckill() throws Exception {
        //id和手机号是唯一标识，运行两次重复秒杀// seckill repeated
        long id = 1L;
        long phone =18600505375L;
        String md5 = "7f552bf00707b88fec0c2e805d297d39";
        SeckillExcution excution = seckillService.executeSeckill(id,phone,md5);
        logger.info("result={}",excution);
    }

}