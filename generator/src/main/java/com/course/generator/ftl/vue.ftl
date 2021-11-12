<template>
	<div>
		<p>
			<button class="btn btn-success" @click="edit${Domain}(null)">
				<i class="ace-icon fa fa-search"></i>
				新增
			</button>
			&nbsp;
			<button class="btn btn-primary" @click="get${Domain}List(1)">
				<i class="ace-icon fa fa-search"></i>
				查询
			</button>
		</p>
		<table id="simple-table" class="table  table-bordered table-hover">
			<thead>
			<tr>
                <#list fieldList as field>
					<th>${field.nameCn}</th>
                </#list>
				<th>操作</th>
			</tr>
			</thead>

			<tbody>
			<tr v-for="${domain} in ${domain}List">
                <#list fieldList as field>
					<td>{{${domain}.${field.nameHump}}}</td>
                </#list>


				<td>
					<div class="hidden-sm hidden-xs btn-group">
						<!-- 编辑按钮 -->
						<button class="btn btn-xs btn-info" @click="edit${Domain}(${domain})">
							<i class="ace-icon fa fa-pencil bigger-120"></i>
						</button>
						<!-- 删除按钮 -->
						<button class="btn btn-xs btn-danger" @click="delete${Domain}(${domain})">
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
		<pagination ref="pagination" v-bind:list="get${Domain}List"></pagination>

		<${domain}Edit ref="${domain}Edit"></${domain}Edit>

	</div>

</template>

<script>

	import Pagination from "../../../components/pagination.vue"

	import ${Domain}Edit from "./${domain}Edit.vue"

	//import { delete${Domain} } from "../../../api/${domain}/${domain}.js"

	export default {
		components: {Pagination,${Domain}Edit},
		name: '${domain}',
		data(){
			return{
				${domain}List:[],
				modalTitle: '',  //框体名称
				id:''  //ID
			}
		},
		mounted:function(){
			let _this = this;
			console.log("mounted");
			_this.$refs.pagination.size = 10;
			_this.get${Domain}List(1);
		},
		methods:{
			//编辑按钮显示模态框
			edit${Domain}(${domain}){
				let _this = this;
				if(${domain} != null){
					_this.id = ${domain}.id;
					_this.modalTitle = '编辑课程';
					_this.get${Domain}(${domain}.id);
				}else{
					_this.$refs.${domain}Edit.${domain} = {};
					_this.id = '';
					_this.modalTitle = '新增课程';
				}
				$(".modal").modal("show");
			},
			//获取课程列表
			get${Domain}List(page){
				Loading.show();
				let _this = this;
				let params = {
					page:page,
					size:_this. $refs.pagination.size
				}
				_this.$ajax.get(process.env.VUE_APP_SERVER +'/${module}/admin/${domain}/get${Domain}List',{params}).then((response)=>{
					Loading.hide();
					_this.${domain}List = response.data.list;
					_this.$refs.pagination.render(page,response.data.total);
				})
			},
			//获取课程信息
			get${Domain}(id){
				let _this = this;
				let params = {
					id:id,
				}
				_this.$ajax.get(process.env.VUE_APP_SERVER + '/${module}/admin/${domain}/get${Domain}',{params}).then((response)=>{
					let data = response.data;
					_this.$refs.${domain}Edit.courseId = data.data.courseId;
					_this.$refs.${domain}Edit.name = data	.data.name;
				})
			},
			//删除
			delete${Domain}(${domain}){
				let _this = this;
				Comfirm.show("删除成功后不可恢复!",function(){
					Loading.show();
					let params = {
						id:${domain}.id
					}
					_this.$ajax(process.env.VUE_APP_SERVER + '/${module}/admin/${domain}/delete${Domain}',{params}).then((response)=>{
						if(response != null && response.data.code == 100){
							Loading.hide();
							_this.get${Domain}List(1);
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
