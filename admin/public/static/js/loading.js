Loading = {
	show: function(){
		$.blockUI({
			message:'<img src="/static/img/loading.gif" />',
			css:{
				padding:'10px',
				left:'50%',
				width:'3%'
			}
		});
	},
	hide: function(){
		//等待延时
		setTimeout(function(){
			$.unblockUI();
		}, 200)
	}
};