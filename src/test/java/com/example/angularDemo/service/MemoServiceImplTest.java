package com.example.angularDemo.service;

import com.example.angularDemo.model.Customer;
import com.example.angularDemo.model.Memo;
import com.example.angularDemo.repository.CustomerRepository;
import com.example.angularDemo.repository.MemoRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemoServiceImplTest {

    /**
     * テスト対象のクラスに関係ないものをモックするために使用する
     * テストする際に依存するクラスやインターフェースのオブジェクトを生成するために使用する
     */
    @Mock
    CustomerRepository customerRepository;

    @Mock
    MemoRepository memoRepository;

    /**
     * @InjectMocksアノテーションは、テストしたい対象のクラスのインスタンスに対して使用する
     * serviceクラスのテストを行う場合、repositoryインターフェースに対しては、@Mockの方のアノテーションを使用する
     * 理由:テストする対象のserviceクラスには直接関係がないため
     */
    @InjectMocks
    MemoServiceImpl memoService;


    @Nested
    @DisplayName("updateMemoAndCustomerInfoメソッド関連のテスト")
    class testUpdateMemoAndCustomerInfo{

        /**
         * 正常系 : 処理が最後まで到達するテスト
         * true × true のケース
         */
        @Test
        @DisplayName("正常系1 : 処理が最後まで到達するテスト")
        public void updateMemoAndCustomerInfo() {
            // ①.引数に使用するクラスのインスタンスをモックで用意する
            Customer mockedCustomer = mock(Customer.class);
            Memo mockedMemo = mock(Memo.class);
            Memo updatedMemo = new Memo();
            updatedMemo.setId(10L);
            updatedMemo.setContent("更新完了メモ");

            // TODO ②.メソッド内部で利用されるDBに対するアクション結果をモック化する
            // この正常系のテストにおいては、serviceクラスのメソッドを最後まで処理するため、顧客・メモ 両方とも何らかのインスタンスを返却するものとする
            //when(mockedCustomer.getId()).thenReturn(anyLong());
            // `mockedCustomer.getId()`は実数扱い モックで用意したインスタンスでも、getterでフィールドにアクセスした場合は実数扱いとなる
            //when(mockedCustomer.getId()).thenReturn(123L);
            //when(mockedMemo.getId()).thenReturn(anyLong());
            //when(mockedMemo.getId()).thenReturn(234L);

            // 検索処理に関しては、 `顧客・メモ` インスタンスを返却するものとする
            //Mockito.when(customerRepository.findById(mockedCustomer.getId())).thenReturn(Optional.of(mockedCustomer));
            Mockito.when(customerRepository.findById(anyLong())).thenReturn(Optional.of(mockedCustomer));
            //Mockito.when(memoRepository.findById(mockedMemo.getId())).thenReturn(Optional.of(mockedMemo));
            Mockito.when(memoRepository.findById(anyLong())).thenReturn(Optional.of(mockedMemo));
            //Mockito.when(memoRepository.findById(anyLong())).thenReturn(Optional.of(updatedMemo));

            // TODO③.保存処理(save)に関しては、 `メモ` 側のみインスタンスを返却するものとする
            // 更新後の値を指定する場合は実態のあるインスタンスを生成して値を設定することも可能
            Mockito.when(customerRepository.save(mockedCustomer)).thenReturn(null);
            //Mockito.when(memoRepository.save(mockedMemo)).thenReturn(mockedMemo);
            Mockito.when(memoRepository.save(mockedMemo)).thenReturn(updatedMemo);

            // TODO ④.モックで返却して値も確認する場合は when で 各フィールドの値を設定すること
//        Mockito.when(mockedMemo.getId()).thenReturn(10L);
//        Mockito.when(mockedMemo.getContent()).thenReturn("更新完了メモ");

            // `updatedMemo` は実態のあるインスタンスであり、モックでない。 モックでないものにWhenなどのメソッドは使用できないので以下の処理はエラー
            // -----------------------------------------------------------------
//        Mockito.when(updatedMemo.getId()).thenReturn(10L);
//        Mockito.when(updatedMemo.getContent()).thenReturn("更新完了メモ");
            // -----------------------------------------------------------------

//        updatedMemo.setId(10L);
//        updatedMemo.setContent("更新完了メモ");


            // テスト実行
            Memo result = memoService.updateMemoAndCustomerInfo(mockedCustomer, mockedMemo);

            Assertions.assertEquals(10L, result.getId());
            Assertions.assertEquals("更新完了メモ", result.getContent());

        }

    }


    /**
     * ネストする親クラスの名前は `test` + `テスト対象のメソッド名` とする
     */
    @Nested
    @DisplayName("checkDataメソッドのテスト")
    class testCheckData {

        /**
         * if分のカバレッジを埋めるための練習用テストプログラム④
         * true × true のケース 4/4
         * ログにテキストが出力されるケース
         */
        @Test
        @Order(1)
        @DisplayName("正常系1: customerData != null が true / memoData != null が true")
        public void test_checkDataTrueTrue() {
            // Mock the repositories
            Customer actualCustomer = new Customer();
            actualCustomer.setId(30L);
            actualCustomer.setName("Test Customer");
            when(customerRepository.findById(anyLong())).thenReturn(Optional.of(actualCustomer)); // customerData is not null


            Memo actualMemo = new Memo();
            actualMemo.setId(40L);
            actualMemo.setContent("Test Memo");
            when(memoRepository.findById(anyLong())).thenReturn(Optional.of(actualMemo)); // memoData is not null

            // Prepare method arguments
            Customer mockCusData = mock(Customer.class);
            Memo mockMemoData = mock(Memo.class);

            // Execute the method
            memoService.checkData(mockCusData, mockMemoData);
        }


        /**
         * if分のカバレッジを埋めるための練習用テストプログラム①
         * false × false のケース 1/4
         * customer と memo 両方とも `null`
         */
        @Test
        @Order(2)
        @DisplayName("正常系2: customer / memo 両方null")
        void test_checkData() {

            // findByなどでOptional系の戻り値をモックする場合 → null返却ではなく、empty()を使用すること
            // nullを返却しようとすると、nullに対してorElse()メソッドが適用され、NullPointer的な感じになる
            when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());
            when(memoRepository.findById(anyLong())).thenReturn(Optional.empty());

            // メソッド実行用の引数を用意
            Customer customerData = mock(Customer.class);
            Memo memoData = mock(Memo.class);

            // 両方nullのケースなので false × false
            memoService.checkData(customerData, memoData);
        }


        /**
         * if分のカバレッジを埋めるための練習用テストプログラム②
         * true × false のケース 2/4
         * customerがnullでない / memoがnull
         */
        @Test
        @Order(3)
        @DisplayName("正常系3: customerData != null が true / memoData != null が false")
        public void test_checkData_true_false() {

            // メソッド実行用の引数を用意
            Customer mockCusData = mock(Customer.class);

            Memo mockMemoData = mock(Memo.class);
            //when(mockMemoData.getId()).thenReturn(1L);

            // memo検索時に返却されるインスタンスを生成する
            Customer actuelCustomer = new Customer();
            actuelCustomer.setId(10L);
            actuelCustomer.setName("シブヤ");
            // ...
            //Memo actualMemo = new Memo();


            // findByIdの引数を
            when(customerRepository.findById(anyLong())).thenReturn(Optional.of(actuelCustomer));
            // TODO findById()の引数を実数値にする場合は、 以下のようにモックデータ定義としてwhenで挙動を定義しておく必要がある
            // when(mockCusData.getId()).thenReturn(1L);
            // when(customerRepository.findById(1L)).thenReturn(Optional.of(actuelCustomer));
            // ただ、記述するのが面倒なので、基本的に上のようにanyLong()などで問題ないと思う


            when(memoRepository.findById(anyLong())).thenReturn(Optional.empty());


            // 両方nullのケースなので if文の分岐条件は true × falses
            memoService.checkData(mockCusData, mockMemoData);
        }


        /**
         * if分のカバレッジを埋めるための練習用テストプログラム③
         * false × true のケース 3/4
         */
        @Test
        @Order(4)
        @DisplayName("正常系4: customerData != null が false / memoData != null が true")
        public void test_checkData_false_true() {
            // Mock the repositories
            when(customerRepository.findById(anyLong())).thenReturn(Optional.empty()); // customerData is null

            Memo actualMemo = new Memo();
            actualMemo.setId(20L);
            actualMemo.setContent("Test Memo");
            when(memoRepository.findById(anyLong())).thenReturn(Optional.of(actualMemo)); // memoData is not null

            // Prepare method arguments
            Customer mockCusData = mock(Customer.class);
            Memo mockMemoData = mock(Memo.class);

            // Execute the method
            memoService.checkData(mockCusData, mockMemoData);
        }
    }







}