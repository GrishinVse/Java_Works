package dao;

import models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroupDao implements Dao<Group> {

    private List<Group> groups = new ArrayList<>();

    public GroupDao(){ }

    public Group findById(int row_id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Group.class, row_id);
    }

    @Override
    public Optional<Group> get(int row_id) {
        return Optional.ofNullable(groups.get((int) row_id));
    }

    @Override
    public List<Group> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return (List<Group>) session.createQuery("From Group").list();
    }

    @Override
    public void save(Group group) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(group);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Group group) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(group);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Group group) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(group);
        tx1.commit();
        session.close();
    }

    public User findUserById(int row_id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, row_id);
    }

}
