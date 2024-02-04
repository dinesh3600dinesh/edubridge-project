package com.example.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Long> {
	Admin findAdminByUsername(String username);

	Admin findByAdminName(String adminName);

//	@Query(value = "SELECT * FROM ADMIN u WHERE u.username = ?", nativeQuery = true)
//	public Optional<Admin> findByusername(String username);
}
