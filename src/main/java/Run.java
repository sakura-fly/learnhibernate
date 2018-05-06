import entity.Grade;
import entity.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.junit.Test;

import java.util.List;

public class Run {

    @Test
    public void add(){
        //creating configuration object
        Configuration cfg=new Configuration();
        cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file

        //creating seession factory object
        SessionFactory factory=cfg.buildSessionFactory();

        //creating session object
        Session session=factory.openSession();

        //creating transaction object
        Transaction t=session.beginTransaction();
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
    public void list(){
        //creating configuration object
        Configuration cfg=new Configuration();
        cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file

        //creating seession factory object
        SessionFactory factory=cfg.buildSessionFactory();

        //creating session object
        Session session=factory.openSession();

        //creating transaction object
        Transaction t=session.beginTransaction();
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
    public void group(){
        //creating configuration object
        Configuration cfg=new Configuration();
        cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file

        //creating seession factory object
        SessionFactory factory=cfg.buildSessionFactory();

        //creating session object
        Session session=factory.openSession();

        //creating transaction object
        Transaction t=session.beginTransaction();

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
    public void jvhe(){
        //creating configuration object
        Configuration cfg=new Configuration();
        cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file

        //creating seession factory object
        SessionFactory factory=cfg.buildSessionFactory();

        //creating session object
        Session session=factory.openSession();

        //creating transaction object
        Transaction t=session.beginTransaction();

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

}
