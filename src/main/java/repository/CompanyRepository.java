package repository;

import entity.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

public class CompanyRepository {

    public void save(Company company){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(company);
        transaction.commit();
        session.close();
    }

    public void addNewCompany(String name,String country) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Company company = new Company();
        company.setName(name);
        company.setCountry(country);
        session.save(company);

        transaction.commit();
        session.close();
        System.out.println("new company created");
    }

    public Company findByName(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Company where name= :name");
        query.setParameter("name", name);
        Company company = (Company) query.uniqueResult();
        return company;
    }
    public void update(String name, String country,Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("UPDATE Company SET name = :name, country = :country where id= :id")
                .setParameter("name", name)
                .setParameter("country", country)
                .setParameter("id", id)
                .executeUpdate();
        transaction.commit();
        session.close();
        System.out.println("Company data modified");
    }



}
