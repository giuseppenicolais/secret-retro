package com.example.secret_retro.controller;

import com.example.secret_retro.model.FeedMeBody;
import com.example.secret_retro.model.Feedback;
import com.example.secret_retro.service.SecretRetroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/secret_retro", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Validated
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SecretRetroController implements ISecretRetroController {

    @Autowired
    private SecretRetroService secretRetroService;

    @PostMapping(value = "/feedme", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> feedMe(@Valid @RequestBody FeedMeBody payload) throws Exception
    {
        secretRetroService.feedMe(payload);
        return new ResponseEntity<>("Request successfully processed", HttpStatus.OK);
    }

    @GetMapping(value = "/bubble", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Feedback bubble() throws Exception
    {
        Feedback data = secretRetroService.getLastMonthAnalytics();
        log.info("Request successfully processed {}",data);
        return data;
    }
}
