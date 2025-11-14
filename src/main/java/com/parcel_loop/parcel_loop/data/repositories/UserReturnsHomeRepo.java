package com.parcel_loop.parcel_loop.data.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.parcel_loop.parcel_loop.data.documents.UserReturnsHomeDoc;

public interface UserReturnsHomeRepo extends MongoRepository<UserReturnsHomeDoc, String> {
    Optional<UserReturnsHomeDoc> findByEmail(String email);
}
