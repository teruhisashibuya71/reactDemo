package com.example.angularDemo.service;

import java.util.List;

import com.example.angularDemo.model.Customer;
import com.example.angularDemo.model.Memo;

public interface MemoService {

    public Memo updateMemoAndCustomerInfo(Customer customer, Memo memo);

    public List<Memo> getAllMemos();

    public Memo getMemoById(Long id);

    public Memo createMemo(Memo memo);

    public Memo updateMemo(Long id, Memo newMemo);

    public void deleteMemo(Long id);

}
