<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div class="container youplay-content" id="modProfile" style="margin-top: 5%; margin-bottom: 5%;">
	<div class="col-md-9">
	
	          <form onsubmit="return false" id="modificaform">
	          <table class="table table-bordered">
	            <tbody>
	              <tr>
	                <td style="width: 200px;">
	                  <p>Immagine</p>
	                </td>
	                <td>
	                <div id="alertfile">
			  		</div>
	                <div class="youplay-input">
	                  <input type = "file" name = "file" id="file" size = "50"/> <!-- upload file di dimensione massima di 5mb e in formato png -->
	                </div>
	                </td>
	              </tr>
	              <tr>
	                <td style="width: 200px;">
	                  <p>Nome</p>
	                </td>
	                <td>
	                <div id="alertinome"> <!-- div di errore in caso di campo vuoto -->
			  		</div>
	                <div class="youplay-input">
	                  <input type="text" name="inome" id="inome" value="${utenteGameplatform.nome}">
	                </div>
	                </td>
	              </tr>
	              <tr>
	                <td>
	                  <p>Cognome</p>
	                </td>
	                <td>
	                <div id="alerticognome"> <!-- div di errore in caso di campo vuoto -->
			  		</div>
	                <div class="youplay-input">
	                    <input type="text" name="icognome" id="icognome" value="${utenteGameplatform.cognome}">
	               	</div>
	                </td>
	              </tr>
	              <tr>
	                <td style="width: 200px;">
	                  <p>Et&agrave</p>
	                </td>
	                <td>
	                <div id="alertieta"> <!-- div di errore in caso di campo vuoto -->
			  		</div>
	                <div class="youplay-input">
	                	<input type="int" name="ieta" id="ieta" value="${utenteGameplatform.eta}">
	                </div>
	                </td>
	              </tr>
	              <tr>
	                <td>
	                  <p>e-mail</p>
	                </td>
	                <td>
	                <div id="alertiemail"> <!-- div di errore in caso di campo vuoto -->
			  		</div>
	                <div class="youplay-input">
	                	<input type="email" name="iemail" id="iemail" value="${utenteGameplatform.email}">
	                </div>
	                </td>
	              </tr>
	              <tr>
	                <td>
	                  <p>Password</p>
	                </td>
	                <td>
	                <div id="alertipassword"> <!-- div di errore in caso di campo vuoto -->
			  		</div>
	                <div class="youplay-input">
	                	<input type="password" name="ipassword" id="ipassword" value="${utenteGameplatform.password}">
	                </div>
	                </td>
	              </tr>
	            </tbody>
	          </table>
	          <button class="btn btn-default db">Salva modifiche</button>
	          <input type="hidden" id="iusername" name="iusername" value="${utenteGameplatform.username}">
			  </form>
	        </div>

	      </div>