import logo from './logo.svg';
import './App.css';
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

// カレントディレクトリからのパス設定を記述
import MemoList from './components/memo/MemoList';
import MemoCreate from './components/memo/MemoCreate';
import MemoUpdate from './components/memo/MemoUpdate';

// http://localhost:3000/api/memo/all
function App() {
  return (
    //Routerタグの内部にルーティングを記述する 必要なコンポーネントは上でimportしておくこと
    <Router>
      <Routes>
      <Route path="/api/memo/all" element={<MemoList />} />
      <Route path="/api/memo/create" element={<MemoCreate />} />
      <Route path="/api/memo/:id" element={<MemoUpdate />} />
        {/* <Route path="/" element={<MemoList />} /> */}
        {/* その他のルートを追加 */}
      </Routes>
    </Router>

    // 最初の記述 
    // <div className="App">
    //   <header className="App-header">
    //     <img src={logo} className="App-logo" alt="logo" />
    //     <p>
    //       Edit <code>src/App.js</code> and save to reload.
    //     </p>
    //     <a
    //       className="App-link"
    //       href="https://reactjs.org"
    //       target="_blank"
    //       rel="noopener noreferrer"
    //     >
    //       Learn React
    //     </a>
    //   </header>
    // </div>
  );
}

export default App;
