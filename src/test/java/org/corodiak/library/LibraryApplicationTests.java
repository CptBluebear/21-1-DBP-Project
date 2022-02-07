package org.corodiak.library;

import org.corodiak.library.mapper.MemberMapper;
import org.corodiak.library.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LibraryApplicationTests {

	@Autowired
	MemberMapper memberMapper;
	
	@Test
	void contextLoads() {
	}

}
