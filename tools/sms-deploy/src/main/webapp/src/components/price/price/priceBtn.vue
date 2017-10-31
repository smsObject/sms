<template>
    <div class="form-wrap float-left btn-wrap">
        <el-button size="small" @click="handleAdd">费用项</el-button>
        <price-dialog v-model="dialogShow" :type="dialogType"></price-dialog>
    </div>
</template>
<script type="text/babel">
    import dialog from  "./priceDialog"
    import listDialog from "../../../assets/js/mixins/listDialog"
    export default {
        componentName:'price.price',
        mixins:[listDialog],
        components:{
            priceDialog:dialog
        }
    }
</script>