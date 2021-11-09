package com.example.DBUser;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class SimpleController {
    DBUser db = new DBUser();

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return db.getAllUsers();
    }
    @GetMapping("/user")
    public User getAUser(@RequestParam String q){
        return db.getAUserByName(q);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getAUser(@PathVariable int id){
        User user1 =  db.getAUser(id);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("server", Collections.singletonList("codeofChitkara"));

        HttpStatus status = HttpStatus.CREATED;
        ResponseEntity<User> response =
                new ResponseEntity<User>(user1,headers,status);
        return response;

    }
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public boolean createUser(@RequestBody User user) {
        return db.addUser(user);
    }

    @DeleteMapping("/users/{id}")
    public boolean deleteAUser(@PathVariable int id){
        return db.deleteAUser(id);
    }

}
