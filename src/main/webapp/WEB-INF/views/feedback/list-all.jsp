<%@ include file="../common/header.jsp"%>


<div class="container">
	<%@ include file="../common/left-menu.jsp"%>
	<div class="col-sm-9">
	
	<span class="pull-right">
	  <a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/feedback"><i class="fa fa-list-alt"></i> My Feedback</a>
	  &nbsp;&nbsp;&nbsp;	
	  <c:if test="${!loggedInUser.role.toString().equals(\"USER\")}">
	  	<a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath}/feedback/list/all"><i class="fa fa-list-alt"></i> All Feedback</a>
	  	&nbsp;&nbsp;&nbsp;
	  </c:if>
	<a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/feedback/create"><i class="fa fa-plus"></i> Give Feedback</a>
	</span>
	
	<h1>View Feedback</h1>
	<hr/>
	<div class="col-md-12">	    
		<c:forEach items="${feedbacks}" var="f">
			<h2>${f.type}</h2>
		    <div>
		        <span class="label label-primary">Posted: ${f.feedbackDate}</span>
		        <span class="label label-info">Comments: ${f.comments.size()}</span>
		        <c:if test="${!loggedInUser.role.toString().equals(\"USER\")}">
	  				<span class="label label-warning">User: ${f.user.firstName} ${f.user.lastName}</span>
	  			</c:if>
		        <div class="pull-right">
					<a href="${pageContext.request.contextPath}/feedback/view/${f.feedbackId}" class="btn btn-sm btn-default">
			  			<i class="fa fa-camera"></i>
			  		</a>	        	
			  		<a href="${pageContext.request.contextPath}/feedback/delete/${f.feedbackId}" class="btn btn-sm btn-danger">
			  			<i class="fa fa-trash-o"></i>
			  		</a>
		        </div>
		    	
			<hr>
		</c:forEach>
	</div>
	</div>
</div>


<%@ include file="../common/footer.jsp"%>

