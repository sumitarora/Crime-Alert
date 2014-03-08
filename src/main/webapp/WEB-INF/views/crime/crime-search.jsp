<%@ include file="../common/header.jsp"%>

<div class="container">
	<div class="col-md-12">
	<form class="row form-inline text-center" role="form" method="get" action="${pageContext.request.contextPath}/search">
	  <div class="form-group">
	    <input class="form-control" type="text" placeholder="Search Criteria" name="criteria"
	    value="<%= request.getParameter("criteria") %>">
	  </div>
	  <div class="form-group">
	    <select class="form-control" name="type">
	    	<%
	    		String type = request.getParameter("type");
	    		if(type.equals("cr")) {
			%>
				<option selected value="cr">Crime</option>
				<option value="co">Complaints</option>
			<%	    			
	    		} else {
	    	%>
				<option value="cr">Crime</option>
				<option selected value="co">Complaints</option>
			<%	    			
	    		}	    	
	    	%>
		</select>
	  </div>
	  <button type="submit" class="btn btn-success btn-sm"><i class="fa fa-search"></i></button>
	</form>
	<hr/>		    
		<c:forEach items="${crimes}" var="c">
			<h2>${c.title}</h2>
			<p> ${c.address}, ${c.city}, ${c.provience}, ${c.country}</p>
		    <div>
		        <span class="badge">Posted ${c.crimeDate}</span>
		        <div class="pull-right">
					<a href="${pageContext.request.contextPath}/crime/view/${c.crimeId}" class="btn btn-sm btn-default">
			  			<i class="fa fa-camera"></i>
			  		</a>
		        </div>
		    </div>		
			<hr>
		</c:forEach>
	</div>
</div>

<%@ include file="../common/footer.jsp"%>

