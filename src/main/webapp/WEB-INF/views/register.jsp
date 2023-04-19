<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!doctype html>

<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/floating-labels.css"/>" >
  <title>Register</title>

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

<form class="form-signin" action="${cp}/register" method="post">
  <div class="text-center mb-4">

    <h1 class="h3 mb-3 font-weight-normal">Join Us</h1>
  </div>

  <div class="form-label-group">
    <input type="text" name="username" id="username" class="form-control" placeholder="Username" required autofocus>
    <label for="username">Username</label>
    <p style="color:red" id="checkUsername"></p>
  </div>

  <div class="form-label-group">
    <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
    <label for="inputPassword">Password</label>
  </div>

      <div class="form-label-group">
        <input type="password" id="password2" class="form-control" placeholder="Confirm Password" required="required"/>
        <label for="password2">Confirm Password</label>
        <p style="display:none;color:red" id="pwdErr">Inconsistent Password</p>
      </div>

      <div class="float-left">
        <input type="radio" name="role" value="applicant" id="applicant" checked/>
        <label for="applicant">Applicant</label>
      </div>
      
      <div class="float-left">
        <input type="radio" name="role" value="mentor" id="mentor"/>
        <label for="mentor">Mentor</label>
      </div>

      <div class="float-right">
        <input type="radio" name="role" value="recruiter" id="recruiter"/>
        <label for="recruiter">Recruiter</label>
      </div>

  <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
</form>
  

<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script> 
  
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script type="text/javascript">
$( document ).ready(function() {
       $("#username").keyup(function() {
    	   var username = $(this).val();
    	   if(username.length>0){
    		   $.ajax({
   				type : "POST",
   				url : "/check-username",
   				data : {
   					"username":username
   				},
   				success : function(msg) {
   					console.log(msg)
   					$("#checkUsername").html(msg);
   				}
   			});
    	   }else{
    		   $("#checkUsername").html("Required");
    	   }
      });
    
	  $("#password2").focusout(function(){
	    var pw1=$("#inputPassword").val();
	    var pw2=$("#password2").val();
	    if(pw1!=pw2){
	    	$("#pwdErr").show();
	    }else{
	      $("#pwdErr").hide();
	    }
	  });
}); 
	  
</script>
</body>
</html>