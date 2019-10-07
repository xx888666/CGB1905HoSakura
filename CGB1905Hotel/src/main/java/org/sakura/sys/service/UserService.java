package org.sakura.sys.service;


import org.sakura.common.pojo.User;

public interface UserService {

	boolean checkUsername(String username);


    void register(User user);

    String findUserByUP(User user);
}
