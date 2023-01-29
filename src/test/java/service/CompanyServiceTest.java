package service;

import entity.Company;
import entity.Vehicle;
import junit.framework.TestCase;
import org.hibernate.Session;
import org.junit.Test;
import utils.HibernateUtil;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class CompanyServiceTest {

    @Test
    public void testGetCompaniesAverageBudget() {

//        CompanyService companyService = new CompanyService();
//
//        // Create test data
//        Company company1 = new Company(101L, "Google", "ES", 1000000, null);
//        Company company2 = new Company(102L, "Facebook", "ES", 2000000, null);
//        Company company3 = new Company(103L, "Amazon", "ES", 3000000, null);
//        List<Company> companies = Arrays.asList(company1, company2, company3);
//
//        // Insert test data into the database
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        session.save(company1);
//        session.save(company2);
//        session.save(company3);
//        session.getTransaction().commit();
//
//        // Test the method
//        Double expectedAverage = Double.valueOf((company1.getBudget() + company2.getBudget() + company3.getBudget()) / 3);
//        Double actualAverage = companyService.getCompaniesAverageBudget();
//
//        assertEquals(expectedAverage, actualAverage);
//
//        // Clean up test data
//        session.beginTransaction();
//        session.delete(company1);
//        session.delete(company2);
//        session.delete(company3);
//        session.getTransaction().commit();
    }
}