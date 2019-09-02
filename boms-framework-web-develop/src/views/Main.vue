<template>
	<section>
		<el-tabs>
			<el-tab-pane label="用户管理" name="first">用户管理</el-tab-pane>
			<el-tab-pane label="航班管理" name="second">航班管理</el-tab-pane>
			<el-tab-pane label="订单管理" name="third">订单管理</el-tab-pane>
		</el-tabs>

		<!--搜索列表-->
		<el-form ref="form" :model="form" label-width="80px">
			<!--<el-form-item label="出发地">-->
				<!--<el-input></el-input>-->
			<!--</el-form-item>-->
			<!--<el-form-item label="到达地">-->
				<!--<el-input></el-input>-->
			<!--</el-form-item>-->
			<el-form-item label="起点">
				<el-select v-model="value_start" placeholder="出发城市">
					<el-option-group
							v-for="group in options_start"
							:key="group.label_start"
							:label="group.label_start">
						<el-option
								v-for="item in group.options_start"
								:key="item.value_start"
								:label="item.label_start"
								:value="item.value_start">
						</el-option>
					</el-option-group>
				</el-select>
			</el-form-item>

			<el-form-item label="终点">
				<el-select v-model="value_end" placeholder="到达城市">
				<el-option-group
						v-for="group in options_end"
						:key="group.label_end"
						:label="group.label_end">
					<el-option
							v-for="item in group.options_end"
							:key="item.value_end"
							:label="item.label_end"
							:value="item.value_end">
					</el-option>
				</el-option-group>
			  </el-select>
			</el-form-item>

			<el-form-item label="出发时间">
				<el-col :span="11">
					<el-date-picker type="date" placeholder="选择日期" v-model="form_one.date1" style="width: 100%;"></el-date-picker>
				</el-col>
				<!--<el-col class="line" :span="2">-</el-col>-->
				<!--<el-col :span="11">-->
					<!--<el-time-picker placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>-->
				<!--</el-col>-->
			</el-form-item>

			<el-form-item label="返程时间">
				<el-col :span="11">
					<el-date-picker type="date" placeholder="选择日期" v-model="form_two.date1" style="width: 100%;"></el-date-picker>
				</el-col>
				<!--<el-col class="line" :span="2">-</el-col>-->
				<!--<el-col :span="11">-->
					<!--<el-time-picker placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>-->
				<!--</el-col>-->
			</el-form-item>

			<!--<el-form-item>-->
				<!--<el-radio-group v-model="form.resource">-->
					<!--<el-radio label="学生"></el-radio>-->
					<!--<el-radio label="机票"></el-radio>-->
				<!--</el-radio-group>-->
			<!--</el-form-item>-->
			<el-form-item>
				<el-button type="primary" @click="onSubmit">搜索</el-button>
			</el-form-item>
		</el-form>
	</section>
</template>

<script>
	export default {
		data() {
			return {
				options_start: [{
					label_start: '热门城市',
					options_start: [{
						value_start: 'Shanghai',
						label_start: '上海'
					}, {
						value_start: 'Beijing',
						label_start: '北京'
					}]
				}, {
					label: '城市名',
					options_start: [{
						value_start: 'Chengdu',
						label_start: '成都'
					}, {
						value_start: 'Shenzhen',
						label_start: '深圳'
					}, {
						value_start: 'Guangzhou',
						label_start: '广州'
					}, {
						value_start: 'Dalian',
						label_start: '大连'
					}]
				}],
				options_end: [{
					label_end: '热门城市',
					options_end: [{
						value_end: 'Shanghai',
						label_end: '上海'
					}, {
						value_end: 'Beijing',
						label_end: '北京'
					}]
				}, {
					label_end: '城市名',
					options_end: [{
						value_end: 'Chengdu',
						label_end: '成都'
					}, {
						value_end: 'Shenzhen',
						label_end: '深圳'
					}, {
						value_end: 'Guangzhou',
						label_end: '广州'
					}, {
						value_end: 'Dalian',
						label_end: '大连'
					}]
				}],
				value_start: '',
				value_end:'',
				form_one: {
					name: '',
					region: '',
					date1: '',
					date2: '',
					delivery: false,
					type: [],
					resource: '',
					desc: ''
				},
				form_two: {
					name: '',
					region: '',
					date1: '',
					date2: '',
					delivery: false,
					type: [],
					resource: '',
					desc: ''
				},
			}
		},
		methods: {
			onSubmit() {
				this.$router.push({
					path: '/Fight',
				})
			} //搜索提交结束
		} //methods结束
	}

</script>

<style scoped>

</style>