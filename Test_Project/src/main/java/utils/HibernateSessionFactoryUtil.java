package utils;

import models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory_new;

    private HibernateSessionFactoryUtil(){}

    public static SessionFactory getSessionFactory(){
        if(sessionFactory_new == null){
            try {
                Configuration configuration = new Configuration().configure();
                //configuration.configure(new File(HibernateSessionFactoryUtil.class.getClassLoader().getResource("hibernate.cfg.xml").toURI()));
                //Configuration configuration = new Configuration().configure("C:\\Users\\Asus\\IdeaProjects\\post\\src\\main\\resources\\hibernate.cfg.xml");
                //configuration.configure("/resources/hibernate.cfg.xml");
                /*configuration.addAnnotatedClass(Answer.class);
                configuration.addAnnotatedClass(Group.class);
                configuration.addAnnotatedClass(Question.class);
                configuration.addAnnotatedClass(Role.class);
                configuration.addAnnotatedClass(Schedule.class);
                configuration.addAnnotatedClass(Student_answer.class);
                configuration.addAnnotatedClass(Subject.class);
                configuration.addAnnotatedClass(Test.class);
                configuration.addAnnotatedClass(TestList.class);
                configuration.addAnnotatedClass(User.class);*/

                configuration.addAnnotatedClass(Group.class);
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory_new = configuration.buildSessionFactory(builder.build());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory_new;
    }
}
