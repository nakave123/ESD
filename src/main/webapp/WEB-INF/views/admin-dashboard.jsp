<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	
<title>Admin Dashboard</title>

</head>

<body class="bg-light">


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




	<main class="jumbotron" style="background-color: beige;">
		<div class="container">
			<h1 class="display-3">Hello, ${admin.username}!</h1>
			<p>
				<a class="btn btn-outline-success btn-lg" href="${cp}/new-job"
					role="button">New Job &raquo;</a> <a
					class="btn btn-outline-success btn-lg" href="${cp}/find-talents"
					role="button">Find Talents &raquo;</a>
			</p>
		</div>


		<div class="container">
			<hr>

			<div class="row">
				<c:forEach items="${jobs}" var="job">
					<div class="col-md-4">
						<h2>${job.title}</h2>
						<p>Number Of Application: ${job.numberOfApplications}</p>
						<p>Company: ${job.company}</p>
						<p>Salary: $${job.salary}/Monthly</p>
						<p>Location: ${job.city},${job.state}</p>
						<p>Create Date: ${job.postDate}</p>
						<p>Close Date: ${job.closeDate}</p>
						<a href="${cp}/job/${job.id}" class="btn btn-secondary">View
							details &raquo;</a>
						<c:if test="${job.numberOfApplications>0}">
							<a href="${cp}/job/${job.id}/applications"
								class="btn btn-secondary">Review Applications</a>
						</c:if>
					</div>
				</c:forEach>
			</div>
		</div>

	</main>


	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>
</body>
</html>