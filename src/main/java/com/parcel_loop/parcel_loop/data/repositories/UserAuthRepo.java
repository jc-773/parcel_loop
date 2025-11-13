package com.parcel_loop.parcel_loop.data.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.parcel_loop.parcel_loop.data.documents.UserAuthDoc;

public interface UserAuthRepo extends MongoRepository<UserAuthDoc, String> {
    Optional<UserAuthDoc> findByEmail(String email);
}
