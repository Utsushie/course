<template>
	<div>
		<h4 class="lighter">
			<p v-show="JSON.stringify(course) != '{}'">
				<i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue"></i>
				<a href="/business/course" data-toggle="modal" class="pink">{{course.name}}</a>
			</p>
		</h4>
		<hr>
		<!-- <h3>{{course.name}}</h3> -->
		<p>
			<router-link to="/business/course" class="btn btn-info" v-show="JSON.stringify(course) != '{}'">
				<i class="ace-icon fa fa-arrow-left"></i>
				返回课程
			</router-link>
			&nbsp;
			<button class="btn btn-success" @click="editChapter(null)">
				<i class="ace-icon fa fa-search"></i>
				新增
			</button>
			&nbsp;
			<button class="btn btn-primary" @click="getChapterList(1)">
				<i class="ace-icon fa fa-search"></i>
				查询
			</button>
		</p>
		<table id="simple-table" class="table  table-bordered table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>课程ID</th>
					<th>名称</th>
					<th>操作</th>
				</tr>
			</thead>
		
			<tbody>
				<tr v-for="chapter in chapterList">
					<td>{{chapter.id}}</td>
					<td>{{chapter.courseId}}</td>
					<td>{{chapter.name}}</td>
		
					<td>
						<div class="hidden-sm hidden-xs btn-group">
							<!-- 跳转小节页面 -->
							<button class="btn btn-xs btn-primary" @click="toSection(chapter)">
								小节
							</button>
							<!-- 编辑按钮 -->
							<button class="btn btn-xs btn-info" @click="editChapter(chapter)">
								编辑
							</button>
							<!-- 删除按钮 -->
							<button class="btn btn-xs btn-danger" @click="deleteChapter(chapter)">
								删除
							</button>
		
						</div>
		
						<!-- <div class="hidden-md hidden-lg">
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
					 -->
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
		<pagination ref="pagination" v-bind:list="getChapterList"></pagination>
		
		<chapterEdit ref="chapterEdit"></chapterEdit>
		
	</div>
	
</template>

<script>
	
	import Pagination from "../../../components/pagination.vue"
	
	import ChapterEdit from "./chapterEdit.vue"
	
	//import { deleteChapter } from "../../../api/chapter/chapter.js"
	
	export default {
		components: {Pagination,ChapterEdit},
		name: 'chapter',
		data(){
			return{
				chapterList:[],
				modalTitle: '',  //框体名称
				id:'',  //ID
				optionType:'', //编辑操作
				course:{}
			}
		},
		mounted:function(){
			let _this = this;
			_this.$refs.pagination.size = 10;
			let course = SessionStorage.get("course") || {};
			/* if(Tool.isEmpty(course)){
				_this.$router.push("/welcome");
			} */
			_this.course = course;
			_this.getChapterList(1);
		},
		methods:{
			//编辑按钮显示模态框
			editChapter(chapter){
				let _this = this;
				console.log(_this.course);
				_this.$refs.chapterEdit.course = _this.course;
				if(chapter != null){
					_this.id = chapter.id;
					_this.optionType = 'edit';
					_this.modalTitle = '编辑课程';
					_this.getChapter(chapter.id); 
				}else{
					_this.$refs.chapterEdit.chapter = {};
					_this.optionType = 'add';
					_this.id = '';
					_this.modalTitle = '新增课程';
				}
				$(".modal").modal("show");
			},
			//获取课程列表
			getChapterList(page){
				Loading.show();
				let _this = this;
				let params = {
						page:page,
						size:_this.$refs.pagination.size,
						courseId:_this.course.id
					}
				_this.$ajax.get(process.env.VUE_APP_SERVER +'/business/admin/chapter/getChapterList',{params}).then((response)=>{
					Loading.hide();
					_this.chapterList = response.data.list;
					this.$refs.pagination.render(page,response.data.total);
				})
			},
			//获取课程信息
			getChapter(id){
				let _this = this;
				let params = {
						id:id,
					}
					_this.$ajax.get(process.env.VUE_APP_SERVER + '/business/admin/chapter/getChapter',{params}).then((response)=>{
						let data = response.data;
						_this.$refs.chapterEdit.chapter = data.data;
					})
			},
			//删除
			deleteChapter(chapter){
				let _this = this;
				Comfirm.show("删除成功后不可恢复!",function(){
					Loading.show();
					let params = {
						id:chapter.id
					}
					_this.$ajax(process.env.VUE_APP_SERVER + '/business/admin/chapter/deleteChapter',{params}).then((response)=>{
						if(response != null && response.data.code == 100){
							Loading.hide();
							_this.getChapterList(1);
							Toast.success("删除成功")
						}else{
							Toast.error("删除失败");
						}
					})
				})
				
			},
			//跳转到章节列表页
			toSection(chapter){
				let _this = this;
				SessionStorage.set("chapter", chapter);
				_this.$router.push("/business/section");
			}
		}
	}
	
</script>

<style>
</style>
