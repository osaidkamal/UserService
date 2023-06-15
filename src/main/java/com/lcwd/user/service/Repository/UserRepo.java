package com.lcwd.user.service.Repository;

import com.lcwd.user.service.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

}
