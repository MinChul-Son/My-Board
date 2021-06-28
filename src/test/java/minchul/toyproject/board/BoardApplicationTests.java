package minchul.toyproject.board;

import minchul.toyproject.board.domain.entity.Post;
import minchul.toyproject.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class BoardApplicationTests {

	@Autowired
	EntityManager em;
	@Autowired
	BoardRepository boardRepository;

	@Test
	void contextLoads() {
	}

}
