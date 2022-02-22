package com.param.bootbooks.mapper;

import com.param.bootbooks.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    // 增加
    int addUser(User user);

    // 删除
    int deleteUserById(@Param("UserID") int id);

    // 更新
    int updateUser(User user);

    // 查找
    User queryUserById(@Param("userID") String id);

    // 查找
    User queryUserByName(@Param("username") String name);

    // 查找所有用户
    List<User> queryAllUser();
}
