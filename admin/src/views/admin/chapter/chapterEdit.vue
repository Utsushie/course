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
				let params = {
					id : this.$parent.id,
					courseId : _this.courseId,
					name : _this.name
				}
				 _this.$parent.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/saveChapter',params).then((response)=>{
					console.log(response);
					if(response != null){
						if(response.data.code == 100){
							//$('.alert').html(response.data.msg).addClass('alert-success').show().delay(1500).fadeOut();
							success_prompt(response.data.msg,1200);
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
	
	var prompt = function (message, style, time)
	{
	    style = (style === undefined) ? 'alert-success' : style;
	    time = (time === undefined) ? 1200 : time;
	    $('<div>')
	        .appendTo('body')
	        .addClass('alert ' + style)
	        .html(message)
	        .show()
	        .delay(time)
	        .fadeOut();
	};
	
	// 成功提示
	var success_prompt = function(message, time)
	{
	    prompt(message, 'alert-success', time);
	};
	
	// 失败提示
	var fail_prompt = function(message, time)
	{
	    prompt(message, 'alert-danger', time);
	};
	
	// 提醒
	var warning_prompt = function(message, time)
	{
	    prompt(message, 'alert-warning', time);
	};
	
	// 信息提示
	var info_prompt = function(message, time)
	{
	    prompt(message, 'alert-info', time);
	};
	
</script>

<style>
	.alert {
		display: none;
		position: fixed;
		top: 12%;
		left: 50%;
		min-width: 300px;
		max-width: 600px;
		transform: translate(-50%,-50%);
		z-index: 99999;
		text-align: center;
		padding: 15px;
		border-radius: 3px;
	}
	
	.alert-success {
	    color: #3c763d;
	    background-color: #dff0d8;
	    border-color: #d6e9c6;
	}
	
	.alert-info {
	    color: #31708f;
	    background-color: #d9edf7;
	    border-color: #bce8f1;
	}
	
	.alert-warning {
	    color: #8a6d3b;
	    background-color: #fcf8e3;
	    border-color: #faebcc;
	}
	
	.alert-danger {
	    color: #a94442;
	    background-color: #f2dede;
	    border-color: #ebccd1;
	}
</style>
