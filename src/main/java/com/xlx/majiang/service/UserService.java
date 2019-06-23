package com.xlx.majiang.service;

import com.xlx.majiang.mapper.UserMapper;
import com.xlx.majiang.model.User;
import com.xlx.majiang.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service层:User
 *
 * @author xielx on 2019/6/23
 */
@Service
public class UserService {

  @Autowired
  private UserMapper userMapper;

  /**
   * 插入:
   *    根据user的account_id查询数据库对象dbUser,未找到,插入前台数据
   * 更新:
   *    找到,以dbUser的id为更新条件更新
   * @param user 前台对象
   * @return boolean
   */
  public boolean createOrUpdate(User user){
    UserExample userExample = new UserExample();
    // 加入查询条件accountId
    userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());

    List<User> userList =  userMapper.selectByExample(userExample);

    if (userList.size() == 0){
      // 插入
      user.setGmtCreate(System.currentTimeMillis());
      user.setGmtModified(user.getGmtCreate());
      int effectRow = userMapper.insertSelective(user);
      return effectRow > 0;
    }else {
      // 修改
      User updateUser = new User();
      updateUser.setGmtModified(System.currentTimeMillis());
      updateUser.setAvatarUrl(user.getAvatarUrl());
      updateUser.setToken(user.getToken());
      updateUser.setName(user.getName());
      UserExample example = new UserExample();
      //添加数据库查询到的user的id作为更新id
      example.createCriteria().andIdEqualTo(userList.get(0).getId());

      int effectRow = userMapper.updateByExampleSelective(updateUser,example);
      return effectRow > 0;
    }
  }

}
