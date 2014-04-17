<%@ include file="../common/header.jsp"%>


<div class="container">
	<%@ include file="../common/left-menu.jsp"%>
	<div class="col-sm-9">
	
	<h1>View News</h1>
	<hr/>
	<div class="col-md-12">
		<h2>${news.title}</h2>
		<h4>${news.description}</h4>
	    <div>
	    	<span class="label label-primary">Posted: ${news.newsDate}</span>
		</div>
	</div>
	<br/><br/><br/><br/>

</div>
</div>

<%@ include file="../common/footer.jsp"%>

