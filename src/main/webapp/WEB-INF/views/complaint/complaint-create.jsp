<%@ include file="../common/header.jsp"%>


<div class="container">
<%@ include file="../common/left-menu.jsp"%>
<div class="col-sm-9">
<h2>Save Complaint</h2>
<hr noshade="noshade"/>
<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/complaint/save" method="post">
  <input type="hidden" name="complaintId" value="${complaint.complaintId}">
  <div class="form-group">
    <label for="Title" class="col-sm-2 control-label">Title</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="Title" placeholder="Title" name="title" value="${complaint.title}">
    </div>
  </div>
  <div class="form-group">
    <label for="Description" class="col-sm-2 control-label">Description</label>
    <div class="col-sm-4">
      <textarea rows="4" cols="6" class="form-control" id="Description" name="description" placeholder="Description">${complaint.description}</textarea>
    </div>
  </div>
  <div class="form-group">
    <label for="Location_Name" class="col-sm-2 control-label">Location Name</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="location_name" placeholder="Location Name" name="location" value="${complaint.location}">
    </div>
  </div>
  <div class="form-group">
    <label for="Address" class="col-sm-2 control-label">Address</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="address" placeholder="Address" name="address" value="${complaint.address}">
    </div>
  </div>
  <div class="form-group">
    <label for="City" class="col-sm-2 control-label">City</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="city" placeholder="City" name="city" value="${complaint.city}">
    </div>
  </div>
  <div class="form-group">
    <label for="Province" class="col-sm-2 control-label">Province/State</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="province" placeholder="Province" name="provience" value="${complaint.provience}">
    </div>
  </div>
  <div class="form-group">      
   <label for="Province" class="col-sm-2 control-label">Country</label>
   <div class="col-sm-4">
   		<input type="text" class="form-control" id="country" placeholder="Country" name="country" value="${complaint.country}">
   </div>
   </div>

  <div class="form-group">
    <label for="Map" class="col-sm-2 control-label">Map</label>
    <div class="col-sm-4">
      <img src="http://www.punjab-state.com/maps/renderer/patiala_map.JPG" width="300"height="200">
    </div>
  </div>
  
  <div class="form-group">
    <label for="Upload" class="col-sm-2 control-label">Upload</label>
    <div class="col-sm-offset-5">
       <button type="button" class="btn btn-primary"><i class="fa fa-folder"></i>   Browse</button>
    </div>
  </div>
  
  <div class="form-group">
<!--     <label for="Upload" class="col-sm-2 control-label"></label> -->
    <div class="col-sm-4 col-sm-offset-2">
          <button href="#" class="btn btn-success"><i class="fa fa-floppy-o"></i> Save</button>
          <a href="${pageContext.request.contextPath}/complaint/list" class="btn btn-danger"><i class="fa fa-times"></i> Cancel</a>
    </div>
  </div>
  
</form>
</div>

<%@ include file="../common/footer.jsp"%>