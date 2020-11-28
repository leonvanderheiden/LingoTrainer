package com.example.lingotrainer.round.controller;

import com.example.lingotrainer.round.service.RoundServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoundController {

    @Autowired
    public RoundServiceInterface roundService;
}
