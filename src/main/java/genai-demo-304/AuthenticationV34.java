```java
//code-start

package com.example.loginapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginApiApplication.class, args);
    }

}

// --- User.java ---
package com.example.loginapi.model;

public class User {

    private String username;
    private String password;

    // Constructors, Getters and Setters

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

// --- UserController.java ---
package com.example.loginapi.controller;

import com.example.loginapi.model.User;
import com.example.loginapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * API endpoint for user login
     *
     * @param user User object containing username and password
     * @return ResponseEntity with login status
     */
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        boolean isAuthenticated = userService.authenticate(user);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}

// --- UserService.java ---
package com.example.loginapi.service;

import com.example.loginapi.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    /**
     * Authenticates the user by username and password
     *
     * @param user User object containing the credentials
     * @return boolean indicating if authentication is successful
     */
    public boolean authenticate(User user) {
        // Validate user inputs to prevent injection attacks. // Security
        if (user.getUsername() == null || user.getPassword() == null) {
            return false;
        }

        // For demonstration, hard-coded "admin" and "password". Implement actual authentication logic here. // Security
        if ("admin".equals(user.getUsername()) && "password".equals(user.getPassword())) {
            return true;
        }
        return false;
    }
}

//code-end
```