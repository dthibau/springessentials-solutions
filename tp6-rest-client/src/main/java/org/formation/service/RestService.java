package org.formation.service;

import org.formation.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

	private final RestTemplate restTemplate;

	@Autowired
	public RestService(RestTemplateBuilder builder) {
		restTemplate = builder.rootUri("http://localhost:8080/api").build();
	}

	public Member getMember(long id) {

		return restTemplate.getForObject("/members/{id}", Member.class, id);
	}
}
