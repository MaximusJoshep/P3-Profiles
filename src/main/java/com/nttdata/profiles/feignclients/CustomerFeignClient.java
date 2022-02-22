package com.nttdata.profiles.feignclients;

import com.nttdata.profiles.model.Customers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@FeignClient(name = "customers-service")
public interface CustomerFeignClient {

    @PostMapping("/profiles/customers")
    Mono<Customers> saveCustomer(@RequestBody Customers customer);

    @GetMapping("/profiles/customers/{id}")
    Flux<Customers> getCustomerByProfile(@RequestBody Customers customer);

    @GetMapping("/customers/byprofile/{ProfileId}")
    Flux<Customers> getCustomers(@PathVariable String ProfileId);
}
