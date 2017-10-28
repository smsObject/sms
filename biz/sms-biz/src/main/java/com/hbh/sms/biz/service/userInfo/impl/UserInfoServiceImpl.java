package com.hbh.sms.biz.service.userInfo.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hbh.sms.biz.service.setting.PriceService;
import com.hbh.sms.biz.service.userInfo.UserInfoService;
import com.hbh.sms.dal.dao.UserInfoMapper;
import com.hbh.sms.model.entity.Price;
import com.hbh.sms.model.entity.PriceItem;
import com.hbh.sms.model.entity.UserInfo;
import com.hbh.sms.model.entity.UserPrice;
import com.sms.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by user on 2016/11/20.
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;
    @Autowired
    private PriceService priceService;

    @Override
    public Result<Long> add(UserInfo userInfo) {
        if (userInfo == null) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        UserInfo search = new UserInfo();
        search.setName(userInfo.getName());
        search.setPhone(userInfo.getPhone());
        List<UserInfo> list = userInfoMapper.query(search);
        if (list.size() > 0) {
            return ResultUtil.newFailedResult(StateCode.ERROR, "用户已存在");
        }
        userInfoMapper.insert(userInfo);
        return ResultUtil.newSuccessResult(userInfo.getId());
    }

    @Override
    public Result<Boolean> deleteById(Long id) {
        if (id == null || id.longValue() == 0) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        int i = userInfoMapper.deleteByPrimaryKey(id);
        return ResultUtil.newSuccessResult(i > 0);
    }

    @Override
    public Result<Boolean> update(UserInfo userInfo) {
        if (userInfo == null) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        UserInfo search = new UserInfo();
        search.setName(userInfo.getName());
        search.setPhone(userInfo.getPhone());
        List<UserInfo> list = userInfoMapper.query(search);
        if (list.size() > 0) {
            if (list.get(0).getId().longValue() != userInfo.getId().longValue()) {
                return ResultUtil.newFailedResult(StateCode.ERROR, "用户名和手机号已存在");
            }
        }

        int i = userInfoMapper.updateByPrimaryKey(userInfo);
        return ResultUtil.newSuccessResult(i > 0);
    }

    @Override
    public Result<PagedData<UserInfo>> page(UserInfo userInfo) {
        if (userInfo == null) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        Result<PagedData<UserInfo>> result = null;
        Page page = PageHelper.startPage(userInfo.getPageNo(), userInfo.getPageSize());
        List<UserInfo> list = userInfoMapper.query(userInfo);
        PagedData<UserInfo> pagedData = new PagedData<>();
        pagedData.setPageNo(userInfo.getPageNo());
        pagedData.setPageSize(userInfo.getPageSize());
        pagedData.setTotalSize(page.getTotal());
        pagedData.setResultList(list);
        result = ResultUtil.newSuccessResult(pagedData);
        return result;
    }

    @Override
    public Result<UserInfo> getUserInfoById(Long id) {
        if (id == null) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        List<UserInfo> list = userInfoMapper.query(userInfo);

        if (!list.isEmpty()) {
            return ResultUtil.newSuccessResult(list.get(0));
        }
        return ResultUtil.newSuccessResult(null);
    }

    @Override
    public Result<UserPrice> getUserWaterValue(UserPrice userPrice) {
        if (userPrice == null) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        UserPrice userPrice1 = userInfoMapper.getUserWaterValue(userPrice);
        userPrice.setUserId(userPrice1.getUserId());
        userPrice.setMeterId(userPrice1.getMeterId());
        UserPrice userPrice2 = userInfoMapper.getLastBuyWaterValue(userPrice);
        if (userPrice2 != null) {
            userPrice1.setLastBuyWaterValue(userPrice2.getBuyWaterValue());
            userPrice1.setLastBuyWaterTime(userPrice2.getCreateTime());
        } else {
            userPrice1.setLastBuyWaterValue(null);
            userPrice1.setLastBuyWaterTime(null);
        }

        //预付费的用户 自己手动输入
        if (SmsConst.USER_INFO_USER_TYPE_PREPAY == userPrice1.getUserType()) {
            userPrice1.setBuyWaterValue(null);
            userPrice1.setPayableAmount(null);
        } else if (SmsConst.USER_INFO_USER_TYPE_NORMAL == userPrice1.getUserType()) {
            //普通用户
            if (userPrice1.getBuyWaterValue() != 0f) {
                Long buyType = userPrice1.getBuyType();
                //获取费用项 计算费用
                calculateWaterPriceProcess(buyType,userPrice1.getBuyWaterValue());
            } else {
                userPrice1.setBuyWaterValue(0f);
                userPrice1.setPayableAmount(BigDecimal.ZERO);
            }
        }
        return ResultUtil.newSuccessResult(userPrice1);
    }

    @Override
    public Result<Boolean> buyWaterValue(UserPrice userPrice) {
        if (userPrice == null) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        UserPrice userPrice1 = userInfoMapper.getLastBuyWaterValue(userPrice);
        if (userPrice1 != null) {
            userPrice.setLastBuyWaterValue(userPrice1.getBuyWaterValue());
            userPrice.setLastBuyWaterTime(new Date());
        } else {
            userPrice.setLastBuyWaterValue(null);
            userPrice.setLastBuyWaterTime(null);
        }
        int i = userInfoMapper.saveBuyWaterValue(userPrice);
        return ResultUtil.newSuccessResult(i > 0);
    }

    @Override
    public Result<BigDecimal> calculateWaterPrice(Long userId, Float waterValue) {
        UserInfo userInfoForUpdate = new UserInfo();
        userInfoForUpdate.setId(userId);
        UserInfo userInfo = userInfoMapper.get(userInfoForUpdate);
        if (userInfo == null || userInfo.getBuyType() == null) {
            return ResultUtil.newFailedResult(StateCode.ERROR, "用户信息不全");
        }
        return calculateWaterPriceProcess(userInfo.getBuyType(), waterValue);
    }

    private Result<BigDecimal> calculateWaterPriceProcess(Long buyType, Float waterValue) {
        Result<Price> priceResult = priceService.getPriceById(buyType);
        if (priceResult.isSuccess()) {
            Price price = priceResult.getData();
            List<PriceItem> priceItems = price.getPriceItems();
            //普通计价
            if (SmsConst.PRICE_TYPE_NORMAL == price.getType()) {
                PriceItem priceItem = priceItems.get(0);
                return ResultUtil.newSuccessResult(new BigDecimal(waterValue * priceItem.getPrice()));
            } else if (SmsConst.PRICE_TYPE_STEP == price.getType()) {
                //阶梯计价1. 获取所有阶梯费用
                Map<Float, PriceItem> endValueMap = new HashMap<>();
                for (PriceItem priceItem : priceItems) {
                    endValueMap.put(priceItem.getEndValue().floatValue(), priceItem);
                }

                //排序 2.阶梯费用
                List<Float> endValueList = new ArrayList<>();
                for (float endValue : endValueMap.keySet()) {
                    endValueList.add(endValue);
                }
                endValueList.sort(new Comparator<Float>() {
                    @Override
                    public int compare(Float o1, Float o2) {
                        if (o1 < o2) {
                            return -1;
                        } else if (o1 == o2) {
                            return 0;
                        } else {
                            return 1;
                        }
                    }
                });

                //3.获取到当前的水量所在阶梯位置
                int i = 1;
                //阶梯,阶梯值
                int currentSetp = 0;

                List<Map<Integer,Float>> setpList = new ArrayList<>();
                for (float endValue : endValueList){
                    Map<Integer,Float> setpMap = new HashMap<>();
                    setpMap.put(i,endValue);
                    setpList.add(setpMap);
                    if (waterValue < endValue){
                        currentSetp = i;
                        break;
                    }
                    i++;
                }

                if (currentSetp == 0){
                    return ResultUtil.newFailedResult(StateCode.ERROR,"未找到费用");
                }

                //4.开始结算费用
                int size = setpList.size();
                if (size >1 ){
                    BigDecimal waterPrice = BigDecimal.ZERO;

                    for (int j =0 ; j< size -1;j++){
                        Map<Integer,Float> map = setpList.get(j);
                        waterPrice = waterPrice.add(BigDecimal.valueOf(map.get(j+1)*endValueMap.get(map.get(j+1)).getPrice()));
                    }
                    //所属阶梯开始值
                    Map<Integer,Float> map = setpList.get(size - 1);
                    float endValue = map.get(size);
                    PriceItem endProceItem = endValueMap.get(endValue);
                    waterPrice = waterPrice.add(BigDecimal.valueOf(waterValue - endProceItem.getStartValue()*endProceItem.getPrice()));
                    return ResultUtil.newSuccessResult(waterPrice);
                }else {
                    Map<Integer,Float> map = setpList.get(0);
                    return ResultUtil.newSuccessResult(new BigDecimal(waterValue*endValueMap.get(map.get(1)).getPrice()));
                }
            }
        }
        return ResultUtil.newFailedResult(StateCode.ERROR,"处理费用异常");
    }

    public static void main(String[] args) {
        List<Float> endValueList = new ArrayList<>();
        endValueList.add(5f);
        endValueList.add(2f);
        endValueList.add(3f);
        endValueList.add(1f);
        endValueList.add(7f);
        endValueList.add(8f);
        endValueList.add(3f);
        endValueList.sort(new Comparator<Float>() {
            @Override
            public int compare(Float o1, Float o2) {
                if (o1 < o2) {
                    return -1;
                } else if (o1 == o2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        System.out.println(endValueList);
    }
}