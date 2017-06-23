<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<section style="background-color: #201B3A;">
	<h2 class="container h1">Games <a href="allgame.op" class="btn pull-right">Mostra tutti</a></h2>
	<div class="youplay-carousel" data-autoplay="5000">
	
	<c:forEach items="${giochi}" var="gioco">
      <a class="angled-img" href="store-product-1.html">
        <div class="img">
          <img src="assets/images/games/${gioco.nome}/cover.jpg" alt="">
        </div>
        <div class="over-info">
          <div>
            <div>
              <h4>${gioco.nome}</h4>
              <div class="rating">
              <c:forEach var="i" begin= "1" end = "5">
              	 <c:choose>
         			<c:when test = "${gioco.mediaGioco >= i}">
           				<i class="fa fa-star"></i>
         			</c:when>
         			<c:otherwise>
           				<i class="fa fa-star-o"></i>
         			</c:otherwise>
      			</c:choose>
              </c:forEach>
              </div>
            </div>
          </div>
        </div>
      </a>
     </c:forEach> 
    </div>
</section>