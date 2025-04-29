package com.example.angularDemo.service;

import com.example.angularDemo.model.Customer;

import java.util.List;

/**
 * 顧客サービスのインターフェース
 */
public interface CustomerService {

    /**
     * 顧客情報を取得する
     * @return 顧客情報のリスト
     */
    List<Customer> getAllCustomers();

    /**
     * 顧客情報を作成する
     * @param customer 顧客情報
     * @return 作成された顧客情報
     */
    Customer createCustomer(Customer customer);

    /**
     * 顧客情報を更新する
     * @param targetCustomer 既存顧客情報
     * @return 更新された顧客情報
     */
    Customer updateCustomer(Customer existCustomer);

    /**
     * 顧客情報を削除する
     * @param id 顧客ID
     */
    void deleteCustomer(Long id);
}
