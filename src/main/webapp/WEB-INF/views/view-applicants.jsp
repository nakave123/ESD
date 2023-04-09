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
	        <a class="nav-link" href="${cp}/mentor-dashboard">Dashboard</a>
	      </li>
	</sec:authorize>    
	
	<sec:authorize access="hasAuthority('recruiter')">
	      <li class="nav-item">
	        <a class="nav-link" href="${cp}/recruiter-dashboard">Dashboard</a>
	      </li>
	</sec:authorize>   
      
    </ul>


		<a href="${cp}/register" class="btn btn-primary mx-2 my-2 my-sm-0" role="button">Register</a>
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
<c:when test="${not empty applicants}">      
		<table class="table table-hover">
		  <h3>My Applicants</h3>
		  <thead class="thead-light">
		    <tr>
		   	  <th scope="col">Name</th>
		   	  <th scope="col">Email</th>
		   	  <th scope="col">Telephone</th>
		      <th scope="col">Resume</th>
		    </tr>
		  </thead>
		  <tbody>
<c:forEach items="${applicants}" var="applicant">		  
		    <tr>
		      
		      <%-- <td><a href="${cp}/position/${application.position.id}">${application.position.title}</a></td> --%>
		  	  <c:forEach items="${applicant.resumes}" var="resume">
		  	    <td>${resume.firstname} ${resume.lastname}</td>
		  	    <td>${resume.email}</td>
		  	    <td>${resume.tel}</td>
		  	  	<td><a href="${cp}/view-resume/${resume.id}">Resume-${resume.id}</a></td>
		  	  </c:forEach>
		  	  <%-- <td>${application.status}</td>
		      <td>${application.lastUpdate}</td>
		      <td>${application.message}</td> --%>
		    </tr>
</c:forEach>		    
		  </tbody>
		</table>
</c:when>		
<c:otherwise>
		<p>You have no applicants.</p>
		<%-- <a class="btn btn-primary" href="${cp}/seek-jobs"  role="button">Seek Jobs To Apply &raquo;</a> --%>
</c:otherwise>				
</c:choose>		
      </div>

</body>
</html>