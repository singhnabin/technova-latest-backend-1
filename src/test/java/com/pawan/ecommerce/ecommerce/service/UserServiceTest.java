//package com.pawan.ecommerce.ecommerce.service;
//
//import com.pawan.ecommerce.ecommerce.dto.LoginRequest;
//import com.pawan.ecommerce.ecommerce.dto.UserRequest;
//import com.pawan.ecommerce.ecommerce.model.User;
//import com.pawan.ecommerce.ecommerce.repo.UserRepo;
//import com.pawan.ecommerce.ecommerce.utils.JwtUtils;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class UserServiceTest {
//
//    @Autowired
//    private UserService userService;
//
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private MyuserDetailsService myuserDetailsService;
//
//    @MockBean
//    private UserRepo userRepo;
//    @Autowired
//    private JwtUtils jwtUtils;
//
//    @Test
//    void createUser() {
//        UserRequest userRequest= new UserRequest("nabin","singh","nabinsingh@db.com","nnn");
//        userService.createUser(userRequest);
//        User user=new User();
//        user.setIsenabled(false);
//        user.setEmail(userRequest.getEmail());
//        user.setFirstname(userRequest.getFirstname());
//        user.setLastname(userRequest.getLastname());
//        user.setPassword(userRequest.getPassword());
//
//        verify(userRepo,times(1)).save(user);
//
//
//    }
//
//    @Test
//    void getallUsers() {
//    }
//
//    @Test
//    void getUserbyId() {
//    }
//
//    @Test
//    void deleteUser() {
//    }
//
//    @Test
//    void upateUserInfo() {
//    }
//
//    @Test
//    void verifyaccount() {
//    }
//
//    @Test
//    void login() throws Exception {
//        LoginRequest loginRequest=new LoginRequest("nabin@gmail.com","nnnn");
//       //doNothing().when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()))).getDetails();
////        doNothing().when();
//        when(jwtUtils.generateToken(myuserDetailsService.loadUserByUsername(loginRequest.getEmail()))).thenReturn(anyString());
//        assertEquals(anyString(),userService.login(loginRequest));
//    }
//
//    @Test
//    void findByEmail() {
//        User user=new User(1,"nabin","singh","nabinsingh@gmail.com","nnnn",true);
//        when(userRepo.getByEmail(user.getEmail())).thenReturn(java.util.Optional.of(user));
//        assertEquals(user.getEmail(),userService.findByEmail(user.getEmail()).get().getEmail());
//
//
//    }
//}