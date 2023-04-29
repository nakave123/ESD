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
<title>Check My Mentees</title>

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
					
					<sec:authorize access="hasAuthority('mentor')">
						<li class="nav-item"><a class="nav-link"
							href="${cp}/mentor-dashboard">Dashboard</a></li>
					</sec:authorize>

					<sec:authorize access="hasAuthority('admin')">
						<li class="nav-item"><a class="nav-link"
							href="${cp}/admin-dashboard">Dashboard</a></li>
					</sec:authorize>

				</ul>


				<sec:authorize access="!isAuthenticated()">
					<a href="${cp}/login" class="btn btn-outline-success mx-2 my-2 my-sm-0"
						role="button">Log In</a>'
      			</sec:authorize>

				<sec:authorize access="isAuthenticated()">
					<a href="${cp}/logout" class="btn btn-outline-success mx-2 my-2 my-sm-0"
						role="button">Log Out</a>
				</sec:authorize>

			</div>
		</div>
	</nav>


	<div class="container" style="margin-top: 100px;background-color: beige;">
		<c:choose>
			<c:when test="${not empty mentees}">
				<table class="table table-hover">
					<h3>My Mentees</h3>
					<thead class="thead-light">
						<tr>
							<th scope="col">Name</th>
							<th scope="col">Email</th>
							<th scope="col">Mobile</th>
							<th scope="col">Resume</th>
							<th scope="col">Slot Id</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${mentees}" var="mentee">
							<tr>
								<c:forEach items="${mentee.resumes}" var="resume">
									<td>${resume.fname}${resume.lname}</td>
									<td>${resume.email}</td>
									<td>${resume.mob}</td>
									<td><a href="${cp}/view-resume/${resume.id}">Resume-${resume.id}</a></td>

								</c:forEach>
								<c:choose>
									<c:when test="${not empty mentee.timeSlot.id}">
										<td>${mentee.timeSlot.id}</td>
									</c:when>
									<c:otherwise>
										<td>N/A</td>
									</c:otherwise>
								</c:choose>
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


	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>


</body>
</html>