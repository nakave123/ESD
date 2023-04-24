<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!doctype html>

<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <title>Check My Mentees</title>

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
    	<%-- <li class="nav-item">
        	<a class="nav-link" href="${cp}/find-jobs">Find Jobs</a>
      	</li> --%>


	<sec:authorize access="hasAuthority('mentor')">
	      <li class="nav-item">
	        <a class="nav-link" href="${cp}/mentor-dashboard">Dashboard</a>
	      </li>
	</sec:authorize>    
	
	<sec:authorize access="hasAuthority('admin')">
	      <li class="nav-item">
	        <a class="nav-link" href="${cp}/admin-dashboard">Dashboard</a>
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


      <div class="container" style="margin-top: 100px">
<c:choose>
<c:when test="${not empty mentees}">      
		<table class="table table-hover">
		  <h3>My Mentees</h3>
		  <thead class="thead-light">
		    <tr>
		   	  <th scope="col">Name</th>
		   	  <th scope="col">Email</th>
		   	  <th scope="col">Telephone</th>
		      <th scope="col">Resume</th>
		      <th scope="col">Slot Id</th>
		    </tr>
		  </thead>
		  <tbody>
<c:forEach items="${mentees}" var="mentee">		  
		    <tr>
		      
		      <%-- <td><a href="${cp}/position/${application.position.id}">${application.position.title}</a></td> --%>
		  	  <c:forEach items="${mentee.resumes}" var="resume">
		  	    <td>${resume.firstname} ${resume.lastname}</td>
		  	    <td>${resume.email}</td>
		  	    <td>${resume.tel}</td>
		  	  	<td><a href="${cp}/view-resume/${resume.id}">Resume-${resume.id}</a></td>
		  	  	
		  	  </c:forEach>
		  	  <td>${mentee.timeSlot.id}</td>
		  	  <%-- <td><button id="remove-button" onclick="onClick(${mentee.mentor.id},${mentee.id})">Remove</button></td> --%>
		    </tr>
</c:forEach>		    
		  </tbody>
		</table>
</c:when>		
<c:otherwise>
		<p>You have no mentees.</p>
		
</c:otherwise>				
</c:choose>		
      </div>


		<script
	src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
	
	
	<!-- <script>
 			function onClick(m,a){
 				console.log("Inside AJAX");
	            //event.preventDefault();
	            var form = $(this);
	            //var id = $("#remove-button").val();
	            //console.log(id);
	            var url = 'http://localhost:8080/update-mentor/'+m+'/'+a;
	            console.log(url);
	
	            $.ajax({
	                type : 'PUT',
	                url : url,
	                contentType: 'application/x-www-form-urlencoded',
	                data : "m="+m+"a="+a
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
 -->


</body>
</html>