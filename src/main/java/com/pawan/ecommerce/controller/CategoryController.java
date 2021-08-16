package com.pawan.ecommerce.controller;

import com.pawan.ecommerce.dto.CategoryRequest;
import com.pawan.ecommerce.model.ApiReponse;
import com.pawan.ecommerce.model.Category;
import com.pawan.ecommerce.service.CategoryService;
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
@RequestMapping("/api/category")
@CrossOrigin(value = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/create", produces = "application/json",consumes = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<?> create_product(@RequestBody @Valid CategoryRequest categoryrequest, Errors errors){
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

        //JSONObject jsonObject= new JSONObject();
        categoryService.createCategory(categoryrequest);
        //jsonObject.put("Status",200);
        //jsonObject.put("message","Sucessfully added category");
       // return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiReponse(200,"Sucessfully added category",""));

    }

    @GetMapping("/getallCategory")
    public ResponseEntity<?>  getallCategory(){
        List<Category> categories=categoryService.getallCategory();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message","successfully fetched");
        jsonObject.put("status",200);
        jsonObject.put("list",categories);

        return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());


    }

    @GetMapping("/getCategory/{id}")
    public Category getCategory(@PathVariable int id){

        return categoryService.getCategory(id).get();
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiReponse(200,"SuccessFully Deleted",""));
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable int id, @RequestBody CategoryRequest categoryRequest){

        Category category = categoryService.getCategory(id).get();
        categoryService.updateCategory(category,categoryRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiReponse(200,"SuccessFully Updated",""));


    }





    }



