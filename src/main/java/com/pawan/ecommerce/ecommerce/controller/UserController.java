package com.pawan.ecommerce.ecommerce.controller;


import com.pawan.ecommerce.ecommerce.dto.LoginRequest;
import com.pawan.ecommerce.ecommerce.dto.UserRequest;
import com.pawan.ecommerce.ecommerce.model.ApiReponse;
import com.pawan.ecommerce.ecommerce.model.User;
import com.pawan.ecommerce.ecommerce.service.UserService;
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
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@Slf4j
@CrossOrigin(value = "*")
public class UserController {

    @Autowired
    private UserService userservice;

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        // TODO Auto-generated method stub
        String str2 = "This Is is done by one one and only Saket Saket";
        String[] split = str2.split(" ");

        HashMap<String,Integer> map = new HashMap<String,Integer>();
        for (int i=0; i<split.length; i++) {
            if (map.containsKey(split[i])) {
                int count = map.get(split[i]);
                map.put(split[i], count+1);
            }
            else {
                map.put(split[i], 1);
            }
        }
        System.out.println(map);


        List<String> list = Arrays.asList("John", "Mark", "Robert", "Lucas", "Brandon","nabin","singh","john","");
//        List<String> list= Array.asList("nabin","singh","john","doe");
        List<String> newList=list.stream().filter(name->!name.isEmpty()).collect(Collectors.toList());
        System.out.println(newList);
        //
        String newStr="frequent, Java program to counts how many times a words appears in a String or find repeated words. It can help you in to find the most frequent words in a string also check the count which will be equal to one for unique words.".replace(",","").replace(".","");
        ;
        String[] words=newStr.split(" ");

        List<String> repeatedWords = new ArrayList<>();
      /*      List<String> listOfWords = Arrays.asList(strArray);
      /* Declare HashSet of String that will
      contain unique words */
        List<String> listOfWords = Arrays.asList(words);
      /* Declare HashSet of String that will
      contain unique words */
        HashSet<String> uniqueWords = new HashSet<>(listOfWords);
        for(String word : uniqueWords)
        {
            if(Collections.frequency(listOfWords,word) > 1)
                System.out.print(" "+ word+" ");
        }





        String name= String.valueOf(list.stream().filter(l->l=="John"));
        System.out.println(name);
        List<String> newlist=list.stream().sorted().collect(Collectors.toList());
        System.out.println(newlist.toString());
        List<Integer> numbers= Arrays.asList(1,3,4,5,6,7,8);
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        linkedList.add(6);
        linkedList.add(9);
        Collections.reverse(linkedList);
        System.out.println(linkedList);

        String str="aashishgiri";
//        System.out.println(str.distinct());
        System.out.println(str.chars().distinct().mapToObj(c -> String.valueOf((char)c)).collect(Collectors.joining()));
        char s[]=str.toCharArray();
//        char n[]= Stream.of(s
        int c[]=new int[26];
        for(int i=0;i<s.length;i++){
            if(s[i]!=' '){
                c[s[i]-'a']++;
            }
        }
        String s2="";
        for(int i=0;i<26;i++){
            if(c[i]==0){
                s2=s2+(char)(i+'a');
            }
        }
        System.out.println(s2);
        String str1="aashish";
        byte b1[] = str1.getBytes();

        for(byte b=65;b<=90;b++) {

            for (int i = 0; i < b1.length; i++) {
                if (b != b1[i]) {
                    System.out.println((char) b);
                    break;
                }
            }
        }

//       Optional<Integer> number= numbers.stream().reduce((num, num2)-> {
////                    System.out.println(num + " " + num2);
////                    return num+num2;
////                }
////                ).filter(n->n*4);
        List<Integer> number= (List<Integer>) numbers.stream().mapToInt(n->n*25).filter(num-> num%2==0);
        number.stream().mapToInt(num->{
                    System.out.println(num);
                    return 0;
                }

                );

        return null;
    }

    private boolean getName(String name) {
        return name.length()>4;
    }


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
