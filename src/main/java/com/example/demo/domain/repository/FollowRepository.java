package com.example.demo.domain.repository;
import com.example.demo.domain.entity.Follow;
import com.example.demo.domain.entity.User;
import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByUser(User user);

    Follow findByFollowerAndFollowing(User follower, User following);
}