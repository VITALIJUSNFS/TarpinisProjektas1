package repository;

import entity.RepairingWorkshop;
import entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

public class RepairWorkshopRepository {

    public void save(RepairingWorkshop repairingWorkshop) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(repairingWorkshop);
        transaction.commit();
        session.close();
    }


    public RepairingWorkshop findByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from RepairingWorkshop where name =: name")
                .setParameter("name", name);
        RepairingWorkshop repairingWorkshop  = (RepairingWorkshop) query.uniqueResult();
        return repairingWorkshop;
    }
}
