/**
 * 
 */

//리스트 출력
listServer = function(page){
	$.ajax({
		url : '/board_ex/List',
		type : 'post',
		data : {'page' : page},
		success : function(res){
			//BS collapse-Accordion 게시판 예제 활용
			code = '<div class="panel-group" id="accordion">';
			
			$.each(res.datas, function(i,v){
				
				code += '<div class="panel panel-default">';
				code += '  <div class="panel-heading">';
				code += '    <h4 class="panel-title">';    
				code += '      <a idx="' + v.num + '" class="list" data-toggle="collapse" data-parent="#accordion" href="#collapse' + v.num + '">';
				code += 		v.subject + '</a>';      
				code += '    </h4>';
				code += '  </div>';
				code += '  <div id="collapse' + v.num + '" class="panel-collapse collapse">';
				code += '    <div class="panel-body pbody">';
				code += '		<p class="p1">작성자 : ' + v.writer + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				code += '									조회수 : ' + v.hit + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				code += '									날짜 : ' + v.wdate + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				code += '		</p>';
				code += '		<p class="p2">';
				code += '  			<input idx="' + v.num + '" type="button" name="modify" value="수정" class="action btn btn-warning">';
				code += '			<input idx="' + v.num + '" type="button" name="delete" value="삭제" class="action btn btn-danger">';
				code += '		</p>';
				code += '		<p class="p3">' + v.cont + '</p>';
				
				//댓글구간s
				code += '<p class="p4">';
				code += '<textarea cols="100"></textarea>';
				code += '<input idx="' + v.num + '" type="button" value="등록" name="reply" class="action btn btn-md btn-primary">';
				code += '</p>';
				//댓글구간e
				
				code += '    </div>';
				code += '  </div>';
				code += '</div>';
			});
		    code += '</div>';
		    $('#list').html(code);
		    
		    
		    //페이지 리스트
		    //BS Pagination 예제 활용
			//이전버튼, 다음버튼 : BS Pager 예제 활용
		    pager = '<div class="container">';
		    //이전버튼
		    if(res.sp > 1){
		    	pager += '<ul class="pager">';
		    	pager += '<li><a class="prev" href="#">Prev</a></li></ul>';
		    }
		    //페이지 번호 출력
		    pager += '<ul class="pagination pager">';
		    //현재 설정된 페이지갯수:2
		    for(i=res.sp; i<=res.ep; i++){
		    	//현재 페이지와 i값이 같은가
		    	if(currentPage == i){ //currentPage-전역변수
		    		pager += '<li class="active"><a class="paging" href="#">' + i + '</a></li>';
		    	}else{
		    		pager += '<li><a class="paging" href="#">' + i + '</a></li>';
		    	}
		    }
		    pager += '</ul>';
		    //다음버튼
		    if(res.ep < res.tp){
		    	pager += '<ul class="pager">';
		    	pager += '<li><a class="next" href="#">Next</a></li></ul>';
		    }
		    pager += '</div>';
		    $('#pagelist').html(pager);
		},
		error : function(xhr){
			alert(xhr.status);
		},
		dataType : 'json'
	});
};

$(function(){
//페이지 번호 클릭 이벤트 - 동적으로 생성된 요소 - delegate방식
	$('#pagelist').on('click', '.paging', function(){
// 		currentPage = $(this).text().trim();
		currentPage = $(this).text();
		listServer(currentPage);
	});
	
	//이전 버튼 클릭 이벤트 - 동적으로 생성된 요소
	$('#pagelist').on('click','.prev', function(){
// 		vprev = $('.pagination .paging').first().text().trim();
		vprev = $('.pagination .paging').first().text();
		currentPage = vprev - 1;
		listServer(currentPage);
	});
	
	//다음 버튼 클릭 이벤트 - 동적으로 생성된 요소
	$('#pagelist').on('click','.next', function(){
// 		vnext = $('.pagination .paging').last().text().trim();
		vnext = $('.pagination .paging').last().text();
		currentPage = parseInt(vnext) + 1;
		listServer(currentPage);
	});
});
//=======================================================================


//게시글 저장
writeServer = function(){
	$.ajax({
		url : '/board_ex/Write',
		method : 'post',
//		data : $('form').serializeArray(),
		data : $('form').serialize(),
		success : function(res){
			if(res.sw == 'ok'){
				alert("게시글이 등록되었습니다");
				$('#wModal').modal('hide');
				$('.txt').val("");
			}else{
				alert("게시글 등록에 실패하였습니다");
			}
			//리스트 출력
			listServer(1);
		},
		error : function(xhr){
			alert(xhr.status);
		},
		dataType : 'json'
	});
};


//=======================================================================


//댓글 저장
replyServer = function(btn){	//btn : reply btn
	
	$.ajax({
		url : '/board_ex/ReplySave',
		type : 'post',
		data : reply,	//객체 - bonum, name, cont
		success : function(res){
			//댓글 리스트 출력
			/* replyListServer함수호출불가
			if(res.sw == 'no') alert("댓글 등록에 실패했습니다!"); return false;
			replyListServer(btn);
			*/
			
			/* 정상
			 * if(res.sw == 'no'){
				alert("댓실패");
				return false;
			}
			replyListServer(btn); */

			/*삼항연산자*/
			res.sw=='no' ? alert("댓글등록실패") : replyListServer(btn); //저장 시 댓글 조회
		},
		error : function(xhr){
			alert(xhr.status);
		},
		dataType : 'json'
	});
};


//=======================================================================



//댓글 리스트 조회
replyListServer = function(btn){	//btn : (reply) - 댓글 append, remove의 셀렉터로 활용
	
	$.ajax({
		
		url : '/board_ex/ReplyList',
//		type : 'get',
		data : {'bonum' : boardNum},
		success : function(res){
			
			$(btn).parents('.panel').find('.rep').remove(); //게시글 제목 눌렀을 때 중복 방지
			
			reply = "";	//초기화 후 내용담기(index순)
			$.each(res, function(i,v){
				reply += '<div class="panel-body rep">';
				reply += '	<p class="p1">작성자 : '	 + v.name + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				reply += '				  날 짜 : ' + v.redate + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				reply += '	</p>';
				reply += '	<p class="p2">';
				reply += '  	<input idx="' + v.renum + '" type="button" name="r_modify" value="댓글수정" class="action btn btn-xs btn-warning">';
				reply += '		<input idx="' + v.renum + '" type="button" name="r_delete" value="댓글삭제" class="action btn btn-xs btn-danger">';
				reply += '	</p>';
				reply += '	<p class="cont p3">' + v.cont + '</p>';
				reply += '</div>';
			});
			$(btn).parents('.panel').find('.pbody').append(reply);
		},
		error : function(xhr){
			alert(xhr.status);
		},
		dataType : 'json'
	});
};


//댓글 삭제
replyDelete = function(){

	$.ajax({
		url : '/board_ex/ReplyDelete',
		type : 'get',
		data : {'vidx' : vidx},
		error : function(xhr){
			alert(xhr.status);
		},
		dataType : 'json'
	});
};


//댓글 수정
replyUpdate = function(){
	
	$.ajax({
		url : '/board_ex/ReplyUpdate',
		type : 'post',
		data : reply,	//renum, cont
		/*success : function(res){
		},
		error : function(xhr){
			alert(xhr.status);
		},*/
		dataType : 'json'
	});
};





