<%@ include file="../common/header.jsp"%>

<div class="container">
<%@ include file="../common/left-menu.jsp"%>
<div class="col-sm-9">
<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/crime/save" method="post">
	<input type="hidden" name="crimeId" value="${crime.crimeId}">
  <div class="form-group">
    <h1>Save Crime</h1>
    <hr>	
  <div class="form-group">
    <label for="title" class="col-sm-2 control-label">Title</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="title" placeholder="Title" name="title" value="${crime.title}">
    </div>
  </div>
  <div class="form-group">
    <label for="description" class="col-sm-2 control-label">Description</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="description" placeholder="Description" name="description" value="${crime.description}">
    </div>
  </div>
  <div class="form-group">
    <label for="location_name" class="col-sm-2 control-label">Location Name</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="location_name" placeholder="Location Name" name="location" value="${crime.location}">
    </div>
  </div>
  <div class="form-group">
    <label for="address" class="col-sm-2 control-label">Address</label>
    <div class="col-sm-4">
      <textarea class="form-control" id="address" placeholder="Address" name="address">${crime.address}</textarea>
    </div>
  </div>
  <div class="form-group">
    <label for="city" class="col-sm-2 control-label">City</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="city" placeholder="City" name="city" value="${crime.city}">
    </div>
  </div>
  <div class="form-group">
    <label for="state" class="col-sm-2 control-label">State/Province</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="state" placeholder="State/Province" name="provience" value="${crime.provience}">
    </div>
  </div>
  <div class="form-group">
    <label for="country" class="col-sm-2 control-label">Country</label>
    <div class="col-sm-4">
      	<input type="text" class="form-control" id="country" placeholder="Country" name="country" value="${crime.country}">
    </div>
  </div>
  <div class="form-group">
    <label for="map" class="col-sm-2 control-label">Map</label>
    <div class="col-sm-4">
      <img id="map" alt="" src="http://www.iiap.res.in/files/google-map1.jpg" width="380" height="200">
    </div>
  </div>
  <div class="form-group">
    <label for="upload" class="col-sm-2 control-label">Upload</label>
    <div class="col-sm-4">
      <button type="upload" class="btn btn-info col-sm-offset-10" ><i class="fa fa-upload"></i> Upload</button>
    </div>
  </div>
  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-6">
          <button class="btn btn-success"><i class="fa fa-floppy-o"></i> Save</button>
          <a href="${pageContext.request.contextPath}/crime/list" class="btn btn-danger"><i class="fa fa-times"></i> Cancel</a>
    </div>
  </div>
</form>
</div>
</div>

<%@ include file="../common/footer.jsp"%>