package com.example.angularDemo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.angularDemo.model.TestUser;

import com.example.angularDemo.repository.TestUserRepository;

/**
 * テストユーザー 
 * テスト用クラス
 * 
 * テスト実行コマンド
 * ./gradlew test --tests TestUserServiceImplTest
 * 
 * 成功の場合
 * 4 actionable tasks: 4 executed....
 * 
 * エラーの場合
 * Testfailed .... となるのでわかりやすい
 * 
 */
@ExtendWith(MockitoExtension.class)
public class TestUserServiceImplTest {

    // 本物のDBにアクセスしないようにMockアノテーションを使用する
    @Mock
    private TestUserRepository userRepository;

    @InjectMocks
    private TestUserServiceImpl userServiceImpl;

    @Test
    void testGetAllUsers() {

        // テスト用のUserオブジェクトをインスタンス化して用意する
        List<TestUser> userList = new ArrayList<>();
        TestUser user1 = new TestUser();
        user1.setId(1L);
        user1.setName("testUser1");
        userList.add(user1);
        TestUser user2 = new TestUser();
        user2.setId(2L);
        user2.setName("testUser2");
        userList.add(user2);

        // レポジトリのMockを設定
        when(userRepository.findAll()).thenReturn(userList);

        // サービスクラスのメソッドを実行する
        List<TestUser> usetList = userServiceImpl.getAllUsers();

        // usetListを検証する

        // リストの件数を確認する
        assertNotNull(usetList);
        assertEquals(2, usetList.size());
        // 各フィールド値の検証
        assertEquals("testUser1", usetList.get(0).getName());
        assertEquals("testUser2", usetList.get(1).getName());
    }

    
}
