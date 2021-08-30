<template>
<!--  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>-->
<div class="main-container">
	<div class="main-content">
		<div class="row">
			<div class="col-sm-10 col-sm-offset-1">
				<div id="loginDiv" class="login-container">
					<div class="center">
						<h1>
							<i class="ace-icon fa fa-leaf green"></i>
							<span style="color:#C5EF35;">控制中心登录</span>
							<!-- <span class="red"></span>
							<span class="white" id="id-text2">Application</span> -->
						</h1>
					<h4 class="blue" id="id-company-text">@Yx.co</h4>
					</div>
			
					<div class="space-6"></div>

					<div class="position-relative">
						<div id="login-box" class="login-box visible widget-box no-border">
							<div class="widget-body" style="background-color:rgba(0,0,0,0)">
								<div class="widget-main" style="background-color:rgba(0,0,0,0)">
									<!-- <h4 class="header blue lighter bigger">
										<i class="ace-icon fa fa-coffee green"></i>
										Please Enter Your Information
									</h4> -->
							
									<div class="space-6"></div>

										<form>
											<fieldset>
												<label class="block clearfix">
													<span class="block input-icon input-icon-right">
														<input type="text" class="form-control" placeholder="Username" />
														<i class="ace-icon fa fa-user"></i>
													</span>
												</label>
										
												<label class="block clearfix">
													<span class="block input-icon input-icon-right">
														<input type="password" class="form-control" placeholder="Password" />
														<i class="ace-icon fa fa-lock"></i>
													</span>
												</label>

												<div class="space"></div>

												<div class="clearfix">
													<label class="inline">
														<input type="checkbox" class="ace" />
														<span class="lbl" style="color:#D67E31">记住我</span>
													</label>

													<button type="button" class="width-35 pull-right btn btn-sm btn-primary" @click="userLogin">
														<i class="ace-icon fa fa-key"></i>
														<span class="bigger-110">登录</span>
													</button>
												</div>

												<div class="space-4"></div>
											</fieldset>
										</form>
							
									<div class="social-or-login center">
										<span class="bigger-110">使用第三方登录</span>
									</div>

									<div class="space-6"></div>

									<div class="social-login center">
										<a class="btn btn-primary">
											<i class="ace-icon fa fa-wechat"></i>
										</a>

										<a class="btn btn-info">
											<i class="ace-icon fa fa-qq"></i>
										</a>

										<a class="btn btn-danger">
											<i class="ace-icon fa fa-weibo"></i>
										</a>
									</div>
								</div><!-- /.widget-main -->
							</div><!-- /.widget-body -->
						</div><!-- /.login-box -->
					</div><!-- /.position-relative -->
				</div>
			</div><!-- /.col -->
		</div><!-- /.row -->
	</div><!-- /.main-content -->
</div><!-- /.main-container -->
</template>

<script>
	
	export default {
		name: 'login',
		mounted:function(){
			$('body').removeClass('no-sking');
			$('body').attr('class', 'login-layout light-login');
		},
		methods: {
			userLogin(){
				this.$router.push("/admin")
			}
		}
	}

</script>

<style>
	#loginDiv{
		margin-top:15%;
	}
</style>