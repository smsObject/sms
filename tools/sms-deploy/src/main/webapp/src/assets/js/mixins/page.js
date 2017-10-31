export default {
	data() {
		return {
			pageSizes:[20, 50, 100, 150],
			total:0,
			pageNo:1,
			pageSize:20,
		}
	},
	computed:{
		page() {
			return {
				pageNo:this.pageNo,
				pageSize:this.pageSize,
			}
		}
	},
	watch:{
		page:{
			immediate:true,
			handler(val) {
				this.fetchListData()
			}
		}
	},
	created(){
		Object.defineProperty(this, 'initialPageSize', {
			value: this.pageSize
		})
	},
	methods:{
		handleReset(form) {
			form && this.$refs[form] && this.$refs.form.resetFields()
			if(this.pageSize == this.initialPageSize && this.pageNo == 1){
				this.fetchListData()
			}else {
				this.pageSize = this.initialPageSize
				this.pageNo = 1
			}
		},
		handleSizeChange(pageSize) {
			this.pageSize = pageSize
		},
		handleCurrentChange(pageNo) {
			this.pageNo = pageNo
		}
	}
}