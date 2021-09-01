<template>
	<div>
		<h1>welcome here !!</h1>
	</div>
</template>

<script>
	export default {
		name: 'welcome',
		mounted:function(){
			this.$parent.activeSidebar("welcome-sidebar");
		}
	}
</script>

<style>
</style>
