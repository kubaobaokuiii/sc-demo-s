<template>
	<section>

		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
					<el-button type="primary" @click="deleteTickets">退票</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--&lt;!&ndash;用户列表展示&ndash;&gt;-->
		<el-form :model="form_user" ref="form_user" label-width="80px" size="mini">
			<el-form-item label="用户名" prop="username">
				<el-input  v-model="form_user.username"></el-input>
			</el-form-item>
			<el-form-item label="身份证" prop="card">
				<el-input  v-model="form_user.card" ></el-input>
			</el-form-item>
			<!--<el-form-item label="出生日期" prop="birthday">-->
				<!--<el-input  v-model="form_user.birthday" ></el-input>-->
			<!--</el-form-item>-->
			<!--<el-form-item label="年龄" prop="age">-->
				<!--<el-input  v-model="form_user.age" ></el-input>-->
			<!--</el-form-item>-->
			<!--<el-form-item label="性别" prop="sex">-->
				<!--<el-input  v-model="form_user.sex" ></el-input>-->
			<!--</el-form-item>-->
			<!--<el-form-item label="地址" prop="adress">-->
				<!--<el-input  v-model="form_user.adress"></el-input>-->
			<!--</el-form-item>-->
		</el-form>

		<!--&lt;!&ndash;订票信息列表展示&ndash;&gt;-->
		<el-form :model="form_flight" ref="form_flight" label-width="80px" size="mini">
			<el-form-item label="航班号" prop="number">
				<el-input  v-model="form_flight.number" ></el-input>
			</el-form-item>
			<!--<el-form-item label="容量" prop="capacity">-->
				<!--<el-input  v-model="form_flight.capacity" ></el-input>-->
			<!--</el-form-item>-->
			<!--<el-form-item label="人数" prop="people">-->
				<!--<el-input  v-model="form_flight.people" ></el-input>-->
			<!--</el-form-item>-->
			<el-form-item label="起始时间" prop="starttime">
				<el-input  v-model="form_flight.starttime" ></el-input>
			</el-form-item>
			<el-form-item label="结束时间" prop="create">
				<el-input  v-model="form_flight.endtime" ></el-input>
			</el-form-item>
			<!--<el-form-item label="总时长" prop="time">-->
				<!--<el-input  v-model="form_flight.time" ></el-input>-->
			<!--</el-form-item>-->
			<!--<el-form-item label="备注" prop="remark">-->
				<!--<el-input  v-model="form_flight.remark" ></el-input>-->
			<!--</el-form-item>-->
		</el-form>

		<!--查询订票时间-->


	</section>
</template>

<script>
	import util from '../../common/js/util';


	export default {

		number: 'number',
		data() {
			return {
				filters: {
					number: ''
				},
				form_user: {
					username: '',
					card: '',
					birthday: '',
					age: '',
					sex: '',
					adress:'',
				},
				form_flight: {
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
				viewOrderId:'',
				viewFight:'',
				viewUser:''

			}
		},

		//请求后端数据
		created() {
			this.getParams() //接收参数
		},

		//方法
		methods:{

			getParams() {
				// 取到路由带过来的参数
				const routerParamsOrder = this.$route.query.id;
				const routerParamsFight = this.$route.query.fightId;
				const routerParamsUser = this.$route.query.userId;

				// 将数据放在当前组件的数据内
				this.viewOrderId = routerParamsOrder;
				this.viewFight = routerParamsFight;
				this.viewUser = routerParamsUser;

				if(routerParamsOrder == undefined){

					this.$router.push({
						path: '/Order',   //false回到航班列表
					})

				}else {

						//根据用户id查询用户信息
						fetch('/api/user/user/allUser/' + this.viewUser, {
							method: 'POST',
							headers: {
								'Accept': 'application/json',
								'Content-Type': 'application/json'
							},
							// body: JSON.stringify(this.form_user)
						}).then(res => res.json()).then(res => {

							this.form_user = res;

						})

						//根据用户id查询用户信息
						fetch('/api/flight/hangBan/allFight/' + this.viewFight, {
							method: 'POST',
							headers: {
								'Accept': 'application/json',
								'Content-Type': 'application/json'
							},
						}).then(res => res.json()).then(res => {

							console.log(res);
							this.form_flight = res;

						})
				}

			},//getParams结束

			//退票业务的实现
			deleteTickets:function () {
				let orderId = this.viewOrderId;
				//确认是否删除
				this.$confirm('此用户确定退票, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					fetch('/api/order/deleteTickets/' + orderId , {
						method: 'post',
					}).then((res) => {
						this.$message({
							type: 'success',
							message: '退票成功!'
						});
						this.$router.push({
							path: '/Order',
						}) //push
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消退票'
					});
				});

			}, //deleteTickets的结束

		},//methods的结束

	}//export default的结束

</script>
