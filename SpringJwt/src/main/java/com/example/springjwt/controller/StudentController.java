package com.example.springjwt.controller;

import com.example.springjwt.DTORequest.LoginDto;
import com.example.springjwt.DTOResponse.JwtResponse;
import com.example.springjwt.Service.UserDetailImpl;
import com.example.springjwt.jwt.JwtUtil;
import com.example.springjwt.model.Student;
import com.example.springjwt.repository.StudentRepo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.springjwt.DTORequest.SignupDto;
import com.example.springjwt.Service.StudentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins="*")
public class StudentController {


    @Autowired
    private StudentService studentService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private JwtUtil jwtUtil;

        @PostMapping("/signup")
        public ResponseEntity<String> sigupHandler(@RequestBody  SignupDto signupDto){

            signupDto.setPassword(passwordEncoder.encode(signupDto.getPassword()));

            return ResponseEntity.ok(studentService.signup(signupDto));


        }

        @PostMapping("/login")
        public ResponseEntity<JwtResponse> loginHandler(@RequestBody LoginDto loginDto) {

            Authentication auth =   authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(auth);

            String jwt = jwtUtil.createtoken(auth);

            UserDetailImpl userDetail =  (UserDetailImpl)  auth.getPrincipal();

            List<String> list =  userDetail.getAuthorities().stream().map(i->i.getAuthority()).collect(Collectors.toList());
            JwtResponse jwtResponse = new JwtResponse();
            jwtResponse.setToken(jwt);
            jwtResponse.setEmail(userDetail.getUsername());
            jwtResponse.setRole(list);


          return ResponseEntity.ok( jwtResponse);

        }

        @GetMapping("/hello")
        public ResponseEntity<Student> getCurrentUser() {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetailImpl userDetail = (UserDetailImpl) auth.getPrincipal();
            Optional<Student> opt = studentRepo.findByEmail(userDetail.getUsername());
            return ResponseEntity.ok(opt.get());
        }


}
