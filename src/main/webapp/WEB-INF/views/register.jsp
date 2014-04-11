<%@ include file="common/header.jsp"%>

<div class="container">
<div class="row">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
		<form role="form" method="post" action="registeruser" onsubmit="return checkPassword();">
			<h2>Please Sign Up <small></small></h2>
			<hr class="colorgraph">
			<div class="alert alert-danger" id="divError" style="display:none;">
				Password and Confirm Password should match
			</div>
			<%
			    if (request.getParameter("emailexists") != null) {
			%>
			<div class="alert alert-danger">
				Email already exists
			</div>			
			<%
			    }
			%>			
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
                        <input required="true" type="text" name="first_name" id="first_name" class="form-control input-lg" placeholder="First Name" tabindex="1">
					</div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
						<input required="true" type="text" name="last_name" id="last_name" class="form-control input-lg" placeholder="Last Name" tabindex="2">
					</div>
				</div>
			</div>

			<div class="form-group">
				<input required="true" type="email" name="email" id="email" class="form-control input-lg" placeholder="Email Address" tabindex="4">
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
						<input required="true" type="password" name="password" id="password" class="form-control input-lg" placeholder="Password" tabindex="5">
					</div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
						<input required="true" type="password" name="password_confirmation" id="password_confirmation" class="form-control input-lg" placeholder="Confirm Password" tabindex="6">
					</div>
				</div>
			</div>
			
			<hr class="colorgraph">
			<div class="row">
				<div class="col-xs-12 col-md-6"><input type="submit" value="Register" class="btn btn-primary btn-block btn-lg" tabindex="7" id="btnRegister"></div>
				<div class="col-xs-12 col-md-6"><a href="${pageContext.request.contextPath}/login" class="btn btn-success btn-block btn-lg">Sign In</a></div>
			</div>
		</form>
	</div>
</div>
</div>

<script type="text/javascript">
function checkPassword() {
	$("#divError").attr("style","display:none;");
	var password = $("#password").val();
	var cpassword = $("#password_confirmation").val();
	if(password !== cpassword) {
		$("#divError").attr("style","");
		return false;
	}
	return true;
}
</script>


<%@ include file="common/footer.jsp"%>