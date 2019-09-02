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

		<!--//下拉框的选择测试-->
		<el-select v-model="value" placeholder="请选择用户" @change="changeUserId">
			<el-option
					v-for="item in options"
					:key="item.id"
					:label="item.username"
					:value="item.id">
			</el-option>
	    </el-select>


		<!--列表-->
		<el-table :key='tableKey' :data="list.slice((currentPage-1)*pagesize,currentPage*pagesize)"  highlight-current-row v-loading="listLoading" style="width: 100%;">
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

			<el-table-column prop="number" label="用户数 端口" width="250" sortable>
				<template scope="scope">
					<span>{{scope.row.user}}</span>
				</template>
			</el-table-column>

			<!--<el-table-column prop="capacity" label="容量" width="90" sortable>-->
				<!--<template scope="scope">-->
					<!--<span>{{scope.row.capacity}}</span>-->
				<!--</template>-->
			<!--</el-table-column>-->
			<el-table-column prop="yuPiao" label="余量" width="90" sortable>
				<template scope="scope">
					<span>{{scope.row.yuPiao}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="people" label="人数" width="90" sortable>
				<template scope="scope">
					<span>{{scope.row.people}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="starttime" label="起始时间" width="150" sortable>
				<template scope="scope">
					<span>{{scope.row.starttime}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="endtime" label="结束时间" width="150" sortable>
				<template scope="scope">
					<span>{{scope.row.endtime}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="time" label="总时长" width="150" sortable>
				<template scope="scope">
					<span>{{scope.row.time}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="remark" label="备注" width="90" sortable>
				<template scope="scope">
					<span>{{scope.row.remark}}</span>
				</template>
			</el-table-column>
			<el-table-column label="操作" width="300">
			<template scope="scope">
				<el-button size="small"  @click="handleView(scope.$index, scope.row)">查看</el-button>
				<el-button size="small"  @click="orderTickets(scope.$index, scope.row)">模拟订票</el-button>
                <el-button size="small"  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button type="danger" size="small"  @click="handleDelete(scope.row)">删除</el-button>
			</template>
			</el-table-column>
		</el-table>

		<!--//编辑与新增航班-->
		<el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
			<el-form :model="form" ref="form" label-width="100px">
				<el-form-item label="航班号" prop="number">
					<el-input v-if="dialogStatus == 'create'" v-model="form.number" placeholder="请输入航班号"></el-input>
					<el-input v-else v-model="form.number" placeholder="请输入账户"></el-input>
				</el-form-item>
				<el-form-item label="容量" prop="capacity">
					<el-input v-if="dialogStatus == 'create'" v-model="form.capacity" placeholder="请输入容量"></el-input>
					<el-input v-else v-model="form.capacity" placeholder="请输入容量"></el-input>
				</el-form-item>
				<el-form-item label="人数" prop="people">
					<el-input v-if="dialogStatus == 'create'" v-model="form.people" placeholder="请输入人数"></el-input>
					<el-input v-else v-model="form.people" placeholder="请输入人数"></el-input>
				</el-form-item>
				<el-form-item label="起始时间" prop="starttime">
					<el-input v-if="dialogStatus == 'create'" v-model="form.starttime" placeholder="请输入起始时间"></el-input>
					<el-input v-else v-model="form.starttime" placeholder="请输入起始时间"></el-input>
				</el-form-item>
				<el-form-item label="结束时间" prop="create">
					<el-input v-if="dialogStatus == 'addFight'" v-model="form.endtime" placeholder="请输入结束时间"></el-input>
					<el-input v-else v-model="form.endtime" placeholder="请输入结束时间"></el-input>
				</el-form-item>
				<el-form-item label="总时长" prop="time">
					<el-input v-if="dialogStatus == 'create'" v-model="form.time" placeholder="总时长"></el-input>
					<el-input v-else v-model="form.time" placeholder="请输入总时长"></el-input>
				</el-form-item>
				<el-form-item label="备注" prop="remark">
					<el-input v-if="dialogStatus == 'create'" v-model="form.remark" placeholder="请输入备注"></el-input>
					<el-input v-else v-model="form.remark" placeholder="请输入备注"></el-input>
				</el-form-item>

			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="cancel('form')">取 消</el-button>
				<el-button v-if="dialogStatus=='create'" type="primary" @click="addFight()">确 定</el-button>
				<el-button v-else type="primary" @click="updateFight()">确 定</el-button>
			</div>
		</el-dialog>

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
    // import { mapGetters } from '../../../node_modules/vuex/types/helpers.d.ts';
	//导入航班的增删改查接口
	import {getFightListPage,getFightId,addFight,updateFight,deleteFight } from '../../api/flight';
    import {getUserListPage} from '../../api/user';
	import {getOrderListPage} from '../../api/order'; //导入订单列表

	export default {

		number: 'number',
		data() {
			return {
				filters: {
					number: ''
				},
				options: '',
				value:'',
				//航班列表
				form: {
					number: '',
					capacity: '',
					people: '',
					starttime: '',
					endtime: '',
					time:'',
					remark:''
				},

				formUser: {
					username: '',
					card: '',
					birthday: '',
					age: '',
					sex: '',
					adress:'',
				},
				orderForm:{
					id:'',
					fightId:'',
					hangBanCapa:'',
					bookTicketsTime:''
				},
                Fight_btn_edit: false,
                Fight_btn_add: false,
                Fight_btn_del:false,

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
                userId:null,
				nowTime:'',
				yuPiaoAll:'',
				hangBanAll:'',
				orderForId:''

			}
		},

		//请求后端数据
		created() {
			this.getFightListPage();
			this.getUserListPage();
			this.getOrderListPage();
		},

		//方法
		methods:{

			//获取所有的订单信息
			getOrderListPage(){
				this.listLoading = true;
				getOrderListPage()
						.then(response => {
							var orderForView = [];
							for(let i = 0;i<response.length;i++){
								orderForView.push(response[i].fightId)
							}
							this.orderForId = orderForView
							console.log(orderForView)
						})

			},

		    //查询到所有用户push到下拉框
            getUserListPage(){
                this.listLoading = true;
                getUserListPage()
                    .then(response => {
                        this.options = response;
                    })
            },

			//获取下拉框切换的id值
			changeUserId(val){
				let obj = {};
				obj = this.options.find((item)=>{
					return item.id === val;
				});
				this.options.id = obj.id; //获取id信息
				this.userId = obj.id;
			},

			orderTickets(index,row){
            	var viewForIds = this.orderForId.indexOf(row.fightId);

				if(viewForIds != -1){

					if(this.userId != null){
						//请求后台订票的方法进行订票测试
						var userId = this.userId;
						var fightId = row.fightId;

						//vue获取当前的日期
						let now = new Date(); //当前日期
						let nowDay = now.getDate(); //当前日
						let nowMonth = now.getMonth(); //当前月
						let nowYear = now.getFullYear(); //当前年
						let nowHours = now.getHours(); //获取系统时，
						let nowMinutes = now.getMinutes(); //分
						let nowSeconds = now.getSeconds(); //秒

						//获取当前时间
						this.nowTime = nowYear + "-" +  nowMonth + "-" + nowDay + " " + nowHours + ":" + nowMinutes + ":" + nowSeconds;

						this.$confirm('确认此用户订票吗？', '确认信息', {
							distinguishCancelAndClose: true,
							confirmButtonText: '确认',
							cancelButtonText: '取消'
						})
								.then(() => {
									//如果无余票则去航班列表查询容量，根据容量去增减
									//首先判断订单列表是否存在当前的fightId,
									// 如果存在则去订单列表查询余票，若不存在则在航班列表查询容量减余票

									//去查询余票
									fetch('/api/order/yuPiao/'+ fightId , {
										method: 'POST',
										headers: {
											'Accept': 'application/json',
											'Content-Type': 'application/json'
										},
									}).then(res => res.json()).then(res => {  //如果余票相同会报错，需要处理(要根据时间去判断)
	                                    console.log(res)
										console.log(userId)
										console.log(fightId)
										this.orderForm.hangBanCapa = res - 1;
										this.orderForm.bookTicketsTime = this.nowTime;
										fetch('/api/order/insertUserAndFight/'+ userId  +'/' + fightId, {
											method: 'POST',
											headers: {
												'Accept': 'application/json',
												'Content-Type': 'application/json'
											},
											body: JSON.stringify(this.orderForm)
										}).then(res => res.json()).then(res => {
											this.$message({
												type: 'info',
												message: '订票成功',
											});
										}) //fetch插入接口的结束
									})

								})
								.catch(action => {
									this.$message({
										type: 'info',
										message: action === 'cancel'
												? '取消'
												: '停留在当前页面'
									})
								}); //this.$confirm的结束

					}else{  //this.userId != null中的if-else结束
						this.$notify({
							title: '警告',
							message: '请选择要订票的用户',
							type: 'warning'
						});
					}

				}else{  //判断-1的if-else的结束

					this.$notify({
						title: '警告',
						message: '此航班已无票',
						type: 'warning'
					});

				}

			},

			//实现模拟订票业务test
			getFightListPage(){
				this.listLoading = true;
				getFightListPage()
						.then(response => {
							this.hangBanAll = response;
							//列表渲染
							//查询所有的订票信息，是为了展示余量
							fetch('/api/order/allOrders', {
								method: 'POST',
								headers: {
									'Accept': 'application/json',
									'Content-Type': 'application/json'
								},
							}).then(res => res.json()).then(res => {
								let yuPiaoAll = res;
								let hangBanAll = this.hangBanAll;
								for (let i = 0; i < hangBanAll.length; i++) {
									for (let j = 0; j < yuPiaoAll.length; j++) {
										if (hangBanAll[i].fightId==yuPiaoAll[j].fightId){
											console.log(hangBanAll[i].fightId,yuPiaoAll[j].fightId,yuPiaoAll[j].hangBanCapa);
											hangBanAll[i].yuPiao = yuPiaoAll[j].hangBanCapa
											continue
										}
									}//j循环结束
								} //i循环结束
								console.log(hangBanAll)
								//列表渲染
								this.list = hangBanAll;
								this.listLoading = false;
							})

						})//then结束

			},

			//显示增加界面
			handleCreate(){
				this.form;
				this.dialogStatus = 'create';
				this.dialogFormVisible = true;
				console.log(this.form)

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
            addFight(){
				fetch('/api/flight/hangBan/addFight', {
					method: 'POST',
					headers: {
						'Accept': 'application/json',
						'Content-Type': 'application/json'
					},
					body: JSON.stringify(this.form)
				}).then((res) => {

					this.dialogFormVisible = false;
					this.getFightListPage();

				})
			},

			//显示编辑更新界面
			handleEdit(index, row) {
				this.form = row;
				this.dialogFormVisible = true;
				this.dialogStatus = 'update';
			},
			
			//更新数据
			updateFight(form) {
				console.log(form)
				this.dialogFormVisible = false;
				fetch('/api/flight/hangBan/updateFight', {
					method: 'POST',
					headers: {
						'Accept': 'application/json',
						'Content-Type': 'application/json'
					},
					body: JSON.stringify(this.form)
				}).then((res) => {
					this.getFightListPage()
					console.log(res);
				})
			},

			//删除数据
            handleDelete: function (form) {
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					this.listLoading = true;
					let para = form.fightId;
					fetch('/api/flight/hangBan/deleteFight/' + para , {
						method: 'post',
					}).then((res) => {
						this.getFightListPage();
					})
				});
				this.getFightListPage();
			},

			//查看接口
			handleView(index,row){
				var id = row.fightId;
				this.$router.push({
					path: '/viewUser',
					query: {
						id: id
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
			}

		},//methods的结束

	}//export default的结束

</script>

<style scoped>

    .el-dropdown-link {
        cursor: pointer;
        color: #409EFF;
    }
    .el-icon-arrow-down {
        font-size: 12px;
    }

</style>