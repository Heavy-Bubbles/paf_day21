package sg.edu.nus.iss.paf_day21.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.paf_day21.model.Customer;
import sg.edu.nus.iss.paf_day21.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    
    @Autowired
    CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/limit")
    public List<Customer> getAllCustomersWithLimitAndOffset(@RequestParam("limit") int limit, @RequestParam("offset") int offset){
        return customerService.getAllCustomersWithLimitOff(limit, offset);
    }

    @GetMapping ("/{custId}")
    public Customer getCustomer(@PathVariable("custId") int custId){
        return customerService.getCustomerById(custId);
    }
}
