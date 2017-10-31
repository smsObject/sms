<template>
        <el-tag
                v-if="!inputVisible"
                type="primary"
                :closable="closable"
                :close-transition="true"
                @click="inputVisible = true"
                @close="handleClose"
        >{{value}}</el-tag>
        <el-input
                v-else
                class="input-new-tag"
                v-model="inputValue"
                ref="saveTagInput"
                size="mini"
                @keyup.enter.native="handleInputConfirm"
                @blur="handleInputConfirm">
        </el-input>
</template>
<script type="text/babel">
    export default {
        props:{
            closable:{
                type:Boolean,
                default:true
            },
            tag:String,
            index:Number,
            value:String
        },
        data() {
            return {
                inputVisible: false,
                inputValue: this.value,
            }
        },
        watch:{
            value(val){
                this.inputValue = val
            },
            inputVisible(val){
                val && this.$nextTick(() => {
                    this.$refs.saveTagInput.$refs.input.focus();
                });
            }
        },
        methods:{
            handleInputConfirm() {

                this.inputVisible  = false
                if(!this.inputValue){
                    this.inputValue = this.value
                }
                this.$emit("input",this.inputValue,this.index)
            },
            handleClose(){
                this.$emit("close",this.index)
            }
        }
    }
</script>