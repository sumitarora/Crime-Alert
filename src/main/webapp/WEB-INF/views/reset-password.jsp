
<%@ include file="common/header.jsp"%>

<div class="container">

<div class="col-sm-8 col-sm-offset-2">
	<h1 class="page-header">Reset Password</h1>

	<form class="form-horizontal col-sm-10" role="form" method="post" onsubmit="return matchPassword();"
		action="${pageContext.request.contextPath}/changeforgotpassword">
		<div class="form-group" >
			<span id="errorPassword" class="col-sm-4 control-label" style="color: red">${error }</span>
			<c:if test="${changed}">
				<div class="alert alert-success">
					Password Changed
				</div>
			</c:if>
			<c:if test="${usernotfound}">
				<div class="alert alert-danger">
					Invalid Token or Token already used
				</div>
			</c:if>				
		</div>

		<input type="hidden" value="${token}" name="token" />
		
		<div class="form-group">
			<label for="inputPassword" class="col-sm-4 control-label">New
				Password</label>
			<div class="col-sm-6">
				<input type="password" id="newPassword" class="form-control" name="newPassword"
					placeholder="Enter new password">
			</div>
		</div>

		<div class="form-group">
			<label for="inputPassword" class="col-sm-4 control-label">Retype
				Password</label>
			<div class="col-sm-6">
				<input type="password" id="retypePassword" class="form-control" name="retypePassword"
					placeholder="Retype new password">
			</div>
		</div>

		<div class="form-group">
			<span class="col-sm-4 control-label"></span>
			<div class="col-sm-6">
				<button type="submit" class="btn btn-success btn-sm">Save Changes</button>
			</div>
		</div>

	</form>
</div>


<script>
function matchPassword(){
	var newPassword = $("#newPassword").val();
	var retypePassword = $("#retypePassword").val();
	if(newPassword === retypePassword){
		return true;
	}
	else{
		$("#errorPassword").html("Password did not match");
	}
	console.log(newPassword);
	console.log(retypePassword);
	return false;	
}
</script>

<%@ include file="common/footer.jsp"%>

