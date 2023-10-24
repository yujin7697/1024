package com.example.demo.controller;

import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.domain.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FollowController {
    @Autowired
    private FollowService followService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/follow")
    public ResponseEntity<String> follow(@RequestParam Long followerId, @RequestParam Long followingId) {
        User follower = userRepository.findById(followerId);
        User following = userRepository.findById(followingId);

        System.out.println("follower : " + follower);
        System.out.println("following : " + following);

        // 요청으로 넘어온 followerId와 followingId에 해당하는 사용자가 존재하는지 확인
        if (follower == null || following == null) {
            return ResponseEntity.badRequest().body("Invalid followerId or followingId");
        }

        // 이미 팔로우 중인지 확인
        if (followService.isFollowing(follower, following)) {
            return ResponseEntity.badRequest().body("Already following");
        }

        // 팔로우 관계 생성
        followService.follow(follower, following);
        return ResponseEntity.ok("Followed successfully");
    }

    @PostMapping("/unfollow")
    public ResponseEntity<String> unfollow(@RequestParam Long followerId, @RequestParam Long followingId) {
        User follower = userRepository.findById(followerId);
        User following = userRepository.findById(followingId);

        // 요청으로 넘어온 followerId와 followingId에 해당하는 사용자가 존재하는지 확인
        if (follower == null || following == null) {
            return ResponseEntity.badRequest().body("Invalid followerId or followingId");
        }

        // 이미 팔로우 중이지 않은지 확인
        if (!followService.isFollowing(follower, following)) {
            return ResponseEntity.badRequest().body("Not following");
        }

        // 팔로우 관계 삭제
        followService.unfollow(follower, following);
        return ResponseEntity.ok("Unfollowed successfully");
    }

}
