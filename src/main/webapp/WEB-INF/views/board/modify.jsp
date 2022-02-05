<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>


<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">Board Modify/Delete</h1>
  </div>
  <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">

      <div class="panel-heading">Board Modify/Delete</div>
      <!-- /.panel-heading -->
      
      <div class="panel-body">
      <form>
      	<div class="form-group">
        	<label>Bno</label> 
        	<input class="form-control" name='bno'
            value='<c:out value="${board.bno }"/>' readonly="readonly">
        </div>

        <div class="form-group">
        	<label>Title</label> 
            <input class="form-control" name='title' value='<c:out value="${board.title }"/>' readonly="readonly">
        </div>

        <div class="form-group">
          <label>Text area</label>
          <textarea class="form-control" rows="3" name='content'
            readonly="readonly"><c:out value="${board.content}" /></textarea>
        </div>

        <div class="form-group">
          <label>Writer</label> 
          <input class="form-control" name='writer' value='<c:out value="${board.writer }"/>' readonly="readonly">
        </div>


		<button class="btn btn-default" data-oper='modify'>Modify</button>
		<button class="btn btn-danger" data-oper='remove'>Remove</button>
		<button class="btn btn-info" data-oper='list'>List</button>
		</form>
	</div>
  </div>
 </div>
<script type="text/javascript">

$(document).ready(function(e) {
	
	var formObj = $("form");
	
	$('.btn').click(function(e){
		e.preventDefault();
		
		var operation = $(this).data("oper");
		console.log(operation);
		
		if (operation ==='list') {
			self.location = "/board/list";
		} else if (operation === 'remove') {
			formObj.attr("action", "/board/remove")
			.attr("method", "post");
			formObj.submit();
		}
	})
	
})

</script>

<%@include file="../includes/footer.jsp"%>
