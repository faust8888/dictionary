package com.faust8888.project.dictionary.service;

import com.faust8888.project.dictionary.db.dao.UserDAO;
import com.faust8888.project.dictionary.db.items.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public User getUserByLogin(final String login) {
        return userDAO.getUserByLogin(login);
    }
}
