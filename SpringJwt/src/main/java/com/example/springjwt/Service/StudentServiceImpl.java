package com.example.springjwt.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.springjwt.DTORequest.LoginDto;
import com.example.springjwt.DTOResponse.JwtResponse;
import com.example.springjwt.jwt.JwtUtil;
import com.example.springjwt.model.MyRoles;
import com.example.springjwt.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springjwt.DTORequest.SignupDto;
import com.example.springjwt.model.Role;
import com.example.springjwt.model.Student;
import com.example.springjwt.repository.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService, UserDetailsService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Student s =  studentRepo.findByEmail(username).
        orElseThrow(() -> new UsernameNotFoundException("User not found"));
            List<GrantedAuthority> authorities = new ArrayList<>();
            for(Role role : s.getRole()) {
                authorities.add(new SimpleGrantedAuthority(role.getRole().toString()));
            }
        return new UserDetailImpl(s.getEmail(), s.getPassword(), authorities);
    }

    @Override
    public String signup(SignupDto signupDto) {
        Role role = roleRepo.findByRole(MyRoles.USER);
        Student s = new Student(signupDto.getName(), signupDto.getEmail(),signupDto.getPassword());
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        s.setRole(roles);
        System.out.println(s);
           Student  svaStudent =  studentRepo.save(s);

           if(svaStudent != null) {
               return "Student saved";
           }
           else {
               return "Student not saved";
           }
    }

    @Override
    public JwtResponse login(LoginDto loginDto) {

        return null;
    }
}
