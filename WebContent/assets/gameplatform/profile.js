/**
 * 
 */


function profileInfo(nome, cognome, username, age, data, exp, accessi, mail){
	$("#profileButton").addClass("active");
	$("#activityButton").removeClass("active");
	$('#profileBody').html(' <div class="row">' +

	        '<div class="col-md-9">' +

	          '<h3 class="mt-0 mb-20">' + nome + ' ' + cognome + '</h3>' +
	          '<table class="table table-bordered">' +
	            '<tbody>' +
	              '<tr>' +
	                '<td style="width: 200px;">' +
	                  '<p>Username</p>' +
	                '</td>' +
	                '<td>' +
	                  '<p>' + username +
	                  '</p>' +
	                '</td>' +
	              '</tr>' +
	              '<tr>' +
	                '<td style="width: 200px;">' +
	                  '<p>Et&agrave</p>' +
	                '</td>' +
	                '<td>' +
	                  '<p>' + age +
	                  '</p>' +
	                '</td>' +
	              '</tr>' +
	              '<tr>' +
	                '<td>' +
	                  '<p>Data Iscrizione</p>' +
	                '</td>' +
	                '<td>' +
	                  '<p>' + data[0][1] +
	                  '</p>' +
	                '</td>' +
	              '</tr>' +
	              '<tr>' +
	                '<td>' +
	                  '<p>Exp</p>' +
	                '</td>' +
	                '<td>' +
	                  '<p>' + exp +
	                  '</p>' +
	                '</td>' +
	              '</tr>' +
	              '<tr>' +
	                '<td>' +
	                  '<p>Numero Accessi</p>' +
	                '</td>' +
	                '<td>' +
	                  '<p>' + accessi +
	                  '</p>' +
	                '</td>' +
	              '</tr>' +
	              '<tr>' +
	                '<td>' +
 	                  '<p>E-Mail</p>' +
	                '</td>' +
	                '<td>' +
	                  '<p>' + mail +
	                  '</p>' +
	                '</td>' +
	              '</tr>' +
	            '</tbody>' +
	          '</table>' +

	        '</div>' +

	      '</div>' +
	      	'<div>' +
	      		'<button class="btn btn-default db">Modifica profilo</button>' +
	      	'</div>');
};

function profileActivity(livelli){
	$("#profileButton").removeClass("active");
	$("#activityButton").addClass("active");
	var activityHtml = '<div class="row">' +

    '<div class="col-md-9">' +

      '<!-- Activity -->' +
      '<h2 class="mt-0">Activity</h2>' +
      '<div class="youplay-timeline">';
	for (i = 0; i < livelli.length; i++) {
		
		activityHtml +=  '<!-- Timeline Notification -->' +
        '<div class="youplay-timeline-block">' +
        '<!-- icon -->' +
        '<div class="youplay-timeline-icon bg-warning">' +
          '<i class="fa fa-arrow-up"></i>' +
        '</div>' +
        '<!-- /icon -->' +

        '<!-- content -->' +
        '<div class="youplay-timeline-content">' +
          '<h3 class="mb-0">Livello ' + livelli[i][0] + '</h3>' +
          '<span class="youplay-timeline-date pt-0">' + livelli[i][1] + '</span>' +
        '</div>' +
        '<!-- content -->' +
      '</div>' +
      '<!-- /Timeline Notification -->';
	} 
	
	activityHtml += '</div>' +
    '<!-- /Activity -->' +

  '</div>' +

'</div>';
	$('#profileBody').html(activityHtml);
	
}