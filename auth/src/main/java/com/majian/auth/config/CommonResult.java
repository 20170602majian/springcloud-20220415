package com.majian.auth.config;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Accessors(chain = true)
@NoArgsConstructor
@Data

public class CommonResult implements Serializable {
    private static final long serialVersionUID = 6191745064790884707L;
    private String code;
    private String message;
    private Object data;


}
