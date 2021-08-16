package com.pawan.ecommerce.service;

import com.pawan.ecommerce.dto.LoginRequest;
import com.pawan.ecommerce.dto.UserRequest;
import com.pawan.ecommerce.exception.BadException;
import com.pawan.ecommerce.model.Token;
import com.pawan.ecommerce.model.User;
import com.pawan.ecommerce.repo.TokenRepo;
import com.pawan.ecommerce.repo.UserRepo;
import com.pawan.ecommerce.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepo userrepo;
    @Autowired
    private TokenRepo tokenRepo;
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyuserDetailsService myuserDetailsService;
    @Autowired
    private JwtUtils jwtUtils;




    public void createUser(UserRequest userRequest) {

        User user = new User();
        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setEmail(userRequest.getEmail());
        //user.setPassword(userRequest.getPassword());
        user.setPassword(getEncodedPassword(userRequest.getPassword()));
        user.setIsenabled(false);
        userrepo.save(user);
        String token =generateToken(user);
        String link ="Please Activate Account"+"http://localhost:8080/api/user/verifytoken/"+token;

        mailSenderService.sendMail(user.getEmail(),link,"Activation Link");



    }

    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String generateToken(User user) {

       String token_unique= UUID.randomUUID().toString();

       Token token = new Token();
       token.setToken(token_unique);
       token.setUser(user);
       //token.setExpirytime();

       tokenRepo.save(token);

        return token_unique;
    }

    public List<User> getallUsers() {

        return userrepo.findAll();
    }

    public Optional<User> getUserbyId(int id) {

        return userrepo.findById(id);

    }

    public void deleteUser(int id) {

        userrepo.deleteById(id);


    }

    public void upateUserInfo(Optional<User> user, UserRequest userRequest) {

        user.get().setLastname(userRequest.getLastname());
        user.get().setEmail(userRequest.getEmail());
        user.get().setFirstname(userRequest.getFirstname());
        //user.get().setPassword(userRequest.getPassword());
        user.get().setIsenabled(true);
        userrepo.save(user.get());
    }

    public void verifyaccount(String token) {
     Optional<Token> tokenOptional = tokenRepo.findByToken(token);
        if(tokenOptional.isPresent()){
            Optional<User> user = userrepo.getByEmail((tokenOptional.get().getUser()).getEmail());
            if(user.isPresent()){
                user.get().setIsenabled(true);
                userrepo.save(user.get());
            }
        }
}

    public String login(LoginRequest loginRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        }catch( AuthenticationException ex){
            throw new BadException("Incorrect Credentials");

        }

        UserDetails userDetails = myuserDetailsService.loadUserByUsername(loginRequest.getEmail());
        String jwtToken = jwtUtils.generateToken(userDetails);
        return jwtToken;

    }

    public Optional<User> findByEmail(String email) {
        return userrepo.getByEmail(email);
    }
}
