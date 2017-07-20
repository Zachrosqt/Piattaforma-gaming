<%@ page import="java.util.*, gameplatform.db.table.Gioco" %>

  <!-- Slider Revolution -->
  <!--
    Use classes:
    rs-fullscreen-->
  <div class="tp-banner-container rs-youplay rs-fullscreen">
    <div class="tp-banner">
      <ul>
      
      <%
            boolean esc = false;
            List<Gioco> game = (List<Gioco>)request.getAttribute("giochi");
            Iterator<Gioco> it = game.iterator();
            int counter = 0;
            while (!esc && it.hasNext()) {
            	Gioco obj = it.next();
            %>
        <!-- SLIDE NR. 1  -->
        <li data-thumb="assets/images/games/<%= obj.getNome() %>/cover.jpg" data-saveperformance="on" data-transition="random-static" data-slotamount="7" data-masterspeed="700">
          <!-- MAIN IMAGE -->
          <img src="assets/images/dummy.png" alt="" data-lazyload="assets/images/games/<%= obj.getNome() %>/cover.jpg" data-bgposition="center top" data-bgfit="cover" data-bgrepeat="no-repeat">
          <!-- LAYERS -->

          <!-- LAYER NR. 1 -->
          <div class="tp-caption customin customout" data-x="left" data-hoffset="60" data-y="center" data-voffset="-60" data-customin="x:200;scaleX:0.5;scaleY:0.5;" data-customout="x:0;scaleX:1;scaleY:1;" data-start="500" data-speed="700" data-easing="Sine.easeInOut"
          data-endspeed="600" data-endeasing="Linear.easeNone">
            <h2 class="h1"><%= obj.getNome() %></h2>
          </div>

          <!-- LAYER NR. 2 -->
          <div class="tp-caption customin customout" data-x="left" data-hoffset="60" data-y="center" data-voffset="60" data-customin="x:200;scaleX:0.5;scaleY:0.5;" data-customout="x:0;scaleX:1;scaleY:1;" data-start="1000" data-speed="700" data-easing="Sine.easeInOut"
          data-endspeed="600" data-endeasing="Linear.easeNone">
            <a class="btn btn-lg" href="<%= obj.getNome() %>.op?username=${utenteGameplatform.username}" target="_blank">Gioca</a>
          </div>
        </li>

         <% if (counter == 3){
            	esc = true;
            }
            counter++;
            } %>
      </ul>
      <div class="tp-bannertimer"></div>
    </div>
  </div>
  <!-- /Slider Revolution-->