package com.example.angularDemo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.angularDemo.model.Customer;
import com.example.angularDemo.repository.CustomerRepository;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import com.example.angularDemo.model.Memo;
import com.example.angularDemo.repository.MemoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {

    /** RequiredArgsConstructorで対応する場合は「final」 付与すること */
    private final MemoRepository memoRepository;
    private final CustomerRepository customerRepository;


    @Transactional
    public Memo updateMemoAndCustomerInfo(Customer cus, Memo memo) {

        //顧客情報とメモ情報の両方を更新する
        Customer customerData = customerRepository.findById(cus.getId()).orElse(null);
        if (customerData == null) {
            return null;
        }
        customerData.setName(cus.getName());
        customerData.setEmail(cus.getEmail());
        customerRepository.save(customerData);


        // 続いて メモ情報を更新する
        Memo memoData = memoRepository.findById(memo.getId()).orElse(null);
        if (memoData == null) {
            return null;
        }
        memoData.setContent(memo.getContent());
        memoData.setUpdatedAt(LocalDateTime.now());
        Memo updatedMemo = memoRepository.save(memoData);

        if (customerData != null && memoData != null) {
            System.out.println("updateMemoAndCustomerInfo: 両方ともNULLでない");
        }

        return updatedMemo;
    }


    /**
     * if文の切り替え
     * @param cus
     * @param memo
     */
    @ReadOnlyProperty
    public void checkData(Customer cus, Memo memo) {

        Customer customerData = customerRepository.findById(cus.getId()).orElse(null);
        Memo memoData = memoRepository.findById(memo.getId()).orElse(null);

        if (customerData != null && memoData != null) {
            System.out.println("checkData: 両方ともNULLでない");
        }

    }


    /**
     * if文の切り替え
     * @param cus
     * @param memo
     */
    @ReadOnlyProperty
    public void checkDataFix(Customer cus, Memo memo) {

        Customer customerData = customerRepository.findById(cus.getId()).orElse(null);

        // 以下の1行がある場合は、各メソッドチェーンがnullにならないようにｓるため、テストメソッドに書くプログラム量が多くなる
        customerData.getMemos().get(0).getDetail().getDescription();
    }

    /**
     * LocaDateTimeのgetNow() メソッドのテストを実施することが目的のメソッド
     */
    @ReadOnlyProperty
    public LocalDateTime checkNowMethod() {

        LocalDateTime now = LocalDateTime.now();
        System.out.println("現在の日時: " + now);
        return now;
    }






    @Override
    public List<Memo> getAllMemos() {
        return memoRepository.findAll();
    }


    /**
     * 検索処理
     * 指定したidのレコードが存在しない場合はnullを返却する形式にしておく
     * 
     * @param id
     * @return
     */
    @Override
    public Memo getMemoById(Long id) {
        return memoRepository.findById(id).orElse(null);
    }

    /**
     * 作成処理
     * @param memo
     * @return
     */
    @Override
    @Transactional
    public Memo createMemo(Memo memo) {
        
        memo.setCreatedAt(LocalDateTime.now());
        try {

        return memoRepository.save(memo);
        } catch (StaleObjectStateException e) {
            // 例外処理：ログ出力、ユーザーへのエラーメッセージ表示、再試行など
            System.out.println("例外処理中");
            throw new RuntimeException("メモの更新に失敗しました。別のユーザーが既に更新している可能性があります。", e); // 例外を再スロー
        }
    }

    /**
     * 更新処理
     * @param id
     * @param newMemo
     * @return
     */
    public Memo updateMemo(Long id, Memo newMemo) {
        Memo existingMemo = memoRepository.findById(id).orElse(null);
        if (existingMemo != null) {
            existingMemo.setContent(newMemo.getContent());
            existingMemo.setUpdatedAt(LocalDateTime.now());
            return memoRepository.save(existingMemo);
        } else {
            return null;
        }
    }

    /**
     * 削除処理
     * @param id
     */
    public void deleteMemo(Long id) {
        memoRepository.deleteById(id);
    }


}
