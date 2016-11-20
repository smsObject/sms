package com.hbh.sms.biz.service.UserInfo.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hbh.sms.biz.service.UserInfo.UserInfoService;
import com.hbh.sms.dal.dao.UserInfoMapper;
import com.hbh.sms.model.entity.UserInfo;
import com.sms.common.PagedData;
import com.sms.common.Result;
import com.sms.common.ResultUtil;
import com.sms.common.StateCode;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2016/11/20.
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public Result<Long> add(UserInfo userInfo) {
        if (userInfo == null) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
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
        int i = userInfoMapper.updateByPrimaryKey(userInfo);
        return ResultUtil.newSuccessResult(i > 0);
    }

    @Override
    public Result<PagedData<UserInfo>> page(UserInfo userInfo) {
        if (userInfo == null) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        Result<PagedData<UserInfo>> result = null;
        Page page = PageHelper.startPage(userInfo.getPageNo(),userInfo.getPageSize());
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
    public Result<UserInfo> getCompanyInfoById(Long id) {
        if (id == null || id.longValue() == 0) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        return ResultUtil.newSuccessResult(userInfo);
    }

}