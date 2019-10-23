package com.sanelee.collegeentrance.mapper;

import com.sanelee.collegeentrance.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserMapper extends CrudRepository<User,Long> {
    public User findByUsernameAndPassword(String username,String password);

    public User findByUsername(String username);
}
