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
	                <div class="youplay-input">
	                  <input type = "file" name = "file" size = "50" required />
	                </div>
	                </td>
	              </tr>
	              <tr>
	                <td style="width: 200px;">
	                  <p>Nome</p>
	                </td>
	                <td>
	                <div class="youplay-input">
	                  <input type="text" name="inome" value="${utenteGameplatform.nome}" required>
	                </div>
	                </td>
	              </tr>
	              <tr>
	                <td>
	                  <p>Cognome</p>
	                </td>
	                <td>
	                <div class="youplay-input">
	                    <input type="text" name="icognome" value="${utenteGameplatform.cognome}" required>
	               	</div>
	                </td>
	              </tr>
	              <tr>
	                <td style="width: 200px;">
	                  <p>Et&agrave</p>
	                </td>
	                <td>
	                <div class="youplay-input">
	                	<input type="int" name="ieta" value="${utenteGameplatform.eta}" required>
	                </div>
	                </td>
	              </tr>
	              <tr>
	                <td>
	                  <p>e-mail</p>
	                </td>
	                <td>
	                <div class="youplay-input">
	                	<input type="email" name="iemail" value="${utenteGameplatform.email}" required>
	                </div>
	                </td>
	              </tr>
	              <tr>
	                <td>
	                  <p>Password</p>
	                </td>
	                <td>
	                <div class="youplay-input">
	                	<input type="password" name="ipassword" value="${utenteGameplatform.password}" required>
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