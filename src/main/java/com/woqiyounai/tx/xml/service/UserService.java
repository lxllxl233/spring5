package com.woqiyounai.tx.xml.service;

import com.woqiyounai.tx.xml.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional//通过配置类替代
public class UserService {

    @Autowired
    private UserDao userDao;

    public void accountOpt(){
        // lucy 少 100
        userDao.reduceMoney();
        // mary 多 100
        userDao.addMoney();
    }

    public void txAccountOpt() {
        userDao.reduceMoney();
        int i = 1/0;
        userDao.addMoney();
    }
}
