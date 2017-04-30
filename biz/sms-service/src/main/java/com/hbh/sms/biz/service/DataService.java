package com.hbh.sms.biz.service;

import com.hbh.sms.model.entity.Meter;
import com.sms.common.Result;
import org.smslib.InboundMessage;

/**
 *
 * @author hbh
 * @create 2017-04-30 23:19
 **/
public interface DataService {
    void parseGsmData(InboundMessage msg);
}