<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Banner -->
    <div class="youplay-banner banner-top youplay-banner-parallax small">
      <div class="image" style="background-image: url('assets/images/game-diablo-iii-1400x656.jpg')">
      </div>

      <div class="info">
        <div>
          <div class="container youplay-user">
            <a href="assets/images/user-photo.jpg" class="angled-img image-popup">
              <div class="img">
                <img src="assets/images/user-avatar.jpg" alt="">
              </div>
              <i class="fa fa-search-plus icon"></i>
            </a>
            <!--
                -->
            <div class="user-data">
              <h2>${utenteGameplatform.nome} ${utenteGameplatform.cognome}</h2>
              <div class="activity">
                <div>
                  <div class="num">${utenteGameplatform.username}</div>
                  <div class="title">Username</div>
                </div>
                <div>
                  <div class="num" id="dataIscrizione"></div>
                  <div class="title">Livello</div>
                </div>
                <div>
                  <div class="num">${giocati}</div>
                  <div class="title">Games</div>
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
    <!-- /Banner -->