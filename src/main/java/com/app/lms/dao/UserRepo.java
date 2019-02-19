package com.app.lms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.lms.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

}
