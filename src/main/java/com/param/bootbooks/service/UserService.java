package com.param.bootbooks.service;

import com.param.bootbooks.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhoujingyu（976944083@qq.com）
 */
public interface UserService {
    // 增加
    int addUser(User user);

    // 删除
    int deleteUserById(int id);

    // 更新
    int updateUser(User user);

    // 查找
    User queryUserById(int id);

    // 查找
    User queryUserByName(String name);

    // 查找所有书
    List<User> queryAllUser();
}
