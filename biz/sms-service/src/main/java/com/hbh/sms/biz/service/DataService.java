package com.hbh.sms.biz.service;

import org.smslib.InboundMessage;

/**
 *
 * @author hbh
 * @create
 **/
public interface DataService {
    void parseGsmData(InboundMessage msg);
}