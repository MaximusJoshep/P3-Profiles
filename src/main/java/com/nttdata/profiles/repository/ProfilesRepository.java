package com.nttdata.profiles.repository;

import com.nttdata.profiles.entity.Profiles;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProfilesRepository extends ReactiveMongoRepository<Profiles, String> {
}
