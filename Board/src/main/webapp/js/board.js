/**
 * 
 */
// 리스트 출력
listServer = function(page) {
	$.ajax({
		url: '/Board/List',
		type: 'post',
		data: { 'page': page },
		success: function(res) {
			// bs - accordion 게시판으로 찍어줄거에요...
			let code = '<div class="panel-group" id="accordion">';

			// 반복하여 accordion형태의 게시판을 생성
			$.each(res.data, function(i) {

				code += '<div class="panel panel-default">';
				code += '  <div class="panel-heading">';
				code += '    <h4 class="panel-title">';
				code += '      <a data-toggle="collapse" data-parent="#accordion" href="#collapse' + this.num + '">' + res.data[i].subject + '</a>';
				code += '    </h4>';
				code += '  </div>';
				code += '  <div id="collapse' + this.num + '" class="panel-collapse collapse">';
				code += '    <div class="panel-body">';
				code += '      <p class="p1">작성자 : ' + this.writer + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				code += '                    조회수 : ' + this.hit + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>";
				code += '                    날짜 : ' + this.wdate;
				code += '      </p>';
				code += '      <p class="p2">';
				code += '        <input type="button" value="수정" class="btn btn-warning btn-xs">';
				code += '        <input type="button" value="삭제" class="btn btn-danger btn-xs">';
				code += '      </p>';
				code += '      <p class="p3">' + this.content + '</p>';
				code += '    </div>';
				code += '  </div>';
				code += '</div>';
			});

			code += '</div>';
			$('#list').html(code);

			// 페이징 영역 그리기
			let pager =  '<div class="container">';
			
			// 이전버튼 (prev) 생성
			if (res.sp > 1) {
				pager += '	<ul class="pager">';
  				pager += '		<li><a href="#" class="prev">Prev</a></li>';
				pager += '	</ul>';
			}

			// 페이지 번호 출력
				pager += '	<ul class="pagination pager">';
			for(let i = res.sp; i <= res.ep; i++) {
				pager += '		<li><a href="#" class="paging">' + i + '</a></li>';
			}			
				pager += '	</ul>';
				
			// 다음버튼 (next) 생성
			if (res.ep < res.tp) {
				pager += '	<ul class="pager">';
  				pager += '		<li><a href="#" class="next">Next</a></li>';
				pager += '	</ul>';
			}
			
				pager += '</div>';
			$('#pagelist').html(pager);
		},
		error: function(xhr) {
			alert(xhr.status);
		},
		dataType: 'json'
	});
}

$(function(){
	// 동적생성요소 - delegate방식으로 접근
	$('pagelist').on('click', '.next', function() {
		// next 클릭했을 때 현재 페이지 기준으로 끝 값을 확인해서 처리
		$('.paging')
	});
});
























