// バックエンドのfindAll処理に紐づくフロント処理
// 基礎的ななimport文 これらは基本的に必ず記述することになるはず
// それぞれ使用するためにライブラリのインストールが必要なので注意 npmコマンドでインストールすること
import React, { useState, useEffect } from 'react';
import axios from 'axios';

//カレントディレクトリからのパスで指定 cssファイルを配置するディレクトリは要検討のこと
import './css/MemoList.css';

// 親コンポーネント使用手順①.コンポーネントをimportする
// 共通で使用するようなコンポーネントは、一般的に「親コンポーネント」と呼ぶ
import MemoLayout from './MemoLayout';

//画面遷移処理を実装するためのもの
import { useNavigate } from 'react-router-dom'; // useNavigate をインポート


// バックエンドから返却されるJSONデータ構造と一致させる
// 通常のjsと異なり型を指定できるので理解しやすい
interface Memo {
  id: number;
  content: string;
  createdAt: string;
  updatedAt: string;
}

const MemoList: React.FC = () => {
  //apiアクセスに伴い必要になる定数を用意  
  const [memos, setMemos] = useState<Memo[]>([]);
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate(); // useNavigate を初期化

  //ここからが本番
  //①.メモを取得する処理
  useEffect(() => {
    const fetchMemos = async () => {
      try {
        const response = await axios.get<Memo[]>('http://localhost:8080/api/memo/all');
        //正常終了
        setMemos(response.data);
      } catch (error) {
        setError(error instanceof Error ? error.message : 'An error occurred');
      } finally {
        setLoading(false);
      }
    };

    fetchMemos();
  }, []);


  //②.メモを更新する処理
  const handleEdit = (id: number) => {
    navigate(`/api/memo/${id}`); // 編集画面に遷移
  };

  //③.メモを削除する処理
  const handleDelete = async (id: number) => {
    try {
      //await axios.delete(`http://localhost:8080/api/memo/delete/${id}`); // 削除APIを呼び出す
      await axios.post(`http://localhost:8080/api/memo/delete/${id}`); // 削除APIを呼び出す
      // 成功したら、メモ一覧を再取得する
      const response = await axios.get<Memo[]>('http://localhost:8080/api/memo/all');
      setMemos(response.data);
      alert('メモを削除しました');
    } catch (error) {
      setError(error instanceof Error ? error.message : 'An error occurred');
    }
  };




  //fetch処理が完了するまでの間の画面表示
  if (loading) {
    return <div>Loading...</div>;
  }

  //レスポンスがエラーだった場合
  if (error) {
    return <div>Error {error}</div>;
  }

  // 画面表示内容を記述
  return (
    // 親コンポーネント使用手順②.htmlタグのような形式で要素を囲む
    <MemoLayout>
      <div className="memo-list">
        <h1>Memo List Element <span style ={{color: 'blue'}}>by MemoList.tsx</span></h1>
        <ul>
          {memos.map((memo) => (
            <li key={memo.id}>
              <div>
                <h3>{memo.content}</h3>
                <p>Created At: {memo.createdAt}</p>
                <p>Updated At: {memo.updatedAt}</p>
                <button onClick={() => handleEdit(memo.id)}>編集</button>
                <button onClick={() => handleDelete(memo.id)}>削除</button>
              </div>
            </li>
          ))}
        </ul>
      </div>
    </MemoLayout>

  );
};

export default MemoList;