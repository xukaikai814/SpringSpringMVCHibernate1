import com.ssh.entity.Dept;
import com.ssh.entity.Emp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/27.
 */
public class AppTest {
    @Test
    public void fun1() {
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
        SessionFactory sessionFactory = (SessionFactory) act.getBean("sqlSessionFactory");

//        sessionFactory.openSession();
//        Session session=sessionFactory.getCurrentSession();

        Session session1 = sessionFactory.openSession();
        Session session2 = sessionFactory.openSession();
        System.out.println(session1 == session2);
    }


    @Test
    public void fun2() {
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
        SessionFactory sessionFactory = (SessionFactory) act.getBean("sqlSessionFactory");

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();

            //从主动方开始查，只查一次
            Dept dept = new Dept(1, "1111111", "1111111");
            session.persist(dept);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }
    @Test
public void fun3(){
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
        SessionFactory sessionFactory = (SessionFactory) act.getBean("sqlSessionFactory");

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();

            Serializable save = session.save(new Dept(1, "张三", "北京"));

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }
}
