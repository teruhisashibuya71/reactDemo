import React, { useState, useEffect } from "react";

export const ApiFetch = () => {
	const [memos, setMemo] = useState([]);

	useEffect(() => {
		// APIをfetchする(呼び出す)
		fetch("http://localhost:8080/api/memo/show", { method: "GET" })
			// レスポンスのデータ形式をjsonに設定
			.then((res) => res.json())
			// APIから渡されるレスポンスデータ(data)をstateにセットする
			.then((data) => {
				setMemo(data);
			});
	}, []);

	return (
		<div>
			<ul>
				<li>{memos.id}</li>
				<li>{memos.content}</li>
				<li>{memos.createdAt}</li>
                <li>{memos.updatedAt}</li>
			</ul>
		</div>
	);
};