package com.nttdata.profiles.service;

import com.nttdata.profiles.entity.Profiles;
import com.nttdata.profiles.model.Customers;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProfilesService extends CrudService<Profiles, String> {
    Mono<Profiles> findById(String id);
    Flux<Customers> getCustomers(String ProfileId);
    Mono<Customers> saveCustomer(Customers customer);
}
