package org.seckill.web;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/* Created with IntelliJ IDEA.
* User: Dolen
* Date: 2017/10/26
* Time: 20:38
*/
@Controller//@Service @Component
@RequestMapping("/seckill")//url:/模块/资源/{id}/细分 /seckill/list
public class SeckillController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    /**
     *  返回列表页
     * @param model
     * @return
     */
    @RequestMapping(value="list",method = RequestMethod.GET)
    public String list(Model model){
        List<Seckill> list =seckillService.getSeckillList();
        model.addAttribute("list",list);
        return "list";//WEB-INF/jsp/"list".jps
    }

    /**
     * 返回详情页面
     * @param seckillId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){
       if(seckillId == null){
           return "redirect:/seckill/list";
       }
       Seckill seckill = seckillService.getById(seckillId);
       if(seckill == null){
           return "forward:/seckill/list";
       }
       model.addAttribute("seckill",seckill);
       return  "detail";
    }

    /**
     * 返回秒杀暴露接口
     * @param seckillId
     * @return
     */
    //ajax json
    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable  Long seckillId){
        SeckillResult<Exposer> result ;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true,exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            result = new SeckillResult<Exposer>(false,e.getMessage());
        }
        return  result;
    }

    /**
     * 执行秒杀
     * @param seckillId
     * @param md5
     * @param phone
     * @return
     */
    @RequestMapping(value = "/{seckillId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExcution>  excute(@PathVariable("seckillId") Long seckillId,
                                                  @PathVariable("md5") String md5,
                                                  @CookieValue(value = "killPhone",required = false) Long phone){
        //springmvc valid
        if(phone == null){
            return new SeckillResult<SeckillExcution>(false,"未注册");
        }
        SeckillResult<SeckillExcution> result;
        try {
            SeckillExcution excution =seckillService.executeSeckill(seckillId,phone,md5);
            return new SeckillResult<SeckillExcution>(true,excution);
        }catch (RepeatKillException e){
            SeckillExcution excution = new SeckillExcution(seckillId, SeckillStatEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExcution>(true,excution);
        }catch (SeckillCloseException e){
            SeckillExcution excution = new SeckillExcution(seckillId, SeckillStatEnum.END);
            return new SeckillResult<SeckillExcution>(true,excution);
        } catch (SeckillException e) {
            logger.error(e.getMessage(),e);
            SeckillExcution excution = new SeckillExcution(seckillId, SeckillStatEnum.INNER_ERROR);
            return new SeckillResult<SeckillExcution>(true,excution);
        }
    }

    /**
     * 获取服务器时间
     * @return
     */
    @RequestMapping(value = "/time/now" ,method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time(){
        Date now = new Date();
        return new SeckillResult<Long>(true,now.getTime());
    }
}
