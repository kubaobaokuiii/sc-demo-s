<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
					<el-button type="primary" @click="handleCreate">新增</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<el-table :key='tableKey' :data="list.slice((currentPage-1)*pagesize,currentPage*pagesize)"  highlight-current-row v-loading="listLoading" style="width: 100%;" >
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
			<el-table-column prop="username" label="端口 航班数" width="230" sortable>
				<template scope="scope">
					<span>{{scope.row.number}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="birthday" label="出生日期" width="120" sortable>
				<template scope="scope">
					<span>{{scope.row.birthday}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="sex" label="性别" width="100" sortable>
				<template scope="scope">
					<span>{{scope.row.sex}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="age" label="年龄" width="100" sortable>
				<template scope="scope">
					<span>{{scope.row.age}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="card" label="身份证" width="120" sortable>
				<template scope="scope">
					<span>{{scope.row.card}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="adress" label="地址" width="120" sortable>
				<template scope="scope">
					<span>{{scope.row.adress}}</span>
				</template>
			</el-table-column>
			<el-table-column label="操作" width="300">
				<template scope="scope">
					<el-button size="small"  @click="handleView(scope.$index, scope.row)">查看</el-button>
					<el-button size="small"  @click="handleViewOrder(scope.$index, scope.row)">查看订单</el-button>
					<el-button size="small"  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
					<el-button type="danger" size="small"  @click="handleDelete(scope.row)" >删除</el-button>
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

		<!--//编辑与新增航班-->
		<el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" >
			<el-form :model="form" ref="form" label-width="100px" :rules="rules" >
				<el-form-item label="用户名" prop="username">
					<el-input v-if="dialogStatus == 'create'" v-model="form.username" placeholder="请输入用户名"></el-input>
					<el-input v-else v-model="form.username" placeholder="请输入用户名"></el-input>
				</el-form-item>
				<el-form-item label="身份证" prop="card">
					<el-input v-if="dialogStatus == 'create'" v-model="form.card" placeholder="请输入身份证"></el-input>
					<el-input v-else v-model="form.card" placeholder="请输入身份证"></el-input>
				</el-form-item>
				<el-form-item label="出生日期" prop="birthday">
					<el-input v-if="dialogStatus == 'create'" v-model="form.birthday" placeholder="请输入出生日期"></el-input>
					<el-input v-else v-model="form.birthday" placeholder="请输入出生日期"></el-input>
				</el-form-item>
				<el-form-item label="年龄" prop="age">
					<el-input v-if="dialogStatus == 'create'" v-model="form.age" placeholder="请输入年龄"></el-input>
					<el-input v-else v-model="form.age" placeholder="请输入年龄"></el-input>
				</el-form-item>
				<el-form-item label="性别" prop="sex">
					<el-input v-if="dialogStatus == 'create'" v-model="form.sex" placeholder="请输入性别"></el-input>
					<el-input v-else v-model="form.sex" placeholder="请输入性别"></el-input>
				</el-form-item>
				<el-form-item label="地址" prop="adress">
					<el-input v-if="dialogStatus == 'create'" v-model="form.adress" placeholder="地址"></el-input>
					<el-input v-else v-model="form.adress" placeholder="请输入地址"></el-input>
				</el-form-item>

			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="cancel('form')">取 消</el-button>
				<el-button v-if="dialogStatus=='create'" type="primary" @click="addUser()">确 定</el-button>
				<el-button v-else type="primary" @click="updateUser()">确 定</el-button>
			</div>
		</el-dialog>


	</section>
</template>

<script>
	import util from '../../common/js/util';
	import { mapGetters } from 'vuex';
	// import { mapGetters } from '../../../node_modules/vuex/types/helpers.d.ts';
	//导入航班的增删改查接口
	import {getUserListPage,} from '../../api/user';

	//校验
	// import {isvalidPhone} from '../../utils/validate';

	//导入验证

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
					number:''
				},

				tableKey: 0,
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
				rules: {
					username: [
						{
							required: true,
							message: '请输入11位电话号码信息',
							trigger: 'blur'
						},
						{
							message: '长度在11位',
							trigger: 'blur',
							pattern:/^1[3456789]\d{9}$/,
						}
					],
					age:[
						{
							required: true,
							message: '请输入您的年龄',
							trigger: 'blur'
						},
						{
							message: '输入的年龄是1-120之间有效',
							trigger: 'blur',
							pattern:/^(?:[1-9][0-9]?|1[01][0-9]|120)$/
						}
					],

				}, //rules的结尾

			}
		},

		//请求后端数据
		created() {
			this.getUserListPage();
		},

		//方法
		methods:{
			getUserListPage(){
				console.log("开始调用用户列表方法....................")
				this.listLoading = true;
				getUserListPage()
						.then(response => {
							this.list = response;
							this.listLoading = false;
						})
			},

			//显示增加界面
			handleCreate(){
				this.form;
				this.dialogStatus = 'create';
				this.dialogFormVisible = true;

			},
			//显示删除界面
			handleDelete(form) {
				this.$confirm('此操作将永久删除, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				})
			},

			//增加数据
			addUser(){
				fetch('/api/user/user/addUser', {
					method: 'POST',
					headers: {
						'Accept': 'application/json',
						'Content-Type': 'application/json'
					},
					body: JSON.stringify(this.form)
				}).then((res) => {

					this.dialogFormVisible = false;
					this.getUserListPage();

				})
			},

			//显示编辑更新界面
			handleEdit(index, row) {
				this.form = row;
				this.dialogFormVisible = true;
				this.dialogStatus = 'update';
			},

			//更新数据
			updateUser(form) {
				this.dialogFormVisible = false;
				fetch('/api/user/user/updateUser', {
					method: 'POST',
					headers: {
						'Accept': 'application/json',
						'Content-Type': 'application/json'
					},
					body: JSON.stringify(this.form)
				}).then((res) => {
					this.getUserListPage();
					console.log(res);
				})
			},

			//删除数据
			handleDelete: function (form) {
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					this.listLoading = true;
					let para = form.id;
					fetch('/api/user/user/deleteUser/' + para , {
						method: 'post',
					}).then((res) => {
						this.getUserListPage();
					})
				});
				this.getUserListPage();
			},
			//显示查看的界面
			handleView(index,row){
				var id = row.id;
				this.$router.push({
					path: '/viewFight',
					query: {
						id: id
					}
				})
			},

			//从用户端口查看用户订单信息
			handleViewOrder(index,row){

				var userToOrderId = row.id;

				//根据用户id去查询在订单列表是否存在
				fetch('/api/order/allOrders', {
					method: 'POST',
					headers: {
						'Accept': 'application/json',
						'Content-Type': 'application/json'
					},
				}).then(res => res.json()).then(res => {
					//对比订单用户与用户列表的id是否匹配
					var userIdTest = []; //循环数组对比
					for(var i=0;i<res.length;i++){
						userIdTest.push(res[i].id)
					}
					//判断
					if(userToOrderId in userIdTest){
						this.$notify({
							title: '提示',
							message: '此用户已订票，去订单列表核对',
							type: 'warning'
						});
						this.$router.push({
							path: '/Order',
						})
					}else{
						this.$notify({
							title: '提示',
							message: '此用户未订票，请去航班列表核对用户订单',
							type: 'warning'
						});
						this.$router.push({
							path: '/Fight',
							query:{
								id:userToOrderId
							}
						})
					}

				})

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
			},

		},//methods的结束

	}//export default的结束

</script>

<style scoped>

</style>