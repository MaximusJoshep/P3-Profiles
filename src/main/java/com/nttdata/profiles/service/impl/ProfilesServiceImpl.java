package com.nttdata.profiles.service.impl;

import com.nttdata.profiles.entity.Profiles;
import com.nttdata.profiles.feignclients.CustomerFeignClient;
import com.nttdata.profiles.model.Customers;
import com.nttdata.profiles.repository.ProfilesRepository;
import com.nttdata.profiles.service.ProfilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProfilesServiceImpl implements ProfilesService {

	@Autowired
    ProfilesRepository profilesRepository;

	@Autowired
    RestTemplate restTemplate;

	@Autowired
    CustomerFeignClient customerFeignClient;

	@Autowired
    WebClient webClient;

    @Override
    public Flux<Profiles> findAll() {
        return profilesRepository.findAll();
    }

    @Override
    public Mono<Profiles> save(Profiles entity) {
        return  profilesRepository.save(entity);
    }

    @Override
    public Mono<Profiles> update(Profiles entity) {
        return  profilesRepository.findById(entity.getId())
                .switchIfEmpty(Mono.empty())
                .flatMap(origin -> {
                    origin.setName(entity.getName());
                    origin.setStatus(entity.getStatus());
                    origin.setCodProfile(entity.getCodProfile());
                    origin.setType(entity.getType());
                    return profilesRepository.save(origin);
                });
    }

    @Override
    public Mono<Profiles> deleteById(String id) {
        return profilesRepository.findById(id)
                .switchIfEmpty(Mono.empty())
                .flatMap(origin -> {
                    origin.setStatus(false);
                    return profilesRepository.save(origin);
                });
    }

    @Override
    public Mono<Profiles> findById(String id) {
        return profilesRepository.findById(id);
    }

    @Override
    public Flux<Customers> getCustomers(String ProfileId) {
        Flux<Customers> flux = webClient
                .get()
                .uri("http://localhost:8080/customers/byprofile/{ProfileId}", ProfileId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Customers.class);
        return flux;
    }

    @Override
    public Mono<Customers> saveCustomer(Customers customer) {
        customer.setProfileId(customer.getProfileId());
        return customerFeignClient.saveCustomer(customer);
    }
}
