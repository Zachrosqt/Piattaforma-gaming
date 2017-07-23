<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Banner -->
    <div class="youplay-banner youplay-banner-parallax banner-top">
    <c:set var="first" value="${gioco[0]}"/>
    <c:set var="firstimage" value="${gallery[0]}"/>
      <div class="image" style="background-image: url('assets/images/games/${first.nome}/${firstimage.path}')">
      </div>

      <div class="info">
        <div>
          <div class="container">
            <h1>${first.nome}</h1>
            <br>
            <a href="${first.nome}.op?username=${utenteGameplatform.username}" target="_blank" class="btn btn-lg" title="Add to Cart">Gioca</a>
          </div>
        </div>
      </div>
    </div>
    <!-- /Banner -->

<!-- Slider con le immagini del gioco -->
    <!-- Images With Text -->
    <div class="youplay-carousel gallery-popup">
    <c:forEach items="${gallery}" var="immagine">
         
      <a class="angled-img" href="assets/images/games/${first.nome}/${immagine.path}">
        <div class="img">
          <img src="assets/images/games/${first.nome}/${immagine.path}" alt="">
        </div>
        <i class="fa fa-search-plus icon"></i>
      </a>
    </c:forEach>
    </div>
    <!-- /Images With Text -->