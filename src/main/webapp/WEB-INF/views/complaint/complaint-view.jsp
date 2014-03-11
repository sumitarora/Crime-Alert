<%@ include file="../common/header.jsp"%>


<div class="container">
<div class="form-horizontal col-sm-8 col-sm-offset-2" role="form">
  
  <div class="form-group">
    <label for="Title" class="col-sm-2 control-label">Title</label>
    <div class="col-sm-4">
      ${complaint.title}
    </div>
  </div>
  <div class="form-group">
    <label for="Description" class="col-sm-2 control-label">Description</label>
    <div class="col-sm-4">
      ${complaint.description}
    </div>
  </div>
  <div class="form-group">
    <label for="Address" class="col-sm-2 control-label">Address</label>
    <div class="col-sm-4">
      ${complaint.address}
    </div>
  </div>
  <div class="form-group">
    <label for="City" class="col-sm-2 control-label">City</label>
    <div class="col-sm-4">
      ${complaint.city}
    </div>
  </div>
  <div class="form-group">
    <label for="Province" class="col-sm-2 control-label">State / Province</label>
    <div class="col-sm-4">
      ${complaint.provience}
    </div>
  </div>
  <div class="form-group">      
    <label for="Province" class="col-sm-2 control-label">Country</label>
    <div class="col-sm-4">
            ${complaint.country}
    </div>
  </div>
  <div class="form-group">
    <label for="Location_Name" class="col-sm-2 control-label">Geo-Location</label>
    <div class="col-sm-4">
      ${complaint.location}
    </div>
  </div>    

  <div class="form-group">
    <label for="Map" class="col-sm-2 control-label">Map</label>
    <div class="col-sm-6">
      <div style="width:400px;height:250px;" id="address_map"></div>
    </div>
  </div>
  <input type="hidden" id="address" name="address">
  
  <div class="form-group">
    <label for="Upload" class="col-sm-2 control-label">Uploads</label>
    <div class="col-sm-6">
       <ul id="filesList">
       
       </ul>
    </div>
  </div>

<hr/>
<div class="row">
	<div id="error" class="error"></div>
	<div class="form-group">
		<textarea rows="3" cols="20" id="txtComment" name="txtComment" class="form-control col-sm-6"></textarea>
		<br/><br/><br/><br/>
		<button id="btnAddComment" class="btn btn-success btn-sm pull-right">Add Comment</button>
	</div>
	
	<c:forEach items="${complaint.comments}" var="c" varStatus="loop">
		<div class="container text-center">
		
		<c:if test="${loop.index % 2 eq 0}">
			<span class="alert alert-info col-sm-2" style="height: 141px;">
				<img src="${c.user.photo}" width="100" />
				<br/><br/>
				${c.user.firstName} ${c.user.lastName} 
			</span>
			<span class="alert alert-success col-sm-6" style="height: 141px;">
				${c.description }
			</span>
		</c:if>
		<c:if test="${loop.index % 2 ne 0}">
			<span class="alert alert-success col-sm-6" style="height: 141px;">
				${c.description }
			</span>			
			<span class="alert alert-info col-sm-2" style="height: 141px;">
				<img src="${c.user.photo}" width="100" />
				<br/><br/>
				${c.user.firstName} ${c.user.lastName} 
			</span>
		</c:if>
		</div>
		
	</c:forEach>
</div>  
 
</div>
<script type="text/javascript">
function addComment() {
	var comment = $("#txtComment").val();
	if(comment === "undefined" || comment === "") {
		$("#error").html("Please enter comment.");
		return;
	}
	
	var obj = {
			complaintId:"${complaint.complaintId}",
			description: comment
	};
	$.post( "http://localhost:8080/crime-alert/complaint/comment",obj, function( data ) {
		 console.log(data);
		 if(data.result === "success") {
			 window.location.reload();
		 } else {
			 $("#error").html(data.message);
		 }
	});
}

$(document).ready(function(){
	$("#btnAddComment").click(addComment);
});
</script>

<%@ include file="../common/footer.jsp"%>

