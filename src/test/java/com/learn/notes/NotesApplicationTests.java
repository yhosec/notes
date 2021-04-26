package com.learn.notes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@Profile("test")
class NotesApplicationTests {

	@Test
	void contextLoads() {
		NotesApplication.main(new String[]{"test"});
		Assertions.assertTrue(true);
	}

}
