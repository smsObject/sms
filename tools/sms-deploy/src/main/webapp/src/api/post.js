import Vue from 'vue'
export const  postApi = (url,params)=>{
	console.log(params)
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

export const signin = ({params}) => {
	let url = "auth/signIn"
	return postApi(url,params)
}
export const signOut = ({params}) => {
	let url = "auth/signOut"
	return postApi(url,params)
}
export const getUserPermission = ({params}) => {
	let url = "/auth/getUserPermission"
	return postApi(url,params)
}
