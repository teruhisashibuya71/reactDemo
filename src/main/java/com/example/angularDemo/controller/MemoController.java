package com.example.angularDemo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.angularDemo.model.Memo;
import com.example.angularDemo.service.MemoServiceImpl;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

/**
 * メモ コントローラー
 * Memoモデルに対するCRUD処理を実装した基礎的にコントローラー
 * Reactによるフロント処理に対応するため、RestControllerとした
 */
@RestController
@RequestMapping("/api/memo")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "http://localhost:3000", methods = {org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.PUT, org.springframework.web.bind.annotation.RequestMethod.DELETE})
@RequiredArgsConstructor
public class MemoController {

    private final MemoServiceImpl memoService;

    /**
     * メモ内容の表示テスト
     * http://localhost:8080/api/memo/show
     * 
     * @return
     */
    // @GetMapping("/show")
    // @ResponseBody
    // public Memo showMemo(){

    //     // Memoを新規作成してフロントに渡す
    //     Memo memo = new Memo(1L, "テストメモ", LocalDateTime.now(), LocalDateTime.now());
        
    //     System.out.println("処理完了");
    //     return memo;
    // }

    /**
     * メモの全表示
     * http://localhost:8080/api/memo/all
     * @return
     */
    @GetMapping("/all")
    @ResponseBody
    public List<Memo> showAllMemo(Model model){

        // すべてメモを取得
        List<Memo> memos = memoService.getAllMemos();

        return memos;
    }



    /**
     * メモの新規登録処理
     * 
     * @param memo メモ（フォーム入力値）
     * @return ステータスコード201 生成成功
     */
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Memo> showCreate(@RequestBody Memo newMemo){

        // 登録処理を実行
        Memo creatMemo = memoService.createMemo(newMemo);
    
        // 新規登録完了のステータスコードを返却(201)
        return new ResponseEntity<Memo>(creatMemo, HttpStatus.CREATED);
    }

    
    /**
     * 更新対象のメモを取得する
     * 
     * @param id
     * @param updateMemo
     * @return
     */
    @GetMapping("/{ID}")
    @ResponseBody
    public ResponseEntity<Memo> showMemo(@PathVariable(value = "ID") Long id){

        // 更新対象のメモを取得
        Memo targetMemo = memoService.getMemoById(id);
    
        // 更新対象のメモ情報とステータスコードを返却
        return new ResponseEntity<Memo>(targetMemo, HttpStatus.OK);
    }

    /**
     * メモの更新処理 
     * TODO 24日フロントの処理を考えるところから
     * 
     * @param id 更新対象メモのID
     * @param updateMemo メモの更新内容
     * @return ステータスコード200 成功
     */
    @PostMapping("/update/{ID}")
    @ResponseBody
    public ResponseEntity<Memo> updateMemo(@PathVariable(value = "ID") Long id,
                                           @RequestBody Memo updateMemo){

        // 更新処理を実行
        Memo updatedMemo = memoService.updateMemo(id, updateMemo);
    
        // 新規登録完了のステータスコードを返却(200)
        return new ResponseEntity<Memo>(updatedMemo, HttpStatus.OK);
    }


    /**
     * メモの削除
     * 一覧画面上の「削除」ボタンより実行
     * 
     * @param id
     * @param model
     * @return
     */
    @PostMapping("/delete/{ID}")
    @ResponseBody
    public ResponseEntity<Void> deleteMemo(@PathVariable(value = "ID") Long id){

        // 対象のメモを削除する
        memoService.deleteMemo(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
