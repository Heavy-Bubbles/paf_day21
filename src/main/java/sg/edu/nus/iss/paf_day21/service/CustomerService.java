package sg.edu.nus.iss.paf_day21.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.paf_day21.model.Customer;
import sg.edu.nus.iss.paf_day21.repository.CustomerRepo;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    public List<Customer> getAllCustomers(){
        return customerRepo.getAllCustomers();
    }

    public List<Customer> getAllCustomersWithLimitOff(int limit, int offset){
        return customerRepo.getAllCustomersWithLimitOff(limit, offset);
    }

    public Customer getCustomerById (int id){
        return customerRepo.getCustomerById(id);
    }
    
}
