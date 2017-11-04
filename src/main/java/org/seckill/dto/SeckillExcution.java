package org.seckill.dto;

import org.seckill.entity.SuccessSeckilled;
import org.seckill.enums.SeckillStatEnum;

/*
   封装秒杀执行后结果
* User: Dolen
* Date: 2017/10/22
* Time: 19:27
*/public class SeckillExcution {

    private long seckillId;
    //秒杀执行结果状态
    private int state;
    //状态表示
    private String stateInfo;
    //秒杀成功对象
    private SuccessSeckilled successSeckilled;

    //构造方法由于初始化
    public SeckillExcution(long seckillId, SeckillStatEnum statEnum, SuccessSeckilled successSeckilled) {
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
        this.successSeckilled = successSeckilled;
    }

    public SeckillExcution(long seckillId,SeckillStatEnum statEnum) {
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
    }

    //getter setter 方法
    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessSeckilled getSuccessSeckilled() {
        return successSeckilled;
    }

    public void setSuccessSeckilled(SuccessSeckilled successSeckilled) {
        this.successSeckilled = successSeckilled;
    }

    @Override
    public String toString() {
        return "SeckillExcution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successSeckilled=" + successSeckilled +
                '}';
    }
}
