package com.majian.redis.service.impl;

import ch.qos.logback.core.util.TimeUtil;
import com.majian.redis.common.CommonResult;
import com.majian.redis.service.CURDService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class CURDServiceImpl implements CURDService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private Redisson redisson;

    @Override
    public Boolean putData(){
        ValueOperations valueOperations = redisTemplate.opsForValue();

        try {
            valueOperations.set("mnnn",1);
            System.out.println(valueOperations.get("mnnn"));

            redisTemplate.boundValueOps("majian1").set("jsdvakhsv",50, TimeUnit.MINUTES);
            System.out.println(redisTemplate.boundValueOps("majian1").get());

            BoundHashOperations userRedis = redisTemplate.boundHashOps("user");
            userRedis.put("1",1);
            System.out.println(userRedis.get(1));

            System.out.println(redisTemplate.getExpire("majian1"));


        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
        return  true;
    }

    public String getStock(){
        RLock redissonLock = redisson.getLock("majianStock");
        try {

       /* BoundValueOperations majianStockOps = redisTemplate.boundValueOps("majianStock");

        Boolean majianHaveStock = majianStockOps.setIfAbsent("majianHave",10,TimeUnit.SECONDS);



         if (!majianHaveStock){
             log.info("????????????,????????????????????????");
             return "????????????,????????????????????????";      //????????????
         }else {*/
            redissonLock.lock();
            BoundValueOperations stockOps = redisTemplate.boundValueOps("stock");
              Integer number =Integer.valueOf(String .valueOf(stockOps.get())) ;
              if (number > 0){
                  stockOps.set(number-1);
                  int k = 0;
                  k = number - 1;
                //  redisTemplate.delete("majianStock");  //??????
                  log.info("????????????,????????????????????? -> "+k);
                  return"????????????,????????????????????? -> "+k;
              }else {
                  log.info("????????????");
                 // redisTemplate.delete("majianStock");  //??????
                  return "????????????";
              }

        // }
        }catch (Exception e){
            log.error(e.getMessage());
            return "??????????????????";
        }finally {
           // redisTemplate.delete("majianStock");  //??????
            redissonLock.unlock();
        }
    }

    public Integer getNumberByStock(){
        Integer number =Integer.valueOf(String.valueOf( redisTemplate.boundValueOps("stock").get()));
        return number;
    }

}
