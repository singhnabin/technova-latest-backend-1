package com.pawan.ecommerce.controller;


import com.pawan.ecommerce.dto.LoginRequest;
import com.pawan.ecommerce.dto.UserRequest;
import com.pawan.ecommerce.model.ApiReponse;
import com.pawan.ecommerce.model.User;
import com.pawan.ecommerce.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@Slf4j
@CrossOrigin(value = "*")
public class UserController {

    @Autowired
    private UserService userservice;


    @PostMapping(value = "/create", produces = "application/json",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequest userRequest, Errors errors) {

//        JSONObject jsonObject = new JSONObject();
//        userservice.createUser(userRequest);
//        jsonObject.put("Status", 200);
//        jsonObject.put("message", "Sucessfully added user");
//        return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());


        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        if(errors.hasErrors()){
            List<ObjectError> err = errors.getAllErrors();
            for(ObjectError objectError:err){
                jsonArray.put(objectError.getDefaultMessage());
            }
            jsonObject.put("status",409);
            jsonObject.put("error",jsonArray);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObject.toString());

        }
       Optional<User>user= userservice.findByEmail(userRequest.getEmail());

        if(user.isPresent()){

            jsonObject.put("status",409);
            jsonObject.put("error",userRequest.getEmail()+" Email already exists");
           // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObject.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiReponse(409,"",userRequest.getEmail()+" Email already exists"));

        }
        userservice.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiReponse(200,"User created successfully",""));
    }



    @GetMapping("/getallUsers")
    public List<User> get_allUsers() {

        return userservice.getallUsers();
    }

    @GetMapping("/getUser/{id}")
    public Optional<User> getUserId(@PathVariable int id) {
        return userservice.getUserbyId(id);
    }

    @GetMapping("/verifytoken/{token}")
    public ResponseEntity<?> verifyaccount(@PathVariable String token){

        userservice.verifyaccount(token);
       // return ResponseEntity.status(HttpStatus.OK).body("Account activated successfully");
        return ResponseEntity.status(HttpStatus.OK).body(new ApiReponse(200,"Account activated successfully",""));


  }

    @PutMapping("/updateUser/{id}")
    public String updateUserInfo(@PathVariable int id,@RequestBody UserRequest userRequest ){

        Optional<User> user = userservice.getUserbyId(id);
        userservice.upateUserInfo(user,userRequest);
        return "successfully updated";
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable int id) {

        userservice.deleteUser(id);
    }
    @PostMapping(value="/login",produces=("application/json"))
    public ResponseEntity<?>loginUser(@RequestBody LoginRequest loginRequest) throws Exception {

      String jwt = userservice.login(loginRequest);
      //return ResponseEntity.status(HttpStatus.OK).body(new ApiReponse(200,jwt,""));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message",jwt);
        jsonObject.put("status",200);
        jsonObject.put("username",loginRequest.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());

    }



}
