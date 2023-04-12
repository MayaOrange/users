package com.registration.users.repository;

import org.springframework.data.repository.CrudRepository;

import com.registration.users.model.User;

/**
 * This interface defines a repository for managing user entities.
 * It extends the Spring Data CrudRepository interface and provides basic CRUD operations
 * for managing user entities in the database.
 */
public interface UserRepository extends CrudRepository<User, Long>{

}
