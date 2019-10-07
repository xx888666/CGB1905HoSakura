package org.sakura.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import org.sakura.common.pojo.User;

@Mapper
public interface UserMapper {

    User checkUsername(String username);

    int register(User user);

    User findUserByUp(User user);
}
