import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

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
        u.setGender(1);
        u.setGrade(66);
        u.setName("sakura");
        u.setPhone("18812345678");
        u.setPwd("666");
        session.save(u);
        t.commit();
        session.close();
    }
}
