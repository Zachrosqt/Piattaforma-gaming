<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<section style="background-color: #201B3A;">
	<div style="margin: 10%; margin-top: 5%; margin-bottom: 5%;">
	<!-- Reviews -->
        <div class="reviews-block mb-0">
          <h2>Recensioni <small>(3)</small></h2>
          <!-- Reviews List -->
          <ul class="reviews-list">
            <!-- Kristen Bradley review -->
            <c:forEach items="${review}" var="recensione">
            <li>
              <article>
                <div class="review-avatar pull-left">
                  <img src="assets/images/avatar-user-1.png" alt="">
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
                  <a class="review-author h4" href="#!">${recensione.user.username}</a>
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
          <h2>Add a Review</h2>
          <form action="#!" class="review-form mb-0">
            <div class="review-cont clearfix">
              <div class="youplay-input">
                <input type="text" name="username" placeholder="Your Name *">
              </div>
              <div class="youplay-input">
                <input type="email" name="email" placeholder="Your Email *">
              </div>
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
          <!-- /Review Form -->
        </div>
        </div>
        <!-- /Reviews -->
        </section>