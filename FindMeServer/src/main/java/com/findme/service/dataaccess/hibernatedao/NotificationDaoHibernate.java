package com.findme.service.dataaccess.hibernatedao;

import com.findme.service.dataaccess.NotificationDao;
import com.findme.service.model.Notification;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

@Component
public class NotificationDaoHibernate implements NotificationDao {

    private SessionFactory sessionFactory;

    public NotificationDaoHibernate() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void addNotification(Notification notification) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(notification);

        transaction.commit();
        session.close();
    }
}
