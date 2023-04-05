<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="cp" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <title>View Position</title>
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
	        <a href="${cp}/logout" class="nav-link" href="${cp}/recruiter-dashboard">Dashboard</a>
	      </li>
	</sec:authorize>   
      
    </ul>


		<a href="${cp}/register" class="btn btn-primary mx-2 my-2 my-sm-0" role="button">Register</a>
	<sec:authorize access="!isAuthenticated()">
      	<a href="${cp}/login" class="btn btn-primary mx-2 my-2 my-sm-0" role="button">Log In</a>'
      </sec:authorize>

	<sec:authorize access="isAuthenticated()">
	      <a class="btn btn-primary mx-2 my-2 my-sm-0" role="button">Log Out</a>
	</sec:authorize>

  </div>
  </div>
</nav>

<div class="container">
      <div class="row justify-content-md-center">
        <div class="col-md-8 order-md-1">
			<h2 class="py-5 text-center">Detailed Information</h2>
          <div class="card">
            <!-- <img class="card-img-top" src="" alt="Card image cap"> -->
            <div class="card-body">
              <h5 class="card-title">${position.title}</h5>
              <p class="card-text">${position.postDate}</p>
            </div>
            
            <ul class="list-group list-group-flush">
            	<li class="list-group-item">Company: ${position.company}</li>
            	<li class="list-group-item">Location: ${position.city}, ${position.state}</li>
              <li class="list-group-item">Category: ${position.category}</li>
              <li class="list-group-item">Job Type: ${position.jobType}</li>
              <li class="list-group-item">Level: ${position.level}</li>
              <li class="list-group-item">Openings: ${position.openings}</li>
              <li class="list-group-item">Salary: ${position.salary}</li>            
              <li class="list-group-item">qualifications: ${position.qualifications}</li>
              <li class="list-group-item">responsibilities: ${position.responsibilities}</li>
              <li class="list-group-item">Close Date: ${position.closeDate}</li>
            </ul>
          </div>
          
<sec:authorize access="!isAuthenticated()">
  <a class="btn btn-primary btn-lg btn-block" href="${cp}/login"  role="button">Login To Apply</a>
</sec:authorize>

<sec:authorize access="hasAuthority('applicant')">
	<c:choose>
		<c:when test="${not empty applicant.resumes}">
			<button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="modal" data-target="#exampleModal">Apply</button>
		</c:when>
		<c:otherwise>
			 <a class="btn btn-primary btn-lg btn-block" href="${cp}/new-resume"  role="button">Add A Resume To Apply</a>
		</c:otherwise>
	</c:choose>
</sec:authorize>
   
        </div>
      </div>
      
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		    
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">New Application</h5>
		        <button type="button" class="close" data-dismiss="modal">
		          <span>&times;</span>
		        </button>
		      </div>
		      
		      <form id="applyForm" action="${cp}/position/${position.id}/apply" method="POST">
		      <div class="modal-body">

		          <div class="form-group">
	                  <label for="resumes">Select From My Resumes:</label>
	                  <select class="custom-select d-block w-100" name="resumeId" id="resumes" required="required">
						<c:forEach items="${applicant.resumes}" var="resume">
	                    	<option value="${resume.id}">${resume.title}</option>
	                    </c:forEach> 
					  </select>
                  </div>
		        
		      </div>
		      
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-primary" id="apply" data-dismiss="modal">Apply</button>
		      </div>
		      
		      </form>
		      
		    </div>
		  </div>
		</div>      
</div>

  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script type="text/javascript">
$( document ).ready(function() {
    $("#apply").click(function() {
 	   $("#applyForm").submit();
   });
});
</script>
</body>
</html>