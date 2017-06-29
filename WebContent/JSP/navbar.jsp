<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

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
                <li><a href="userprofile.op">Profilo <span class="badge pull-right bg-warning"></span></a>

                <li><a id = "logout" style="cursor: pointer; cursor: hand;">Log Out</a>
                </li>
              </ul>
            </div>
          </li>
          <li>
            <a class="search-toggle" href="search.html">
              <i class="fa fa-search"></i>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <!-- /Navbar -->