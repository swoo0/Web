/**
 * 
 */

// 리스트 출력
listServer = function(page) {
	$.ajax({
		url		: '/board/List',
		type	: 'post',
		data	: {'page' : page},
		success : function(){},
		error	: function(xhr) {
			alert(xhr.status);
		},
		dataType : 'json'
	});
}