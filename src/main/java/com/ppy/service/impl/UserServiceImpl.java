package com.ppy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ppy.entity.User;
import com.ppy.mapper.UserMapper;
import com.ppy.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
