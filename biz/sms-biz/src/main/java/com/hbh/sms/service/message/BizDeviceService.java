package com.hbh.sms.service.message;

import com.hbh.sms.model.entity.Concentrator;
import com.hbh.sms.model.entity.SendMessageData;
import com.sms.common.Result;

import java.util.List;

/**
 * Created by hbh on 2016/7/17.
 */
public interface BizDeviceService {

    public  boolean sendMessage(Concentrator concentrator , SendMessageData messageData);

    public Result<List<Concentrator>> scanner();

}
