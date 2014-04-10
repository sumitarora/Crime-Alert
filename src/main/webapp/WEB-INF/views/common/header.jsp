<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="../../assets/ico/favicon.png">
	<title>Crime-Alert Web Application</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<%-- <%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css"> --%>
	<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
	<link rel="stylesheet" href="http://bootswatch.com/yeti/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">
	
	<script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="http://maps.googleapis.com/maps/api/js?sensor=false&amp;libraries=places,geometry,visualization"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.geocomplete.min.js"></script>
	<script src="http://malsup.github.com/jquery.form.js"></script>
	
	<script src="${pageContext.request.contextPath}/resources/js/vendor/jquery.ui.widget.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.iframe-transport.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.fileupload.js"></script>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/nvd3/nv.d3.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/stylish-portfolio.css">
	
	<script src="${pageContext.request.contextPath}/resources/nvd3/lib/d3.v2.js"></script>
	<script src="${pageContext.request.contextPath}/resources/nvd3/nv.d3.js"></script>
	
	<script src="http://googlemapapitutorial.com/js/custom_map_tooltip.js" type="text/javascript"></script>
    <!-- <script type="text/javascript"
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCqedN_hmIAD2x_Dahwa6VGIwPyAd8S-Ew&sensor=false">
    </script> -->   
    
	<style type="text/css">
   		#map-canvas { 
   			width: 800px;
   			height: 600px;
   		}
  	</style>
</head>
<body>
   	<c:if test="${!hideMenu}">
   	<div>
      <!-- Static navbar -->
      <div class="navbar navbar-inverse" role="navigation">
        <div class="container">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}">Crime Alert</a>
          </div>
          <div class="navbar-collapse collapse">
          	<ul class="nav navbar-nav navbar-left">
				<sec:authorize access="isAuthenticated()">
					<li><a id="btnHomeHeader" href="${pageContext.request.contextPath}/home">Home</a></li>
				</sec:authorize>
          	</ul>
            <ul class="nav navbar-nav navbar-right">
			<sec:authorize access="isAnonymous()">
				<li><a href="${pageContext.request.contextPath}/register">Register</a></li>
			    <li><a href="${pageContext.request.contextPath}/login">Login</a></li>			    
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<li><h4 style="color:#fff;">Welcome ${loggedInUser.firstName} ${loggedInUser.lastName}</h4></li>
				<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<%-- <li><a id="btnHomeHeader" href="${pageContext.request.contextPath}/home">Home</a></li> --%>
			    <li><a id="btnLogout" href="${pageContext.request.contextPath}/logout">Logout</a></li>			    
			</sec:authorize>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </div>
      </c:if>
