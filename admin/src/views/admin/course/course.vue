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
		
		<div class="row">
			<div v-for="course in courseList" class="col-md-2">
				<div class="thumbnail search-thumbnail">
					<img v-show="!course.image" class="media-object" src="/static/img/demoPic.jpg" />
					<img v-show="course.image" class="media-object" v-bind:src="course.image" />
					<div class="caption">
						<div class="clearfix">
							<span class="pull-right label label-primary info-label">
								{{COURSE_LEVEL | optionKV(course.level)}}
							</span>
							<span class="pull-right label label-primary info-label">
								{{COURSE_CHARGE | optionKV(course.charge)}}
							</span>
							<span class="pull-right label label-primary info-label">
								{{COURSE_STATUS | optionKV(course.status)}}
							</span>
						</div>
						<h3 class="search-title">
							<a href="#" class="blue">{{course.name}}</a>
						</h3>
						<p>
							<span class="blue bolder bigger-150">{{course.price}}&nbsp;<i class="fa fa-rmb"></i></span>
						</p>
						<p>{{course.summary}}</p>
						<p>
							<span class="badge badge-info">{{course.id}}</span>
							<span class="badge badge-info">排序:{{course.sort}}</span>
							<span class="badge badge-info">时长:{{course.time}}</span>
						</p>
						<p>
							<!-- 章节 -->
							<button class="btn btn-primary btn-xs btn-info btn-round" @click="toChapter(course)">
								章节
							</button>
							&nbsp;
							<!-- 编辑按钮 -->
							<button class="btn btn-white btn-xs btn-info btn-round" @click="editCourse(course)">
								编辑
							</button>
							<!-- 删除按钮 -->
							<button class="btn btn-white btn-xs btn-warning btn-round" @click="deleteCourse(course)">
								删除
							</button>
						</p>
					</div>
				</div>
			</div>
		</div>
		

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

			},
			//跳转到章节列表页
			toChapter(course){
				let _this = this;
				SessionStorage.set("course", course);
				_this.$router.push("/business/chapter");
			}
		}
	}

</script>

<style scoped>
	.caption h3{
		font-size:20px;
	}
</style>
