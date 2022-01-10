package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;

public interface IBoardService {

	//글쓰기 저장
	public int insertBoard(BoardVO vo);

	//전체 글 갯수 가져오기
	public int countList();

	//페이지 별 리스트 가져오기
	public List<BoardVO> boardList(Map<String, Integer> map);

	//댓글 저장 - renum 리턴
	public int insertReply(ReplyVO vo);

	//댓글 리스트 조회
	public List<ReplyVO> replyList(int bonum);

	//댓글 삭제
	public int replyDelete(int renum);

	//댓글 수정
	public int replyUpdate(ReplyVO vo);
	
}
