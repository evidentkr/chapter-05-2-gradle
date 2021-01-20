package com.rubypaper;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.querydsl.core.BooleanBuilder;
import com.rubypaper.domain.Board;
import com.rubypaper.domain.QBoard;
import com.rubypaper.persistence.DynamicBoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicQueryTest {
	@Autowired
	private DynamicBoardRepository boardRepo;

	@Before
	public void dataPrepare() {
		for (int i = 1; i <= 200; i++) {
			Board board = new Board();
			board.setTitle("테스트 제목 " + i);
			board.setWriter("테스터");
			board.setContent("테스트 내용 " + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			boardRepo.save(board);
		}
	}

	@After
	public void tearDown() throws Exception {
		boardRepo.deleteAll();
	}
	
	//291 동적 쿼리 사용하기
	@Test
	public void testDynamicQuery() {
		String searchCondition = "TITLE";
		String searchKeyword = "테스트 제목 10";

		
		BooleanBuilder builder = new BooleanBuilder();

		QBoard qboard = QBoard.board;

		if (searchCondition.equals("TITLE")) {
			builder.and(qboard.title.like("%" + searchKeyword + "%"));
		} else if (searchCondition.equals("CONTENT")) {
			builder.and(qboard.content.like("%" + searchKeyword + "%"));
		}

		Pageable paging = PageRequest.of(0, 5);

		Page<Board> boardList = boardRepo.findAll(builder, paging);

		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("--->" + board.toString());
		}

	}

}
