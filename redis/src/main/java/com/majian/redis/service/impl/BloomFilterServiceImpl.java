package com.majian.redis.service.impl;


import com.majian.redis.service.BloomFilterService;
import com.netflix.discovery.converters.Auto;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloomFilterServiceImpl implements BloomFilterService {

    @Autowired
    private Redisson redisson;

      public Boolean containsInteger(Integer number){
          String keyBloom = "intBloomKey";
          RBloomFilter<Integer> bloomFilterInteger = redisson.getBloomFilter(keyBloom);
          //容量为10000，误差率为0.01的博隆过滤器
          bloomFilterInteger.tryInit(10000,0.01);
          //往布隆过滤器里面存放一些数据，一会做是否存在的判断
          /*for (int i = 0; i < 10000; i++) {
              bloomFilterInteger.add(i);
          }*/
          //初始化成功后开始判断
          boolean containsFlag = bloomFilterInteger.contains(number);
          return  containsFlag;

      }
}
