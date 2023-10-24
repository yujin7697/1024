package com.example.demo.controller;

import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.domain.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class FollowController {
    @Autowired
    private FollowService followService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/follow/{Email}")
    public ResponseEntity<String> followUser(@PathVariable String Email, HttpServletRequest request) {
        System.out.println("followcontroller임!?!?!?!?!!!" + Email);

        Principal principal = request.getUserPrincipal();
        String currentUsername = principal.getName();

        User user = userRepository.findByEmail(currentUsername);
        System.out.println("user " + user);

        User follower = userRepository.findByEmail(Email);
        System.out.println("follower " + follower);

        if (user != null && follower != null) {
            followService.follow(user, follower);
            return ResponseEntity.ok("팔로우 성공");
        }
        return ResponseEntity.badRequest().body("팔로우 실패");
    }

    @PostMapping("/unfollow/{Email}")
    public ResponseEntity<String> unfollowUser(@PathVariable String Email,HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        String currentUsername = principal.getName();

        User user = userRepository.findByEmail(currentUsername);
        User follower = userRepository.findByEmail(Email);
        if (user != null && follower != null) {
            followService.unfollow(user, follower);
            return ResponseEntity.ok("언팔로우 성공");
        }
        return ResponseEntity.badRequest().body("언팔로우 실패");
    }
}
