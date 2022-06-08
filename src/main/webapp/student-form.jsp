<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Management Application</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
  <header>
   <nav class="navbar navbar-expand-lg bg-primary">
      <div class="container-fluid">
        <a class="navbar-brand" href="#" style="font-weight: 900;">Student Management Application</a>
        <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="<%=request.getContextPath()%>/list"><b>Students</b></a>
        </li>
      </ul>
     </div>  
    </nav>
  </header> 
 <br>
 <div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${student != null}">
					<form action="updateStudent" method="post">
				</c:if>
				<c:if test="${student == null}">
					<form action="insertStudent" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${student != null}">
            			Edit Student Details
            		    </c:if>
						<c:if test="${student == null}">
            			Add New Student
            		    </c:if>
					</h2>
				</caption>

				<c:if test="${student != null}">
					<input type="hidden" name="id" value="<c:out value='${student.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Student Name</label> <input type="text"
						value="<c:out value='${student.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Student Email</label> <input type="text"
						value="<c:out value='${student.email}' />" class="form-control"
						name="email">
				</fieldset>

				<fieldset class="form-group">
					<label>Subject</label> <input type="text"
						value="<c:out value='${student.subject}' />" class="form-control"
						name="subject">
				</fieldset>
				<br>
                <div class="d-flex justify-content-center">
				    <button type="submit" class="btn btn-success" id="click"  >Save</button> 
				</div>
				</form>
			</div>
		</div>
	</div>
    <script > $('#click').on('click',function(){
    	Swal.fire({
    		  icon: 'success',
    		  title: 'Student details has been saved!!',
    		  showConfirmButton: false,
    		  timer: 120000
    		})

		
	})</script>

</body>
</html>