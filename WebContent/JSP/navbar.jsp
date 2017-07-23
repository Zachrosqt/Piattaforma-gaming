<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!-- Navbar -->
  <nav class="navbar-youplay navbar navbar-default navbar-fixed-top ">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="off-canvas" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="gameplatform.op">
          <img src="assets/images/logo.png" alt="">
        </a>
      </div>
      <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
          <li class="dropdown dropdown-hover ">
            <a href="allgame.op" class="dropdown-toggle" role="button" aria-expanded="false">
                      Giochi
            </a>
          </li>
          <li class="dropdown dropdown-hover ">
            <a href="contact.op" class="dropdown-toggle" role="button" aria-expanded="false">
                      Contattaci
            </a>
          </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li class="dropdown dropdown-hover">
            <a href="#!" class="dropdown-toggle"  role="button" aria-expanded="false">
                      ${utenteGameplatform.nome} ${utenteGameplatform.cognome} <span class="badge bg-default"></span> <span class="caret"></span> <span class="label">Bentornato!</span>
            </a>
            <div class="dropdown-menu">
              <ul role="menu">
              	<c:if test = "${utenteGameplatform.gruppo.nome == 'Admin' || utenteGameplatform.gruppo.nome == 'Moderatore'}"> <!-- Se l'utente è un amministratore o moderatore comparirà la redirezione al backoffice nel menù a tendina relativa all'utente -->
	              	<li><a href="showUser.op">BackOffice <span class="badge pull-right bg-warning"></span></a>
					</li>
				</c:if>
              	
                <li><a href="userprofile.op?user=${utenteGameplatform.username}">Profilo <span class="badge pull-right bg-warning"></span></a>
				</li>
				
                <li><a id = "logout" style="cursor: pointer; cursor: hand;">Log Out</a>
                </li>
              </ul>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <!-- /Navbar -->