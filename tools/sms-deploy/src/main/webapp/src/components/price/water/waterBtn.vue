<template>
    <div class="form-wrap float-left btn-wrap">
        <el-button size="small" @click="handleAdd">用水类型</el-button>
        <price-dialog v-model="dialogShow" :type="dialogType"></price-dialog>
    </div>
</template>
<script type="text/babel">
    import dialog from  "./waterDialog"
    import listDialog from "../../../assets/js/mixins/listDialog"
    export default {
        componentName:'price.water',
        mixins:[listDialog],
        created() {
            this.$on("show-dialog",this.handleEdit)
        },
        components:{
            priceDialog:dialog
        }
    }
</script>