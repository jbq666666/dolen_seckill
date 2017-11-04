package org.seckill.enums;

/*
*使用枚举表示常量数据字段
* User: Dolen
* Date: 2017/10/22
* Time: 20:55
*/public enum  SeckillStatEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATE_REWRITE(-3,"数据篡改");//目前属于系统异常里面

    private int state;
    private String stateInfo;

    SeckillStatEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }


    //以下 getter
    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStatEnum stateOf(int index){
        //valuse()：所有的枚举
        for (SeckillStatEnum state : values()){
            if(state.getState() == index){
                return state;
            }
        }
        return null;
    }

}
