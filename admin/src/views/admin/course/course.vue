<template>
	<div>
		<p>
			<button class="btn btn-success" @click="editCourse(null)">
				<i class="ace-icon fa fa-search"></i>
				新增
			</button>
			&nbsp;
			<button class="btn btn-primary" @click="getCourseList(1)">
				<i class="ace-icon fa fa-search"></i>
				查询
			</button>
		</p>
		<table id="simple-table" class="table  table-bordered table-hover">
			<thead>
			<tr>
					<th>ID</th>
					<th>名称</th>
					<th>概述</th>
					<th>时长</th>
					<th>价格(元)</th>
					<th>封面</th>
					<th>级别</th>
					<th>收费</th>
					<th>状态</th>
					<th>报名数</th>
					<th>顺序</th>
					<th>创建时间</th>
					<th>更新时间</th>
				<th>操作</th>
			</tr>
			</thead>

			<tbody>
			<tr v-for="course in courseList">
					<td>{{course.id}}</td>
					<td>{{course.name}}</td>
					<td>{{course.summary}}</td>
					<td>{{course.time}}</td>
					<td>{{course.price}}</td>
					<td>{{course.image}}</td>
					<td>{{COURSE_LEVEL | optionKV(course.level)}}</td>
					<td>{{COURSE_CHARGE | optionKV(course.charge)}}</td>
					<td>{{COURSE_STATUS | optionKV(course.status)}}</td>
					<td>{{course.enroll}}</td>
					<td>{{course.sort}}</td>
					<td>{{course.createdTime}}</td>
					<td>{{course.updatedTime}}</td>


				<td>
					<div class="hidden-sm hidden-xs btn-group">
						<!-- 编辑按钮 -->
						<button class="btn btn-xs btn-info" @click="editCourse(course)">
							<i class="ace-icon fa fa-pencil bigger-120"></i>
						</button>
						<!-- 删除按钮 -->
						<button class="btn btn-xs btn-danger" @click="deleteCourse(course)">
							<i class="ace-icon fa fa-trash-o bigger-120"></i>
						</button>

					</div>

					<div class="hidden-md hidden-lg">
						<div class="inline pos-rel">
							<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
								<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
							</button>

							<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
								<li>
									<a href="#" class="tooltip-info" data-rel="tooltip" title="View">
											<span class="blue">
												<i class="ace-icon fa fa-search-plus bigger-120"></i>
											</span>
									</a>
								</li>

								<li>
									<a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
											<span class="green">
												<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
											</span>
									</a>
								</li>

								<li>
									<a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
											<span class="red">
												<i class="ace-icon fa fa-trash-o bigger-120"></i>
											</span>
									</a>
								</li>
							</ul>
						</div>
					</div>
				</td>
			</tr>
			</tbody>
		</table>

		<!-- 模态框   信息删除确认 -->
		<!-- 		<div class="modal fade" id="delcfmOverhaul">
					<div class="modal-dialog">
						<div class="modal-content message_align">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">×</span>
								</button>
								<h4 class="modal-title">提示</h4>
							</div>
							<div class="modal-body"> sss-->
		<pagination ref="pagination" v-bind:list="getCourseList"></pagination>

		<courseEdit ref="courseEdit"></courseEdit>

	</div>

</template>

<script>

	import Pagination from "../../../components/pagination.vue"

	import CourseEdit from "./courseEdit.vue"

	//import { deleteCourse } from "../../../api/course/course.js"

	export default {
		components: {Pagination,CourseEdit},
		name: 'course',
		data(){
			return{
				courseList:[],
				modalTitle: '',  //框体名称
				id:'',  //ID
				COURSE_CHARGE:COURSE_CHARGE,
				COURSE_LEVEL:COURSE_LEVEL,
				COURSE_STATUS:COURSE_STATUS,
				optionType:'' //编辑操作
			}
		},
		mounted:function(){
			let _this = this;
			console.log("mounted");
			_this.$refs.pagination.size = 10;
			_this.getCourseList(1);
		},
		methods:{
			//编辑按钮显示模态框
			editCourse(course){
				let _this = this;
				if(course != null){
					_this.id = course.id;
					_this.optionType = 'edit';
					_this.modalTitle = '编辑课程';
					_this.getCourse(course.id);
				}else{
					_this.$refs.courseEdit.course = {};
					_this.optionType = 'add';
					_this.id = '';
					_this.modalTitle = '新增课程';
				}
				$(".modal").modal("show");
			},
			//获取课程列表
			getCourseList(page){
				Loading.show();
				let _this = this;
				let params = {
					page:page,
					size:_this. $refs.pagination.size
				}
				_this.$ajax.get(process.env.VUE_APP_SERVER +'/business/admin/course/getCourseList',{params}).then((response)=>{
					Loading.hide();
					_this.courseList = response.data.list;
					_this.$refs.pagination.render(page,response.data.total);
				})
			},
			//获取课程信息
			getCourse(id){
				let _this = this;
				let params = {
					id:id,
				}
				_this.$ajax.get(process.env.VUE_APP_SERVER + '/business/admin/course/getCourse',{params}).then((response)=>{
					let data = response.data;
					_this.$refs.courseEdit.course = data.data;
				})
			},
			//删除
			deleteCourse(course){
				let _this = this;
				Comfirm.show("删除成功后不可恢复!",function(){
					Loading.show();
					let params = {
						id:course.id
					}
					_this.$ajax(process.env.VUE_APP_SERVER + '/business/admin/course/deleteCourse',{params}).then((response)=>{
						if(response != null && response.data.code == 100){
							Loading.hide();
							_this.getCourseList(1);
							Toast.success("删除成功")
						}else{
							Toast.error("删除失败");
						}
					})
				})

			}
		}
	}

</script>

<style>
</style>
