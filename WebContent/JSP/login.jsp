<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <!-- Main Content -->
	<section class="content-wrap full youplay-login">

    <!-- Banner -->
    <div class="youplay-banner banner-top">
      <div class="image" style="background-image: url('assets/images/banner-bg.jpg')"></div>

      <div class="info">
        <div>
          <div class="container align-center">
            <div class="youplay-form">
              <h1>Login</h1>
				
              <form onsubmit="return false" id="loginform">
              <div id="alertusername"> <!-- messaggio di errore in caso di campo vuoto -->
				</div>
                <div class="youplay-input">
                  <input type="text" id="username" name="username" placeholder="Username">
                </div>
              <div id="alertpassword"> <!-- messaggio di errore in caso di campo vuoto -->
				</div>
                <div class="youplay-input">
                  <input type="password" id="password" name="password" placeholder="Password">
                </div>
                <button class="btn btn-default db">Login</button>
              </form>
              <br>
              <a href="registration.op">
               <button class="btn btn-default db"> Registrazione </button>    
              </a>        
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- /Banner -->

  </section>
  <!-- /Main Content --> 