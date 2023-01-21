import entity.Company;
import entity.RepairingWorkshop;
import entity.TruckDriver;
import entity.Vehicle;
import org.hibernate.Session;
import repository.CompanyRepository;
import repository.DriverRepository;
import repository.RepairWorkshopRepository;
import repository.VehiclesRepository;
import service.DataProvider;
import utils.HibernateUtil;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Company nikVit = new Company();
        nikVit.setName("UAB NIK&VIT");
        nikVit.setCountry("Lithuania");

        TruckDriver antanasAntanaitis = new TruckDriver();
        antanasAntanaitis.setFistName("Antanas");
        antanasAntanaitis.setLastName("Antanaitis");
        antanasAntanaitis.setSalary(2300.00);

        TruckDriver jonasJonaitis = new TruckDriver();
        jonasJonaitis.setFistName("Jonas");
        jonasJonaitis.setLastName("Jonaitis");
        jonasJonaitis.setSalary(3200.00);

        RepairingWorkshop volvo = new RepairingWorkshop();
        volvo.setName("UAB VOLVO");
        volvo.setAddress("Minsko plentas 9, Vilnius");

        RepairingWorkshop daf = new RepairingWorkshop();
        daf.setName("Centrako");
        daf.setAddress("Taikos 135, Kaunas");

        Vehicle abc001 = new Vehicle();
        abc001.setBrand("DAF");
        abc001.setCompany(nikVit);
        abc001.setName("fridge");
        abc001.setRegNumber("ABC_001");
        abc001.setTruckDrivers(Arrays.asList(antanasAntanaitis,jonasJonaitis));
        abc001.setRepairingWorkshop(daf);

        CompanyRepository companyRepository = new CompanyRepository();
        companyRepository.save(nikVit);

        DriverRepository driverRepository = new DriverRepository();
        driverRepository.save(antanasAntanaitis);
        driverRepository.save(jonasJonaitis);

        RepairWorkshopRepository repairWorkshopRepository = new RepairWorkshopRepository();
        repairWorkshopRepository.save(volvo);
        repairWorkshopRepository.save(daf);

        VehiclesRepository vehiclesRepository = new VehiclesRepository();
        vehiclesRepository.save(abc001);


        Company byName = companyRepository.findByName("UAB NIK&VIT");//find company by id
        System.out.println("Company found" + byName.getName() +" "+ byName.getCountry());

        //new company
        companyRepository.addNewCompany("DAS","Germany");
        companyRepository.addNewCompany("LAS","France");
        //company data change
        companyRepository.update("GAS", "Estonia", 3L);


        session.getTransaction().commit();



//
//        //find by id
//        Long a = 1L;
//        Company company = session.find(Company.class, a);
//        System.out.println(company.getCountry());
//        System.out.println(company.getName());

        //delete company by id
//        session.beginTransaction();
//        session.delete(company);
//        session.getTransaction().commit();





    }

}
