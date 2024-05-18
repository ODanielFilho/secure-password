package com.odanielfilho.securepassword.controller;

import com.odanielfilho.securepassword.service.PasswordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    private final PasswordService service;

    public ApiController(PasswordService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FailureResponse> validatePassword(@RequestBody BodyRequest request){
        var failures = service.validatePass(request.password());
        if(failures.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.badRequest().body(new FailureResponse(failures));
        }
    }
}
