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
                        <#list fieldList as field>
							<#if field.name != "id" && field.nameHump != "createdTime" && field.nameHump != "updatedTime">
								<div class="form-group">
									<label class="col-sm-2 control-label">${field.nameCn}</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" v-model="${domain}.${field.nameHump}">
									</div>
								</div>
							</#if>
                        </#list>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" @click="save${Domain}()">保存</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
</template>

<script>

	export default{
		name: '${domain}Edit',
		props:{
			list:{
				type:Function,
				default: null
			}
		},
		data(){
			return{
				${domain}:{
				<#list fieldList as field>
					${field.nameHump}:'',
				</#list>
				}
			}
		},
		mounted() {

		},
		methods:{
			//保存课程信息
			save${Domain}(){
				let _this = this;

				/*if(!Validator.require(_this.courseId,"课程ID")
					|| !Validator.require(_this.name,"名称")
					|| !Validator.length(_this.courseId,"课程ID",1,8)){
					return;
				}*/
				let params = _this.${domain};
				Loading.show();
				_this.$parent.$ajax.post(process.env.VUE_APP_SERVER + '/${module}/admin/${domain}/save${Domain}',params).then((response)=>{
					console.log(response);
					Loading.hide();
					if(response != null){
						if(response.data.code == 100){
							$.blockUI();
							Toast.success(response.data.msg)
							_this.${domain} = {};
							$(".modal").modal("hide");
							//刷新列表页
							_this.$parent.get${Domain}List(1);
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
