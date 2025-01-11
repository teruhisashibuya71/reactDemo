# git コマンド集
よく使いそうなものを記述しています。


## ブランチ一覧
ローカル
```
git bracnh
```
リモート
```
git branch -r
```
全部
```
git branch -a
```


## 変更のあるファイル名を一覧で出力する
```
git diff --name-only
```
ファイルパスは表示されない。
なお、新たに新規で追加されたファイルは表示されないので注意が必要。

# 新規追加されたファイルも含めて出力する
```
git status
```
新規追加されたファイルも含めて表示。
内容がやや確認しづらい。

表示例
```
tspc:reactDemo ts$ git status
On branch master
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
        modified:   README.md

Untracked files:
  (use "git add <file>..." to include in what will be committed)
        README_git.md

no changes added to commit (use "git add" and/or "git commit -a")
```


## ブランチ名の変更
```
git branch -m 古い名前 新しい名前
git branch -m shibuya-fix feature/switching-usertype
```
すでにリモートにプッシュしてしまった場合は削除するしかない
## リモートブランチの削除コマンド
```

```
