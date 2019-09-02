<template>
  <section>
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters">
        <el-form-item>
          <el-input v-model="filters.number" placeholder="航班号"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary">查询</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <!--列表-->
    <el-table :key='tableKey' :data="list"  highlight-current-row v-loading="listLoading" style="width: 100%;">
      <el-table-column type="index" width="60" prop="fightId" label="id">
        <template scope="scope">
          <span>{{scope.row.fightId}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="number" label="航班号" width="100" sortable>
        <template scope="scope">
          <span>{{scope.row.number}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="capacity" label="容量" width="70" sortable>
        <template scope="scope">
          <span>{{scope.row.capacity}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="people" label="人数" width="70" sortable>
        <template scope="scope">
          <span>{{scope.row.people}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="starttime" label="起始时间" width="70" sortable>
        <template scope="scope">
          <span>{{scope.row.starttime}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="endtime" label="结束时间" min-width="70" sortable>
        <template scope="scope">
          <span>{{scope.row.endtime}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="time" label="总时长" min-width="60" sortable>
        <template scope="scope">
          <span>{{scope.row.time}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="remark" label="备注" min-width="40" sortable>
        <template scope="scope">
          <span>{{scope.row.remark}}</span>
        </template>
      </el-table-column>
    </el-table>
  </section>
</template>

<script>
  import util from '../../common/js/util';
  import { mapGetters } from 'vuex';
  import {getViewFightListPage} from '../../api/viewFight';

  export default {

    number: 'number',
    data() {
      return {
        filters: {
          number: ''
        },
        form: {
          number: '',
          capacity: '',
          people: '',
          starttime: '',
          endtime: '',
          time:'',
          remark:''
        },

        tableKey: 0,
        list:null,
        total:null,
        listLoading: true,
        //列表查询
        listQuery: {
          page: 1,
          limit: 20,
          name: undefined
        },
        dialogStatus: '',
        dialogFormVisible:false,
        viewFightId:''

      }
    },

    //请求后端数据
    created() {
      this.getParams()
    },

    //方法
    methods:{
      getParams(){
        // 取到路由带过来的参数
        const routerParams = this.$route.query.id
        // 将数据放在当前组件的数据内
        this.viewFightId = routerParams;
        console.log(routerParams)

        if(routerParams == undefined){

          this.$router.push({
            path: '/main',   //false回到航班列表
          })
        }else {

          //往列表渲染数据
          fetch('/api/order/selectFightIdByUserId/' + this.viewFightId,{
            method: 'post'
          }).then(res => res.json()).then(res => {
            var data = res;
            if(isNaN(data) == false){
              this.$router.push({
                path: '/Fight',   //false回到航班列表
              })
            }else {
              for (var i = 0; i < data.length; i++) {
                var arrData = []; //定义数组初始值
                console.log(data)
                fetch('/api/flight/hangBan/allFight/' + data[i].fightId, {
                  method: 'get'
                })
                        .then(res => res.json()).then(res => {
                  console.log(res)
                  console.log("..................列表........")
                  arrData.push(res)
                  this.list = arrData;
                  this.listLoading = false;

                })

              }  //for循环的结束

            }  //if else 判断的结束
          })

        } //if-else结束

      }
    },//methods的结束

  }//export default的结束

</script>

<style scoped>

</style>