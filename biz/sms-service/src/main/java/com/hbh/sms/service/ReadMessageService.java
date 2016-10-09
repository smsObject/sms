package com.hbh.sms.service;

import com.hbh.sms.model.entity.Concentrator;
import org.smslib.InboundMessage;

import java.util.List;

/**
 * Created by hbh on 2016/7/7.
 */
public interface ReadMessageService {
        public List<InboundMessage> readMessage(Concentrator concentrator , InboundMessage.MessageClasses messageClasses) throws Exception;
}
