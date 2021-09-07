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
							<!-- 编辑按钮 -->
							<button class="btn btn-xs btn-info" @click="editChapter(chapter)">
								<i class="ace-icon fa fa-pencil bigger-120"></i>
							</button>
							<!-- 删除按钮 -->
							<button class="btn btn-xs btn-danger" @click="showDeleteModal(chapter)">
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
		<div class="modal fade" id="delcfmOverhaul">
			<div class="modal-dialog">
				<div class="modal-content message_align">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title">提示</h4>
					</div>
					<div class="modal-body">
						<!-- 隐藏需要删除的id -->
						<input type="hidden" id="deleteId" />
						<p>您确认要删除该条信息吗？</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default"
							data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary"
							@click="deleteRecord()">确认</button>
					</div>
				</div>
			</div>
		</div>
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
				id:''  //ID
			}
		},
		mounted:function(){
			let _this = this;
			console.log("mounted");
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
						size:_this. $refs.pagination.size
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
					_this.$ajax.get('http://127.0.0.1:9000/business/admin/chapter/getChapter',{params}).then((response)=>{
						console.log("getChapter接口返回结果",response);
						let data = response.data;
						_this.$refs.chapterEdit.courseId = data.data.courseId;
						_this.$refs.chapterEdit.name = data	.data.name;
					})
			},
			//删除
			deleteRecord(){
				let _this = this;
				console.log($("#deleteId").val());
				let params = {
					id:$("#deleteId").val()
				}
				_this.$ajax('http://127.0.0.1:9000/business/admin/chapter/deleteChapter',{params}).then((response)=>{
					if(response != null && response.data.code == 100){
						success_prompt(response.data.msg,1200);
						$(".modal").modal("hide");
						_this.getChapterList(1);
					}
				})
			},
			// 打开询问是否删除的模态框
			showDeleteModal(chapter) {
			    $("#deleteId").val(chapter.id);// 将模态框中需要删除的大修的ID设为需要删除的ID
			    $("#delcfmOverhaul").modal({
			        backdrop : 'static',
			        keyboard : false
			    });
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
		top: 30%;
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
