package com.nttdata.profiles.controller;

import com.nttdata.profiles.entity.Profiles;
import com.nttdata.profiles.model.Customers;
import com.nttdata.profiles.service.ProfilesService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class profilesController {

    private final ProfilesService profilesService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Profiles> getProfiles(){
        System.out.println("Listar perfiles");
        return profilesService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Profiles> saveProfile(@RequestBody Profiles profiles){
        System.out.println("Guardar nuevo perfil");
        return profilesService.save(profiles);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Profiles> updateProfile(@RequestBody Profiles profiles){
        System.out.println("Actualizar datos de perfil");
        return profilesService.update(profiles);
    }

    @PutMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Profiles> deleteProfile(@PathVariable String id){
        System.out.println("Eliminar perfil - cambiar de estado");
        return profilesService.deleteById(id);
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Customers> saveCustomer(@RequestBody Customers customer){
        System.out.println("Cliente creado desde perfil");
        return profilesService.saveCustomer(customer);
    }

    @GetMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Customers> getCustomerByProfile(@PathVariable String id){
        System.out.println("Listar clientes");
        return profilesService.getCustomers(id);
    }
}
