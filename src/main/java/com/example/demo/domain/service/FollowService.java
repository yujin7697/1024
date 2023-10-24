package com.example.demo.domain.service;

import com.example.demo.domain.entity.Follower;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.FollowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {
    @Autowired
    private FollowerRepository followerRepository;

    public void follow(User user, User follower) {
        Follower follow = new Follower();
        follow.setUser(user);
        follow.setFollower(follower);
        followerRepository.save(follow);
    }

    public void unfollow(User user, User follower) {
        Follower follow = followerRepository.findByUserAndFollower(user, follower);
        if (follow != null) {
            followerRepository.delete(follow);
        }
    }
}