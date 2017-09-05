package com.hbh.sms.biz.service.setting.impl;

import com.hbh.sms.biz.service.setting.PriceService;
import com.hbh.sms.dal.dao.PriceItemMapper;
import com.hbh.sms.dal.dao.PriceMapper;
import com.hbh.sms.dal.dao.PriceTemplateMapper;
import com.hbh.sms.model.entity.Price;
import com.hbh.sms.model.entity.PriceInterval;
import com.hbh.sms.model.entity.PriceItem;
import com.hbh.sms.model.entity.PriceTemplate;
import com.sms.common.Result;
import com.sms.common.ResultUtil;
import com.sms.common.StateCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

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
        PriceTemplate template = new PriceTemplate();
        template.setParentId(0L);
        List<PriceTemplate> lists = priceTemplateMapper.query(template);
        return ResultUtil.newSuccessResult(lists);
    }

    @Override
    public Result<Long> addAndUpdatePriceTemplate(PriceTemplate priceTemplate) {
        if (priceTemplate == null) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        if (priceTemplate.getId() == null || priceTemplate.getId().longValue() == 0) {
            if (priceTemplate.getParentId() == null) {
                priceTemplate.setParentId(0L);
            }

            //同一层级名称不能相同
            PriceTemplate search = new PriceTemplate();
            search.setName(priceTemplate.getName());
            search.setParentId(priceTemplate.getParentId());
            List<PriceTemplate> list = priceTemplateMapper.query(search);
            if (list.size() > 0){
                return ResultUtil.newFailedResult(StateCode.ERROR,"名称已存在");
            }

            priceTemplateMapper.insert(priceTemplate);
            return ResultUtil.newSuccessResult(priceTemplate.getId());
        } else {
            PriceTemplate search = new PriceTemplate();
            search.setName(priceTemplate.getName());
            search.setParentId(priceTemplate.getParentId());
            List<PriceTemplate> list = priceTemplateMapper.query(search);
            if (list.size() > 0){
                if (list.get(0).getId().longValue() != priceTemplate.getId().longValue()){
                    return ResultUtil.newFailedResult(StateCode.ERROR,"名称已存在,不能修改");
                }
            }
            int i = priceTemplateMapper.update(priceTemplate);
            return ResultUtil.newSuccessResult(priceTemplate.getId());
        }
    }

    @Override
    public Result<Boolean> deletePriceTemplate(Long id) {
        int i = priceTemplateMapper.delete(id);
        return ResultUtil.newSuccessResult(i > 0);
    }

    @Transactional
    @Override
    public Result<Long> addPrice(Price price) {
        if (price.getId() != null && price.getId() != 0){
            Result result = updatePrice(price);
            return result;
        }
        price.setCreatePerson("system");
        priceMapper.insert(price);
        insert(price);
        return ResultUtil.newSuccessResult(price.getId());
    }

    private void insert(Price price) {
        List<PriceItem> priceItemList = new ArrayList<>();
        List<PriceItem> priceItems = price.getPriceItems();
        for (PriceItem priceItem : priceItems) {
            Map<Long,Float> prices = priceItem.getPrices();
            Iterator<Long> templateIds = prices.keySet().iterator();

            PriceInterval priceInterval = new PriceInterval();
            if (priceItem.getEndValue() == null){
                priceInterval.setEndValue(0f);
            }else {
                priceInterval.setEndValue(priceItem.getEndValue());
            }
            priceInterval.setStartValue(priceItem.getStartValue());
            priceInterval.setPriceId(price.getId());
            priceMapper.insertInterval(priceInterval);
            Long intervalId = priceInterval.getId();

            while (templateIds.hasNext()){
                PriceItem priceItem1 = new PriceItem();
                priceItem1.setCreatePerson("system");
                priceItem1.setPriceId(price.getId());
                Long templateId = templateIds.next();
                Float priceValue = prices.get(templateId);
                priceItem1.setTemplateId(templateId);
                priceItem1.setIntervalId(intervalId);
                priceItem1.setPrice(priceValue);
                priceItemList.add(priceItem1);
            }
        }
        priceItemMapper.batchInsert(priceItemList);
    }

    @Transactional
    @Override
    public Result<Boolean> deletePrice(Long id) {
        int i = priceMapper.delete(id);
        priceItemMapper.deleteByPriceId(id);
        return ResultUtil.newSuccessResult(i > 0);
    }

    @Transactional
    @Override
    public Result<Boolean> updatePrice(Price price) {
        int i = priceMapper.update(price);
        priceMapper.deleteInterValByPriceId(price.getId());
        priceItemMapper.deleteByPriceId(price.getId());
        insert(price);
        return ResultUtil.newSuccessResult(i > 0);
    }

    @Override
    public Result<List<Price>> listPrices(Price record) {
        List<Price> prices = priceMapper.query(record);
        return ResultUtil.newSuccessResult(prices);
    }

    @Override
    public Result<Map<Long, String>> queryPriceNames() {
        Price price1 = new Price();
        List<Price> prices = priceMapper.query(price1);
        Map<Long, String> map = new HashMap<>();
        for (Price price : prices) {
            map.put(price.getId(), price.getName());
        }
        return ResultUtil.newSuccessResult(map);
    }

    @Override
    public Result<Price> getPriceById(Long id) {
        Price price = new Price();
        price.setId(id);
        List<Price> prices = priceMapper.query(price);
        List<PriceItem> priceItems = new ArrayList<>();

        if (prices.size() > 0){
            price = prices.get(0);
            price.setPriceItems(priceItems);

            //获取区间值
            List<PriceInterval> list = priceMapper.queryInterval(id);
            for (PriceInterval priceInterval : list){
                PriceItem priceItem = new PriceItem();
                priceItem.setIntervalId(priceInterval.getId());
                //获取水费值
                List<PriceItem> items = priceItemMapper.query(priceItem);
                Map<Long,Float> priceMap = new HashMap<>();

                PriceItem priceItem2 = new PriceItem();
                priceItem2.setStartValue(priceInterval.getStartValue());
                priceItem2.setEndValue(priceInterval.getEndValue());
                priceItem2.setPrices(priceMap);
                priceItems.add(priceItem2);
                for(PriceItem priceItem1 : items){
                    priceMap.put(priceItem1.getTemplateId(),priceItem1.getPrice());
                }
            }
        }else {
            price = null;
        }
        return ResultUtil.newSuccessResult(price);
    }
}