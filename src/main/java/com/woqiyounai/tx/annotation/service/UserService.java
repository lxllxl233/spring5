package com.woqiyounai.tx.annotation.service;

import com.woqiyounai.tx.annotation.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED
        ,isolation = Isolation.REPEATABLE_READ
                )
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
        userDao.addMoney();
    }
}
