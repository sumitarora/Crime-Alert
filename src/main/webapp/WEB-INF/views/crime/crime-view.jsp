<%@ include file="../common/header.jsp"%>


<div class="container">
<%@ include file="../common/left-menu.jsp"%>
<div class="col-sm-9">
<form class="form-horizontal" role="form">

  <div class="form-group">
    <div class="col-sm-4 col-sm-offset-2">
       <a href="${pageContext.request.contextPath}/crime/list" class="btn btn-success"><i class="fa fa-arrow-circle-o-left"></i> Back</a>
    </div>
  </div>
  
  <div class="form-group">
    <label for="Title" class="col-sm-2 control-label">Title</label>
    <div class="col-sm-4">
      ${crime.title}
    </div>
  </div>
  <div class="form-group">
    <label for="Description" class="col-sm-2 control-label">Description</label>
    <div class="col-sm-4">
      ${crime.description}
    </div>
  </div>
  <div class="form-group">
    <label for="Location_Name" class="col-sm-2 control-label">Location Name</label>
    <div class="col-sm-4">
      ${crime.location}
    </div>
  </div>
  <div class="form-group">
    <label for="Address" class="col-sm-2 control-label">Address</label>
    <div class="col-sm-4">
      ${crime.address}
    </div>
  </div>
  <div class="form-group">
    <label for="City" class="col-sm-2 control-label">City</label>
    <div class="col-sm-4">
      ${crime.city}
    </div>
  </div>
  <div class="form-group">
    <label for="Province" class="col-sm-2 control-label">Province/State</label>
    <div class="col-sm-4">
      ${crime.provience}
    </div>
  </div>
  <div class="form-group">      
    <label for="Province" class="col-sm-2 control-label">Country</label>
    <div class="col-sm-4">
            ${crime.country}
        </div>
    </div>

  <div class="form-group">
    <label for="Map" class="col-sm-2 control-label">Map</label>
    <div class="col-sm-4">
      <img src="http://www.punjab-state.com/maps/renderer/patiala_map.JPG" width="300"height="200">
    </div>
  </div>
  
  <div class="form-group">
    <label for="Upload" class="col-sm-2 control-label">Uploads</label>
    <div class="col-sm-offset-5">
       <a href="#">ada</a>
    </div>
  </div>
  

  
</form>
</div>

<%@ include file="../common/footer.jsp"%>