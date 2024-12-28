package com.example.angularDemo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.StaleObjectStateException;
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
