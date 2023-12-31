package com.tendertinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    Services services;
    @GetMapping("/keepalive")
    public ResponseEntity<String> keepAlive(){
        return new ResponseEntity<>("I am Alive :)", HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Object> getProfiles(){
        try {
            return new ResponseEntity<>(services.getProfiles(), HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(path = {"/recs"}, params = {"token"})
    public ResponseEntity<Object> getRecommendations(@RequestParam String token){
        try{
            return new ResponseEntity<>(services.getRecommendations(token), HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(path = {"/likes"}, params = {"token"})
    public ResponseEntity<Object> myLikes(@RequestParam String token){
        try{
            return new ResponseEntity<>(services.myLikes(token), HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(params = {"name"})
    public ResponseEntity<Object> searchByName(@RequestParam String name){
        try{
            return new ResponseEntity<>(services.searchByName(name), HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(params = {"gender"})
    public ResponseEntity<Object> searchByGender(@RequestParam String gender){
        try{
            return new ResponseEntity<>(services.searchByGender(gender), HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(params = {"city"})
    public ResponseEntity<Object> searchByCity(@RequestParam String city){
        try{
            return new ResponseEntity<>(services.searchByCity(city), HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
