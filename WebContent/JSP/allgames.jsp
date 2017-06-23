<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ page import="java.util.*, gameplatform.db.table.Gioco" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 <!-- Main Content -->
  <section>

    <div class="container youplay-store store-grid">

      <!-- Games List -->
      <div class="col-md-9 isotope">
        <!-- Sort Categories -->
        <ul class="pagination isotope-options">
          <li data-filter="all" class="active"><span>All</span>
          </li>
          <li data-filter="free"><span>Free</span>
          </li>
          <li data-filter="popular"><span>Popular</span>
          </li>
          <li data-filter="specials"><span>Specials</span>
          </li>
          <li data-filter="upcoming"><span>Upcoming</span>
          </li>
        </ul>
        <!-- /Sort Categories -->

        <div class="isotope-list row vertical-gutter">

          <!-- Single Product Block -->
          <c:forEach items="${giochi}" var="gioco">
          <div class="item col-lg-4 col-md-6 col-xs-12" data-filters="popular">
            <a href="#!" class="angled-img">
              <div class="img img-offset">
                <img src="assets/images/games/${gioco.nome}/cover.jpg" alt="">
              </div>
              <div class="bottom-info">
              
                <h4>${gioco.nome}</h4>
                <div class="row">
                  <div class="col-xs-6">
                    <div class="rating">
                     <c:forEach var="i" begin= "1" end = "5">
              	 	 	<c:choose>
         					<c:when test = "${gioco.mediaGioco >= i}">
           						<i class="fa fa-star"></i>
         					</c:when>
         					<c:otherwise>
           						<i class="fa fa-star-o"></i>
         					</c:otherwise>
      					</c:choose>
              		</c:forEach>
                    </div>
                  </div>
                </div>
              </div>
            </a>
          </div>
          </c:forEach>
          <!-- /Single Product Block -->

          <!-- /Single Product Block -->
        </div>

      </div>
      <!-- /Games List -->

      <!-- Right Side -->
      <div class="col-md-3">

        <!-- Side Search -->
        <div class="side-block">
          <p>Search by Games:</p>
          <form action="http://html.nkdev.info/youplay/dark/search.html">
            <div class="youplay-input">
              <input type="text" name="search" placeholder="enter search term">
            </div>
          </form>
        </div>
        <!-- /Side Search -->

        <!-- Side Popular News -->
        <div class="side-block">
          <h4 class="block-title">Last Games</h4>
          <div class="block-content p-0">

            <!-- Single News Block -->
            <%
            boolean esc = false;
            List<Gioco> game = (List<Gioco>)request.getAttribute("giochi");
            Iterator<Gioco> it = game.iterator();
            int counter = 0;
            while (!esc && it.hasNext()) {
            	Gioco obj = it.next();
            %>
            <div class="row youplay-side-news">
              <div class="col-xs-3 col-md-4">
                <a href="#!" class="angled-img">
                  <div class="img">
                    <img src="assets/images/games/<%= obj.getNome() %>/cover.jpg" alt="">
                  </div>
                </a>
              </div>
              <div class="col-xs-9 col-md-8">
                <h4 class="ellipsis"><a href="#!" title="Dark Souls II"><%= obj.getNome() %></a></h4>
              </div>
            </div>
            <% if (counter == 3){
            	esc = true;
            }
            counter++;
            } %>
            <!-- /Single News Block -->
          </div>
        </div>
        <!-- /Side Popular News -->

      </div>
      <!-- /Right Side -->
    </div>
    
    </section>
  <!-- /Main Content -->