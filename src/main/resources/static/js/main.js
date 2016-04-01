(function () {
	
	$("#delete").click(function(e) {
		e.preventDefault();
		$.ajax({
		    url: $(this).attr("href"),
		    type: 'delete',
		    success: function(result) {
		        console.log("sucesso");
		        $(location).attr('href','/models');
		    }
		});
	});
	
	$("#edit").click(function(e) {
		e.preventDefault();
		$.ajax({
			url: $("form").attr("action"),
			type: 'put',
			data:$('#edit-form').serialize(),
			success: function(result) {
				console.log("sucesso");
				$(location).attr('href','/models');
			}
		});
	});
	
	$("#add_att").click(function(e) {
		
		var html = $("#append div.form-group:last").html();	
		var id = parseInt(html.match(/attributes(\d+)/)[1]) + 1;
		html = html.replace(/attributes\[\d+\]/g, "attributes["+ id +"]");
		html = html.replace(/attributes\d+/g, "attributes"+ id);
		
		var div = $("<div class=\"form-group\"></div>");
		div.html(html);
		
		div.appendTo("#append");
		
	})
	
	$("#model_name").blur(function() {
		$("#model_url").val(slug($(this).val()));
	});
	
	
	var slug = function(str) {
	    var $slug = '';
	    var trimmed = $.trim(str);
	    $slug = trimmed.replace(/[^aA-zZ0-9-]/gi, '-').
	    replace(/\s+/,'-')
	    
	    return $slug.toLowerCase();
	}
}());