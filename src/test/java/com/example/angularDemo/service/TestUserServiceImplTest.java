package com.example.angularDemo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.angularDemo.controller.form.UserForm;
import com.example.angularDemo.exception.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.angularDemo.model.TestUser;

import com.example.angularDemo.repository.TestUserRepository;

/**
 * JunitとMockito(モックイット×モキート) を利用したテスト用のクラスです
 *
 * TestUserモデルに対する簡単なCRUDテストを実装しています.
 * 
 * テスト実行コマンド
 * ./gradlew test --tests TestUserServiceImplTest
 * 成功の場合
 * 4 actionable tasks: 4 executed....
 * エラーの場合
 * Testfailed .... 表示されるのでわかりやすい
 *
 */
@ExtendWith(MockitoExtension.class)
public class TestUserServiceImplTest {

    // 本物のDBにアクセスしないようにMockアノテーションを使用する
    @Mock
    private TestUserRepository userRepository;

    @InjectMocks
    private TestUserServiceImpl userServiceImpl;



    /**
     * 対象のユーザーデータを作成するテスト
     */
    @Test
    void createTargetUser() {

        // 登録するユーザー情報を作成する
        TestUser inputTestUser = new TestUser();
        inputTestUser.setId(1L);
        inputTestUser.setName("testUser1");
        inputTestUser.setAge(38);

        // 得られる期待値のユーザー情報も作っておく
        TestUser expectedTestUser = new TestUser();
        expectedTestUser.setName("testUser1");
        expectedTestUser.setAge(38);

        // TODO 前提として、モックの処理と実際のメソッド処理の結果を比較するのが最終的な目的
        // when→ when以下の引数の処理が成功した時
        // thenReturn→ whenが成功した時に得られる期待値を引数に入れて使う
        when(userRepository.save(inputTestUser)).thenReturn(expectedTestUser);

        // 実際のcreate処理は型がformクラスなので、インスタンス生成する
        UserForm inputUserForm = new UserForm();
        inputUserForm.setId(1L);
        inputUserForm.setName("testUser1");
        inputUserForm.setAge(38);

        // テスト対象の実際のメソッドを実行する
        TestUser actualTestUser = userServiceImpl.createUser(inputUserForm);

        // テスト結果を検証する
        assertEquals(expectedTestUser, actualTestUser);
    }


    /**
     * 簡単なCRUDテスト その1.Read
     * すべてのテストユーザーデータを取得する
     */
    @Test
    void testGetAllUsers() {

        // テスト用のTestUserオブジェクトをインスタンス化して用意する
        List<TestUser> userList = new ArrayList<>();
        TestUser firstUser = new TestUser();
        firstUser.setId(1L);
        firstUser.setName("firstUserです");
        userList.add(firstUser);
        TestUser secondUser = new TestUser();
        secondUser.setId(2L);
        secondUser.setName("secondUserです");
        userList.add(secondUser);

        // TODO Mockitoライブラリを使用してデータベースの振る舞いをモックする
        // whenメソッドでfindAllメソッドが呼ばれたら、userListを返すように設定
        // Mockito.when(userRepository.findAll()).thenReturn(userList);
        when(userRepository.findAll()).thenReturn(userList);

        // 実際のサービスクラスのメソッドを実行する
        List<TestUser> usetList = userServiceImpl.getAllUsers();

        // モックの動作と、実際のメソッドの結果を比較する
        // リストの件数を確認する
        assertNotNull(usetList);
        assertEquals(2, usetList.size());
        // 各フィールド値の検証
        assertEquals("testUser1", usetList.get(0).getName());
        assertEquals("testUser2", usetList.get(1).getName());
    }


    /**
     * 対象のユーザーデータを更新するテスト
     */
    @Test
    void updateTargetUser() {

        // テストデータ
        Long userId = 1L;
        TestUser existingUser = new TestUser();
        existingUser.setId(userId);
        existingUser.setName("Old Name");

        TestUser updatedUser = new TestUser();
        updatedUser.setName("New Name");

        // モックで上書きした時に戻ってくるデータを別途用意
        TestUser mockUpdatedUser = new TestUser();
        mockUpdatedUser.setId(userId);
        mockUpdatedUser.setName("New Name");

        // モックの設定
        // 既存のユーザー情報を取得
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        // 既存のユーザーデータをsaveすると、更新されたユーザー情報を返すとする(擬似的な上書き)
        when(userRepository.save(existingUser)).thenReturn(mockUpdatedUser);

        // 実際の更新メソッドを実行
        //TestUser actualUser = userServiceImpl.updateUser(userId, updatedUser);

        // 実物のソッドの戻り値と、モック用に用意したテストデータの値を比較する
        //assertEquals(mockUpdatedUser, actualUser);
    }

    /**
     * 対象のユーザーデータを削除するテスト (正常系)
     */
    @Test
    @DisplayName("削除処理のテスト (正常系)")
    void deleteTargetUser() {

        //削除用のデータ作成する
        TestUser testData = new TestUser();
        testData.setId(1L);
        testData.setName("シブヤ");
        testData.setAge(38);
        testData.setDeleted(false);

        // 定義したデータを取得する処理 JPAのfindByIdメソッドはOptionalを返すので、Optionalでラップする
        when(userRepository.findById(1L)).thenReturn(Optional.of(testData));
        // TestUserクラスのオブジェクトを引数にしてsaveメソッドが走った場合、saveしたデータ自体を返すように設定
        // TODO 一言でいうと `saveメソッド度に戻り値を設定するための記述`
        when(userRepository.save(any(TestUser.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // 削除処理をテストする
        userServiceImpl.deleteUser(1L);

        //削除処理フラグがtrueになっているか確認する 呼ばれた回数が一致するかも確認する
        assertEquals(true, testData.isDeleted());

        // 以下の処理はsaveメソッドが1回だけ呼ばれたことを確認している。
        // 以下の内容は記述しなくても良さそう。 もし記述するなら、すべてのメソッドに記述する必要が出てくる
        //verify(userRepository, times(1)).save(testData);
    }

    /**
     * 対象のユーザーデータを削除するテスト (異常系)
     */
    @Test
    @DisplayName("削除処理のテスト (異常系)")
    void deleteErrorTargetUser() {
        // TODO 自作のread処理はnullを返すので、thenReturnはnullをセットすれば良い
        //when(userRepository.findById(1L)).thenReturn(null);


        String expectedMessage = "User not found";

        // 取得できないモック設定を定義する
        // TODO 通常のJPAのメソッドでデータを取得する場合はnullセーフなので、データが無い場合はemptyを返す
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        // 削除処理を実行し、例外が発生することを確認する
        NotFoundException notFoundEx = assertThrows(NotFoundException.class, () -> {
            userServiceImpl.deleteUser(1L);
        });

        assertEquals(notFoundEx.getMessage(), expectedMessage);
    }
}
