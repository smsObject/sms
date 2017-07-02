package com.hbh.sms.biz.service.setting.impl;

import com.hbh.sms.biz.service.setting.PriceService;
import com.hbh.sms.dal.dao.PriceItemMapper;
import com.hbh.sms.dal.dao.PriceMapper;
import com.hbh.sms.dal.dao.PriceTemplateMapper;
import com.hbh.sms.model.entity.Price;
import com.hbh.sms.model.entity.PriceItem;
import com.hbh.sms.model.entity.PriceTemplate;
import com.sms.common.Result;
import com.sms.common.ResultUtil;
import com.sms.common.StateCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hbh on 2017/6/17.
 */
@Service("priceService")
public class PriceServiceImpl implements PriceService {

    @Resource
    private PriceTemplateMapper priceTemplateMapper;
    @Resource
    private PriceMapper priceMapper;
    @Resource
    PriceItemMapper priceItemMapper;

    @Override
    public Result<List<PriceTemplate>> listPriceTemplates() {
        List<PriceTemplate> lists = priceTemplateMapper.query();
        return ResultUtil.newSuccessResult(lists);
    }

    @Override
    public Result<Long> addAndUpdatePriceTemplate(PriceTemplate priceTemplate) {
        if (priceTemplate == null){
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        if (priceTemplate.getId() == null || priceTemplate.getId().longValue() == 0){
            if (priceTemplate.getParentId() == null){
                priceTemplate.setParentId(0L);
            }
            priceTemplateMapper.insert(priceTemplate);
            return ResultUtil.newSuccessResult(priceTemplate.getId());
        } else {
            int i = priceTemplateMapper.update(priceTemplate);
            return ResultUtil.newSuccessResult(priceTemplate.getId());
        }
    }

    @Override
    public Result<Boolean> deletePriceTemplate(Long id) {
        int i = priceTemplateMapper.delete(id);
        return ResultUtil.newSuccessResult(i>0);
    }

    @Transactional
    @Override
    public Result<Long> addPrice(Price price) {
        priceMapper.insert(price);
        List<PriceItem> priceItems = price.getPriceItems();
        for (PriceItem priceItem : priceItems){
            priceItem.setPriceId(price.getId());
        }
        priceItemMapper.batchInsert(priceItems);
        return ResultUtil.newSuccessResult(price.getId());
    }

    @Transactional
    @Override
    public Result<Boolean> deletePrice(Long id) {
        int i = priceMapper.delete(id);
        priceItemMapper.deleteByPriceId(id);
        return ResultUtil.newSuccessResult(i>0);
    }

    @Transactional
    @Override
    public Result<Boolean> updatePrice(Price price) {
        int i = priceMapper.update(price);
        List<PriceItem> priceItems = price.getPriceItems();
        priceItemMapper.deleteByPriceId(price.getId());

        for (PriceItem priceItem: priceItems){
            priceItem.setId(price.getId());
        }

        priceItemMapper.batchInsert(priceItems);
        return ResultUtil.newSuccessResult(i>0);
    }

    @Override
    public Result<List<Price>> listPrices() {
        List<Price> prices = priceMapper.query();
        return ResultUtil.newSuccessResult(prices);
    }

}