package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.userServ.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<? extends Object> currentUserName(Authentication authentication){
        try {
            User user = userService.getUserByEmail(authentication.getName());
            Gson gson = new Gson();
            JSONObject objClass = new JSONObject();
            JSONArray classArr = new JSONArray();

            objClass.put("firstName",user.getFirstName());
            objClass.put("lastName", user.getLastName());
            objClass.put("email", user.getEmail());
            classArr.put(objClass);

            JSONArray jsonArray = new JSONArray();
            jsonArray.put(classArr);
            JSONObject jsonResp = new JSONObject();
            jsonResp.put("profile",classArr);
            String gsonToJson = gson.toJson(JsonParser.parseString(jsonResp.toString()));
            return new ResponseEntity<>(gsonToJson, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>("Some error", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<? extends Object>register(@RequestBody User user){
        try {
            return new ResponseEntity<>(userService.register(user), HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error with registration", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<? extends Object> getAllUsers() {
        try {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Some Error", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<? extends Object> addUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Some Error", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<? extends Object> updateUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Some Error", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<? extends Object> deleteAllUsers() {
        try {
            userService.deleteAllUsers();
            return new ResponseEntity<>("Deleted!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<? extends Object> getUserById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<? extends Object> deleteUserById(@PathVariable Integer id) {
        try {
            userService.deleteUserById(id);
            return new ResponseEntity<>("User with id : " + id + " deleted!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
