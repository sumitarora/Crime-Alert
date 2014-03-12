<%@ include file="../common/header.jsp"%>

<div class="container">
	<%@ include file="../common/left-menu.jsp"%>
	<div class="col-sm-9">
	<a class="btn btn-success pull-right" href="${pageContext.request.contextPath}/crime/create"><i class="fa fa-plus"></i> Add Crimes</a>
	<h1>View Crime</h1>
	<div class="col-md-12">	    
		<c:forEach items="${users}" var="u">
			<h2>${u.firstName} ${u.lastName}</h2>
			<p> ${u.email}, ${u.country}</p>
		    <div>
		        <c:choose>
		        	<c:when test="${u.enabled}">
		        		<span class="label label-success">Enabled</span>
		        	</c:when>
		        	<c:otherwise>
		        		<span class="label label-danger">Disabled</span>
		        	</c:otherwise>
		        </c:choose>
		        <span class="badge">Role: ${u.role.toString()}</span>
		        
		        <div class="pull-right">
					<a href="${pageContext.request.contextPath}/user/edit/${u.userId}" class="btn btn-sm btn-primary">
			  			<i class="fa fa-pencil"></i>
			  		</a>
		        </div>
		    </div>		
			<hr>
		</c:forEach>
	</div>
	</div>
</div>

<%@ include file="../common/footer.jsp"%>
