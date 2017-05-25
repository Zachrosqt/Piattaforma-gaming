<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta charset="utf-8">
		 <meta http-equiv="X-UA-Compatible" content="IE=edge">
		
		 <meta name="description" content="">
		 <meta name="viewport" content="width=device-width, initial-scale=1">  
		
		 <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">    

	  <!-- Icon -->
	  <link rel="icon" type="image/png" href="assets/images/icon.png">
	  <!-- Google Fonts -->
	
	  <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700' rel='stylesheet' type='text/css'>
	
	  <!-- Bootstrap -->
	  <link rel="stylesheet" type="text/css" href="assets/bower_components/bootstrap/dist/css/bootstrap.min.css" />
	
	  <!-- FontAwesome -->
	  <!-- <link rel="stylesheet" type="text/css" href="../assets/bower_components/font-awesome/css/font-awesome.min.css" /> -->
	  
	   <!-- Revolution Slider -->
  	  <link rel="stylesheet" type="text/css" href="assets/plugins/slider-revolution/examples%26source/rs-plugin/css/settings.css" />	
	
	  <!-- Owl Carousel -->
	  <link rel="stylesheet" type="text/css" href="assets/bower_components/owl.carousel/dist/assets/owl.carousel.min.css" />
	  
	  <!-- Magnific Popup -->
	  <link rel="stylesheet" type="text/css" href="assets/bower_components/magnific-popup/dist/magnific-popup.css" />
	
	  <!-- Social Likes -->
	  <link rel="stylesheet" type="text/css" href="assets/bower_components/social-likes/dist/social-likes_flat.css" />
	  
	    <!-- Bootstrap Sweetalert -->
	  <link rel="stylesheet" type="text/css" href="assets/bower_components/bootstrap-sweetalert/lib/sweet-alert.css" />
  
	  <!-- Youplay -->
	
	  <link rel="stylesheet" type="text/css" href="assets/youplay/css/youplay.min.css" />
	
	  <!-- Custom Styles -->
	  <link rel="stylesheet" type="text/css" href="assets/css/custom.css" />
		 <!-- RTL (uncomment this to enable RTL support) -->
		 <!-- <link rel="stylesheet" type="text/css" href="../assets/youplay/css/youplay-rtl.css" /> -->

	</head>
	
	<body>
		
		<c:forEach items="${template}" var="temp">
		   <jsp:include page="${temp.indirizzo}" flush="true" />
		</c:forEach>
		
		
		  <!-- jQuery -->
		  <script type="text/javascript" src="assets/bower_components/jquery/dist/jquery.min.js"></script>
		
		  <!-- Hexagon Progress -->
		  <script type="text/javascript" src="assets/bower_components/HexagonProgress/jquery.hexagonprogress.min.js"></script>
		
		
		  <!-- Bootstrap -->
		  <script type="text/javascript" src="assets/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
		
		  <!-- Jarallax -->
		  <script type="text/javascript" src="assets/bower_components/jarallax/dist/jarallax.min.js"></script>
		
		  <!-- Smooth Scroll -->
		  <script type="text/javascript" src="assets/bower_components/smoothscroll-for-websites/SmoothScroll.js"></script>
		  
		  <!-- Revolution Slider -->
		  <script type="text/javascript" src="assets/plugins/slider-revolution/examples%26source/rs-plugin/js/jquery.themepunch.tools.min.js"></script>
		  <script type="text/javascript" src="assets/plugins/slider-revolution/examples%26source/rs-plugin/js/jquery.themepunch.revolution.min.js"></script>
		
		  <!-- ImagesLoaded -->
		  <script type="text/javascript" src="assets/bower_components/imagesloaded/imagesloaded.pkgd.min.js"></script>
		  
		
		  <!-- Owl Carousel -->
		  <script type="text/javascript" src="assets/bower_components/owl.carousel/dist/owl.carousel.min.js"></script>
		
		  <!-- Countdown -->
		  <script type="text/javascript" src="assets/bower_components/jquery.countdown/dist/jquery.countdown.min.js"></script>
		  
		  <!-- Magnific Popup -->
		  <script type="text/javascript" src="assets/bower_components/magnific-popup/dist/jquery.magnific-popup.min.js"></script>
		
		  <!-- Social Likes -->
		  <script type="text/javascript" src="assets/bower_components/social-likes/dist/social-likes.min.js"></script>
		  
		  <!-- Bootstrap Validator -->
		  <script type="text/javascript" src="assets/bower_components/bootstrap-validator/dist/validator.min.js"></script>
		
		  <!-- Bootstrap Sweetalert -->
		  <script type="text/javascript" src="assets/bower_components/bootstrap-sweetalert/lib/sweet-alert.min.js"></script>
		  
		  <!-- Youplay -->
		  <script type="text/javascript" src="assets/youplay/js/youplay.min.js"></script>
		
		  <!-- init youplay -->
		  <script>
		    if(typeof youplay !== 'undefined') {
		        youplay.init({
		            // enable parallax
		            parallax:         true,
		    
		            // set small navbar on load
		            navbarSmall:      false,
		    
		            // enable fade effect between pages
		            fadeBetweenPages: true,
		    
		            // twitter and instagram php paths
		            php: {
		                twitter: './php/twitter/tweet.php',
		                instagram: './php/instagram/instagram.php'
		            }
		        });
		    }
		  </script>
		
	</body>
</html>