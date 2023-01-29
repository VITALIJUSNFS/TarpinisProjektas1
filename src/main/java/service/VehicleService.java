package service;

import entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;

public class VehicleService {

    public Long countVehiclesInDb() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query queryAverage = session.createQuery("SELECT count(regNumber) from Vehicle", Long.class);
        Long vehiclesCounter = (Long) queryAverage.uniqueResult();
        System.out.println("Number of registered vehicles in DB: " + vehiclesCounter);
        System.out.println();
        return vehiclesCounter;
    }

    public List<Vehicle> sortByDesc(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Vehicle ORDER BY regNumber ASC", Vehicle.class);
        List<Vehicle> vehicles = query.list();
        return vehicles;
    }

}
