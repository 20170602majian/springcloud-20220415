package com.majian.fegin.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Person implements Serializable {
    private Integer pId;
    private String pName;
    private Integer pAge;

}
