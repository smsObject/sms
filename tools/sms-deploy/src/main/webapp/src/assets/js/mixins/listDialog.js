/**
 * Created by Administrator on 2017/5/13 0013.
 */
export default {
	created(){
		this.$on("show-dialog",this.handleAdd)
		this.$on("update-list",this.fetchListData)
	},
	data:function () {
		return {
			dialogType:0,
			dialogModel:null,
			dialogShow:false
		}
	},
	methods:{
		handleEdit(index, row) {
			this.dialogType = 1
			this.dialogModel = row
			this.dialogShow = true
		},
		handleAdd(){
			this.dialogType = 0
			this.dialogShow = true
		}
	}
}