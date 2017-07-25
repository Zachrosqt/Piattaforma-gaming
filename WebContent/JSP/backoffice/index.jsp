<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>YouPlay GamePlatform - Backoffice </title>
	
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta charset="utf-8">
		 <meta http-equiv="X-UA-Compatible" content="IE=edge">
		
		 <meta name="description" content="">
		 <meta name="viewport" content="width=device-width, initial-scale=1">  
		
	 
<!-- Bootstrap -->
    <link href="JSP/backoffice/vendors/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="JSP/backoffice/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="JSP/backoffice/vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- FullCalendar -->
    <link href="JSP/backoffice/vendors/fullcalendar/dist/fullcalendar.min.css" rel="stylesheet">
    <link href="JSP/backoffice/vendors/fullcalendar/dist/fullcalendar.print.css" rel="stylesheet" media="print">

    <!-- Custom styling plus plugins -->
    <link href="JSP/backoffice/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <!-- NProgress -->
    <link href="JSP/backoffice/vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="JSP/backoffice/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
    <!-- bootstrap-wysiwyg -->
    <link href="JSP/backoffice/vendors/google-code-prettify/bin/prettify.min.css" rel="stylesheet">
    <!-- Select2 -->
    <link href="JSP/backoffice/vendors/select2/dist/css/select2.min.css" rel="stylesheet">
    <!-- Switchery -->
    <link href="JSP/backoffice/vendors/switchery/dist/switchery.min.css" rel="stylesheet">
    <!-- starrr -->
    <link href="JSP/backoffice/vendors/starrr/dist/starrr.css" rel="stylesheet">
    <!-- bootstrap-daterangepicker -->
    <link href="JSP/backoffice/vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="JSP/backoffice/build/css/custom.min.css" rel="stylesheet">

 
	
	</head>
	
	<body>
		
		<section class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="" onclick="window.open('gameplatform.op');" class="site_title"><i class="fa fa-gamepad"></i> <span>Game Platform </span></a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic">
                <img src="assets/images/avatar/${utenteGameplatform.username}.png" alt="..." class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span>Benvenuto,</span>
                <h2>${utenteGameplatform.nome} ${utenteGameplatform.cognome}</h2>
              </div>
            </div>
            <!-- /menu profile quick info -->

            <br />
            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">

                <ul class="nav side-menu">

                  <li><a><i class="glyphicon glyphicon-plus" aria-hidden="true"></i> Add<span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                     
                     <c:if test = "${utenteGameplatform.gruppo.nome == 'Admin'}">
                      <li><a href="addCategoria.op">Category</a></li> 
                      <li><a href="addGroup.op">Group</a></li>
                      <li><a href="addGroupPermission.op">Group Permission </a></li> 
                      <li><a href="addImage.op">Image </a></li>  
                      <li><a href="addPlay.op">Play</a></li>                  
                      <li><a href="addTrophy.op">Trophy</a></li>
                      <li><a href="addUser.op">User</a></li>
                      </c:if>
                    </ul>
                  </li>



                  <li><a><i class="glyphicon glyphicon-eye-open" aria-hidden="true"></i> Show<span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                    
                    <c:if test = "${utenteGameplatform.gruppo.nome == 'Admin'}">
                      <li><a href="showCategoria.op">Category</a></li>
                      <li><a href="showGruppo.op">Group</a></li>
                      
                      <li><a href="showGroupPermission.op">Group Permission </a></li>
                      <li><a href="showImage.op">Image </a></li>
                      <li><a href="showPlay.op">Play</a></li>                  
                      
                      <li><a href="showTrophy.op">Trophy</a></li>
                      <li><a href="showUser.op">User</a></li>
                         
                      <li><a href="showRecensione.op">Review</a></li>
                    </c:if>
                    
                    <c:if test = "${utenteGameplatform.gruppo.nome == 'Moderatore'}">
                      <li><a href="showRecensione.op">Review</a></li>
                    </c:if>
                      
                    </ul>
                  </li>

                </ul>
              </div>


            </div>
            <!-- /sidebar menu -->

            <br />
            <!-- sidebar menu -->
            <!-- /menu footer buttons -->
            <div class="sidebar-footer hidden-small">
            </div>
            <!-- /menu footer buttons -->
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
          <div class="nav_menu">
            <nav>
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>

              <ul class="nav navbar-nav navbar-right">
                <li class="">
                  <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    <img src="22.png" alt="">
                    <span class=" fa fa-angle-down"></span>
                  </a>
                  <ul class="dropdown-menu dropdown-usermenu pull-right">
                    <li><a id = "logout" style="cursor: pointer; cursor: hand;"> Log Out</a></li>
                  </ul>
                </li>
              </ul>
            </nav>
          </div>
        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main"> 
<c:forEach items="${template}" var="temp">
		   <jsp:include page="${temp.indirizzo}" flush="true" />
		</c:forEach>
            </div>
		
            
              </div>

		</div>


	  </section>
		   <!-- jQuery -->
    <script src="JSP/backoffice/vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="JSP/backoffice/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="JSP/backoffice/vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="JSP/backoffice/vendors/nprogress/nprogress.js"></script>
    <!-- FullCalendar -->
    <script src="JSP/backoffice/vendors/moment/min/moment.min.js"></script>
    <script src="JSP/backoffice/vendors/fullcalendar/dist/fullcalendar.min.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="JSP/backoffice/build/js/custom.min.js"></script>
		
<!-- bootstrap-progressbar -->
<script src="JSP/backoffice/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
<!-- iCheck -->
<script src="JSP/backoffice/vendors/iCheck/icheck.min.js"></script>
<!-- bootstrap-daterangepicker -->
<script src="JSP/backoffice/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- bootstrap-wysiwyg -->
<script src="JSP/backoffice/vendors/bootstrap-wysiwyg/js/bootstrap-wysiwyg.min.js"></script>
<script src="JSP/backoffice/vendors/jquery.hotkeys/jquery.hotkeys.js"></script>
<script src="JSP/backoffice/vendors/google-code-prettify/src/prettify.js"></script>
<!-- jQuery Tags Input -->
<script src="JSP/backoffice/vendors/jquery.tagsinput/src/jquery.tagsinput.js"></script>
<!-- Switchery -->
<script src="JSP/backoffice/vendors/switchery/dist/switchery.min.js"></script>
<!-- Select2 -->
<script src="JSP/backoffice/vendors/select2/dist/js/select2.full.min.js"></script>
<!-- Parsley -->
<script src="JSP/backoffice/vendors/parsleyjs/dist/parsley.min.js"></script>
<!-- Autosize -->
<script src="JSP/backoffice/vendors/autosize/dist/autosize.min.js"></script>
<!-- jQuery autocomplete -->
<script src="JSP/backoffice/vendors/devbridge-autocomplete/dist/jquery.autocomplete.min.js"></script>
<!-- starrr -->
<script src="JSP/backoffice/vendors/starrr/dist/starrr.js"></script>


		  <script type="text/javascript" src="assets/gameplatform/ajaxcall.js"></script>

<!-- Cropper -->
<script>
    $(document).ready(function() {
        var $image = $('#image');
        var $download = $('#download');
        var $dataX = $('#dataX');
        var $dataY = $('#dataY');
        var $dataHeight = $('#dataHeight');
        var $dataWidth = $('#dataWidth');
        var $dataRotate = $('#dataRotate');
        var $dataScaleX = $('#dataScaleX');
        var $dataScaleY = $('#dataScaleY');
        var options = {
            aspectRatio: 16 / 9,
            preview: '.img-preview',
            crop: function (e) {
                $dataX.val(Math.round(e.x));
                $dataY.val(Math.round(e.y));
                $dataHeight.val(Math.round(e.height));
                $dataWidth.val(Math.round(e.width));
                $dataRotate.val(e.rotate);
                $dataScaleX.val(e.scaleX);
                $dataScaleY.val(e.scaleY);
            }
        };


        // Tooltip
        $('[data-toggle="tooltip"]').tooltip();


        // Cropper
        $image.on({
            'build.cropper': function (e) {
                console.log(e.type);
            },
            'built.cropper': function (e) {
                console.log(e.type);
            },
            'cropstart.cropper': function (e) {
                console.log(e.type, e.action);
            },
            'cropmove.cropper': function (e) {
                console.log(e.type, e.action);
            },
            'cropend.cropper': function (e) {
                console.log(e.type, e.action);
            },
            'crop.cropper': function (e) {
                console.log(e.type, e.x, e.y, e.width, e.height, e.rotate, e.scaleX, e.scaleY);
            },
            'zoom.cropper': function (e) {
                console.log(e.type, e.ratio);
            }
        }).cropper(options);


        // Buttons
        if (!$.isFunction(document.createElement('canvas').getContext)) {
            $('button[data-method="getCroppedCanvas"]').prop('disabled', true);
        }

        if (typeof document.createElement('cropper').style.transition === 'undefined') {
            $('button[data-method="rotate"]').prop('disabled', true);
            $('button[data-method="scale"]').prop('disabled', true);
        }


        // Download
        if (typeof $download[0].download === 'undefined') {
            $download.addClass('disabled');
        }


        // Options
        $('.docs-toggles').on('change', 'input', function () {
            var $this = $(this);
            var name = $this.attr('name');
            var type = $this.prop('type');
            var cropBoxData;
            var canvasData;

            if (!$image.data('cropper')) {
                return;
            }

            if (type === 'checkbox') {
                options[name] = $this.prop('checked');
                cropBoxData = $image.cropper('getCropBoxData');
                canvasData = $image.cropper('getCanvasData');

                options.built = function () {
                    $image.cropper('setCropBoxData', cropBoxData);
                    $image.cropper('setCanvasData', canvasData);
                };
            } else if (type === 'radio') {
                options[name] = $this.val();
            }

            $image.cropper('destroy').cropper(options);
        });


        // Methods
        $('.docs-buttons').on('click', '[data-method]', function () {
            var $this = $(this);
            var data = $this.data();
            var $target;
            var result;

            if ($this.prop('disabled') || $this.hasClass('disabled')) {
                return;
            }

            if ($image.data('cropper') && data.method) {
                data = $.extend({}, data); // Clone a new one

                if (typeof data.target !== 'undefined') {
                    $target = $(data.target);

                    if (typeof data.option === 'undefined') {
                        try {
                            data.option = JSON.parse($target.val());
                        } catch (e) {
                            console.log(e.message);
                        }
                    }
                }

                result = $image.cropper(data.method, data.option, data.secondOption);

                switch (data.method) {
                    case 'scaleX':
                    case 'scaleY':
                        $(this).data('option', -data.option);
                        break;

                    case 'getCroppedCanvas':
                        if (result) {

                            // Bootstrap's Modal
                            $('#getCroppedCanvasModal').modal().find('.modal-body').html(result);

                            if (!$download.hasClass('disabled')) {
                                $download.attr('href', result.toDataURL());
                            }
                        }

                        break;
                }

                if ($.isPlainObject(result) && $target) {
                    try {
                        $target.val(JSON.stringify(result));
                    } catch (e) {
                        console.log(e.message);
                    }
                }

            }
        });

        // Keyboard
        $(document.body).on('keydown', function (e) {
            if (!$image.data('cropper') || this.scrollTop > 300) {
                return;
            }

            switch (e.which) {
                case 37:
                    e.preventDefault();
                    $image.cropper('move', -1, 0);
                    break;

                case 38:
                    e.preventDefault();
                    $image.cropper('move', 0, -1);
                    break;

                case 39:
                    e.preventDefault();
                    $image.cropper('move', 1, 0);
                    break;

                case 40:
                    e.preventDefault();
                    $image.cropper('move', 0, 1);
                    break;
            }
        });

        // Import image
        var $inputImage = $('inputImage');
        var URL = window.URL || window.webkitURL;
        var blobURL;

        if (URL) {
            $inputImage.change(function () {
                var files = this.files;
                var file;

                if (!$image.data('cropper')) {
                    return;
                }

                if (files && files.length) {
                    file = files[0];

                    if (/^image\/\w+$/.test(file.type)) {
                        blobURL = URL.createObjectURL(file);
                        $image.one('built.cropper', function () {

                            // Revoke when load complete
                            URL.revokeObjectURL(blobURL);
                        }).cropper('reset').cropper('replace', blobURL);
                        $inputImage.val('');
                    } else {
                        window.alert('Please choose an image file.');
                    }
                }
            });
        } else {
            $inputImage.prop('disabled', true).parent().addClass('disabled');
        }
    });
</script>	
	</body>
</html>