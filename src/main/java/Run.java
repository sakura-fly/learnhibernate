import entity.Grade;
import entity.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Run {

    @Test
    public void add() {
        //creating configuration object
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file

        //creating seession factory object
        SessionFactory factory = cfg.buildSessionFactory();

        //creating session object
        Session session = factory.openSession();

        //creating transaction object
        Transaction t = session.beginTransaction();
        User u = new User();
        u.setGender(2);
        u.setGrade(66);
        u.setName("naruto");
        u.setPhone("13838381438");
        u.setPwd("666");
        session.save(u);
        t.commit();
        session.close();
    }


    @Test
    public void list() {
        //creating configuration object
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file

        //creating seession factory object
        SessionFactory factory = cfg.buildSessionFactory();

        //creating session object
        Session session = factory.openSession();

        //creating transaction object
        Transaction t = session.beginTransaction();
        // User u = new User();
        // u.setGender(2);
        // u.setGrade(66);
        // u.setName("naruto");
        // u.setPhone("13838381438");
        // u.setPwd("666");

        String sql = "select * from user";
        SQLQuery sq = session.createSQLQuery(sql);
        sq.addEntity(User.class);
        List r = sq.list();
        System.out.println(r);
        // session.
        t.commit();
        session.close();
    }

    @Test
    public void group() {
        //creating configuration object
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file

        //creating seession factory object
        SessionFactory factory = cfg.buildSessionFactory();

        //creating session object
        Session session = factory.openSession();

        //creating transaction object
        Transaction t = session.beginTransaction();

        String sql = "select gender  ,  sum(grade) as grade  from user group by gender;";
        SQLQuery sq = session.createSQLQuery(sql);
        // sq.addEntity(Grade.class);
        List<Object[]> r = sq.list();
        for (int i = 0; i < r.size(); i++) {
            Object[] l = r.get(i);
            for (int j = 0; j < l.length; j++) {
                System.out.println(l[j]);
            }
        }
        // r.get(0).getGender();
        // System.out.println(r);
        // session.
        t.commit();
        session.close();
    }


    @Test
    public void jvhe() {
        //creating configuration object
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file

        //creating seession factory object
        SessionFactory factory = cfg.buildSessionFactory();

        //creating session object
        Session session = factory.openSession();

        //creating transaction object
        Transaction t = session.beginTransaction();

        // String sql = "select gender  ,  sum(grade) as grade  from user group by gender;";
        // SQLQuery sq = session.createSQLQuery(sql);
        // // sq.addEntity(Grade.class);

        // List r = sq.list();
        // r.get(0).getGender();

        Criteria cr = session.createCriteria(User.class);
        // List r = cr.list();
        cr.setProjection(Projections.sum("grade"));
        cr.setProjection(Projections.groupProperty("gender"));
        List r = cr.list();
        System.out.println(r);
        // session.
        t.commit();
        session.close();
    }

    @Test
    public void addBig() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));
        //creating configuration object
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file

        //creating seession factory object
        SessionFactory factory = cfg.buildSessionFactory();

        //creating session object
        Session session = factory.openSession();

        //creating transaction object
        Transaction t = session.beginTransaction();
        for (int i = 0; i < 10000; i++) {
            System.out.println(i);
            User u = new User();
            u.setGender(2);
            u.setGrade(66);
            u.setName("naruto");
            u.setPhone("13838381438");
            u.setPwd(i + "");
            session.save(u);
        }
        t.commit();
        session.close();
        System.out.println(sdf.format(new Date()));

    }

    @Test
    public void rollBack() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));
        //creating configuration object
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file

        //creating seession factory object
        SessionFactory factory = cfg.buildSessionFactory();

        //creating session object
        Session session = factory.openSession();

        //creating transaction object
        Transaction t = session.beginTransaction();
        try {
            User u = new User();
            u.setGender(2);
            u.setGrade(66);
            u.setName("naruto");
            u.setPhone("13838381438");
            u.setPwd("qweqwe");
            session.save(u);
            // t.commit();

            String s = "e";
            int a = Integer.valueOf(s);
            User u2 = new User();
            u2.setGender(22);
            u2.setGrade(662);
            u2.setName("naruto2");
            u2.setPhone("138383814382");
            u2.setPwd("qweqwe2");
            session.save(u2);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // t.rollback();
        }

        session.close();
        System.out.println(sdf.format(new Date()));

    }


}
