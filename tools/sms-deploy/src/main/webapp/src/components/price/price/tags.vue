<template>
    <div class="el-price-tag">
        <tag  :key="index" :value="tag.name" @input="handleAddUpdate" :index="index" v-for="(tag,index) in tags" @close="handleClose"></tag>
        <el-input
            class="input-new-tag"
            v-if="inputVisible"
            v-model="inputValue"
            ref="saveTagInput"
            size="mini"
            @keyup.enter.native="handleInputConfirm"
            @blur="handleInputConfirm"
        >
        </el-input>
        <el-button v-else class="button-new-tag" size="small" @click="showInput">+ 费用项</el-button>
    </div>
</template>
<script type="text/babel">
    import tag from "./tag"
    import {addAndUpdatePriceTemplate,deletePriceTemplate} from "../../../api/get"
    import { mapGetters } from 'vuex'
    export default {
        props:{
            tags:{
                type:Array,
                default() {
                    return []
                }
            },
            parentId:Number
        },
        data() {
            return {
                inputVisible: false,
                inputValue: '',
            }
        },
        components:{
            tag:tag
        },
        computed:{
            ...mapGetters({
                userPermission:'userPermission'
            })
        },
        methods: {
            handleClose(index) {
                deletePriceTemplate({params:{id:this.tags[index].id}}).then(res => {
                    this.$message({
                        type: 'success',
                        message: '删除成功'
                    });
                })
                this.tags.splice(index, 1);
            },
            handleAddUpdate(value,index) {
                if(!value) return false
                var item = this.tags[index]

                addAndUpdatePriceTemplate({params:{id:item.id || "",name:value,parentId:this.parentId}}).then(res => {
                    this.$set(item,"id",res.data)
                    this.$set(item,"name",value)
                    this.$message.success("操作成功")
                }).catch(res => {
                    this.$message.error(res.data)
                })
            },
            showInput() {
                this.inputVisible = true;
                this.$nextTick(() => {
                    this.$refs.saveTagInput.$refs.input.focus();
                });
            },

            handleInputConfirm() {
                let inputValue = this.inputValue;
                if(!this.parentId){
                    this.$message.error("请先添加父类")
                    return false
                }
                if (inputValue) {
                    this.tags.push({
                        name:inputValue,
                        parentId:this.parentId
                    });
                }
                this.inputVisible = false;
                this.inputValue = '';
                var tag = this.tags[this.tags.length-1]
                addAndUpdatePriceTemplate({params:tag}).then(res => {
                    tag.id = res.data
                }).catch(res => {
                    this.$message.error(res.statusText)
                })
            }
        }
    }
</script>
<style lang="scss" rel="stylesheet/scss">
    .el-price-tag .el-tag{margin-right:10px;margin-top:10px;}
    .el-price-tag .button-new-tag{margin-right:10px;margin-top:10px;height:24px;line-height:22px;padding-top:0;padding-bottom:0}
    .el-price-tag .input-new-tag{width:100px;margin-right:10px;margin-top:10px;vertical-align:bottom}
    .el-price-tag .input-new-tag .el-input__inner{height:24px}
</style>