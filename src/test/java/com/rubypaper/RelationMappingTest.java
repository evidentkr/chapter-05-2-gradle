package com.rubypaper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Member;
import com.rubypaper.persistence.BoardRepository;
import com.rubypaper.persistence.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RelationMappingTest {
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	//p302 단방향 테스트 - many to one
	@Test
	public void 단방향_테스트() {
		Member member1 = new Member();
		member1.setId("member1");
		member1.setPassword("member111");
		member1.setName("둘리");
		member1.setRole("User");
		memberRepo.save(member1);
		
		Member member2 = new Member();
		member2.setId("member2");
		member2.setPassword("member222");
		member2.setName("도우너");
		member2.setRole("Admin");
		memberRepo.save(member2);
		
		for (int i = 1; i <= 10; i++) {
			Board board = new Board();
			//멤버 객체를 인수로 넣는다
			board.setMember(member1);
			board.setTitle("둘리가 등록한 게시글 " + i);
			board.setContent("둘리가 등록한 게시글 내용 " + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			boardRepo.save(board);
		}
		//memberRepo.save(member1);
		
		for (int i = 1; i <= 10; i++) {
			Board board = new Board();
			//멤버 객체를 인수로 넣는다
			board.setMember(member2);
			board.setTitle("도우너가 등록한 게시글 " + i);
			board.setContent("도우너가 등록한 게시글 내용 " + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			boardRepo.save(board);
		}
		//memberRepo.save(member2);
	/*}

	//p307 board 를 조회하면 member 까지 조회됨
	@Test
	public void board_상세글조회() {*/
		Board board = boardRepo.findById(5L).get();

		System.out.println("[ " + board.getSeq() + "번 게시글 정보 ]");
		System.out.println("제목 : " + board.getTitle());
		System.out.println("내용 : " + board.getContent());
		System.out.println("작성자 : " + board.getMember().getName());
		System.out.println("작성자 권한 : " + board.getMember().getRole());
	}

	//================================================================

	//p319 양방향 테스트
	@Test
	public void 양방향_테스트() {
		Member member1 = new Member();
		member1.setId("member1");
		member1.setPassword("member111");
		member1.setName("둘리");
		member1.setRole("User");
		//memberRepo.save(member1);

		Member member2 = new Member();
		member2.setId("member2");
		member2.setPassword("member222");
		member2.setName("도우너");
		member2.setRole("Admin");
		//memberRepo.save(member2);

		for (int i = 1; i <= 10; i++) {
			Board board = new Board();
			//멤버 객체를 인수로 넣는다
			board.setMember(member1);
			board.setTitle("둘리가 등록한 게시글 " + i);
			board.setContent("둘리가 등록한 게시글 내용 " + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			//boardRepo.save(board);
		}
		//한번에 등록한다 - member 가 board 를 품고 있음
		memberRepo.save(member1);

		for (int i = 1; i <= 10; i++) {
			Board board = new Board();
			//멤버 객체를 인수로 넣는다
			board.setMember(member2);
			board.setTitle("도우너가 등록한 게시글 " + i);
			board.setContent("도우너가 등록한 게시글 내용 " + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			//boardRepo.save(board);
		}
		//한번에 등록한다 - member 가 board 를 품고 있음
		memberRepo.save(member2);
	/*}

	//멤버로 보드 데이터까지 조회
	@Test
	public void 양방향매핑_멤버조회로_보드까지_조회() {*/
		Member member = memberRepo.findById("member1").get();

		System.out.println("===========================");
		System.out.println(member.getName() + "가(이) 저장한 게시글 목록");
		System.out.println("===========================");
		List<Board> list = member.getBoardList();
		for (Board board : list) {
			System.out.println(board.toString());
		}
	/*}

	//p320 영속성 전이 - 멤버 삭제하면 관련된 보드도 삭제
	@Test
	public void 멤버삭제하니_보드도_삭제되는구나(){*/
		memberRepo.deleteById("member2");
	}
	
}
