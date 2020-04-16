FROM mysql:5.7

#パッケージアップデートとwgetインストール
RUN apt-get update
RUN apt-get install -y wget

#entrykitのインストール
RUN wget https://github.com/progrium/entrykit/releases/download/v0.4.0/entrykit_0.4.0_linux_x86_64.tgz
RUN tar -xvzf entrykit_0.4.0_linux_x86_64.tgz
RUN rm entrykit_0.4.0_linux_x86_64.tgz
RUN mv entrykit /usr/local/bin/
RUN entrykit --symlink

#スクリプトと各種設定ファイルのコピー
CMD ["/bin/bash", "chmod", "+x", "add-server-id.sh"]
COPY ./add-server-id.sh /usr/local/bin/
COPY ./mysql.conf.d/mysqld.cnf /etc/mysql/mysql.conf.d/
COPY ./conf.d/mysql.cnf /etc/mysql/conf.d/
COPY ./prepare.sh /etc/mysql/conf.d/
COPY ./init-data.sh /usr/local/bin/
COPY ./sql /sql

#スクリプトとmysqlの実行
c, "--", "docker-entrypoint.sh"]

CMD ["mysqld"]


