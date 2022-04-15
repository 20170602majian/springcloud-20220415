package com.majian.redis;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.majian.redis.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@SpringBootTest
class RedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    String key = "user_majian";
    //string
    @Test
    void contextLoads() {
        User user = new User();
        user.setId(999);
        user.setName("majian");

        String value = user.toString();
        BoundValueOperations userBound = redisTemplate.boundValueOps(key);
        userBound.set(value);
        System.out.println(userBound.get());
    }
/*
* list
* */
    @Test
    void redisTest1(){
        String key = "list";
        BoundListOperations boundListOperations = redisTemplate.boundListOps(key);
        User user1= new User();
        user1.setId(9991);
        user1.setName("majian");
        User user2 = new User();
        user2.setId(9992);
        user2.setName("majian");
        User user4 = new User();
        user4.setId(9993);
        user4.setName("majian");
        User user = new User();
        user.setId(9994);
        user.setName("majian");
        User user5 = new User();
        user5.setId(9995);
        user5.setName("majian");

        boundListOperations.leftPush(user);
        boundListOperations.leftPush(user1);
        boundListOperations.leftPush(user2);
        boundListOperations.leftPush(user4);
        boundListOperations.leftPush(user5);

        Object value = boundListOperations.rightPop();
        while (null != value){
            System.out.println(value);
            value = boundListOperations.rightPop();
        }
    }

    //set
    @Test
    void setValue(){
        String key = "set:user:3";
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("2");
        list.add("1");
        list.forEach(po -> {
            redisTemplate.boundSetOps(key).add(po);
        });
        Object setObject = redisTemplate.boundSetOps(key).pop();
        while (setObject != null) {
            System.out.println(setObject);
            setObject = redisTemplate.boundSetOps(key).pop();
        }
    }

    @Test
    void zset(){
        String key = "redis:zset:1";
        System.out.println(redisTemplate.delete(key));
        for (int i = 0; i <30 ; i++) {
            Integer random =Integer.valueOf((int) (Math.random() * 1000)) ;
            redisTemplate.boundZSetOps(key).add(random,random);
        }
        Long size = redisTemplate.boundZSetOps(key).size();
        redisTemplate.boundZSetOps(key).popMax(size).forEach(
                po -> {
                    System.out.println("MAX -> "+po);
                }
        );
        //从小到大-》size
        redisTemplate.boundZSetOps(key).popMin(size).forEach(
                po -> {
                    System.out.println("MIN -> "+po);
                }
        );
        //从小到大-> (0,size)
        redisTemplate.opsForZSet().range(key,0L,size).forEach(
                po -> {
                    System.out.println("从小到大 -》"+po);
                }
        );
        redisTemplate.opsForZSet().reverseRange(key,0L,size).forEach(
                po -> {
                    System.out.println("从大到小 -》"+po);
                }
        );
    }

    @Test
    void hash(){
        String key = "redis:hash:1";
        redisTemplate.boundHashOps(key).put("1","majian");
        System.out.println(redisTemplate.boundHashOps(key).get("1"));

    }

    //key失效，判断key是否存在
    @Test
    <Char>
    void  keyTime() throws InterruptedException {
        //第一种，直接在存键值时给过期时间
        ValueOperations valueOps = redisTemplate.opsForValue();
        valueOps.set("redis:vlaue:time:1","askdakjsdn",1, TimeUnit.SECONDS);
        System.out.println(valueOps.get("redis:vlaue:time:1"));
        //判断此key是否存在
        System.out.println("是否存在此key ： "+redisTemplate.hasKey("redis:vlaue:time:1"));
        Thread.sleep(1100);
        System.out.println(valueOps.get("redis:vlaue:time:1"));
        //11s后key过期会自动删掉
        System.out.println("10s后是否存在此key ： "+redisTemplate.hasKey("redis:vlaue:time:1"));


        //第二种，存完之后再给一个过期时间
        String key = "redis:time:2";
        User user = new User();
        user.setName("kshgdajhs");
        user.setId(222);
        redisTemplate.opsForValue().set(key,user);
        //expire设置过期时间
        redisTemplate.expire(key,3,TimeUnit.SECONDS);
        System.out.println(redisTemplate.hasKey(key));
        Thread.sleep(2000L);
        System.out.println(redisTemplate.hasKey(key));
        redisTemplate.expire(key,5,TimeUnit.SECONDS);
        Thread.sleep(2000);
        System.out.println(redisTemplate.hasKey(key));
        redisTemplate.opsForValue().set(key,null,15L,TimeUnit.SECONDS);
        System.out.println("修改为空后键是否存在"+redisTemplate.hasKey(key));
        System.out.println("null = "+redisTemplate.opsForValue().get(key));
        //设置过期时间时候 ，要是之前的key没过期。可以重新给他一个过期时间，要是过期了就不可以了 ，因为key被删掉了
        char c ='k';
        short k = 1;
        String s = String.valueOf(k);

    }

    @Test
    void StringOf() throws InterruptedException {
        String key = "user:20220301:";
        User user = new User();
        user.setId(1);
        user.setName("氨基酸的比较");
        String value = JSONObject.toJSONString(user);
        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(key);
        boundValueOperations.set(value);
       // System.out.println("user所对应的json对象为 ： "+boundValueOperations.get());
        Object o = boundValueOperations.get();
        User userReturn = JSONObject.parseObject(o.toString(), User.class);
        System.out.println("user所对应的json对象为"+userReturn);
        //JSONObject.parseObject()
        System.out.println("给key一个过期时间 ： ->"+redisTemplate.expire(key, 2l, TimeUnit.SECONDS));
        for (int i = 0; i < 4; i++) {
            System.out.println(boundValueOperations.get()+"---睡眠1秒---");
            System.out.println("此key剩余时间 -》 " + redisTemplate.getExpire(key));
            if (i == 5){
                //System.out.println("删除操作是否成功 -》 "+redisTemplate.delete(key));
            }
            Thread.sleep(1000L);
        }
        System.out.println("过期时间过后 -》 "+boundValueOperations.get());
        redisTemplate.expire(key,-2l,TimeUnit.SECONDS);
        System.out.println(boundValueOperations.get());
        key = "idid";
        Long value1 = 1L;
        BoundValueOperations boundValueOperations1 = redisTemplate.boundValueOps(key);
        boundValueOperations1.set(value1);
        Long increment = boundValueOperations1.increment(-1000L);
        System.out.println(increment);
        redisTemplate.opsForValue().set("aaa",10);
        System.out.println(redisTemplate.boundValueOps("aaa").get());

    }



}
