<template>
    <el-dialog title="水费设置" :visible.sync="infoDialog" @open="handleOpen" size="large">
        <el-form :model="form" ref="form" label-width="100px" v-if="costs.length">
            <el-row>
                <el-col :span="6">
                    <el-form-item label="水费查询" prop="id">
                        <el-select
                                @change="handleByInfo"
                                v-model="form.id"
                                filterable
                                allow-create
                                placeholder="查询请选择">
                            <el-option label="请选择" value=""></el-option>
                            <el-option
                                    v-for="(value,key) in names"
                                    :key="key"
                                    :label="value"
                                    :value="key">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="4">
                    <el-form-item label="水费名称" prop="name">
                        <el-input placeholder="水费名称" v-model="form.name"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="4">
                    <el-form-item label="年份" label-width="60px" prop="year">
                        <el-date-picker type="year" placeholder="选择年" v-model="form.year"></el-date-picker>
                    </el-form-item>
                </el-col>
                <el-col :span="4">
                    <el-form-item label="类型" prop="timeLength">
                        <el-select v-model="form.timeLength" placeholder="请选择">
                            <el-option v-for="item in timeLength" :key="item.value" :label="item.label" :value="item.value"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="4">
                    <el-form-item label="计价类型" prop="type">
                        <el-select v-model="form.type" placeholder="请选择" :disabled="!!form.id">
                            <el-option v-for="item in types" :key="item.value" :label="item.label" :value="item.value"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-table border :data="form.priceItems">
                <el-table-column label="阶梯" prop="key" align="center"></el-table-column>
                <el-table-column label="水量范围" align="center">
                    <el-table-column label="开始值" prop="startValue" align="center"></el-table-column>
                    <el-table-column label="结束值" prop="endValue" align="center">
                        <template scope="scope">
                            <el-input  v-model="scope.row.endValue" :disabled="form.type == 1"/>
                        </template>
                    </el-table-column>
                </el-table-column>
                <el-table-column :label="item.name" v-for="(item,index) in costs" :key="index" align="center">
                    <el-table-column :prop="child.id+''" v-for="child in item.childs" :key="child.id" :label="child.name" align="center" width="150">
                        <template scope="scope">
                            <el-input  v-model="scope.row[child.id]"/>
                        </template>
                    </el-table-column>
                </el-table-column>
                <el-table-column label="价格" prop="price" align="center"></el-table-column>
                <el-table-column label="操作" align="center" fixed="right" width="200">
                    <template scope="scope">
                        <el-button @click="handleDelete(scope.$index)" v-if="scope.$index && scope.$index <= form.priceItems.length-1">删除</el-button>
                        <el-button  @click="handleStep(scope.$index)" v-if="scope.$index >= 0 && form.type != 1" type="info">添加</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="infoDialog = false">取 消</el-button>
            <el-button type="primary" @click="confirm">保存</el-button>
        </div>
    </el-dialog>
</template>
<script type="text/babel">
    import formDialog from "../../../assets/js/mixins/formDialog"
    import {numberToChinese,deepCopy} from "../../../assets/js/utils/util"
    import {listPriceTemplates,addPrice,queryPriceNames,queryByIdPriceItems} from "../../../api/get"
    export default {
        mixins:[formDialog],
        data() {
            return {
                infoDialog:false,
                formLabelWidth:'100px',
                costs:[],
                names:{},
                types:[{value:1,label:"普通计价"},{value:2,label:"阶梯计价"}],
                timeLength:[
                    {label:"月度",value:1},
                    {label:"季度",value:2},
                    {label:"半年",value:3},
                    {label:"年度",value:4},
                ],
                priceItemsKey:[],
                form:{
                    id:"",
                    name:"",
                    year:new Date(),
                    timeLength:1,
                    type:1,
                    priceItems:[{
                        price:"",key:"基本量",startValue:0,endValue:"",
                    }]
                },
            }
        },
        computed:{
            costsSave(){
                let costsSave = []
                this.costs.forEach(item =>　{
                    item.childs.forEach(cost => {
                        var key = cost.id
                        var obj = {}
                        obj[key] = ''
                        costsSave.push(obj)
                    })
                })
                return costsSave
            }
        },
        watch:{
            "form.priceItems":{
                immediate:true,
                deep:true,
                handler(val) {
                    val.forEach((item,index) => {
                        if(index){
                            item.startValue = val[index-1].endValue
                            item.key = numberToChinese(index)+"阶价"
                        }else {
                            item.startValue = 0
                            item.key = "基本量"
                        }
                        var price = 0
                        this.costs.forEach(costs =>　{
                            costs.childs.forEach(cost => {
                                let id = item[cost.id] || 0
                                price += isNaN(id) ? 0 : parseFloat(id)
                            })
                        })
                        item.price = price.toFixed(2)
                    })
                }
            },
            "form.type":{
                handler(val) {
                    if(val == 1){
                        this.form.priceItems.slice(0,1)
                        this.$nextTick(() => {
                            this.form.priceItems[0].endValue = ""
                        })
                    }
                }
            }
        },
        methods:{
            confirm() {
                let form = deepCopy(this.form)
                form.year = this.form.year.getYear()+1900
                form.priceItems.forEach(item => {
                    item.prices = {}
                    this.priceItemsKey.forEach(key => {
                        item.prices[key] = item[key]
                        delete item[key]
                    })
                })
                addPrice({params:{data:JSON.stringify(form)}}).then(res => {
                    if(res.success){
                        this.$message.success(res.statusText)
                    }else {
                        this.$message.error(res.statusText)
                    }
                    this.infoDialog = false
                })
                // 成功回调
            },

            handleByInfo() {
                if(!this.form.id) return false // 没有id返回 数据当模板处理
                queryByIdPriceItems({params:{id:this.form.id}}).then(res => {
                    let data = Object.assign(res.data,{id:res.data.id+"",year:new Date(res.data.year+"")})
                    data.priceItems = data.priceItems.map(item => {
                        let {startValue,endValue} = item;
                        return Object.assign({},item.prices,{startValue,endValue})
                    })
                    this.form = data
                })
            },

            handleOpen() {
                this.$refs.form && this.$refs.form.resetFields()
                this.form.priceItems = [{
                    price:"",key:"基本量",startValue:0,endValue:"",
                }]
                this.costs = []
                this.$nextTick(() => {
                    listPriceTemplates({params:{}}).then(res => {
                        this.priceItemsKey = []
                        this.costs = res.data.map(item =>{
                            item.childs.forEach(i => {
                                this.$set(this.form.priceItems[0],i.id,0)
                                this.priceItemsKey.push(i.id+"")
                            })
                            return {
                                id:item.id,
                                name:item.name,
                                childs:item.childs
                            }
                        })
                    })
                })

                queryPriceNames({params:{}}).then(res =>　{
                    this.names = res.data
                })
            },


            handleStep(index) {
                var steps = this.form.priceItems
                let endValue = steps[index].endValue,startValue = steps[index].startValue

                if(!endValue || startValue >= parseFloat(endValue) || isNaN(endValue)){
                    this.$message.info("请输入正确的结束值")
                    return false
                }
                var self = this
                var obj =Object.assign({},{
                    price: "",
                    startValue:endValue,
                    endValue:"",
                    key:numberToChinese(index+1)+"阶价"
                },...deepCopy(this.costsSave))
                steps.splice(index+1,0,obj)
            },
            handleDelete(index) {
                var steps = this.form.priceItems
                index && steps.splice(index,1)
            }
        },
        filters:{
            numberToChinese:numberToChinese
        }
    }
</script>