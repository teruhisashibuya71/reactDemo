// 登録処理に必要なものは以下の2つ
import React, { useState, useEffect } from 'react';
import axios from 'axios';

// Memoモデルの定義を示す
interface Memo {
  //id: string | null;
  content: string;
  createdAt: string; // 必要に応じてDateオブジェクトに変更
  updatedAt: string; // 必要に応じてDateオブジェクトに変更
}

const MemoCreate: React.FC = () => {
  const [content, setContent] = useState('');
  const [createdAt, setCreatedAt] = useState(''); // デートピッカーコンポーネントの使用を検討
  const [updatedAt, setUpdatedAt] = useState(''); // デートピッカーコンポーネントの使用を検討
  const [successMessage, setSuccessMessage] = useState(''); // 成功メッセージ用のステートを追加
  const [errorMessage, setErrorMessage] = useState(''); // エラーメッセージ用のステートを追加


  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setSuccessMessage(''); // 成功メッセージをクリア
    setErrorMessage(''); // エラーメッセージをクリア

    try {
      const newMemo: Memo = {
        //id: null, // バックエンドで自動生成されることが多い
        content: content,
        createdAt: '', // バックエンドに合わせてフォーマットする必要がある
        updatedAt: '', // バックエンドに合わせてフォーマットする必要がある
      };

      const response = await axios.post('http://localhost:8080/api/memo/create', newMemo); // 必要に応じてURLを調整
      console.log('メモが作成されました:', response.data);
      setSuccessMessage('メモの作成に成功しました！');

      // フォーム内容をリセットしてcreate画面を再度表示する
      setContent('');

    } catch (error) {
      console.error('メモ作成エラー:', error);
      setErrorMessage('メモの作成に失敗しました。'); // エラーメッセージを設定
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      {successMessage && <div style={{ color: 'green' }}>{successMessage}</div>} {/* 成功メッセージを表示 */}
      {errorMessage && <div style={{ color: 'red' }}>{errorMessage}</div>} {/* エラーメッセージを表示 */}
      <label>
        内容:
        <input type="text" name="content" value={content} onChange={(e) => setContent(e.target.value)} />
      </label>
      <button type="submit">メモを作成</button>
    </form>
  );
};

export default MemoCreate;
