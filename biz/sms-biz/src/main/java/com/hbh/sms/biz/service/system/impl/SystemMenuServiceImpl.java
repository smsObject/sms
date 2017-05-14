package com.hbh.sms.biz.service.system.impl;

import com.hbh.sms.biz.service.system.SystemMenuService;
import com.hbh.sms.dal.dao.SystemMenuMapper;
import com.hbh.sms.model.entity.SystemMenu;
import com.sms.common.Result;
import com.sms.common.ResultUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author hbh
 **/
public class SystemMenuServiceImpl implements SystemMenuService {
    @Resource
    private SystemMenuMapper systemMenuMapper;

    @Override
    public Result<List<SystemMenu>> getSystemMenuList() {
        return ResultUtil.newSuccessResult(systemMenuMapper.getSystemMenuList());
    }

    @Override
    public Result<List<SystemMenu>> getSystemMenuByRoleIds(List<Long> ids) {
        return ResultUtil.newSuccessResult(systemMenuMapper.getSystemMenuByRoleIds(ids));
    }
}
