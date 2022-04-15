package com.majian.rabbitmq.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Person implements Serializable {
    private Integer pId;
    private String pName;
    private Integer pAge;

}
