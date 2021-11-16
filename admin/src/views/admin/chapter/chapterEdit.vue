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
							<label class="col-sm-2 control-label">名称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" v-model="chapter.name">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">课程</label>
							<div class="col-sm-10">
								<p class="form-control-static">{{course.name}}</p>
							</div>
						</div>
						<!-- <div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
							  <button type="submit" class="btn btn-default">Sign in</button>
							</div>
						</div> -->
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" @click="saveChapter(chapter)">保存</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
</template>

<script>
	
	export default{
		name: 'chapterEdit',
		props:{
			list:{
				type:Function,
				default: null
			}
		},
		data(){
			return{
				chapter:{
					id:'',
					courseId: '',  //课程ID
					name:'',  //名称
					optionType:''
				},
				course:{}
			}
		},
		mounted() {
			
		},
		methods:{
			//保存课程信息
			saveChapter(chapter){
				let _this = this;
				_this.chapter.id = this.$parent.id;
				_this.chapter.optionType = _this.$parent.optionType;
				_this.chapter.courseId = _this.course.id;
				if(!Validator.require(chapter.courseId,"课程ID")){
					return;
				}
				let params = _this.chapter;
				Loading.show();
				 _this.$parent.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/saveChapter',params).then((response)=>{
					console.log(response);
				Loading.hide();
						if(response != null){
						if(response.data.code == 100){
							$.blockUI();
							Toast.success(response.data.msg)
							_this.chapter = {};
							$(".modal").modal("hide");
							//刷新列表页
							_this.$parent.getChapterList(1);
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
