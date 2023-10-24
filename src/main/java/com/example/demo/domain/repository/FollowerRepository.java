package com.example.demo.domain.repository;
import com.example.demo.domain.entity.Follower;
import com.example.demo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FollowerRepository extends JpaRepository<Follower, Long> {
    List<Follower> findByUser(User user);

    Follower findByUserAndFollower(User user, User follower);
}