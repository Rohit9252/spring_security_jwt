package com.example.springjwt.Service;

import com.example.springjwt.DTORequest.LoginDto;
import com.example.springjwt.DTORequest.SignupDto;
import com.example.springjwt.DTOResponse.JwtResponse;

public interface StudentService {

    public String signup(SignupDto signupDto);

    public JwtResponse login(LoginDto loginDto);




}
