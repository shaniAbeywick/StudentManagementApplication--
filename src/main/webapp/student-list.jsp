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
          <a class="nav-link"  href="<%=request.getContextPath()%>/list"><b>Students List</b></a>
        </li>
      </ul> 
      </div>
        
    </nav>
  </header> 
   
    <div class="row">
		
		<div class="container-fluid">
			<h3 class="text-center" >Student List</h3>
			<hr>
			<div class="container text-left">

      <a class="btn btn-primary" href="<%=request.getContextPath()%>/newStudent" role="button" >Add New Student</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Student ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Subject</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach items="${listStudent}" var="student" >

						<tr>
							<td><c:out value="${student.id}" /></td>
							<td><c:out value="${student.name}" /></td>
							<td><c:out value="${student.email}" /></td>
							<td><c:out value="${student.subject}" /></td>
							<td><a href="editStudent?id=<c:out value='${student.id}'/>"  class="btn btn-success">Edit</a>
							
							
								&nbsp;&nbsp;&nbsp;&nbsp; <a href="deleteStudent?id=<c:out value='${student.id}' />"
								 class="btn btn-danger" class="btn-del">Delete</a></td>
								 
						</tr>
					</c:forEach>
		    
				</tbody>
                
			</table>
		</div>
	</div>
	    <script > $('.btn-del').on('click',function(e){
	    	e.preventDefault();
	    	const href = $(this).attr('href')
    	Swal.fire({
    		  
    		  title: 'Are You sure?',
    		  text: 'Student details will be deleted?',		  
    		  type: 'warning',
    		  showCancelButton:true,
    		  confirmBottonColor:'#3085d6',
    		  cancelButtonColor:'#d33,'
    		  confirmButtonText:'Delete Student'
              timer: 120000
    		}).then((result)=>{
    			if(result.value){
    				document.location.href=href;
    			}
    		})

		
	})</script>
	 
</body>
</html>