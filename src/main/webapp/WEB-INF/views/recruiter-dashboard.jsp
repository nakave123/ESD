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
  <title>Recruiter Dashboard</title>

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


	<sec:authorize access="hasAuthority('applicant')">
	      <li class="nav-item">
	        <a class="nav-link" href="${cp}/applicant-dashboard">Dashboard</a>
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


   
    
      <main class="jumbotron">
        <div class="container">
          <h1 class="display-3">Hello, ${recruiter.username}!</h1>
          <p>
            <a class="btn btn-primary btn-lg" href="${cp}/new-position"  role="button">New Position &raquo;</a>
            <a class="btn btn-primary btn-lg" href="${cp}/seek-talents"  role="button">Seek Talents &raquo;</a>
          </p>
        </div>
        
        
        <div class="container">
        <hr>
       
            	<div class="row">
            		<c:forEach items="${positions}" var="position">
			          <div class="col-md-4">
			            <h2>${position.title}</h2>
			            <p>Number Of Application: ${position.numberOfApplications}</p>
			            <p>Create Date: ${position.postDate}</p>
			            <p>Close Date: ${position.closeDate}</p>
			            <a href="${cp}/position/${position.id}" class="btn btn-secondary">View details &raquo;</a>
			            <c:if test="${position.numberOfApplications>0}">
			            	<a href="${cp}/position/${position.id}/applications" class="btn btn-secondary">Review Applications</a>
			            </c:if>
			          </div>
			        </c:forEach>
            	</div>
        </div>
        
      </main>


	<script
	src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
</body>
</html>