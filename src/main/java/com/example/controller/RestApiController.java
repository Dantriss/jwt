package com.example.controller;


import com.example.auth.PrincipalDetails;
import com.example.model.User;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin  //CORS 허용
@RequestMapping("api/vi")
@RequiredArgsConstructor
public class RestApiController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private final UserRepository userRepository;

    @GetMapping("/home")
    public String home(){

        return "home";
    }
    @PostMapping("/token")
    public String token(){

        return "token";
    }
    @GetMapping("user")
    public String user(Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("principal : " + principal.getUsername());
        System.out.println("principal : " + principal.getPassword());

        return "<h1>user</h1>";
    }
    @PostMapping("/join")
    public String join(@RequestBody User user){

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        userRepository.save(user);
        return "회원가입완료";
    }

}
