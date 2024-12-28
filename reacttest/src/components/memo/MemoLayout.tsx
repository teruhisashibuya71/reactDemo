//Memoモデル専用で使用されるレイアウトファイル
//アプリ全体で使用されるレイアウト部品は、App.tsxに記載する

import React from 'react';
import { Outlet, redirect } from 'react-router-dom'; // For nested routing
import './css/MemoLayout.css';

//共通コンポーネン化の手順①.プロップの追加 プロップ名:children
interface MemoLayoutProps {
    children?: React.ReactNode;
}

//const MemoLayout: React.FC = () => {
//共通コンポーネント化の手順②.childrenプロップの導入定義
const MemoLayout: React.FC<MemoLayoutProps> = ({ children }) => { // プロップを使用
    return (
        <div className="memo-layout">
            <header className="memo-header">
                <h1>Memo Layout Element Title <span style ={{color: 'red'}}>by MemoLayout.tsx</span></h1>
            </header>

            <main className="memo-main">
                {/* 共通コンポーネント化の手順③.childrenプロップの使用 */}
                {/* ここに共通コンポーネント呼び出し側の要素が入る */}
                {children}
                {/* <Outlet />  */}
            </main>

            <footer className="memo-footer">
                <p>&copy; 2023 Your Company footer <span style ={{color: 'red'}}>by MemoLayout.tsx</span></p>
            </footer>
        </div>
    );
};

export default MemoLayout;
