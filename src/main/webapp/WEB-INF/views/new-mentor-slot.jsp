<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <title>New Resume</title>

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
        <h2>New Slots</h2>
      </div>

      <div class="row justify-content-md-center">
      
        <div class="col-md-8 order-md-1">
        
  
          <form:form modelAttribute="timeslot" class="needs-validation" action="${cp}/new-mentor-slot" method="post">
          
<section>
            <h4 class="mb-3">General Information</h4>
            <hr class="mb-4">
            
            <div class="col-md-6 mb-3">
                <label for="date">Date</label>
                <input type="month" class="form-control date" name="date" id="date" placeholder="" value=""  required="required"/>
            </div>
            
            <div class="mb-3">
              <label for="start">Start</label>
              <form:input type="text" class="form-control" path="start" name="start" id="start" required="required"/>
            </div>
            
            <div class="mb-3">
              <label for="end">End</label>
              <form:input type="text" class="form-control" path="end" name="end" id="end" required="required"/>
            </div>
            
            <div class="mb-3">
				<label for=capacity>Capacity</label>
				<form:input path="capacity" name="capacity" type="numble" class="form-control" id="capacity" required="required" />
			</div>
            
</section>
			
            <hr class="mb-4">
            <button class="btn btn-primary btn-lg btn-block" type="submit" >Save</button>
          
          </form:form>
          
          
          
        </div>
      </div>

    </div>
	
	
	<script
	src="https://code.jquery.com/jquery-3.4.1.min.js"
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