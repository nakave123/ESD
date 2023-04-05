<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!doctype html>

<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	
<title>New Position</title>

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

	<div class="container">

		<div class="py-5 text-center">
			<h2>New Position</h2>
		</div>
		
		<div class="row justify-content-md-center">
		
			<div class="col-md-8 order-md-1">
				<h4 class="mb-3">Details</h4>
				<hr class="mb-4">
				
				<form:form modelAttribute="position" action="${cp}/new-position" method="post" enctype="multipart/form-data">

					<div class="mb-3">
						<label for="title">Title</label>
						<form:input type="text" class="form-control" path="title" name="title" id="title" required="required" />
					</div>

					<div class="row">
					
						<div class="col-md-4 mb-3">
							<label for="company">Company</label>
							<form:input path="company" name="company" type="text" class="form-control" id="company" required="required" />
						</div>

						<div class="col-md-4 mb-3">
							<label for="state">State</label>
							<form:input type="text" path="state" name="state" class="form-control" id="state" required="required" />
						</div>
						
						<div class="col-md-4 mb-3">
							<label for="city">City</label>
							<form:input type="text" path="city" name="city" class="form-control" id="city" required="required" />
						</div>
						
					</div>
					
					<div class="mb-3">
						<label for="logo">Company Logo</label>
						<input type="file" class="form-control" name="file" id="logo"/>
					</div>

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="openings">Openings</label>
							<form:input path="openings" name="openings" type="numble" class="form-control" id="openings" required="required" />
						</div>

						<div class="col-md-6 mb-3">
							<label for="salary">Salary/Month</label>
							<form:input type="numble" path="salary" name="salary" class="form-control" id="salary" required="required" />
						</div>
					</div>

					<div class="row">
						<div class="col-md-4 mb-3">
							<label for="category">Category</label>
							<form:select class="custom-select d-block w-100" path="category" name="category" id="category" required="required">
								<option value="">Choose...</option>
								<form:option value="Front end" selected="selected">Front end</form:option>
								<form:option value="SDE">SDE</form:option>
								<form:option value="DevOps">DevOps</form:option>
							</form:select>
						</div>

						<div class="col-md-4 mb-3">
							<label for="jobType">Job Type</label>
							<form:select class="custom-select d-block w-100" path="jobType" name="jobType" id="jobType" required="required">
								<option value="">Choose...</option>
								<form:option value="Intern" selected="selected">Intern</form:option>
								<form:option value="Part-time">Part-time</form:option>
								<form:option value="Full-time">Full-time</form:option>
							</form:select>
						</div>
						
						<div class="col-md-4 mb-3">
							<label for="level">Level</label>
							<form:select class="custom-select d-block w-100" path="level" name="level" id="level" required="required">
								<option value="">Choose...</option>
								<form:option value="Senior" selected="selected">Senior</form:option>
								<form:option value="Mid">Mid</form:option>
								<form:option value="Entry">Entry</form:option>
							</form:select>
						</div>
					</div>

					<div class="mb-3">
						<label for="qualifications">Qualifications</label>
						<form:textarea name="qualifications" path="qualifications" class="form-control" id="qualifications" required="required"></form:textarea>
					</div>

					<div class="mb-3">
						<label for="responsibilities">Responsibilities</label>
						<form:textarea name="responsibilities" path="responsibilities" class="form-control" value="test" id="responsibilities" required="required"></form:textarea>
					</div>

					<div class="mb-3">
						<label for="closeDate">Close Date</label>
						<form:input type="text" class="form-control" path="closeDate" name="closeDate" id="closeDate" required="required" />
					</div>

					<hr class="mb-4">
					<button class="btn btn-primary btn-lg btn-block" type="submit">Save & Post</button>
				</form:form>
			</div>
		</div>


	</div>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

	<script type="text/javascript">
		$(document).ready(function(){
			
			$('#closeDate').datepicker();
			
		});
	</script>
</body>
</html>