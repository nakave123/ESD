<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!doctype html>

<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <title>Seek Jobs</title>

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

<main role="main">

  <section class="jumbotron text-center">
    <div class="container">
      <h1>Seek Jobs</h1>
      <p class="lead text-muted">Something short and leading about the collection below—its contents</p>
      
      <form action="${cp}/seek-jobs/search" class="form-inline">
      	<input type="text" class="form-control" name="keywords" placeholder="Keywords"/>
      	<button type="submit" class="btn btn-primary my-2">Search</button>
      </form>
      
<form action="${cp}/seek-jobs/filter">
  <div class="form-row">
  
  <div class="col">
							<select class="custom-select d-block w-100" name="category" id="category" multiple>
								<option value="">Choose Category</option>
								<option value="Front end">Front end</option>
								<option value="SDE">SDE</option>
								<option value="DevOps">DevOps</option>
							</select>
  </div>
  
  						<div class="col">
							<select class="custom-select d-block w-100" name="jobType" id="jobType" multiple>
								<option value="">Choose Job Type</option>
								<option value="Intern">Intern</option>
								<option value="Part-time">Part-time</option>
								<option value="Full-time">Full-time</option>
							</select>
						</div>
						
						<div class="col">
							<select class="custom-select d-block w-100" name="level" id="level" multiple>
								<option value="">Choose Level</option>
								<option value="Senior">Senior</option>
								<option value="Mid">Mid</option>
								<option value="Entry">Entry</option>
							</select>
						</div>
  
  
    <div class="col">
      <input type="text" id="location" name="location" class="form-control" placeholder="Location">
    </div>
    
 <div class="col"> 
    <button class="btn btn-primary">Filter</button>
</div>
    
    
    
  </div>
</form>


    </div>
  </section>

  <div class="album py-5 bg-light">
    <div class="container">

      <div class="row">
      
<c:choose>
	      
      <c:when test="${not empty positions}">
      <c:forEach items="${positions}" var="position">
        <div class="col-md-4">
          <div class="card mb-4 shadow-sm">
          	<img class="card-img-top" src="${position.logo}" alt="Card image" style="height:200px;">
            <div class="card-body">
            <h5 class="card-title">${position.title}</h5>
              <p class="card-text">${position.company}</p>
              <p class="card-text">${position.city}, ${position.state}</p>
              <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                  <a href="${cp}/position/${position.id}" role="button" class="btn btn-sm btn-outline-secondary">View</a>
                </div>
                <small class="text-muted">${position.postDate}</small>
              </div>
            </div>
          </div>
        </div> 
	</c:forEach>
	</c:when>
	
	<c:otherwise>
		<p>No such results.</p>
	</c:otherwise>

</c:choose>

      </div>
    </div>
  </div>

</main>

	
		<script
		src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>
		
	</body>
	</html>