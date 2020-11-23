package services;

import dao.UserDao;
import models.*;

import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();

    public UserService(){}

    public User findUser(int id){
        return userDao.findById(id);
    }

    public void saveUser(User user) { userDao.save(user); }

    public void updateUser(User user){
        userDao.update(user);
    }

    public void deleteUser(User user){
        userDao.delete(user);
    }

    public List<User> findAllUsers(){
        return userDao.getAll();
    }

    /*public Auto findAuto(int id){
        return userDao.findAutoById(id);
    }*/
}
