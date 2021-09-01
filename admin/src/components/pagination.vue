<template>
	<div class="pagination" role="group" aria-label="分页">
		<button type="button" class="btn btn-default btn-white btn-round"
			v-bind:disabled="page === 1"
			v-on:click="selectPage(1)">
			1
		</button>
		<button type="button" class="btn btn-default btn-white btn-round"
			v-bind:disabled="page === 1"
			v-on:click="selectPage(page - 1)">
			上一页
		</button>
		<button v-for="p in pages" v-bind:id="'page-' + p"
			type="button" class="btn btn-default btn-white btn-round"
			v-bind:class="{'btn-primary active':page == p}"
			v-on:click="selectPage(p)">
			{{p}}
		</button>
		<button type="button" class="btn btn-default btn-white btn-round"
			v-bind:disabled="page === pageTotal"
			v-on:click="selectPage(page + 1)">
			下一页
		</button>
		<button type="button" class="btn btn-default btn-white btn-round"
			v-bind:disabled="page === pageTotal"
			v-on:click="selectPage(1)">
			1
		</button>
	</div>
</template>

<script>
	export default{
		data(){
			return{
				page: 1,
				pages:[],
				pageTotal:''
			}
		}
	}
</script>

<style>
</style>
