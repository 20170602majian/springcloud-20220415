package com.majian.redis.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
//get和set方法
@Accessors(chain = true)
//
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {
    Integer id;
    String name;

}
