package com.fpt.swd392.cvsts.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @PostMapping("/child-profile")
    public String createChildProfile() {
        return "create new child profile";
    }

    @GetMapping("/child-profile")
    public String getChildProfileDetails() {
        return "get one child profile details";
    }

    @GetMapping("/child-profiles")
    public String getChildProfiles() {
        return "get child profiles list";
    }

    @PatchMapping("/child-profile")
    public String updateChildProfile() {
        return "edit child profile";
    }

    @DeleteMapping("/child-profile")
    public String deleteChildProfile() {
        return "delete child profile";
    }

}