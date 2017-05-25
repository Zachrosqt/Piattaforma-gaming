<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

  <!-- Main Content -->
  <section class="content-wrap" style="background-color: #201B3A;">
<!-- Banner -->
    <div class="youplay-banner youplay-banner-parallax banner-top small">
      <div class="image" style="background-image: url('assets/images/banner-blog-bg.jpg')">
      </div>

      <div class="info">
        <div>
          <div class="container">
            <h1>Contact Us</h1>
          </div>
        </div>
      </div>
    </div>
    <!-- /Banner -->

    <div class="container youplay-content">

      <div class="row">
        <div class="col-md-9 col-md-push-3">
          <!-- Contact Form -->
          <div class="youplay-form p-0" style="background-color: #201B3A;">
            <h2 class="mt-0">Drop us a line</h2>

            <form action="http://html.nkdev.info/youplay/dark/php/contact.php" method="POST" role="form" class="youplay-form-ajax" data-toggle="validator">
              <div class="row">
                <div class="col-md-6">
                  <div class="youplay-input form-group">
                    <input type="text" name="name" placeholder="Name" required>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="youplay-input form-group">
                    <input type="email" name="email" placeholder="Email" required>
                  </div>
                </div>
              </div>
              <div class="youplay-textarea form-group">
                <textarea name="message" placeholder="Message" rows="5" required></textarea>
              </div>
              <button type="submit" class="btn btn-default">Submit</button>
            </form>
          </div>
          <!-- /Contact Form -->
        </div>
        <div class="col-md-3 col-md-pull-9">
          <h2 class="mt-0">Contact info</h2>
          12345 North Main Street,
          <br>New York, NY 555555
          <br>
          <br>Phone: 1.800.555.6789
          <br>Email: demo@demo.com
          <br>Web: <a href="http://nkdev.info/">nkdev.info</a>
          <br>
          <br>
        </div>
      </div>

    </div>
    
   </section>