package com.example.demo.entity;

import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "admin")
public class Admin {

    public Admin(long adminId,
			@NotBlank(message = "Admin name cannot be blank") @Size(min = 2, max = 50, message = "Admin name must be between 2 and 50 characters") String adminName,
			@NotBlank(message = "Username cannot be blank") @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters") String username,
			@NotBlank(message = "Admin password cannot be blank") @Size(min = 6, message = "Admin password must be at least 6 characters") String adminpassword, @NotBlank(message = "Admin phone cannot be blank") @Pattern(regexp = "[0-9]+", message = "Admin phone must contain only digits") @Size(min = 10, max = 15, message = "Admin phone must be between 10 and 15 digits") String adminPhone) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPhone = adminPhone;
		this.username = username;
		this.adminpassword = adminpassword;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminId")
    private long adminId;

    @NotBlank(message = "Admin name cannot be blank")
    @Size(min = 2, max = 50, message = "Admin name must be between 2 and 50 characters")
    @Column(name = "adminName")
    private String adminName;

    @NotBlank(message = "Admin phone cannot be blank")
    @Pattern(regexp = "[0-9]+", message = "Admin phone must contain only digits")
    @Size(min = 10, max = 15, message = "Admin phone must be between 10 and 15 digits")
    @Column(name = "adminPhone")
    private String adminPhone;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Admin password cannot be blank")
    @Size(min = 6, message = "Admin password must be at least 6 characters")
    @Column(name = "adminpassword")
    private String adminpassword;

    public Admin() {}

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdminpassword() {
        return adminpassword;
    }

    public void setAdminpassword(String adminpassword) {
        this.adminpassword = adminpassword;
    }
  
    public boolean verifyPassword(String password) {
    return Objects.equals(this.adminpassword, password);
}

    @Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminPhone=" + adminPhone + ", username="
				+ username + ", adminpassword=" + adminpassword + "]";
	}

}
