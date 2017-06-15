$("#loginform").submit(function(e){
	var url = "login.op";
	$.ajax ({
		type: "POST",
		url: url,
		data: $("#loginform").serialize(),
		
		success: function(data){
			alert(data);
		}
	});
	e.preventDefault();
});