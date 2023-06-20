package sg.edu.nus.iss.paf_day21.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf_day21.model.Customer;

@Repository
public class CustomerRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // 1st function
    private final String findallSql = "select id, first_name, last_name from customer";
    // 2nd function
    private final String findByIdSql = "select * from customer where id = ?";
    // 3rd fuction --> you can do it yourself (Slide 24)
    private final String findAllLimitOffsetSql= "select * from customer limit ? offset ?";

    public List<Customer> getAllCustomers(){
        List <Customer> customerList = new ArrayList<Customer>();

        SqlRowSet rs = jdbcTemplate.queryForRowSet(findallSql);

        while (rs.next()){
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getNString("last_name"));

            customerList.add(customer);
        }

        // ensure list cannot be changed, unmodifiable = read only list.
        return Collections.unmodifiableList(customerList);
    }

    public List<Customer> getAllCustomersWithLimitOff (int limit, int offset){
        List<Customer> customerList = new ArrayList<Customer>();

        SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllLimitOffsetSql, limit, offset);

        while (rs.next()){
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getNString("last_name"));

            customerList.add(customer);
        }

        return Collections.unmodifiableList(customerList);
    }

    public Customer getCustomerById(int id){

        Customer customer = new Customer();
        jdbcTemplate.queryForObject(findByIdSql, BeanPropertyRowMapper.newInstance(Customer.class), id);

        return customer;

    }

   
   
}
