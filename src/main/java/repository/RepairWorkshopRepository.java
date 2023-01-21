package repository;

import entity.RepairingWorkshop;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

public class RepairWorkshopRepository {

    public void save(RepairingWorkshop repairingWorkshop) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(repairingWorkshop);
        transaction.commit();
        session.close();
    }
}
