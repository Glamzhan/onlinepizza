package com.shop1.shop1.repositories;

import com.shop1.shop1.entities.Good;
import com.shop1.shop1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    @Query
    User findByName(@Param("name") String name);
}
