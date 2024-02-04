package com.example.demo.service;

import com.example.demo.dao.AdminDao;
import com.example.demo.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public List<Admin> findAll() {
        return adminDao.findAll();
    }

    @Override
    public Admin findById(long id) {
        Optional<Admin> result = adminDao.findById(id);
        return result.orElse(null);
    }

    @Override
    public Admin findByUsername(String username) {
        return adminDao.findAdminByUsername(username);
    }

    @Override
    public void save(Admin admin) {
        adminDao.save(admin);
    }

    @Override
    public void update(Admin admin) {
        if (adminDao.existsById(admin.getAdminId())) {
            adminDao.save(admin);
        }
    }

    @Override
    public void deleteById(long id) {
        adminDao.deleteById(id);
    }
    @Override
    public boolean loginAdmin(String username, String adminpassword) {
        Admin admin = findByUsername(username);
        return admin != null && admin.getAdminpassword().equals(adminpassword);
    }
}
