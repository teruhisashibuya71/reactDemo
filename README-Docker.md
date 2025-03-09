# Docker構築手順

### ①.application.yml
src/main/resouces/application.ymlにmysqlなどの設定を記述する。

### ②.docker-compose.ymlファイル作成する
アプリケーションのディレクトリ直下にファイルを作成する。
docker上で構築するDBの設定を記述する。

### ③.docker composeコマンドを実行してdocker上にコンテナを作成
以下コマンドを実行し、docker-compose.ymlの内容でコンテナを生成する
```docket
※ `docker-compose`は使えなくなったので注意 commando not foundになる
※-d オプションはバックグランドで実行するためのオプション
docker compose up -d
```
上記コマンド実行後は、Docker for Mac desktop の方で、コンテナが生成されていることを確認できる。

### ④.dockerコンテナへsqlファイルをコピーする
ローカルにて、以下の「docker cp」コマンドでsqlファイルを転送する。
```
※ コンテナ名はコンテナID ポート番号の上に書いてあるIDのようなもの
docker cp your/sqlfile.sql <コンテナ名>:/path/to/destination/sqlfile.sql
コマンド例
tspc:~ ts$ docker cp /Users/ts/desktop/memo_ddl.sql d7c521aac74d6b909034332618b7ea3b8e6920c0fa5bec66bea2e54fa98aae8b:/tmp/exe_sql/memo_ddl.sql
tspc:~ ts$ docker cp /Users/ts/desktop/test_user_ddl.sql d7c521aac74d6b909034332618b7ea3b8e6920c0fa5bec66bea2e54fa98aae8b:/tmp/exe_sql/test_user_ddl.sql
```

### ⑤.すべてのDDLファイルを実行する
DDLファイルのあるディレクトリに移動して
```declarative
for file in /path/to/directory/*.sql; do mysql -u <ユーザー名> -p<パスワード> <データベース名> < $file; done
※コマンド例
for file in /tmp/exe_sql/*.sql; do mysql -u root -ppassword mydb < $file; done
```
実行結果は以下のとおり。「warning」が出力されるが問題ない。
```declarative
sh-5.1# for file in /tmp/exe_sql/*.sql; do mysql -u root -ppassword mydb < $file; done
mysql: [Warning] Using a password on the command line interface can be insecure.
mysql: [Warning] Using a password on the command line interface can be insecure.
```

### ⑥.springBootアプリを起動する

dockerForMac上でコンテナをクリック→「Exec」タブでコマンド実行可能
まずはsqlファイルを







