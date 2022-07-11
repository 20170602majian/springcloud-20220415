package com.majian.kafka.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SS_KAFKA")
@ToString
public class SsKafka implements Serializable {

    /*
    * 实时看版的key
    * */
    @TableId("KEY")
    private String key;

    /*
    * 实时看板存储的json
    * */
    @TableField("VALUE")
    private byte[] value;


}
