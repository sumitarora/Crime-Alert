<div class="col-sm-3">
	<ul class="nav nav-pills nav-stacked">
	  <li class="${homeActive}"><a href="${pageContext.request.contextPath}/home">Home</a></li>
	  <li class="${dashboardActive}"><a href="${pageContext.request.contextPath}/dashboard">Dashboard</a></li>
	  <li class="${complaintActive}"><a id="btnComplaint" href="${pageContext.request.contextPath}/complaint/list">Complaints</a></li>
	  <li class="${crimeActive}"><a id="btnCrime"  href="${pageContext.request.contextPath}/crime/list">Crimes</a></li>
	  <li class="${profileActive}"><a id="btnProfile" href="${pageContext.request.contextPath}/profile">Profile</a></li>
	  <li class="${reportsActive}"><a id="btnReports" href="${pageContext.request.contextPath}/reports">Reports</a></li>
	  <li class="${feedbackActive}"><a id="btnFeedback" href="${pageContext.request.contextPath}/feedback">Feedback</a></li>
	  <c:if test="${loggedInUser.role.toString().equals(\"ADMIN\")}">
	  	<li class="${newsActive}"><a id="btnNews" href="${pageContext.request.contextPath}/news">News</a></li>
	  	<li class="${userActive}"><a id="btnUsers" href="${pageContext.request.contextPath}/user">Users</a></li>
	  </c:if>	  		  
	</ul>
</div>