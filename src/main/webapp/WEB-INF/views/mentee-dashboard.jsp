<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="cp" value="${pageContext.request.contextPath}"/>

<!doctype html>

<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <title>Mentee Dashboard</title>

</head>

<body class="bg-light">


<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">

    <a class="navbar-brand" href="#">Kampus2go</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
    	<li class="nav-item">
        	<a class="nav-link" href="${cp}/find-jobs">Find Jobs</a>
      	</li>


	<sec:authorize access="hasAuthority('mentee')">
	      <li class="nav-item">
	        <a class="nav-link" href="${cp}/mentee-dashboard">Dashboard</a>
	      </li>
	</sec:authorize>    
	
	<sec:authorize access="hasAuthority('admin')">
	      <li class="nav-item">
	        <a class="nav-link" href="${cp}/admin-dashboard">Dashboard</a>
	      </li>
	</sec:authorize>   
      
    </ul>

	<sec:authorize access="!isAuthenticated()">
      	<a href="${cp}/login" class="btn btn-primary mx-2 my-2 my-sm-0" role="button">Log In</a>'
      </sec:authorize>

	<sec:authorize access="isAuthenticated()">
	      <a href="${cp}/logout" class="btn btn-primary mx-2 my-2 my-sm-0" role="button">Log Out</a>
	</sec:authorize>

  </div>
  </div>
</nav>
      <main class="jumbotron">
        <div class="container">
          <h1 class="display-3">Hello, ${mentee.username}!</h1>
          <p>
            <a class="btn btn-primary btn-lg" href="${cp}/new-resume-mentee"  role="button">New Resume &raquo;</a>
            <a class="btn btn-primary btn-lg" href="${cp}/find-jobs"  role="button">Find Jobs &raquo;</a>
            <a class="btn btn-primary btn-lg" href="${cp}/check-applications"  role="button">Check Applications &raquo;</a>
            
            <c:choose>
            	<c:when test="${not empty mentee.mentor}">
            		
            		<%-- <button id="remove-button" onClick()="onclick" value="${mentee.id}">Remove Mentor</button> --%>
            	</c:when>
            	<c:otherwise>
					<a class="btn btn-primary btn-lg" href="${cp}/find-mentors"  role="button">Find Mentor &raquo;</a>
				</c:otherwise>	
            </c:choose>
            <button id="deactivate-button" onClick()="onclick" value="${mentee.id}">Deactivate Account</button>
            
          </p>
        </div>
        
        <div class="container">
        <hr>

            	<div class="row">
            		<c:forEach items="${resumes}" var="resume">
			          <div class="col-md-4">
			            <h2>${resume.title}</h2>
			            <p>Objective: ${resume.objective}</p>
			            <p>Create Date: ${resume.createDate}</p>
			            <a href="${cp}/view-resume/${resume.id}" class="btn btn-secondary">View details &raquo;</a>
			          </div>
			        </c:forEach>
            	</div>
            	
          <hr>

            	<div class="row">
            	<c:choose>
	            	<c:when test="${not empty mentee.mentor}">
	            		<%-- <c:forEach items="${mentee.mentor}" var="mentor"> --%>
				          <div class="col-md-4">
				            <h2>Mentor: ${mentee.mentor.resumes[0].firstname}</h2>
				            <p>Objective: ${mentee.mentor.resumes[0].objective}</p>
				            <p>Years of Exp: ${mentee.mentor.resumes[0].yearsOfExperience}</p>
				            <button id="remove-button" onClick()="onclick" value="${mentee.id}">Remove Mentor</button>
				          </div>
				        <%-- </c:forEach> --%>
				        	<div class="col-md-8">
				        		<h2>Time Slots Available</h2>
				        		
				        		<table class="table table-hover">
				        			<thead class="thead-light">
									    <tr>
									   	  <th scope="col">Date</th>
									   	  <th scope="col">Start time</th>
									   	  <th scope="col">End Time</th>
									      <th scope="col">Capacity</th>
									      <th scope="col">Action</th>
									    </tr>
				        				<tbody>
					        				<c:forEach items="${slots}" var="slot">
				        						<tr>
								        			<td>${slot.date}</td>
								        			<td>${slot.start}</td>
								        			<td>${slot.end}</td>
								        			<td>${slot.capacity}</td>
								        			<td>
								        			<c:choose>
								        				<c:when test="${not empty mentee.timeSlot}">
								        					<button id="cancel-button" onClick()="onclick" value="${slot.id}">Cancel</button>
								        				</c:when>
								        				<c:otherwise>
															<button id="book-button" onClick()="onclick" value="${slot.id}">Book</button>
														</c:otherwise>
													</c:choose>
								        			</td>
								        		</tr>
							        		</c:forEach>
				        				</tbody>
				        			</thead>
				        		</table>
				        		
				        	</div>
				     </c:when>
				     <c:otherwise>
						You have no mentor assigned yet!
					</c:otherwise>	
				</c:choose>
            	</div>
        </div>
        
      </main>


	<script
	src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
	
	
	<script>
 			$("#remove-button").click(function(event){
 				console.log("Inside AJAX");
	            event.preventDefault();
	            var form = $(this);
	            var id = $("#remove-button").val();
	            console.log(id);
	            var url = 'http://localhost:8080/update-mentee/'+id;
	            console.log(url);
	
	            $.ajax({
	                type : 'PUT',
	                url : url,
	                contentType: 'application/x-www-form-urlencoded',
	                data : "id="+id,
	                success : function(data, status, xhr){
	                   /* $("#result").html(data+
	                   " link: <a href='"+url+"'>"+url+"</a>"); */
	                   window.location = "http://localhost:8080/mentee-dashboard/";
	                   console.log("Success!");
	                },
	                error: function(xhr, status, error){
	                  //alert(error);
	                  	window.location = "http://localhost:8080/mentee-dashboard/";
	                	console.log("Error!",error );
	                }
		            });
		        });
 			
 			$("#deactivate-button").click(function(event){
 				console.log("Inside AJAX");
	            event.preventDefault();
	            var form = $(this);
	            var id = $("#deactivate-button").val();
	            console.log(id);
	            var url = 'http://localhost:8080/delete-mentee/'+id;
	            console.log(url);
	
	            $.ajax({
	                type : 'DELETE',
	                url : url,
	                contentType: 'application/x-www-form-urlencoded',
	                data : "id="+id,
	                success : function(data, status, xhr){
	                   /* $("#result").html(data+
	                   " link: <a href='"+url+"'>"+url+"</a>"); */
	                   console.log("Success!");
	                },
	                error: function(xhr, status, error){
	                  //alert(error);
	                	console.log("Error!",error );
	                }
		            });
		        });
 			
 			
 			$("#book-button").click(function(event){
 				console.log("Inside AJAX");
	            event.preventDefault();
	            var form = $(this);
	            var id = $("#book-button").val();
	            console.log(id);
	            var url = 'http://localhost:8080/mentee-slot-book/'+id;
	            console.log(url);
	
	            $.ajax({
	                type : 'PUT',
	                url : url,
	                contentType: 'application/x-www-form-urlencoded',
	                data : "id="+id,
	                success : function(data, status, xhr){
	                   /* $("#result").html(data+
	                   " link: <a href='"+url+"'>"+url+"</a>"); */
	                   alert("Slot Booked!");
	                   window.location = "http://localhost:8080/mentee-dashboard/";
	                   console.log("Success!");
	                },
	                error: function(xhr, status, error){
	                  	//alert("Error- ",error);
	                  	window.location = "http://localhost:8080/mentee-dashboard/";
	                	console.log("Error!",error );
	                }
		            });
		        });
 			
 			$("#cancel-button").click(function(event){
 				console.log("Inside AJAX");
	            event.preventDefault();
	            var form = $(this);
	            var id = $("#cancel-button").val();
	            console.log(id);
	            var url = 'http://localhost:8080/mentee-slot-cancel/'+id;
	            console.log(url);
	
	            $.ajax({
	                type : 'PUT',
	                url : url,
	                contentType: 'application/x-www-form-urlencoded',
	                data : "id="+id,
	                success : function(data, status, xhr){
	                   /* $("#result").html(data+
	                   " link: <a href='"+url+"'>"+url+"</a>"); */
	                   alert("Slot cancelled!");
	                   window.location = "http://localhost:8080/mentee-dashboard/";
	                   console.log("Success!");
	                },
	                error: function(xhr, status, error){
	                  	//alert("Error- ",error);
	                  	window.location = "http://localhost:8080/mentee-dashboard/";
	                	console.log("Error!",error );
	                }
		            });
		        });

		</script>
		
		
</body>
</html>