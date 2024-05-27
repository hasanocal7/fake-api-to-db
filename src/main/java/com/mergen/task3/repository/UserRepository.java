package com.mergen.task3.repository;

import com.mergen.task3.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {
    @Query("SELECT u FROM User u JOIN u.address a WHERE a.city = :city")
    List<User> findByCity(@Param("city") String city);
}
