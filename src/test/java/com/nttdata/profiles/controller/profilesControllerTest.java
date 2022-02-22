package com.nttdata.profiles.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;


import com.nttdata.profiles.entity.Profiles;
import com.nttdata.profiles.model.Customers;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
@RunWith(SpringRunner.class)
@WebFluxTest(profilesController.class)
class profilesControllerTest {
	@Autowired
	private  WebTestClient webClient;
	
	
	@MockBean
	profilesController service;

	Profiles profil;
	Customers customer;
	
	@BeforeEach
	void setUp() throws Exception {
		profil=new Profiles("1","Juan","normal","1",true);
		customer=new Customers("Jose","dni","72838193","1",true,"1");
		
	}
	@Test
	void getProfiles() {
	    Flux<Profiles> profilFlux = Flux.just(profil);
	    Mockito.when(service.getProfiles())
        .thenReturn(profilFlux);
	    Flux<Profiles> flux = webClient.get().uri("/profiles")
	    		.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.returnResult(Profiles.class)
				.getResponseBody();
	   
	    StepVerifier.create(flux)
			.expectSubscription()
			.expectNext(profil)
			.verifyComplete();
		
		
	}
	@Test
	void saveProfile(){
		Mockito.when(service.saveProfile(profil))
		.thenReturn(Mono.just(profil));
		webClient.post().uri("/profiles")
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(profil), Profiles.class)
				.exchange()
				.expectStatus().isCreated();

	}
	@Test
	void updateProfile(){
		Mockito.when(service.updateProfile(profil))
		.thenReturn(Mono.just(profil));

		webClient.put().uri("/profiles/update")
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(profil), Profiles.class)
				.exchange()
				.expectStatus().isEqualTo(200);
	}
	@Test
	void  deleteProfile(){
		Mockito.when(service.deleteProfile(profil.getId()))
		.thenReturn(Mono.just(profil));
		webClient.put().uri("/profiles/delete/1")
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(profil), Profiles.class)
				.exchange()
				.expectStatus().isEqualTo(200);

	}
	@Test
	void saveCustomer(){
		Mockito.when(service.saveCustomer(customer))
		.thenReturn(Mono.just(customer));
		webClient.post().uri("/profiles/customers")
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(customer), Customers.class)
				.exchange()
				.expectStatus().isCreated();

	}
	@Test
	void  getCustomerByProfile(){
		Flux<Customers> customerFlux = Flux.just(customer);
	    Mockito.when(service.getCustomerByProfile(profil.getId()))
        .thenReturn(customerFlux);
	    Flux<Customers> flux = webClient.get().uri("/profiles/customers/1")
	    		.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.returnResult(Customers.class)
				.getResponseBody();

		StepVerifier.create(flux)
				.expectSubscription()
				.expectNext(customer)
				.verifyComplete();
	}
	@AfterEach
	void tearDown() throws Exception {
	}

	

}
