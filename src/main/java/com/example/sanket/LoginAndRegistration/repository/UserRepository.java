package com.example.sanket.LoginAndRegistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sanket.LoginAndRegistration.model.UserDtls;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDtls,Integer> {

	public boolean existsByEmail(String email);
	
	public UserDtls findByEmail(String email);
}
