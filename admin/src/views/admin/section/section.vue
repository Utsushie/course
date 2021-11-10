<template>
	<div>
		<p>
			<button class="btn btn-success" @click="editSection(null)">
				<i class="ace-icon fa fa-search"></i>
				新增
			</button>
			&nbsp;
			<button class="btn btn-primary" @click="getSectionList(1)">
				<i class="ace-icon fa fa-search"></i>
				查询
			</button>
		</p>
		<table id="simple-table" class="table  table-bordered table-hover">
			<thead>
			<tr>
					<th>ID</th>
					<th>课程ID</th>
					<th>章节ID</th>
					<th>标题</th>
					<th>视频</th>
					<th>时长</th>
					<th>顺序</th>
					<th>创建时间</th>
					<th>更新时间</th>
				<th>操作</th>
			</tr>
			</thead>

			<tbody>
			<tr v-for="section in sectionList">
					<td>{{section.id}}</td>
					<td>{{section.courseId}}</td>
					<td>{{section.chapterId}}</td>
					<td>{{section.title}}</td>
					<td>{{section.video}}</td>
					<td>{{section.time}}</td>
					<td>{{SECTION_CHARGE | optionKV(section.charge)}}</td>
					<td>{{section.sort}}</td>
					<td>{{section.createdTime}}</td>
					<td>{{section.updatedTime}}</td>
				<td>
					<div class="hidden-sm hidden-xs btn-group">
						<!-- 编辑按钮 -->
						<button class="btn btn-xs btn-info" @click="editSection(section)">
							<i class="ace-icon fa fa-pencil bigger-120"></i>
						</button>
						<!-- 删除按钮 -->
						<button class="btn btn-xs btn-danger" @click="deleteSection(section)">
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
		<pagination ref="pagination" v-bind:list="getSectionList"></pagination>

		<sectionEdit ref="sectionEdit"></sectionEdit>

	</div>

</template>

<script>

	import Pagination from "../../../components/pagination.vue"

	import SectionEdit from "./sectionEdit.vue"

	//import { deleteSection } from "../../../api/section/section.js"

	export default {
		components: {Pagination,SectionEdit},
		name: 'sectionInfo',
		data(){
			return{
				sectionList:[],
				modalTitle: '',  //框体名称
				id:'',  //ID
				SECTION_CHARGE:SECTION_CHARGE //收费枚举
			}
		},
		mounted:function(){
			let _this = this;
			console.log("mounted");
			_this.$refs.pagination.size = 10;
			_this.getSectionList(1);
		},
		methods:{
			//编辑按钮显示模态框
			editSection(section){
				let _this = this;
				if(section != null){
					_this.id = section.id;
					_this.modalTitle = '编辑课程';
					_this.getSection(section.id);
				}else{
					_this.$refs.sectionEdit.section = {};
					_this.id = '';
					_this.modalTitle = '新增课程';
				}
				$(".modal").modal("show");
			},
			//获取课程列表
			getSectionList(page){
				Loading.show();
				let _this = this;
				let params = {
					page:page,
					size:_this. $refs.pagination.size
				}
				_this.$ajax.get(process.env.VUE_APP_SERVER +'/business/admin/section/getSectionList',{params}).then((response)=>{
					Loading.hide();
					_this.sectionList = response.data.list;
					_this.$refs.pagination.render(page,response.data.total);
				})
			},
			//获取课程信息
			getSection(id){
				let _this = this;
				let params = {
					id:id,
				}
				_this.$ajax.get(process.env.VUE_APP_SERVER + '/business/admin/section/getSection',{params}).then((response)=>{
					let data = response.data;
					console.log(data);
					_this.$refs.sectionEdit.section = data.data;
				})
			},
			//删除
			deleteSection(section){
				let _this = this;
				Comfirm.show("删除成功后不可恢复!",function(){
					Loading.show();
					let params = {
						id:section.id
					}
					_this.$ajax(process.env.VUE_APP_SERVER + '/business/admin/section/deleteSection',{params}).then((response)=>{
						if(response != null && response.data.code == 100){
							Loading.hide();
							_this.getSectionList(1);
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
