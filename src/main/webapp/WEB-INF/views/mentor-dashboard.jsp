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
  <title>Mentor Dashboard</title>

</head>

<body class="bg-light">


<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">

    <a class="navbar-brand" href="#">Job Board</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
    	<li class="nav-item">
        	<a class="nav-link" href="${cp}/seek-jobs">Seek Jobs</a>
      	</li>


	<sec:authorize access="hasAuthority('mentor')">
	      <li class="nav-item">
	        <a class="nav-link" href="mentor">Dashboard</a>
	      </li>
	</sec:authorize>    
	
	<sec:authorize access="hasAuthority('recruiter')">
	      <li class="nav-item">
	        <a class="nav-link" href="${cp}/recruiter-dashboard">Dashboard</a>
	      </li>
	</sec:authorize>   
      
    </ul>


		<%-- <a href="${cp}/register" class="btn btn-primary mx-2 my-2 my-sm-0" role="button">Register</a> --%>
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
          <h1 class="display-3">Hello, ${mentor.username}!</h1>
          <p>
            <a class="btn btn-primary btn-lg" href="${cp}/new-resume-mentor"  role="button">New Resume &raquo;</a>
            <a class="btn btn-primary btn-lg" href="${cp}/new-mentor-slot"  role="button">New Slots &raquo;</a>
            <a class="btn btn-primary btn-lg" href="${cp}/view-applicants"  role="button">View Applicants &raquo;</a>
            <button id="remove-button" onClick()="onclick" value="${mentor.id}">Deactivate Account</button>
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

            	<div>
            		<h2>Time Slots Details</h2>
				        		
				        		<table class="table table-hover">
				        			<thead class="thead-light">
									    <tr>
									      <th scope="col">Slot Id</th>
									   	  <th scope="col">Date</th>
									   	  <th scope="col">Start time</th>
									   	  <th scope="col">End Time</th>
									      <th scope="col">Capacity</th>
									      <!-- <th scope="col">Action</th> -->
									    </tr>
									</thead>
				        				<tbody>
				        					<c:forEach items="${slots}" var="slot">
					        					<tr>
				        							<td>${slot.id}</td>
								        			<td>${slot.date}</td>
								        			<td>${slot.start}</td>
								        			<td>${slot.end}</td>
								        			<td>${slot.capacity}</td>
								        			<!-- <td><button>Book</button></td> -->
								        		</tr>
							        		</c:forEach>
					        			
				        				</tbody>
				        		</table>
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
	            var url = 'http://localhost:8080/delete-mentor/'+id;
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

		</script>
</body>
</html>