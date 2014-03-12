<%@ include file="../common/header.jsp"%>


<div class="container">
	<%@ include file="../common/left-menu.jsp"%>
	<div class="col-sm-9">
	
	<span class="pull-right">
	  <c:if test="${!loggedInUser.role.toString().equals(\"USER\")}">
	  	<a class="btn btn-warning" href="${pageContext.request.contextPath}/crime/list/all"><i class="fa fa-list-alt"></i> All Crimes</a>
	  	&nbsp;&nbsp;&nbsp;
	  </c:if>
	<a class="btn btn-success" href="${pageContext.request.contextPath}/crime/create"><i class="fa fa-plus"></i> Add Crimes</a>
	</span>
	
	<h1>View Crime</h1>
	<div class="col-md-12">	    
		<c:forEach items="${crimes}" var="c">
			<h2>${c.title}</h2>
			<p> ${c.address}, ${c.locality}, ${c.administrative_area_level_1}, ${c.country}</p>
		    <div>
		        <span class="badge">Posted ${c.crimeDate}</span>
		        <div class="pull-right">
					<a href="${pageContext.request.contextPath}/crime/view/${c.crimeId}" class="btn btn-sm btn-default">
			  			<i class="fa fa-camera"></i>
			  		</a>	        	
					<a href="${pageContext.request.contextPath}/crime/edit/${c.crimeId}" class="btn btn-sm btn-primary">
			  			<i class="fa fa-pencil"></i>
			  		</a>
			  		<a href="${pageContext.request.contextPath}/crime/delete/${c.crimeId}" class="btn btn-sm btn-danger">
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

