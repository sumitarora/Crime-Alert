<%@ include file="../common/header.jsp"%>

<div class="container">
<%@ include file="../common/left-menu.jsp"%>
<div class="col-sm-9">

 <h1>Add News</h1>
 <hr>

<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/news/save" method="post">
  <input type="hidden" name="newsId" value="${news.newsId}">
  
  <div class="form-group">
      <input type="text" class="form-control" id="type" placeholder="Title" name="title" value="${news.title}" required>
  </div>
  <div class="form-group">
    <!-- <label for="description" class="col-sm-2 control-label">Description</label> -->
    <!-- <div class="col-sm-6"> -->
      <%-- <textarea class="form-control" id="description" placeholder="Description" name="description" required></textarea> --%>
      	<textarea name="description" id="description" rows="10" cols="80">
         ${news.description}
     	</textarea>
    <!-- </div> -->
  </div>


     <script>
         CKEDITOR.replace( 'description' );
     </script>
  
  <div class="form-group">
    <div>
          <button class="btn btn-success btn-sm" id="btnSuccess"><i class="fa fa-floppy-o"></i> Save</button>
          <a href="${pageContext.request.contextPath}/news/list" class="btn btn-danger btn-sm"><i class="fa fa-times"></i> Cancel</a>
    </div>
  </div>
</form>
</div>
</div>
<%@ include file="../common/footer.jsp"%>

