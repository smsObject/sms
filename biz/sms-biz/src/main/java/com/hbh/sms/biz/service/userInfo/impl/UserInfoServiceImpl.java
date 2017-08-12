package com.hbh.sms.biz.service.userInfo.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hbh.sms.biz.service.userInfo.UserInfoService;
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
        UserInfo search = new UserInfo();
        search.setName(userInfo.getName());
        search.setPhone(userInfo.getPhone());
        List<UserInfo> list = userInfoMapper.query(search);
        if (list.size() > 0){
            return ResultUtil.newFailedResult(StateCode.ERROR,"用户已存在");
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
        if (list.size() > 0){
            if(list.get(0).getId().longValue() != userInfo.getId().longValue()){
                return ResultUtil.newFailedResult(StateCode.ERROR,"用户名和手机号已存在");
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
    public Result<UserInfo> getUserInfoById(Long id) {
        if (id == null){
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        List<UserInfo> list = userInfoMapper.query(userInfo);

        if (!list.isEmpty()){
            return ResultUtil.newSuccessResult(list.get(0));
        }
        return ResultUtil.newSuccessResult(null);
    }
}