import * as types from './mutation-types'
export const state = {

    userPermission:{
        'companyInfo:page':false,
        'companyInfo:add':false,
        'companyInfo:update':false,
        'device:meterPage':false,
        'device:addMeter':false,
        'device:deleteMeter':false,
        'device:updateMeter':false,
        'device:getMeterData':false,
        'device:readMeterData':false,
        'device:setValveStatus':false,
        'device:setTiming':false,
        'device:setActivateTime':false,
        'device:queryNoCorrelationMeters':false,
        'device:setManagerCenter':false,
        'setting:listPriceTemplates':false,
        'setting:addAndUpdatePriceTemplate':false,
        'setting:deletePriceTemplate':false,
        'setting:listPrices':false,
        'setting:addPrice':false,
        'setting:queryPriceNames':false,
        'setting:getPriceById':false,
        'setting:listUseType':false,
        'setting:addUseType':false,
        'setting:updateUseType':false,
        'setting:deleteUseType':false,
        'userInfo:page':false,
        'userInfo:add':false,
        'userInfo:update':false,
        'userInfo:delete':false,
        'userInfo:getUserWaterValue':false,
        'userInfo:buyWaterPrice':false,
        'userInfo:calculateWaterPrice':false,
        'system:system':false,
        'system:pageUser':false,
        'system:addUser':false,
        'system:updateUser':false,
        'system:deleteUser':false,
        'system:pageRole':false,
        'system:addRole':false,
        'system:updateRole':false,
        'system:deleteRole':false,
        'system:queryMenu':false,
}

}
// mutations
export const mutations = {

    // 获取经纬度&当前城市
    [types.LOGIN_SUCCESS]  (state,data) {
        state.userPermission = data
    }
}

