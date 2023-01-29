package service;

import enumai.Countries;
import entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;

public class CompanyService {


    public Double getCompaniesAverageBudget() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query queryAverage = session.createQuery("SELECT avg(budget) from Company", Double.class);
        Double avereage = (Double) queryAverage.uniqueResult();
        System.out.println("Average of all companies budget is : " + String.format("%.0f", avereage));
        return avereage;
    }

    public Long countCompaniesInDb() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query queryAverage = session.createQuery("SELECT count(name) from Company", Long.class);
        Long companiesCounter = (Long) queryAverage.uniqueResult();
        System.out.println("Number of registered companies in DB: " + companiesCounter);
        return companiesCounter;
    }

    public Double calculateRoadTaxes(Countries country, Vehicle vehicle) {
        double cost = 0;
        double emLev = 0;
        if (vehicle.getEmissionLevel().equals("Euro6")) {
            emLev = 1.1;
        } else if (vehicle.getEmissionLevel().equals("Euro5")) {
            emLev = 5.2;
        } else {
            System.out.println("Not exist in calc");
        }
        if (Countries.Lithuania.equals(country)) {
            cost = 1500 * emLev;
        } else if (Countries.Germany.equals(country)) {
            cost = 2500 * emLev;
        } else {
            System.out.println("No countries");
        }
        System.out.println("*****************************************************************************");
        System.out.println("Road cost for vehicle " + vehicle.getRegNumber() + " in country: " + country + " is: " + cost);
        System.out.println("*****************************************************************************");
        return cost;
    }


}
