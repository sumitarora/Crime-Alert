<%@ include file="../common/header.jsp"%>


<div class="container">
	<%@ include file="../common/left-menu.jsp"%>
	<div class="col-sm-9">
	
	<h1>View Feedback</h1>
	<hr/>
	<div class="col-md-12">
		<h2>${feedback.type}</h2>
		<h4>${feedback.description}</h4>
	    <div>
	    	<span class="label label-primary">Posted: ${feedback.feedbackDate}</span>
		</div>
	</div>
	<br/><br/><br/><br/>
	
	<hr/>
	<div class="col-md-12">
		<div id="error" class="error"></div>
		<div class="form-group">
			<textarea rows="3" cols="20" id="txtComment" name="txtComment" class="form-control col-sm-6"></textarea>
			<br/><br/><br/><br/>
			<button id="btnAddComment" class="btn btn-success btn-sm pull-right">Add Comment</button>
		</div>
		<br/><br/>
		<c:forEach items="${feedback.comments}" var="c" varStatus="loop">
			<div id="comments" class="row text-center">
				<span class="alert alert-info col-sm-2">
					${c.user.firstName} ${c.user.lastName} 
				</span>
				<span class="alert alert-success col-sm-10">
					${c.description }
				</span>
			</div>			
		</c:forEach>
	</div> 

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
			feedbackId:"${feedback.feedbackId}",
			description: comment
	};
	$.post( "${pageContext.request.contextPath}/feedback/comment",obj, function( data ) {
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

