package com.example.angularDemo.service;

import com.example.angularDemo.model.Customer;
import com.example.angularDemo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;


    /**
     * 顧客のミッションを
     */

    /**
     * 顧客情報を取得する
     *
     * @return
     */
    @Override
     public List<Customer> getAllCustomers() {
         return customerRepository.findAll();
     }

    /**
     * 顧客情報を作成する
     *
     * @param customer
     * @return
     */
    @Override
     public Customer createCustomer(Customer customer) {
        // コンテンツ情報を取得する

        // コンテンツと顧客情報でDBからデータを取得する

         return customerRepository.save(customer);
     }

    /**
     * 顧客情報を更新する
     *
     * @param existCustomer
     * @return
     */
    @Override
     public Customer updateCustomer(Customer existCustomer) {
         return customerRepository.save(existCustomer);
     }

    /**
     * 顧客情報を削除する
     * @param id
     */
    @Override
     public void deleteCustomer(Long id) {
         customerRepository.deleteById(id);
     }
}
