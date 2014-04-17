<%@ include file="../common/header.jsp"%>


<div class="container">
	<%@ include file="../common/left-menu.jsp"%>
	<div class="col-sm-9">
	
	<span class="pull-right">
	  <a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/news/create"><i class="fa fa-plus"></i> Add News</a>
	</span>
	
	<h1>View News</h1>
	<hr/>
	<div class="col-md-12">	    
		<c:forEach items="${news}" var="f">
			<h2>${f.title}</h2>
		    <div>
		        <span class="label label-primary">Posted: ${f.newsDate}</span>
		        <div class="pull-right">
					<a href="${pageContext.request.contextPath}/news/view/${f.newsId}" class="btn btn-sm btn-default">
			  			<i class="fa fa-camera"></i>
			  		</a>
					<a href="${pageContext.request.contextPath}/news/edit/${f.newsId}" class="btn btn-sm btn-primary">
			  			<i class="fa fa-pencil"></i>
			  		</a>			  			        	
			  		<a href="${pageContext.request.contextPath}/news/delete/${f.newsId}" class="btn btn-sm btn-danger">
			  			<i class="fa fa-trash-o"></i>
			  		</a>
		        </div>
		    	
			<hr>
		</c:forEach>
	</div>
	</div>
</div>


<%@ include file="../common/footer.jsp"%>

