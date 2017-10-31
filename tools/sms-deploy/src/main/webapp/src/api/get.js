import Vue from 'vue'
export const  postApi = (url,params)=>{
	return Vue.http.post(url,params).then(res => {

		let data = res.data

		if(!data.success) {
			console.log(data)
			return Promise.reject(data)
		}
		return Promise.resolve(data)
	}).catch(res => {
		return Promise.reject(res)
	})
}
export const  getApi = (url,params)=>{
	return Vue.http.get(url,params).then(res => {
		let data = res.data
		if(!data.success) {
			return Promise.reject(data)
		}
		return Promise.resolve(data)
	}).catch(res => {
		return Promise.reject(res)
	})
}

export const companyInfoList = ({params}) => {
	let url = "sms/companyInfo/page"
	return postApi(url,params)
}
export const companyInfoUpdate = ({params}) => {
	let url = "sms/companyInfo/update"
	return postApi(url,params)
}
export const companyInfoAdd = ({params}) => {
	let url = "sms/companyInfo/add"
	return postApi(url,params)
}
export const companyInfoDelete = ({params}) => {
	let url = "sms/companyInfo/delete"
	return postApi(url,params)
}


export const userInfoList = ({params}) => {
	let url = "sms/userInfo/page"
	return postApi(url,params)
}
export const userInfoUpdate = ({params}) => {
	let url = "sms/userInfo/update"
	return postApi(url,params)
}
export const userInfoAdd = ({params}) => {
	let url = "sms/userInfo/add"
	return postApi(url,params)
}
export const userInfoDelete = ({params}) => {
	let url = "sms/userInfo/delete"
	return postApi(url,params)
}

// export const userInfoList = ({params}) => {
// 	let url = "sms/userInfo/page"
// 	return postApi(url,params)
// }
// export const userInfoUpdate = ({params}) => {
// 	let url = "sms/userInfo/update"
// 	return postApi(url,params)
// }
// export const userInfoAdd = ({params}) => {
// 	let url = "sms/userInfo/add"
// 	return postApi(url,params)
// }
// export const userInfoDelete = ({params}) => {
// 	let url = "sms/userInfo/delete"
// 	return postApi(url,params)
// }


export const deviceData = ({params}) => {
	let url = "sms/meterData/page"
	return postApi(url,params)
}
// export const deviceDataUpdate = ({params}) => {
// 	let url = "sms/userInfo/update"
// 	return postApi(url,params)
// }
// export const deviceDataAdd = ({params}) => {
// 	let url = "sms/userInfo/add"
// 	return postApi(url,params)
// }
// export const deviceDataDelete = ({params}) => {
// 	let url = "sms/userInfo/delete"
// 	return postApi(url,params)
// }
// 账户权限
export const authorityUser  = ({params}) => {
	let url = "sms/system/pageUser"
	return postApi(url,params)
}
export const roleList  = ({params}) => {
	let url = "sms/system/roleList"
	return postApi(url,params)
}
export const authorityUserAdd  = ({params}) => {
	let url = "sms/system/addUser"
	return postApi(url,params)
}
export const authorityUserUpdate  = ({params}) => {
	let url = "sms/system/updateUser"
	return postApi(url,params)
}
export const authorityUserDelete  = ({params}) => {
	let url = "sms/system/deleteUser"
	return postApi(url,params)
}
// 岗位权限
export const authorityJob  = ({params}) => {
	let url = "sms/system/pageRole"
	return postApi(url,params)
}

export const authorityJobAdd  = ({params}) => {
	let url = "sms/system/addRole"
	return postApi(url,params)
}
export const authorityJobDelete  = ({params}) => {
	let url = "sms/system/deleteRole"
	return postApi(url,params)
}
export const authorityJobUpdate  = ({params}) => {
	let url = "sms/system/updateRole"
	return postApi(url,params)
}
export const queryMenu  = ({params}) => {
	let url = "sms/system/queryMenu"
	return postApi(url,params)
}


export const listPriceTemplates  = ({params}) => {
	let url = "sms/setting/listPriceTemplates"
	return postApi(url,params)
}
export const addAndUpdatePriceTemplate  = ({params}) => {
	let url = "sms/setting/addAndUpdatePriceTemplate"
	return postApi(url,params)
}
export const deletePriceTemplate  = ({params}) => {
	let url = "sms/setting/deletePriceTemplate"
	return postApi(url,params)
}
export const addPrice  = ({params}) => {
	let url = "sms/setting/addPrice"
	return postApi(url,params)
}
export const updatePrice  = ({params}) => {
	let url = "sms/setting/updatePrice"
	return postApi(url,params)
}
// 获取水费名称列表
export const queryPriceNames  = ({params}) => {
	let url = "sms/setting/queryPriceNames"
	return postApi(url,params)
}
// 获取水费详情
export const queryByIdPriceItems  = ({params}) => {
	let url = "sms/setting/getPriceById"
	return postApi(url,params)
}

//用水类型
export const listUseType  = ({params}) => {
	let url = "sms/setting/listUseType"
	return postApi(url,params)
}
export const addUseType  = ({params}) => {
	console.log(params)
	let url = "sms/setting/addUseType"
	return postApi(url,params)
}
export const updateUseType  = ({params}) => {
	let url = "sms/setting/updateUseType"
	return postApi(url,params)
}
export const deleteUseType  = ({params}) => {
	let url = "sms/setting/deleteUseType"
	return postApi(url,params)
}

// 获取用户名称
export const getUserWaterValue  = ({params}) => {
	let url = "sms/userInfo/getUserWaterValue"
	return postApi(url,params)
}
// 查询表号
export const queryNoCorrelationMeters  = ({params}) => {
	let url = "sms/device/queryNoCorrelationMeters"
	return postApi(url,params)
}
export const buyWaterPrice  = ({params}) => {
	let url = "sms/userInfo/buyWaterPrice"
	return postApi(url,params)
}
export const calculateWaterPrice  = ({params}) => {
	let url = "sms/userInfo/calculateWaterPrice"
	return postApi(url,params)
}
export const deleteMeter  = ({params}) => {
	let url = "sms/device/deleteMeter"
	return postApi(url,params)
}


export const meterPage  = ({params}) => {
	let url = "ssms/device/meterPage"
	return postApi(url,params)
}



