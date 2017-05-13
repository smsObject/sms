package com.hbh.sms.biz.service;

import com.hbh.sms.model.entity.Meter;
import com.sms.common.Result;
import org.smslib.InboundMessage;

/**
 *
 * @author hbh
 * @create
 **/
public interface DataService {
    void parseGsmData(InboundMessage msg);
}