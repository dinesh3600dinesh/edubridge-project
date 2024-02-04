package com.example.demo.service;

import com.example.demo.entity.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> findAll();

    Admin findById(long id);

    Admin findByUsername(String username);

    void save(Admin admin);

    void update(Admin admin);

    void deleteById(long id);
    boolean loginAdmin(String username, String adminpassword);
}
