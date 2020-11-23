package services;

import dao.GroupDao;
import models.Group;
import models.User;

import java.util.List;

public class GroupService {
    private GroupDao groupDao = new GroupDao();

    public GroupService(){}

    public Group findGroup(int row_id){
        return groupDao.findById(row_id);
    }

    public void saveGroup(Group group) { groupDao.save(group); }

    public void updateGroup(Group group){
        groupDao.update(group);
    }

    public void deleteGroup(Group group){
        groupDao.delete(group);
    }

    public List<Group> findAllGroups(){
        return groupDao.getAll();
    }

    public User findUser(int row_id){
        return groupDao.findUserById(row_id);
    }
}