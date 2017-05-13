package com.hbh.sms.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * ${DESCRIPTION}
 * @author hbh
 * @create
 **/
@Data
public class SmsData implements Serializable {
    private static final long serialVersionUID = 1L;

    private MeterData meterData;

    private Meter meter;
}