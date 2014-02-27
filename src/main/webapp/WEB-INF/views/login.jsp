<%@ include file="common/header.jsp"%>

<c:url value="/login" var="loginUrl"/>
<div class="container">
<div class="row">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
		<form action="${loginUrl}" method="post" role="form">
			<h2>Please Sign In <small></small></h2>
		    <c:if test="${param.error != null}">        
		        <p>
		            Invalid username and password.
		        </p>
		    </c:if>
		    <c:if test="${param.logout != null}">       
		        <p>
		            You have been logged out.
		        </p>
		    </c:if>
			<hr class="colorgraph">

			<div class="form-group">
				<input required="true" type="email" name="username" id="username" class="form-control input-lg" placeholder="Email Address" tabindex="4">
			</div>
			<div class="form-group">
				<input required="true" type="password" name="password" id="password" class="form-control input-lg" placeholder="Password" tabindex="4">
			</div>	
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>		
			<hr class="colorgraph">
			<div class="row">
				<div class="col-xs-12 col-md-6"><input type="submit" value="Sign In" class="btn btn-success btn-block btn-lg" tabindex="7"></div>
				<div class="col-xs-12 col-md-6"><a href="${pageContext.request.contextPath}/register" class="btn btn-primary btn-block btn-lg">Register</a></div>
			</div>
		</form>
	</div>
</div>
</div>

<%-- <div class="container"> 

<form action="${loginUrl}" method="post" class="col-xs-4 form-signin" role="form"> 
	<h2 class="form-signin-heading">Please sign in</h2>
    <c:if test="${param.error != null}">        
        <p>
            Invalid username and password.
        </p>
    </c:if>
    <c:if test="${param.logout != null}">       
        <p>
            You have been logged out.
        </p>
    </c:if>
    <p>
        <label for="username">Username</label>
        <input type="text" id="username" name="username" class="form-control"/>	
    </p>
    <p>
        <label for="password">Password</label>
        <input type="password" id="password" name="password" class="form-control"/>	
    </p>
    <input type="hidden"                        
        name="${_csrf.parameterName}"
        value="${_csrf.token}"/>
    <button class="btn btn-lg btn-primary btn-block" type="submit" class="btn">Log in</button>
</form>
</div> --%>

<%@ include file="common/footer.jsp"%>