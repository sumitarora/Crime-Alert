<%@ include file="../common/header.jsp"%>

<div class="container">
<%@ include file="../common/left-menu.jsp"%>
<div class="col-sm-9">

 <h1>Provie Feedback</h1>
 <hr>

<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/feedback/save" method="post">
  <input type="hidden" name="crimeId" value="${crime.crimeId}">
  <input type="hidden" name="uploads" id="uploads" value="${crime.uploads}">
  <input type="hidden" name="userId" value="${crime.user.userId}">
  
  <div class="form-group">
  <div class="form-group">
    <label for="title" class="col-sm-2 control-label">Title</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" id="type" placeholder="Title" name="type" value="${feedback.type}" required>
    </div>
  </div>
  <div class="form-group">
    <label for="description" class="col-sm-2 control-label">Description</label>
    <div class="col-sm-6">
      <textarea class="form-control" id="description" placeholder="Description" name="description" required>${feedback.description}</textarea>
    </div>
  </div>

  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-6">
          <button class="btn btn-success btn-sm" id="btnSuccess"><i class="fa fa-floppy-o"></i> Save</button>
          <a href="${pageContext.request.contextPath}/crime/list" class="btn btn-danger btn-sm"><i class="fa fa-times"></i> Cancel</a>
    </div>
  </div>
</form>
</div>
</div>
<%@ include file="../common/footer.jsp"%>

