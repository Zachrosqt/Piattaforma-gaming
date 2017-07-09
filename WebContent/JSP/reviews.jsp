<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.io.File, gameplatform.business.impl.UtenteGiocare" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	
	<section style="background-color: #201B3A;">
	<div style="margin: 10%; margin-top: 5%; margin-bottom: 5%;">
	<!-- Reviews -->
        <div class="reviews-block mb-0">
          <h2>Recensioni <small>(${fn:length(review)})</small></h2>
          <!-- Reviews List -->
          <ul class="reviews-list">
            <!-- Kristen Bradley review -->
            <c:forEach items="${review}" var="recensione">
            <li>
              <article>
              	<div class="review-avatar pull-left">
              	
              	<% 	ServletContext app=getServletContext();
					String path=app.getRealPath("");
					File f=new File(path+"assets/images/avatar/" + ((UtenteGiocare)(pageContext.findAttribute("recensione"))).getUser().getUsername() + ".png");
					if(!f.exists()){ %>
						<img src="assets/images/avatar.png" alt="">
				<%	}
					else { %>
						<img src="assets/images/avatar/${recensione.user.username}.png" alt="">
				<%	} %>
 
                </div>
                <div class="review-cont clearfix">
                  <div class="rating pull-right">
                  	<c:forEach var="i" begin= "1" end = "5">
              	 	 	<c:choose>
         					<c:when test = "${recensione.game.voto >= i}">
           						<i class="fa fa-star"></i>
         					</c:when>
         					<c:otherwise>
           						<i class="fa fa-star-o"></i>
         					</c:otherwise>
      					</c:choose>
              		</c:forEach>
                  </div>
                  <a class="review-author h4" href="userprofile.op">${recensione.user.username}</a>
                  <div class="date"><i class="fa fa-calendar"></i><fmt:formatDate value="${recensione.game.data.time}" type="both"/></div>
                  <div class="review-text">
                    <p>
                      ${recensione.game.recensione }
                    </p>
                  </div>
                </div>
              </article>
            </li>
            </c:forEach>
            <!-- /Kristen Bradley review -->
           
          </ul>
          <!-- /Reviews List -->

          <!-- Review Form -->
           <c:set var = "check" value = "0"/>
           <c:forEach items="${review}" var="recensione">
          	 <c:if test = "${recensione.user.username == utenteGameplatform.username}">
         		 <c:set var = "check" value = "1"/>
      		 </c:if>
           </c:forEach>
           <c:if test = "${check==0}">
          <h2>Aggiungi una recensione</h2>
          <form action="#!" class="review-form mb-0">
            <div class="review-cont clearfix">
             
              <div class="youplay-textarea">
                <textarea name="message" rows="5" placeholder="Your Review..."></textarea>
              </div>
              <div class="youplay-rating pull-right">
                <input type="radio" id="review-rate-5" name="review-rate" value="5">
                <label for="review-rate-5"><i class="fa fa-star"></i>
                </label>
                <input type="radio" id="review-rate-4" name="review-rate" value="4">
                <label for="review-rate-4"><i class="fa fa-star"></i>
                </label>
                <input type="radio" id="review-rate-3" name="review-rate" value="3">
                <label for="review-rate-3"><i class="fa fa-star"></i>
                </label>
                <input type="radio" id="review-rate-2" name="review-rate" value="2">
                <label for="review-rate-2"><i class="fa fa-star"></i>
                </label>
                <input type="radio" id="review-rate-1" name="review-rate" value="1">
                <label for="review-rate-1"><i class="fa fa-star"></i>
                </label>
              </div>
              <div class="clearfix"></div>
              <button class="btn btn-default pull-right">Submit</button>
            </div>
          </form>
          </c:if>
          <!-- /Review Form -->
        </div>
        </div>
        <!-- /Reviews -->
        </section>