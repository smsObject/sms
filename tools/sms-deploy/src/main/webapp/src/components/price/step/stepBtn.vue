<template>
    <div class="form-wrap float-left btn-wrap">
        <el-button size="small" @click="dialogShow = true">水费名称</el-button>
        <price-dialog v-model="dialogShow" :type="dialogType"></price-dialog>
    </div>
</template>
<script type="text/babel">
    import dialog from  "./stepDialog"
    import listDialog from "../../../assets/js/mixins/listDialog"
    export default {
        componentName:'price.step',
        mixins:[listDialog],
        data() {
            return {
                myShow : false
            }
        },
        components:{
            priceDialog:dialog,
        },
        methods:{
            handleShow() {
                this.myShow = true
                this.$nextTick(() => {
                    this.dialogShow = true
                })
            }
        }
    }
</script>