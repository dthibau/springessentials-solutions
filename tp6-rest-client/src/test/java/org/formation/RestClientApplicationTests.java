package org.formation;

import static org.assertj.core.api.Assertions.assertThat;

import org.formation.model.Member;
import org.formation.service.RestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestClientApplicationTests {

	@Autowired
	RestService restService;
	
	
	@Test
	void contextLoads() {
		
		assertThat(restService.getMember(1l)).isInstanceOf(Member.class);
	}

}
