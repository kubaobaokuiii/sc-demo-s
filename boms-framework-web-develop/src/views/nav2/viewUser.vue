<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
					<el-input v-model="filters.username" placeholder="用户名"></el-input>
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
					<span>{{scope.row.id}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="username" label="用户名" width="100" sortable>
				<template scope="scope">
					<span>{{scope.row.username}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="birthday" label="出生日期" width="120" sortable>
				<template scope="scope">
					<span>{{scope.row.birthday}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="sex" label="性别" width="70" sortable>
				<template scope="scope">
					<span>{{scope.row.sex}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="age" label="年龄" width="120" sortable>
				<template scope="scope">
					<span>{{scope.row.age}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="card" label="身份证" min-width="50" sortable>
				<template scope="scope">
					<span>{{scope.row.card}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="adress" label="地址" min-width="60" sortable>
				<template scope="scope">
					<span>{{scope.row.adress}}</span>
				</template>
			</el-table-column>
			<el-table-column label="操作" width="150">
				<template scope="scope">
					<el-button size="small"  @click="handleView(scope.$index, scope.row)">查看</el-button>
					<el-button size="small"  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
					<el-button type="danger" size="small"  @click="handleDelete(scope.row)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>

	</section>
</template>

<script>
	import util from '../../common/js/util';
	import { mapGetters } from 'vuex';

	export default {

		number: 'number',
		data() {
			return {
				filters: {
					number: ''
				},
				form: {
					username: '',
					card: '',
					birthday: '',
					age: '',
					sex: '',
					adress:'',
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
				textMap: {
					update: '编辑',
					create: '创建'
				},
				dialogStatus: '',
				dialogFormVisible:false,
				viewUserId:''

			}
		},

		//请求后端数据
		created() {
			
			//页面加载的时候接收参数
			this.getParams()

		},

		//方法
		methods:{

			getParams(){
				// 取到路由带过来的参数
				const routerParams = this.$route.query.id
				// 将数据放在当前组件的数据内
				this.viewUserId = routerParams;
				console.log(routerParams)

				if(routerParams == undefined){

					this.$router.push({
						path: '/User',   //false回到航班列表
					})

				}else{

					//往列表渲染数据
					fetch('/api/order/selectUserIdByFightId/' + this.viewUserId,{
						method: 'post'
					}).then(res => res.json()).then(res => {
						var data = res;
						if(isNaN(data) == false){
							this.$router.push({
								path: '/User',   //false回到航班列表
							})
						}else {
							for (var i = 0; i < data.length; i++) {
								var arrData = []; //定义数组初始值
								fetch('/api/user/user/allUser/'+ data[i].id, {
									method: 'get'
								}).then(res => res.json()).then(res => {
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