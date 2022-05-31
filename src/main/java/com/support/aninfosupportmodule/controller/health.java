package com.support.aninfosupportmodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class health {

    @GetMapping(path="/")
    public String getHealth() {
        return "Ok";
    }
}
