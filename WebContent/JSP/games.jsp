<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<section style="background-color: #201B3A;">
	<h2 class="container h1">Games <a href="#!" class="btn pull-right">See More</a></h2>
	<div class="youplay-carousel" data-autoplay="5000">
	
	<c:forEach items="${giochi}" var="gioco">
      <a class="angled-img" href="store-product-1.html">
        <div class="img">
          <img src="assets/images/game-bloodborne-500x375.jpg" alt="">
        </div>
        <div class="over-info">
          <div>
            <div>
              <h4>${gioco.nome}</h4>
              <div class="rating">
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
              </div>
            </div>
          </div>
        </div>
      </a>
     </c:forEach> 
    </div>
</section>