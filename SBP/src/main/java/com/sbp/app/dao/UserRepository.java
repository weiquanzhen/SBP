package com.sbp.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sbp.app.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("select count(*) from User u where u.phone = :phone or u.email = :email")
	Integer findUser(@Param("phone")String phone, @Param("email")String email);
}
