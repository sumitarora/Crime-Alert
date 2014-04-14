<%@ include file="common/header.jsp"%>

<div class="container">
<div class="row">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
		<form action="forgotpassword" method="post" role="form">
			<h2>Forgot Password <small></small></h2>
			<hr class="colorgraph">
			<div class="form-group">
				<input required="true" type="email" name="email" id="email" class="form-control input-lg" placeholder="Email Address" tabindex="4">
			</div>
			<hr class="colorgraph">
			<div class="row">
				<div class="col-xs-12 col-md-6"><input type="submit" id="btnSubmit" value="Submit" class="btn btn-success btn-block btn-lg" tabindex="7"></div>
				<div class="col-xs-12 col-md-6"><a id="btnRegister" href="${pageContext.request.contextPath}/login" class="btn btn-primary btn-block btn-lg">Login</a></div>
			</div>
		</form>
		<br/><br/>
		
	</div>
</div>
</div>

<%@ include file="common/footer.jsp"%>