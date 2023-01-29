import entity.*;
import enumai.Countries;
import exceptions.RegNumberException;
import repository.CompanyRepository;
import repository.DriverRepository;
import repository.RepairWorkshopRepository;
import repository.VehiclesRepository;
import service.CompanyService;
import service.VehicleService;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws RegNumberException {

        Company nikVit = new Company();
        nikVit.setName("UAB NIK&VIT");
        nikVit.setCountry("Lithuania");
        nikVit.setBudget(500000);

        TruckDriver antanasAntanaitis = new TruckDriver();
        antanasAntanaitis.setFistName("Antanas");
        antanasAntanaitis.setLastName("Antanaitis");
        antanasAntanaitis.setSalary(2300.00);

        TruckDriver jonasJonaitis = new TruckDriver();
        jonasJonaitis.setFistName("Jonas");
        jonasJonaitis.setLastName("Jonaitis");
        jonasJonaitis.setSalary(3200.00);

        RepairingWorkshop volvoServisas = new RepairingWorkshop();
        volvoServisas.setName("UAB VOLVO");
        volvoServisas.setAddress("Minsko plentas 9, Vilnius");

        RepairingWorkshop centrakoServisas = new RepairingWorkshop();
        centrakoServisas.setName("Centrako");
        centrakoServisas.setAddress("Taikos 135, Kaunas");

        Vehicle abc001 = new Vehicle();
        abc001.setBrand("DAF");
        abc001.setCompany(nikVit);
        abc001.setName("fridge");
        abc001.setRegNumber("ABC_001");
        abc001.setTruckDrivers(Arrays.asList(antanasAntanaitis, jonasJonaitis));
        abc001.setRepairingWorkshop(centrakoServisas);
        centrakoServisas.setVehicle(abc001);

        //SAUGOJIMAS
        CompanyRepository companyRepository = new CompanyRepository();
        companyRepository.save(nikVit);

        DriverRepository driverRepository = new DriverRepository();
        driverRepository.save(antanasAntanaitis);
        driverRepository.save(jonasJonaitis);

        VehiclesRepository vehiclesRepository = new VehiclesRepository();
        vehiclesRepository.save(abc001);

        RepairWorkshopRepository repairWorkshopRepository = new RepairWorkshopRepository();
        repairWorkshopRepository.save(volvoServisas);
        repairWorkshopRepository.save(centrakoServisas);

        //
        Company byName = companyRepository.findByName("UAB NIK&VIT");//find company by id
        System.out.println("Company found" + byName.getName() + " " + byName.getCountry());

        //CRUD COMPANY
        //C -new company creation
        companyRepository.addNewCompany("DAS", "Germany", 1000000);
        companyRepository.addNewCompany("LAS", "France", 500500);
        companyRepository.addNewCompany("XXX", "France", 750500);
        //R - read
        System.out.println("******All registered companies");
        //companyRepository.findAll();
        companyRepository.findAll().forEach(System.out::println);
        //U - company data change
        companyRepository.update("GAS", "Estonia", 3L);
        //D - delete company
        companyRepository.delete("XXX");
        //F - Find company
        companyRepository.findByName("DAS");

        //CRUD VEHICLE
        vehiclesRepository.createNewVehicle("abc002", "Mercedes", "fridge", "Euro6",companyRepository.findByName("DAS"));
        vehiclesRepository.createNewVehicle("abc003", "Scania", "fridge", "Euro5", null);
        //vehiclesRepository.createNewVehicle("abc003", "Scania", "fridge", "Euro5", null);

        vehiclesRepository.asignVehileToCompany(vehiclesRepository.findByRegNumber("abc003"), companyRepository.findByName("DAS"));
        vehiclesRepository.asignVehicleToWorkshop(vehiclesRepository.findByRegNumber("abc003"), repairWorkshopRepository.findByName("UAB VOLVO"));


        // SERVICE COMPANY
        CompanyService companyService = new CompanyService();
        companyService.getCompaniesAverageBudget();
        companyService.countCompaniesInDb();

        companyService.calculateRoadTaxes(Countries.Lithuania, vehiclesRepository.findByRegNumber("abc003"));
        companyService.calculateRoadTaxes(Countries.Germany, vehiclesRepository.findByRegNumber("abc003"));

        // SERVICE VEHICLES
        VehicleService vehicleService = new VehicleService();
        vehicleService.countVehiclesInDb();

        List<Vehicle> vehiclesSorted = vehicleService.sortByDesc();
        System.out.println(" ***** All Vehicles ");
        for (Vehicle vehicle : vehiclesSorted) {
            System.out.println(vehicle);
        }


    }

}
