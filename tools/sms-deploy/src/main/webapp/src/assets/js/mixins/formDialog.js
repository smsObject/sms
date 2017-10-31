/**
 * Created by Administrator on 2017/5/14 0014.
 */
export default {
	props:{
		value:Boolean,
		type:Number,//0 新增, 1 编辑
	},

	watch:{
		value(val) {
			this.infoDialog = val
		},
		infoDialog(val) {
			this.$emit("input",val)
		},
	},
	methods:{
		handleOpen() {
			if(!this.type){
				this.$refs.form && this.$refs.form.resetFields()
			}else {
				if(!this.$refs.form){
					this.$nextTick(()=>{
						console.log(this.$parent.dialogModel)
						this.form = Object.assign({},this.$parent.dialogModel)
					})
				}else {
					this.form = Object.assign({},this.$parent.dialogModel)
				}
			}
		}
	}
}