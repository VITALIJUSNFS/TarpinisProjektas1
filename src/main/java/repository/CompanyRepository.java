package repository;

import entity.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;

public class CompanyRepository {

    public List<Company> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Company> allCompanies = session.createQuery("from Company ", Company.class).list();
//        for (Company allCompany : allCompanies) {
//            System.out.println("\t" + allCompany.getId() + "\t" + allCompany.getName() + "\t" + allCompany.getCountry());
//        }
        session.close();
        return allCompanies;
    }

    public void save(Company company) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(company);
        transaction.commit();
        session.close();
    }

    public void addNewCompany(String name, String country, Integer budget) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Company company = new Company();
        company.setName(name);
        company.setCountry(country);
        company.setBudget(budget);
        session.save(company);

        transaction.commit();
        session.close();
        System.out.println("new company created");
    }


    public Company findByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Company where name= :name");
        query.setParameter("name", name);
        Company company = (Company) query.uniqueResult();
        return company;
    }

    public void update(String name, String country, Long id) {
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

    public void delete(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE from Company where name= :name")
                .setParameter("name", name)
                .executeUpdate();
        System.out.println("Company with name " + name + " deleted");
        transaction.commit();
        session.close();
    }

}
