package com.majian.redis.service;

import org.springframework.http.ResponseEntity;

public interface CURDService {
    Boolean putData();
    public String getStock();
    public Integer getNumberByStock();
}
