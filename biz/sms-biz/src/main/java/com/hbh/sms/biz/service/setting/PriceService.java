package com.hbh.sms.biz.service.setting;

import com.hbh.sms.model.entity.Price;
import com.hbh.sms.model.entity.PriceTemplate;
import com.sms.common.Result;

import java.util.List;

/**
 * Created by hbh on 2017/6/17.
 */

public interface PriceService {

    Result<List<PriceTemplate>> listPriceTemplates();

    Result<Long> addAndUpdatePriceTemplate(PriceTemplate priceTemplate);

    Result<Boolean> deletePriceTemplate(Long id);

    Result<Long> addPrice(Price price);

    Result<Boolean> deletePrice(Long id);

    Result<Boolean> updatePrice(Price price);

    Result<List<Price>> listPrices();
}
