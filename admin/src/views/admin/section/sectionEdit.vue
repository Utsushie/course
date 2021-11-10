<template>
	<div class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="margin-top: 35%;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">{{this.$parent.modalTitle}}</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">标题</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" v-model="section.title">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">课程ID</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" v-model="section.courseId">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">章节ID</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" v-model="section.chapterId">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">视频</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" v-model="section.video">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">时长</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" v-model="section.time">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">收费</label>
							<div class="col-sm-10">
								<select v-model="section.charge" class="form-control">
									<option v-for="c in SECTION_CHARGE" v-bind:value="c.key">{{c.value}}</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">顺序</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" v-model="section.sort">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" @click="saveSection()">保存</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
</template>

<script>

	export default{
		name: 'sectionEdit',
		props:{
			list:{
				type:Function,
				default: null
			}
		},
		data(){
			return{
				section:{
					id: '',  //课程ID
					name:'',  //名称
					courseId:'',
					chapterId:'',
					title:'',
					video:'',
					time:'',
					charge:'',
					sort:'',
					isDel:'',
					createdTime:'',
					updatedTime:'',
					createdBy:'',
					updatedBy:''
				},
				SECTION_CHARGE:SECTION_CHARGE //收费枚举
			}
		},
		mounted() {

		},
		methods:{
			//保存课程信息
			saveSection(){
				let _this = this;

				/*if(!Validator.require(_this.courseId,"课程ID")
					|| !Validator.require(_this.name,"名称")
					|| !Validator.length(_this.courseId,"课程ID",1,8)){
					return;
				}*/
				console.log("保存课程信息");
				console.log(_this.section);
				let params = _this.section;
				console.log(this.params);
				Loading.show();
				_this.$parent.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/section/saveSection',params).then((response)=>{
					console.log(response);
					Loading.hide();
					if(response != null){
						if(response.data.code == 100){
							$.blockUI();
							Toast.success(response.data.msg)
							_this.section = {};
							$(".modal").modal("hide");
							//刷新列表页
							_this.$parent.getSectionList(1);
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
