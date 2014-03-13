<div class="col-sm-3">
	<ul class="nav nav-pills nav-stacked">
	  <li class="${homeActive}"><a href="${pageContext.request.contextPath}/home">Home</a></li>
	  <li class="${complaintActive}"><a id="btnComplaint" href="${pageContext.request.contextPath}/complaint/list">Complaints</a></li>
	  <li class="${crimeActive}"><a id="btnCrime"  href="${pageContext.request.contextPath}/crime/list">Crimes</a></li>
	  <li class="${profileActive}"><a id="btnProfile" href="${pageContext.request.contextPath}/profile">Profile</a></li>
	  <c:if test="${loggedInUser.role.toString().equals(\"ADMIN\")}">
	  	<li class="${userActive}"><a id="btnUsers" href="${pageContext.request.contextPath}/user">Users</a></li>
	  </c:if>	  		  
	</ul>
</div>