<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<title>New Resume</title>

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



	<div class="container" style="background-color: beige;">

		<div class="py-5 text-center">
			<h2>New Resume</h2>
		</div>

		<div class="row justify-content-md-center">

			<div class="col-md-8 order-md-1">


				<form:form modelAttribute="resume" class="needs-validation"
					action="${cp}/new-resume-mentor" method="post">

					<section>
						<h4 class="mb-3">General Information</h4>
						<hr class="mb-4">

						<div class="mb-3">
							<label for="title">Title</label>
							<form:input type="text" class="form-control" path="title"
								name="title" id="title" required="required" />
						</div>

						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="objective">Objective</label>
								<form:select class="custom-select d-block w-100"
									path="objective" name="objective" id="objective"
									required="required">
									<option value="">Choose...</option>
									<form:option value="Front end" selected="selected">Front end</form:option>
									<form:option value="SDE">SDE</form:option>
									<form:option value="DevOps">DevOps</form:option>
								</form:select>
							</div>

							<div class="col-md-6 mb-3">
								<label for=yearsOfExperience>Years Of Experience</label>
								<form:input path="yearsOfExperience" name="yearsOfExperience"
									type="numble" class="form-control" id="yearsOfExperience"
									required="required" />
							</div>
						</div>
					</section>

					<section>
						<h4 class="mb-3">Contact</h4>
						<hr class="mb-4">
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="fname">First Name</label>
								<form:input path="fname" name="fname" type="text"
									class="form-control" id="fname" required="required" />
							</div>

							<div class="col-md-6 mb-3">
								<label for="lname">Last Name</label>
								<form:input type="text" path="lname" name="lname"
									class="form-control" id="lname" required="required" />
							</div>
						</div>

						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="mob">Mob</label>
								<div class="input-group">
									<form:input type="mob" name="mob" path="mob"
										class="form-control" id="mob" required="required" />
								</div>
							</div>

							<div class="col-md-6 mb-3">
								<label for="email">Email</label>
								<form:input type="email" path="email" name="email"
									class="form-control" id="email" />
							</div>
						</div>
					</section>

					<section>
						<h4 class="mb-3">
							Education
							<button type="button" class="btn btn-outline-success add float-right">Add</button>
						</h4>
						<main class="addlist edus">
							<hr class="mb-4">
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="from">Start Year</label> <input type="month"
										class="form-control eduYearFrom" name="educationFrom" id="from"
										placeholder="" value="" required="required" />
								</div>
								<div class="col-md-6 mb-3">
									<label for="to">End Year</label> <input type="month"
										class="form-control eduYearTo" id="to" name="educationTo"
										placeholder="" value="" required="required" />
								</div>
							</div>

							<div class="row">
								<div class="col-md-4 mb-3">
									<label for="university">University</label> <input type="text"
										class="form-control university" name="university"
										required="required" />
								</div>

								<div class="col-md-4 mb-3">
									<label for="degree">Degree</label> <select
										class="custom-select d-block w-100" name="degree" id="degree"
										required="required">
										<option value="">Choose...</option>
										<option value="Bachelor" selected="selected">Bachelor</option>
										<option value="Master">Master</option>
										<option value="PHD">PHD</option>
									</select>
								</div>

								<div class="col-md-4 mb-3">
									<label for="course">Course</label> <input type="text"
										class="form-control course" name="course" required="required" />
								</div>
							</div>

						</main>
					</section>

					<section>
						<h4 class="mb-3">
							Experience
							<button type="button" class="btn btn-outline-success float-right add">Add</button>
						</h4>
						<main class="addlist exps">
							<hr class="mb-4">

							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="from">Start Year</label> <input type="month"
										class="form-control expYearFrom" name="experienceFrom" id="from"
										required="required" />
								</div>
								<div class="col-md-6 mb-3">
									<label for="to">End Year</label> <input type="month"
										class="form-control expYearTo" name="experienceTo" id="to"
										required="required" />
								</div>
							</div>

							<div class="row">
								<div class="col-md-4 mb-3">
									<label for="organization">Organization</label> <input type="text"
										class="form-control organization" name="organization" id="organization"
										required="required" />
								</div>
								<div class="col-md-4 mb-3">
									<label for="job">Job</label> <input type="text"
										class="form-control job" name="job" id="job"
										required="required" />
								</div>
								<div class="col-md-4 mb-3">
									<label for="category">Category</label> <select
										class="custom-select d-block w-100" name="category"
										id="category" required="required">
										<option value="">Choose...</option>
										<option value="Front end" selected="selected">Front
											end</option>
										<option value="SDE">SDE</option>
										<option value="DevOps">DevOps</option>
									</select>
								</div>
							</div>

							<div class="mb-3">
								<label for="responsibilities">Responsibilities</label>
								<textarea name="responsibilities" class="form-control"
									id="responsibilities"></textarea>
							</div>

						</main>
					</section>

					<hr class="mb-4">
					<button class="btn btn-outline-success btn-lg btn-block" type="submit">Save</button>

				</form:form>



			</div>
		</div>

	</div>


	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>

	<script type="text/javascript">
		$(document).ready(function(){

			$(".add").click(function (event) {
				var $clone = $(this).parent().next().clone();
				$clone.find("input").val("");
				$clone.insertAfter($(this).parent().parent());
			});

		});
		
	</script>
</body>
</html>