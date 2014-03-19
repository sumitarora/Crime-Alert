<%@ include file="../common/header.jsp"%>

<div class="container">
<%@ include file="../common/left-menu.jsp"%>
<div class="col-sm-9">

 <h1>Save Crime</h1>
 <hr>

<div class="row">
	<label class="col-sm-2 control-label">Upload Files</label>
	<div class="col-sm-6">
		<form id="myForm" method="post" action="${pageContext.request.contextPath}/upload/do" enctype="multipart/form-data">
		    <input type="file" name="file"/>
		    <input class="btn btn-primary btn-sm" type="submit"/>
		</form>
		<ul id="filesList"></ul>
	</div>
</div>
<br/>

<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/crime/save" method="post">
  <input type="hidden" name="crimeId" value="${crime.crimeId}">
  <input type="hidden" name="uploads" id="uploads" value="${crime.uploads}">
  <input type="hidden" name="userId" value="${crime.user.userId}">
  
  <div class="form-group">
  <div class="form-group">
    <label for="title" class="col-sm-2 control-label">Title</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" id="title" placeholder="Title" name="title" value="${crime.title}" required>
    </div>
  </div>
  <div class="form-group">
    <label for="description" class="col-sm-2 control-label">Description</label>
    <div class="col-sm-6">
      <textarea class="form-control" id="description" placeholder="Description" name="description" required>${crime.description}</textarea>
    </div>
  </div>
  <div class="form-group">
    <label for="address" class="col-sm-2 control-label">Address</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" id="address" placeholder="Address" name="address" value="${crime.address}" required>
    </div>
  </div>
  <div class="form-group">
    <label for="city" class="col-sm-2 control-label">City</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" id="locality" placeholder="City" name="locality" value="${crime.locality}" required>
    </div>
  </div>
  <div class="form-group">
    <label for="state" class="col-sm-2 control-label">State / Province</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" id="state" placeholder="State / Province" name="administrative_area_level_1" value="${crime.administrative_area_level_1}" required>
    </div>
  </div>
  <div class="form-group">
    <label for="country" class="col-sm-2 control-label">Country</label>
    <div class="col-sm-6">
      	<input type="text" class="form-control" id="country" placeholder="Country" name="country" value="${crime.country}" required>
    </div>
  </div>
  <div class="form-group">
    <label for="location_name" class="col-sm-2 control-label">Geo-Location</label>
    <div class="col-sm-6">
      <input readonly type="text" class="form-control" id="location_name" placeholder="Location Name" name="location" value="${crime.location}" required>
    </div>
  </div>  
  <div class="form-group">
    <label for="map" class="col-sm-2 control-label">Map</label>
    <div class="col-sm-6">
	    <div style="width:400px;height:250px;" id="address_map" />
    </div>
  </div>

  <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-6">
          <button class="btn btn-success" id="btnSuccess"><i class="fa fa-floppy-o"></i> Save</button>
          <a href="${pageContext.request.contextPath}/crime/list" class="btn btn-danger"><i class="fa fa-times"></i> Cancel</a>
    </div>
  </div>
</form>
</div>
</div>
<%@ include file="../common/footer.jsp"%>

<script type="text/javascript">
var _location = null;
var _uploads = [];

function getLocation()
{
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(showPosition);
	}
}
function showPosition(position)
{
	<c:choose>
    <c:when test= "${crime.location != null}">
    	_location = "${crime.location}";
    </c:when>
	<c:otherwise>
		_location = position.coords.latitude + "," + position.coords.longitude;
	</c:otherwise>
	</c:choose>
	
	var obj = {
			map: "#address_map",
			details: "form",
			location: _location			
		};
	$("#address").geocomplete(obj);
}

function setUploadsText(uploads) {
	var str = "";
	for(var i = 0; i < uploads.length; i++) {
		str = str + uploads[i].id;
		if(i < uploads.length - 1) {
			str = str + ",";
		}
	}
	$("#uploads").val(str);
}

function getUploadedFiles(uploads) {
	console.log(uploads);
	if(uploads !== "undefined" && uploads !== "") {
		var  uploads = uploads.split(",");
		console.log(uploads);
		_uploads = [];
		for(var i = 0; i < uploads.length; i++) {
			$.get("${pageContext.request.contextPath}/upload/"+uploads[i], function( data ) {
				_uploads.push(data);
				console.log(_uploads);
				populateItem(data);
			});
		}
		console.log(_uploads);
	}	
}

function populateItem(obj) {
	
	var filename = obj.originalFileName;
	var url = obj.filePath;
	var html = "<li><a target='_blank' href='" + url + "'>" + filename + "</a>&nbsp;&nbsp;<i id='btnDelete' file-id='"+obj.id+"' class='fa fa-times'></i></li>";
	console.log(url, html);
    $("#filesList").append(html);
    setUploadsText(_uploads);	
}

$(document).ready(function(){
	getLocation();
	getUploadedFiles("${crime.uploads}");
	
    var options = { 
    	    beforeSend: function() 
    	    {
    	        //$("#progress").show();
    	        //clear everything
    	        //$("#bar").width('0%');
    	        //$("#message").html("");
    	        //$("#percent").html("0%");
    	    },
    	    uploadProgress: function(event, position, total, percentComplete) 
    	    {
    	        //$("#bar").width(percentComplete+'%');
    	        //$("#percent").html(percentComplete+'%');
    	 
    	    },
    	    success: function() 
    	    {
    	        //$("#bar").width('100%');
    	        //$("#percent").html('100%');
    	        $('#myForm')[0].reset();
    	 
    	    },
    	    complete: function(response) 
    	    {
    	    	var response = $.parseJSON(response.responseText);
    	    	_uploads.push(response);
    	    	populateItem(response);
    	    },
    	    error: function()
    	    {
    	        alert("unable to upload files");    	 
    	    }
    	 
    	};
    $("#myForm").ajaxForm(options);
    
    $('body').on('click', '#btnDelete', function() {
    	var file = $(this);
        var fileId = $(this).attr("file-id");
        $.get("${pageContext.request.contextPath}/upload/delete/"+fileId, function( data ) {
        	if(data.result) {
        		file.parent().remove();
        		
        		var _newUploads = [];
        		for(var i = 0; i < _uploads.length; i++) {
        			console.log(_uploads[i].id , fileId);
        			
        			if(_uploads[i].id != fileId) {
        				_newUploads.push(_uploads[i]);
        			}
        		}
        		_uploads = _newUploads;
        		console.log(_uploads);
        		setUploadsText(_uploads);
        	} else {
        		alert("unable to delete file");	
        	}
       	});        
    });
	
});
</script>

