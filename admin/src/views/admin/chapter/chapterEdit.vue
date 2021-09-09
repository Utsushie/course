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
							<label class="col-sm-2 control-label">课程ID</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" v-model="courseId">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">名称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" v-model="name">
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
					<button type="button" class="btn btn-primary" @click="saveChapter()">保存</button>
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
				courseId: '',  //课程ID
				name:''  //名称
			}
		},
		mounted() {
			
		},
		methods:{
			//保存课程信息
			saveChapter(){
				let _this = this;
				/* if(!Validator.require(_this.courseId,"课程ID")
				 || !Validator.require(_this.name,"名称")
				 || !Validator.length(_this.courseId,"课程ID",1,8)){
					return;
				} */
				let params = {
					id : this.$parent.id,
					courseId : _this.courseId,
					name : _this.name
				}
				Loading.show();
				 _this.$parent.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/saveChapter',params).then((response)=>{
					console.log(response);
					Loading.hide();
					if(response != null){
						if(response.data.code == 100){
							$.blockUI();
							Toast.success(response.data.msg)
							_this.courseId = '';
							_this.name = '';
							$(".modal").modal("hide");
							//刷新列表页
							_this.$parent.getChapterList(1);
						}
					}else{
						alert("接口请求失败")
					}
				})
			}
		}
	}
</script>

<style>
</style>
