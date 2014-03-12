<%@ include file="../common/header.jsp"%>


<div class="container">
	<%@ include file="../common/left-menu.jsp"%>
	<div class="col-sm-9">

	<span class="pull-right">
	  <c:if test="${!loggedInUser.role.toString().equals(\"USER\")}">
	  	<a class="btn btn-warning" href="${pageContext.request.contextPath}/complaint/list/all"><i class="fa fa-list-alt"></i> All Complaints</a>
	  	&nbsp;&nbsp;&nbsp;
	  </c:if>
	  <a class="btn btn-success" href="${pageContext.request.contextPath}/complaint/create"><i class="fa fa-plus"></i> Add Complaint</a>
    </span>

	<h1>View Complaints</h1>
	<hr/>
	<div class="col-md-12">
	
	<c:forEach items="${complaints}" var="c">
		<h2>${c.title}</h2>
		<p> ${c.address}, ${c.city}, ${c.provience}, ${c.country}</p>
	    <div>
	        <span class="badge">Posted ${c.complaintDate}</span>
	        <div class="pull-right">
				<a href="${pageContext.request.contextPath}/complaint/view/${c.complaintId}" class="btn btn-sm btn-default">
		  			<i class="fa fa-camera"></i>
		  		</a>	        	
				<a href="${pageContext.request.contextPath}/complaint/edit/${c.complaintId}" class="btn btn-sm btn-primary">
		  			<i class="fa fa-pencil"></i>
		  		</a>
		  		<a href="${pageContext.request.contextPath}/complaint/delete/${c.complaintId}" class="btn btn-sm btn-danger">
		  			<i class="fa fa-trash-o"></i>
		  		</a>
	        </div>
	    </div>		
		<hr>
	</c:forEach>		    
	    
	</div>
	</div>
</div>


<%@ include file="../common/footer.jsp"%>
