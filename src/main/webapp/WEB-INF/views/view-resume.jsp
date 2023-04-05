<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="cp" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Resume</title>


<link type="text/css" rel="stylesheet" href="${cp}/resources/css/blue.css" />
<link type="text/css" rel="stylesheet" href="${cp}/resources/css/print.css" media="print"/>
<!--[if IE 7]>
<link href="css/ie7.css" rel="stylesheet" type="text/css" />
<![endif]-->
<!--[if IE 6]>
<link href="css/ie6.css" rel="stylesheet" type="text/css" />
<![endif]-->
<script type="text/javascript" src="${cp}/resources/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${cp}/resources/js/jquery.tipsy.js"></script>
<script type="text/javascript" src="${cp}/resources/js/cufon.yui.js"></script>
<script type="text/javascript" src="${cp}/resources/js/scrollTo.js"></script>
<script type="text/javascript" src="${cp}/resources/js/myriad.js"></script>
<script type="text/javascript" src="${cp}/resources/js/jquery.colorbox.js"></script>
<script type="text/javascript" src="${cp}/resources/js/custom.js"></script>
<script type="text/javascript">
		Cufon.replace('h1,h2');
</script>
</head>
<body>
<!-- Begin Wrapper -->
<div id="wrapper">
  <div class="wrapper-top"></div>
  <div class="wrapper-mid">
    <!-- Begin Paper -->
    <div id="paper">
      <div class="paper-top"></div>
      <div id="paper-mid">
        <div class="entry">
          <!-- Begin Image -->
          <img class="portrait" src="${cp}/resources/images/image.jpg" alt="John Smith" />
          <!-- End Image -->
          <!-- Begin Personal Information -->
          <div class="self">
            <h1 class="name">${resume.firstname} ${resume.lastname}<br />
              <span>Years of Experience:${resume.yearsOfExperience}</span></h1>
            <ul>
              <li class="mail">${resume.email}</li>
              <li class="tel">${resume.tel}</li>
            </ul>
          </div>
          <!-- End Personal Information -->
          <!-- Begin Social -->
          <div class="social">
            <ul>
              <li><a class='north' href="#" title="Download .pdf"><img src="${cp}/resources/images/icn-save.jpg" alt="Download the pdf version" /></a></li>
              <li><a class='north' href="javascript:window.print()" title="Print"><img src="${cp}/resources/images/icn-print.jpg" alt="" /></a></li>
              <li><a class='north' id="contact" href="contact/index.html" title="Contact Me"><img src="${cp}/resources/images/icn-contact.jpg" alt="" /></a></li>
              <li><a class='north' href="#" title="Follow me on Twitter"><img src="${cp}/resources/images/icn-twitter.jpg" alt="" /></a></li>
              <li><a class='north' href="#" title="My Facebook Profile"><img src="${cp}/resources/images/icn-facebook.jpg" alt="" /></a></li>
            </ul>
          </div>
          <!-- End Social -->
        </div>
        <!-- Begin 1st Row -->
        <div class="entry">
          <h2>OBJECTIVE</h2>
          <p>${resume.objective}</p>
        </div>
        <!-- End 1st Row -->
        <!-- Begin 2nd Row -->
        <div class="entry">
          <h2>EDUCATION</h2>
          
          <c:forEach items="${resume.educations}" var="edu">
          <div class="content">
            <h3>${edu.startYear} - ${edu.endYear}</h3>
            <p>${edu.university}<br />
              <em>${edu.degree}</em>
              <em>${edu.major}</em></p>
          </div>
          </c:forEach>
          
        </div>
        <!-- End 2nd Row -->
        <!-- Begin 3rd Row -->
        <div class="entry">
          <h2>EXPERIENCE</h2>
          
<c:forEach items="${resume.experiences}" var="exp">          
          <div class="content">
            <h3>${exp.startYear} - ${exp.endYear}</h3>
            <p>${exp.company}<br />
              <em>${exp.position}</em></p>
            <ul class="info">
              <li>${exp.responsibilities}</li>
            </ul>
          </div>
</c:forEach>
          
        </div>
        <!-- End 3rd Row -->
        <!-- Begin 4th Row -->
<!--         <div class="entry">
          <h2>SKILLS</h2>
          <div class="content">
            <h3>Software Knowledge</h3>
            <ul class="skills">
              <li>Photoshop</li>
              <li>Illustrator</li>
              <li>InDesign</li>
              <li>Flash</li>
              <li>Fireworks</li>
              <li>Dreamweaver</li>
              <li>After Effects</li>
              <li>Cinema 4D</li>
              <li>Maya</li>
            </ul>
          </div>
        </div> -->
        <!-- End 4th Row -->
         <!-- Begin 5th Row -->
        <!-- Begin 5th Row -->
      </div>
      <div class="clear"></div>
      <div class="paper-bottom"></div>
    </div>
    <!-- End Paper -->
  </div>
  <div class="wrapper-bottom"></div>
</div>
<div id="message"><a href="#top" id="top-link">Go to Top</a></div>
<!-- End Wrapper -->
</body>
</html>
