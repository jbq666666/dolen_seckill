package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessSeckilled;

public interface SuccessSeckilledDao {
    /**
     * 插入购买明细，可以过滤重复
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSeckilled(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);

    /**
     * 根据id查询SuccessSkilled并携带秒杀产品对象实体
     * @param seckillId
     * @return
     */
    SuccessSeckilled queryByIdWithSeckill(@Param("seckillId") long seckillId,@Param("userPhone") long userPhnone);

}
