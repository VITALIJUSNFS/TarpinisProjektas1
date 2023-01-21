package repository;

import entity.TruckDriver;
import entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

public class VehiclesRepository {

    public void save(Vehicle vehicle) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(vehicle);
        transaction.commit();
        session.close();
    }
}
