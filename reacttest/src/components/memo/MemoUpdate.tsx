// いつも通りの3点セット
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom'; // useParamsを追加

// Memoを定義
interface Memo {
    id: number; // バックエンドでLong型に変更する
    content: string;
    createdAt: string;
    updatedAt: string;
  }

// 更新処理を定義
const MemoUpdate: React.FC = () => {
    // 使用する定数を定義
    // useParamsでidを取得
    const { id } = useParams<{ id: string }>();
    const [memo, setMemo] = useState<Memo | null>(null);
    const [content, setContent] = useState('');
    const [createdAt, setCreatedAt] = useState('');
    const [updatedAt, setUpdatedAt] = useState('');
    const [error, setError] = useState<string | null>(null);
    // 更新完了後に画面遷移するために追加
    const navigate = useNavigate(); 

    // まずはfetchで情報を取得する処理を記載
    useEffect(() => {
        const fetchMemo = async () => {
          try {
            const response = await axios.get(`http://localhost:8080/api/memo/${id}`);
            setMemo(response.data);
            setContent(response.data.content);
            setCreatedAt(response.data.createdAt);
            setUpdatedAt(response.data.updatedAt);
          } catch (error) {
            setError("メモの取得に失敗しました。");
            console.error("メモ取得エラー:", error);
          }
        };
    
        if (id) {
          fetchMemo();
        }
      }, [id]);

      //更新内容をバックエンドに送る処理
      const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError(null); // エラーメッセージをクリア
    
        if (!memo) {
          setError("メモデータがありません。");
          return;
        }
    
        try {
          await axios.post(`http://localhost:8080/api/memo/update/${memo.id}`, {
            id: memo.id,
            content: content,
            createdAt: createdAt,
            updatedAt: updatedAt,
          });
          // 更新成功後の処理（例：成功メッセージを表示、一覧ページへリダイレクトなど）
          navigate("/api/memo/all");
          alert("メモを更新しました。");
        } catch (error) {
          setError("メモの更新に失敗しました。");
          console.error("メモ更新エラー:", error);
        }
      };

      if (!memo) {
        return <div>メモを読み込んでいます...</div>;
      }
    
      // html要素を記述
      return (
        <form onSubmit={handleSubmit}>
          <label>
            内容:
            <input
              type="text"
              name="content"
              value={content}
              onChange={(e) => setContent(e.target.value)}
            />
          </label>
          <label>
            作成日時:
            <input type="text" name="createdAt" value={createdAt} readOnly /> {/*readOnly属性を追加*/}
          </label>
          <label>
            更新日時:
            <input type="text" name="updatedAt" value={updatedAt} readOnly /> {/*readOnly属性を追加*/}
          </label>
          {error && <div style={{ color: 'red' }}>{error}</div>}
          <button type="submit">メモを更新</button>
        </form>
    );
};


export default MemoUpdate;