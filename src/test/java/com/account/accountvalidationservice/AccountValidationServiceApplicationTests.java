package com.account.accountvalidationservice;

import com.account.accountvalidationservice.config.Props;
import com.account.accountvalidationservice.dto.AccountValidationRequest;
import com.account.accountvalidationservice.dto.AccountValidationResponse;
import com.account.accountvalidationservice.service.AccountValidationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountValidationServiceApplicationTests {

	@Autowired
	private Props props;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoadsWhenBadRequest() throws Exception{
		this.mockMvc.perform(post("/validate")
		.content(objectMapper.writeValueAsString(new AccountValidationRequest()))
		.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	@Test
	void contextLoadsWhenSuccessAndAllProvidersUsed() throws Exception{
		AccountValidationRequest avr =  new AccountValidationRequest();
		avr.setAccountNumber("100");
		String resp = this.mockMvc.perform(post("/validate",avr).
				content(objectMapper.writeValueAsString(avr))
		.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).
				andReturn().getResponse().getContentAsString();
		AccountValidationResponse reponse = objectMapper.readValue(resp, AccountValidationResponse.class);

		Assertions.assertEquals(props.getProviders().size(), reponse.getResult().size());
	}

	@Test
	void contextLoadsWhenSuccessAndSelectedProvidersUsed() throws Exception{
		AccountValidationRequest avr =  new AccountValidationRequest();
		avr.setAccountNumber("100");
		avr.setProviders(Stream.of("provider1").collect(Collectors.toList()));
		String resp = this.mockMvc.perform(post("/validate",avr).
				content(objectMapper.writeValueAsString(avr))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).
				andReturn().getResponse().getContentAsString();
		AccountValidationResponse reponse = objectMapper.readValue(resp, AccountValidationResponse.class);

		Assertions.assertEquals(avr.getProviders().get(0), reponse.getResult().get(0).getProvider());
	}

}
