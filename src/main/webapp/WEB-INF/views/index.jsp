<%@ include file="common/header.jsp"%>

<div class="container text-center">
	<form class="row form-inline" role="form" method="get" action="${pageContext.request.contextPath}/search">
	  <div class="form-group">
	    <input class="form-control" type="text" id="criteria" placeholder="Search Criteria" name="criteria">
	  </div>
	  <div class="form-group">
	    <select class="form-control" name="type" id="searchType">
			<option value="cr">Crime</option>
			<option value="co">Complaints</option>
		</select>
	  </div>
	  <button type="submit" class="btn btn-success btn-sm" id="btnSearch"><i class="fa fa-search"></i></button>
	</form>
	<hr/>
	<div class="row">
		<h1>Latest Crimes</h1>
		<br/>
		<c:forEach items="${topComplaints}" var="c">
			<div class="col-sm-3">
				<h2>${c.title }</h2>
				<%-- <p>${c.administrative_area_level_1}, ${c.country}</p> --%>
			    <span class="badge">&nbsp;&nbsp;Posted ${c.complaintDate}&nbsp;&nbsp;</span>
			    <br/>
			    <a id="btnComplaintDetails" href="${pageContext.request.contextPath}/complaint/view/${c.complaintId}">Details</a>
			</div>
		</c:forEach>
	</div>
	<hr/>
	<div class="row">
		<h1>Latest Complaints</h1>
		<br/>
		<c:forEach items="${topCrimes}" var="c">
			<div class="col-sm-3">
				<h2>${c.title }</h2>
				<p>${c.administrative_area_level_1}, ${c.country}</p>
			    <span class="badge">&nbsp;&nbsp;Posted ${c.crimeDate}&nbsp;&nbsp;</span>
			    <br/>
			    <a id="btnCrimeDetails" href="${pageContext.request.contextPath}/crime/view/${c.crimeId}">Details</a>
			</div>
		</c:forEach>
	</div>
	<hr/>
 </div>
 

<%@ include file="common/footer.jsp"%>
