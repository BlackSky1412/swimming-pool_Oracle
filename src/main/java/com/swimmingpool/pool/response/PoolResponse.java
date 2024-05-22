package com.swimmingpool.pool.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PoolResponse {
    private String id;
    private String code;
    private String name;
    private BigDecimal numberOfStudent;
    private Boolean active;
    private Date createdDate;
    private Date modifiedDate;
}
