package me.tdcarefor.tdcloud.user.query.service.impl;

import me.tdcarefor.tdcloud.user.query.repository.UserQueryRepository;
import me.tdcarefor.tdcloud.user.query.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xsx on 2017/4/26.
 */
@Service
public class UserServiceImpl implements UserService {

  private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  private UserQueryRepository userQueryRepository;

}
