package repository;

import entity.TruckDriver;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

public class DriverRepository {

    public void save(TruckDriver truckDriver) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(truckDriver);
        transaction.commit();
        session.close();
    }
}
