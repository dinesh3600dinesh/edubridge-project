package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/list")
    public ResponseEntity<List<Admin>> listAdmins() {
        List<Admin> admins = adminService.findAll();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Admin> findAdminById(@PathVariable long id) {
        Admin admin = adminService.findById(id);
        if (admin != null) {
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addadmin")
    public ResponseEntity<String> addAdmin(@RequestBody Admin admin) {
        adminService.save(admin);
        return new ResponseEntity<>("Admin added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAdmin(@RequestBody Admin admin) {
        adminService.update(admin);
        return new ResponseEntity<>("Admin updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable long id) {
        adminService.deleteById(id);
        return new ResponseEntity<>("Admin deleted successfully", HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Admin adminData) {
        System.out.println("Received a login request for admin: " + adminData.getUsername());

        Admin admin = adminService.findByUsername(adminData.getUsername());

        if (admin.getAdminpassword().equals(adminData.getAdminpassword())) {
            System.out.println("Admin login successful: " + admin);

            Admin sendadmin = new Admin();
            sendadmin.setAdminId(admin.getAdminId());
            sendadmin.setAdminName(admin.getAdminName());
            sendadmin.setAdminPhone(admin.getAdminPhone());
            sendadmin.setUsername(admin.getUsername());
            sendadmin.setAdminpassword(admin.getAdminpassword());

            return ResponseEntity.ok(sendadmin);
        } else {
            System.out.println("Admin login failed for: " + adminData.getUsername());
            return (ResponseEntity<?>) ResponseEntity.internalServerError();
        }
    }
}
