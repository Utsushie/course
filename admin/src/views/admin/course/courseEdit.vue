<template>
	<div class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="margin-top: 25%;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">{{this.$parent.modalTitle}}</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">ID</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" v-model="course.id">
							</div>
						</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">名称</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" v-model="course.name">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">概述</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" v-model="course.summary">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">时长</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" v-model="course.time">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">价格(元)</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" v-model="course.price">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">封面</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" v-model="course.image">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">级别</label>
								<div class="col-sm-10">
									<select class="form-control" v-model="course.level">
										<option v-for="level in COURSE_LEVEL" v-bind:value="level.key">{{level.value}}</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">收费</label>
								<div class="col-sm-10">
									<select class="form-control" v-model="course.charge">
										<option v-for="charge in COURSE_CHARGE" v-bind:value="charge.key">{{charge.value}}</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">状态</label>
								<div class="col-sm-10">
									<select class="form-control" v-model="course.status">
										<option v-for="status in COURSE_STATUS" v-bind:value="status.key">{{status.value}}</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">报名数</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" v-model="course.enroll">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">顺序</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" v-model="course.sort">
								</div>
							</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" @click="saveCourse()">保存</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
</template>

<script>

	export default{
		name: 'courseEdit',
		props:{
			list:{
				type:Function,
				default: null
			}
		},
		data(){
			return{
				course:{
					id:'',
					name:'',
					summary:'',
					time:'',
					price:'',
					image:'',
					level:'',
					charge:'',
					status:'',
					enroll:'',
					sort:'',
					optionType:''
				},
				COURSE_CHARGE:COURSE_CHARGE,
				COURSE_LEVEL:COURSE_LEVEL,
				COURSE_STATUS:COURSE_STATUS
			}
		},
		mounted() {

		},
		methods:{
			//保存课程信息
			saveCourse(){
				let _this = this;

				/*if(!Validator.require(_this.courseId,"课程ID")
					|| !Validator.require(_this.name,"名称")
					|| !Validator.length(_this.courseId,"课程ID",1,8)){
					return;
				}*/
				let params = _this.course;
				params.optionType = _this.$parent.optionType;
				Loading.show();
				_this.$parent.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/saveCourse',params).then((response)=>{
					console.log(response);
					Loading.hide();
					if(response != null){
						if(response.data.code == 100){
							$.blockUI();
							Toast.success(response.data.msg)
							_this.course = {};
							$(".modal").modal("hide");
							//刷新列表页
							_this.$parent.getCourseList(1);
						}else{
							Toast.warning(response.data.msg)
						}
					}else{
						Toast.error("接口请求失败！")
					}
				})
			}
		}
	}
</script>

<style>
</style>
