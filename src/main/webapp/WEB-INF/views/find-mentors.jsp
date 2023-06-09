<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

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

<title>Find Mentors</title>

</head>

<body class="bg-light">

	<!-- <nav class="navbar navbar-expand-lg navbar-dark bg-dark"> -->
	<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd;">
		<div class="container">

			<a class="navbar-brand" href="#">Kampus2go</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item"><a class="nav-link"
						href="${cp}/find-jobs">Find Jobs</a></li>

					<sec:authorize access="hasAuthority('mentee')">
						<li class="nav-item"><a class="nav-link"
							href="${cp}/mentee-dashboard">Dashboard</a></li>
					</sec:authorize>

					<sec:authorize access="hasAuthority('admin')">
						<li class="nav-item"><a class="nav-link"
							href="${cp}/admin-dashboard">Dashboard</a></li>
					</sec:authorize>

				</ul>


				<sec:authorize access="!isAuthenticated()">
					<a href="${cp}/login" class="btn btn-outline-success mx-2 my-2 my-sm-0"
						role="button">Log In</a>
      			</sec:authorize>

				<sec:authorize access="isAuthenticated()">
					<a href="${cp}/logout" class="btn btn-outline-success mx-2 my-2 my-sm-0"
						role="button">Log Out</a>
				</sec:authorize>

			</div>
		</div>
	</nav>

	<main role="main">

		<section class="jumbotron text-center" style="background-color: beige;">
			<div class="container">
				<h1>Find Mentors</h1>
				<p class="lead text-muted">Sometimes guidance is all we need!</p>

				<form action="${cp}/find-mentors/filter">
					<div class="form-row">

						<div class="col">
							<select class="custom-select d-block w-100" name="objective"
								id="objective" multiple>
								<option value="">Choose Objective</option>
								<option value="Front end">Front end</option>
								<option value="SDE">SDE</option>
								<option value="DevOps">DevOps</option>
							</select>
						</div>

						<div class="col">
							<select class="custom-select d-block w-100" name="experience"
								id="experience">
								<option value="">Choose Experience</option>
								<option value="0">0-1 year</option>
								<option value="1">1-3 year</option>
								<option value="3">3-5 year</option>
								<option value="5">&gt;= 5 year</option>
							</select>
						</div>

						<div class="col">
							<select class="custom-select d-block w-100" name="degree"
								id="degree" multiple>
								<option value="">Choose Degree</option>
								<option value="Bachelor">Bachelor</option>
								<option value="Master">Master</option>
								<option value="Phd">Phd</option>
							</select>
						</div>

						<div class="col">
							<button class="btn btn-outline-success">Filter</button>
						</div>



					</div>
				</form>


			</div>
		</section>

		<div class="album py-5 bg-light">
			<div class="container">

				<div class="row">

					<c:choose>

						<c:when test="${not empty mentors}">
							<c:forEach items="${mentors}" var="mentor">
								<div class="col-md-4">
									<div class="card mb-4 shadow-sm">
										<div class="card-body">
											<h5 class="card-title">Objective - ${mentor.resumes[0].objective}</h5>
											<p class="card-text">Name: ${mentor.resumes[0].fname}
												${mentor.resumes[0].lname}</p>
											<p class="card-text">Experience :
												${mentor.resumes[0].yearsOfExperience} years</p>
											<p class="card-text">Email : ${mentor.resumes[0].email}</p>
											<p class="card-text">Mobile : ${mentor.resumes[0].mob}</p>
											<div
												class="d-flex justify-content-between align-items-center">
												<div class="btn-group">
													<a href="${cp}/view-resume/${mentor.resumes[0].id}" role="button"
														class="btn btn-sm btn-outline-secondary">View</a>
													<button id="set-button" onClick()="onclick"
														value="${mentor.id}">Set</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:when>

						<%-- <c:otherwise>
							<p>No such results.</p>
						</c:otherwise> --%>

					</c:choose>

				</div>
			</div>
		</div>
		
		<div class="album py-5 bg-light">
			<div class="container">

				<div class="row">

					<c:choose>

						<c:when test="${not empty resumes}">
							<c:forEach items="${resumes}" var="resume">
								<div class="col-md-4">
									<div class="card mb-4 shadow-sm">
										<div class="card-body">
											<h5 class="card-title">Objective - ${resume.objective}</h5>
											<p class="card-text">Name: ${resume.fname}
												${resume.lname}</p>
											<p class="card-text">Experience :
												${resume.yearsOfExperience} years</p>
											<p class="card-text">Email : ${resume.email}</p>
											<p class="card-text">Mobile : ${resume.mob}</p>
											<div
												class="d-flex justify-content-between align-items-center">
												<div class="btn-group">
													<a href="${cp}/view-resume/${resume.id}" role="button"
														class="btn btn-sm btn-outline-secondary">View</a>
													<button id="set-button" onClick()="onclick"
														value="${mentor.id}">Set</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:when>

						<%-- <c:otherwise>
							<p>No such results.</p>
						</c:otherwise> --%>

					</c:choose>

				</div>
			</div>
		</div>

	</main>


	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>

	<script>
		$("#set-button")
				.click(
						function(event) {
							console.log("Inside AJAX");
							event.preventDefault();
							var form = $(this);
							var id = $("#set-button").val();
							console.log(id);
							var url = 'http://localhost:8080/request-mentor/'
									+ id;
							console.log(url);

							$
									.ajax({
										type : 'PATCH',
										url : url,
										contentType : 'application/json',
										data : "id=" + id,
										success : function(data, status, xhr) {
											
											window.location = "http://localhost:8080/mentee-dashboard/";
											console.log("Success!");
										},
										error : function(xhr, status, error) {
											//alert(error);
											window.location = "http://localhost:8080/mentee-dashboard/";
											console.log("Error!", error);
										}
									});
						});
	</script>

</body>
</html>