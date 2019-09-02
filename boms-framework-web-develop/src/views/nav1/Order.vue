<template>
    <section>
        <!--工具条-->
        <!--列表-->
        <el-table :key='tableKey' :data='list.slice((currentPage-1)*pagesize,currentPage*pagesize)'  highlight-current-row v-loading="listLoading" style="width: 100%;" >
            <el-table-column type="index" width="60" prop="fightId" label="id">
                <template scope="scope">
                    <span>{{scope.row.hangBanId}}</span>
                </template>
            </el-table-column>
            <!--<el-table-column prop="id" label="用户id" width="100" sortable>-->
            <!--<el-table-column prop="id" label="用户id" width="100" sortable>-->
                <!--<template scope="scope">-->
                    <!--&lt;!&ndash;<span>{{scope.row.id}}</span>&ndash;&gt;-->
                    <!--<span>{{scope.row.id}}</span>-->
                <!--</template>-->
            <!--</el-table-column>-->
            <el-table-column prop="" label="用户名" width="100" sortable>
                <template scope="scope">
                    <!--<span>{{scope.row.id}}</span>-->
                    <span>{{scope.row.name}}</span>
                </template>
            </el-table-column>

            <el-table-column prop="" label="订单数 端口" width="250" sortable>
                <template scope="scope">
                    <!--<span>{{scope.row.id}}</span>-->
                    <span>{{scope.row.order}}</span>
                </template>
            </el-table-column>

            <el-table-column prop="fightId" label="机票id" width="100" sortable>
                <template scope="scope">
                    <span>{{scope.row.fightId}}</span>
                </template>
            </el-table-column>
            <el-table-column prop="hangBanCapa" label="机票余量" width="140" sortable>
                <template scope="scope">
                    <span>{{scope.row.hangBanCapa}}</span>
                </template>
            </el-table-column>
            <el-table-column prop="bookTicketsTime" label="订票时间" width="140" sortable>
                <template scope="scope">
                    <span>2019-5-21</span>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="200">
                <template scope="scope">
                    <el-button size="small"  @click="handleView(scope.$index, scope.row)">查看订单信息</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--//分页的实现-->
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[5, 10, 20, 40]"
                :page-size="pagesize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="list.length">
        </el-pagination>

    </section>
</template>

<script>
    import util from '../../common/js/util';
    import { mapGetters } from 'vuex';
    import {getOrderListPage} from '../../api/order';

    export default {

        number: 'number',
        data() {
            return {
                orderAll:[],
                userAll:[],
                filters: {
                    number: ''
                },
                form: {
                    hangBanCapa:'',
                    bookTicketsTime:''
                },

                tableKey: 0,
                userlist:null,
                list:[],
                currentPage:1, //初始页
                pagesize:5,    //    每页的数据
                total:null,
                listLoading: true,
                //列表查询
                listQuery: {
                    page: 1,
                    limit: 20,
                    name: undefined
                },
                textMap: {
                    update: '编辑',
                    create: '创建'
                },
                dialogStatus: '',
                dialogFormVisible:false,
                information:'',
                nullUser:''

            }
        },

        //请求后端数据
        created() {
            this.getOrderListPage();  //请求订单列表
        },

        //方法
        methods:{

            getOrderListPage(){
                console.log("开始调用订单列表方法....................")
                this.listLoading = true;
                getOrderListPage()
                    .then(response => {
                        //-------------------测试--------------
                        let orderAll = response;
                        fetch(`/api/user/user/allUser/`, {
                            method: 'get'
                        }).then(res => res.json()).then(res => {
                         let userAll = res;
                            for (let i = 0; i < orderAll.length; i++) {
                                for (let j = 0; j < userAll.length; j++) {
                                    if (orderAll[i].id==userAll[j].id){
                                        console.log(orderAll[i].id,userAll[j].id,userAll[j].username);
                                        orderAll[i].name = userAll[j].username
                                        continue
                                    }
                                }//j循环结束
                            } //i循环结束
                            this.nullUser = orderAll;
                            this.list = orderAll; //orderAll为渲染列表
                            this.listLoading = false;  //加载结束
                        })
                    })
            }, //getOrderListPage结束

            //点击去查看个人中心信息
            handleView:function(index,row){
                var id = row.hangBanId;
                var fightId = row.fightId;
                var userId = row.id;

                if(userId == 13){  //简单测试,待完善

                    this.$notify({
                        title: '提示',
                        message: '此用户信息不存在',
                        type: 'warning'
                    });

                }else{

                    this.$router.push({
                        path: '/Page6',
                        query: {
                            id: id,
                            fightId:fightId,
                            userId:userId
                        }
                    }) //push

                }

            },

            // 初始页currentPage、初始每页数据数pagesize和数据data
            handleSizeChange: function (size) {
                this.pagesize = size;
                console.log(this.pagesize)  //每页下拉显示数据
            },
            handleCurrentChange: function(currentPage){
                this.currentPage = currentPage;
                console.log(this.currentPage)  //点击第几页
            },

            //取消隐藏填写框
            cancel:function () {
                this.dialogFormVisible = false;
            }

        },//methods的结束

    }//export default的结束

</script>

<style scoped>

</style>