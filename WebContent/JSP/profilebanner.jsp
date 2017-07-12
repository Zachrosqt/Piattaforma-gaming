<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.io.File, gameplatform.db.table.Utente" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Banner -->
    <div class="youplay-banner banner-top youplay-banner-parallax small">
      <div class="image" style="background-image: url('assets/images/game-diablo-iii-1400x656.jpg')">
      </div>

      <div class="youplay-user-navigation">
        <div class="container">
          <ul>
          	<li id="profileButton" style="cursor: pointer; cursor: hand;"><a>Profilo</a>
            </li>
            <li id="activityButton" style="cursor: pointer; cursor: hand;"><a>Cronologia attivit&agrave</a>
            </li>
          </ul>
        </div>
      </div>

      <div class="info">
        <div>
          <div class="container youplay-user">
            <a href="assets/images/user-photo.jpg" class="angled-img image-popup">
              <div class="img">
              	<% 	ServletContext app=getServletContext();
					String path=app.getRealPath("");
					File f=new File(path+"assets/images/avatar/" + ((Utente)(pageContext.findAttribute("utenteGameplatform"))).getUsername() + ".png");
					if(!f.exists()){ %>
						<img src="assets/images/avatar.png" alt="">
				<%	}
					else { %>
						<img src="assets/images/avatar/${utenteGameplatform.username}.png" alt="">
				<%	} %>
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
                  <div class="title">Giochi</div>
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
    <!-- /Banner -->