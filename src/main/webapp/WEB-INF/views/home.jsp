<%@ include file="common/header.jsp"%>

<div class="container">
	<%@ include file="common/left-menu.jsp"%>
	<div class="col-sm-9">
	    <h1>Account Summary</h1>
	    <hr/>
	    <h2>My Complaints: ${complaintsCount}</h2>
	    <h2>My Crimes: ${crimesCount}</h2>
	    <h2>Comments: ${commentsCount}</h2>
	    <h2>Misc: ${miscCount}</h2>
	</div>
</div>

<%@ include file="common/footer.jsp"%>