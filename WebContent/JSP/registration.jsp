<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

  <!-- Main Content -->
  <section class="content-wrap full youplay-login">

    <!-- Banner -->
    <div class="youplay-banner banner-top">
      <div class="image" style="background-image: url('assets/images/banner-bg.jpg'); background-repeat: repeat-y;"></div>

      <div class="info">
        <div>
          <div class="container align-center">
            <div class="youplay-form">
              <h1>Registration</h1>
              
              <form onsubmit="return false" id="registrationform">
              	<div id="alertname"></div>
                <div class="youplay-input">
                  <input type="text" id="nome" name="nome" placeholder="Nome">
                </div>
              	<div id="alertsurname"></div>
                <div class="youplay-input">
                  <input type="text" id="cognome" name="cognome" placeholder="Cognome">
                </div>
              	<div id="alerteta"></div>
                <div class="youplay-input">
                  <input type="number" id="eta" name="eta" placeholder="Età" min="3" max="99">
                </div>
                <div id="alertusername"></div>
                <div class="youplay-input">
                  <input type="text" id="username" name="username" placeholder="Username">
                </div>
                <div id="alertpassword"></div>
                <div class="youplay-input">
                  <input type="password" id="password" name="password" placeholder="Password">
                </div>
                <div id="alertconfirm"></div>
                <div class="youplay-input">
                  <input type="password" id="confirmpassword" name="confirmpassword" placeholder="Conferma Password">
                </div>
                <div id="alertemail"></div>
                <div class="youplay-input">
                  <input type="email" id="mail" name="mail" placeholder="E-Mail">
                </div>
                <div id="alertconfirmemail"></div>
                <div class="youplay-input">
                  <input type="email" id="confirmmail" name="mail" placeholder="Conferma E-Mail">
                </div>
                <button class="btn btn-default db">Register</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- /Banner -->

  </section>
  <!-- /Main Content --> 