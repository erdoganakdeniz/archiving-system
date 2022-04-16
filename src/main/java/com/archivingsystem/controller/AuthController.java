package com.archivingsystem.controller;


import com.archivingsystem.auth.TokenManager;
import com.archivingsystem.dto.LoginDto;
import com.archivingsystem.entity.User;
import com.archivingsystem.repository.UserRepository;
import com.archivingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {



    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          TokenManager tokenManager,
                          UserService userService,
                          BCryptPasswordEncoder passwordEncoder){
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.userService=userService;
        this.passwordEncoder = passwordEncoder;
    }
    private AuthenticationManager authenticationManager;
    private TokenManager tokenManager;
    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        try {
            if(loginDto != null && loginDto.getUserName() != null && loginDto.getUserPassword() != null){
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginDto.getUserName(),
                        loginDto.getUserPassword()));
                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization","Bearer " + tokenManager.generateToken(loginDto.getUserName()));
                return ResponseEntity.ok().headers(headers).body("Login Success");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The username or password is incorrect. Please try again.");
        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("/signup") //auth/signup 'a gelen web istekleri ile bu metodun eşleştirilmesi sağlanır.
    public ResponseEntity<String> signUp(@RequestBody LoginDto loginDto){
        User user=new User();
        user.setUserName(loginDto.getUserName());
        user.setUserPassword(passwordEncoder.encode(loginDto.getUserPassword()));


            if(loginDto != null && loginDto.getUserName() != null && loginDto.getUserPassword() != null){
                userService.save(user);
                return ResponseEntity.ok(loginDto.getUserName() +" has been successfully registered.");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The username or password is not correct. Please try again.");


    }





}
