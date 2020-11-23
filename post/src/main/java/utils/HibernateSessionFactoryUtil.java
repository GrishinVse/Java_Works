package utils;

import models.Auto;
import models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil(){}

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try {
                Configuration configuration = new Configuration().configure();
                //configuration.configure(new File(HibernateSessionFactoryUtil.class.getClassLoader().getResource("hibernate.cfg.xml").toURI()));
                //Configuration configuration = new Configuration().configure("C:\\Users\\Asus\\IdeaProjects\\post\\src\\main\\resources\\hibernate.cfg.xml");
                //configuration.configure("/resources/hibernate.cfg.xml");
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Auto.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
