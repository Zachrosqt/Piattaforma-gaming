/**
 * 
 */

function profileInfo(){
	$("#profileButton").addClass("active");
	$("#activityButton").removeClass("active");
	$('#profileBody').html(' <div class="row">' +

	        '<div class="col-md-9">' +

	          '<h3 class="mt-0 mb-20">John Doe</h3>' +
	          '<table class="table table-bordered">' +
	            '<tbody>' +
	              '<tr>' +
	                '<td style="width: 200px;">' +
	                  '<p>Username</p>' +
	                '</td>' +
	                '<td>' +
	                  '<p><a href="#"></a>' +
	                  '</p>' +
	                '</td>' +
	              '</tr>' +
	              '<tr>' +
	                '<td>' +
	                  '<p>Data Iscrizione</p>' +
	                '</td>' +
	                '<td>' +
	                  '<p><a href="#">Married</a>' +
	                  '</p>' +
	                '</td>' +
	              '</tr>' +
	              '<tr>' +
	                '<td>' +
	                  '<p>Exp</p>' +
	                '</td>' +
	                '<td>' +
	                  '<p><a href="#">Married</a>' +
	                  '</p>' +
	                '</td>' +
	              '</tr>' +
	              '<tr>' +
	                '<td>' +
	                  '<p>Numero Accessi</p>' +
	                '</td>' +
	                '<td>' +
	                  '<p><a href="#">New York University, NYU</a>' +
	                  '</p>' +
	                '</td>' +
	              '</tr>' +
	              '<tr>' +
	                '<td>' +
 	                  '<p>E-Mail</p>' +
	                '</td>' +
	                '<td>' +
	                  '<p><a href="#">English</a>, <a href="#">Japan</a>' +
	                  '</p>' +
	                '</td>' +
	              '</tr>' +
	            '</tbody>' +
	          '</table>' +

	        '</div>' +

	      '</div>');
};

function profileActivity(){
	$("#profileButton").removeClass("active");
	$("#activityButton").addClass("active");
	$('#profileBody').html('<div class="row">' +

	        '<div class="col-md-9">' +

	          '<!-- Activity -->' +
	          '<h2 class="mt-0">Activity</h2>' +
	          '<div class="youplay-timeline">' +

	            '<!-- Timeline Notification -->' +
	            '<div class="youplay-timeline-block">' +
	              '<!-- icon -->' +
	              '<div class="youplay-timeline-icon bg-warning">' +
	                '<i class="fa fa-bell"></i>' +
	              '</div>' +
	              '<!-- /icon -->' +

	              '<!-- content -->' +
	              '<div class="youplay-timeline-content">' +
	                '<h3 class="mb-0">Livello 0</h3>' +
	                '<span class="youplay-timeline-date pt-0">20 minutes ago</span>' +
	              '</div>' +
	              '<!-- content -->' +
	            '</div>' +
	            '<!-- /Timeline Notification -->' +
	          '</div>' +
	          '<!-- /Activity -->' +

	        '</div>' +

	      '</div>');
}