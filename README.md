# 本プロジェクトの概要
本プロジェクトはフロントがReact、バックエンドにspringBootを使用した練習用プロジェクトです。
2025年の1月から開始するコミチのプロジェクトに参画するにあたり、必要な学習内容をまとめたものです。
AWSで動かすことも目的の1つとしているため、AWSのソースも追加しています。
このアプリケーションのgitURLは以下の通りです
https://github.com/teruhisashibuya71/reactDemo/tree/master

# アプリケーションの起動
## Gradleを使用した起動
```
gradle bootrun
```
※ビルドして実行される

## jarファイルを叩く場合
```
java -jar build/lib/TestApp.jar
```

## 引数つきでjarファイルを叩く場合
```
java -jar build/lib/TestApp.jar --引数名=値 --引数名2=値2 
```




# RepositoryのURL
```
https://github.com/teruhisashibuya71/reactDemo/branches
```

# グレイドルのビルドコマンド
./gradlew build
./gradlew clean build
./gradlew clean build -x test


# テストを実行しないビルドコマンド
./gradlew build -x test



# Reactのファイル構成
基本的なファイル構成は以下の種類
js jsx ts tsx

## js




# Reactの基本コマンド

## アプリ起動
npm start

## version確認
npm list react
※関連するパッケージのバージョンも表示される。
 なお、本プロジェクトのバージョンは19.0.0


## このファイルのpreview
shift + ⌘ + v


## インデント統一
shift + ⌥ + F


