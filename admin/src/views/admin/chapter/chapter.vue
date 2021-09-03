<template>
	<div>
		<p>
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
							<button class="btn btn-xs btn-success">
								<i class="ace-icon fa fa-check bigger-120"></i>
							</button>
		
							<button class="btn btn-xs btn-info" @click="editChapter(chapter)">
								<i class="ace-icon fa fa-pencil bigger-120"></i>
							</button>
		
							<button class="btn btn-xs btn-danger">
								<i class="ace-icon fa fa-trash-o bigger-120"></i>
							</button>
		
							<button class="btn btn-xs btn-warning">
								<i class="ace-icon fa fa-flag bigger-120"></i>
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
		<pagination ref="pagination" v-bind:list="getChapterList"></pagination>
		
		<ChapterEidt></ChapterEidt>
		
	</div>
	
</template>

<script>
	
	import Pagination from "../../../components/pagination.vue"
	
	import ChapterEidt from "./chapterEdit.vue"
	
	export default {
		components: {Pagination,ChapterEidt},
		name: 'chapter',
		data(){
			return{
				chapterList:[],
				modalTitle: '',  //框体名称
				id:''  //ID
			}
		},
		mounted:function(){
			let _this = this;
			_this.$refs.pagination.size = 10;
			_this.getChapterList(1);
		},
		methods:{
			//编辑按钮显示模态框
			editChapter(chapter){
				let _this = this;
				if(chapter != null){
					_this.id = chapter.id;
					_this.modalTitle = '编辑课程';
					_this.getChapter(chapter.id); 
				}else{
					_this.id = '';
					_this.modalTitle = '新增课程';
				}
				$(".modal").modal("show");
			},
			//获取课程列表
			getChapterList(page){
				let _this = this;
				let params = {
						page:page,
						size:_this.$refs.pagination.size
					}
				_this.$ajax.get('http://127.0.0.1:9000/business/admin/chapter/getChapterList',{params}).then((response)=>{
					console.log("getChapterList接口返回结果",response);
					_this.chapterList = response.data.list;
					_this.$refs.pagination.render(page,response.data.total);
				})
			},
			//获取课程信息
			getChapter(id){
				let _this = this;
				let params = {
						id:id,
					}
					console.log(_this.$refs.chapterEidt);
					console.log(_this.$refs.chapterEidt.courseId);
					_this.$ajax.get('http://127.0.0.1:9000/business/admin/chapter/getChapter',{params}).then((response)=>{
						console.log("getChapter接口返回结果",response);
						let data = response.data;
						_this.$refs.chapterEidt.courseId = data.data.courseId;
						_this.$refs.chapterEidt.name = data	.data.name;
					})
			}
		}
	}
</script>

<style>
</style>
