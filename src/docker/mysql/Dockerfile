FROM mysql:8.0

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
COPY ./add-server-id.sh /usr/local/bin/
COPY ./mysql.conf.d/mysqld.cnf /etc/mysql/mysql.conf.d/
VOLUME ./conf.d/mysql.cnf /etc/mysql/conf.d/
COPY ./prepare.sh /etc/mysql/conf.d/
COPY ./init-data.sh /usr/local/bin/
COPY ./sql /sql


RUN apt-get update \
    && apt-get install -y locales \
    && locale-gen ja_JP.UTF-8 \
    && echo "export LANG=ja_JP.utf8" >> ~/.bashrc \
    && echo "export LANGUAGE=ja_JP:en" >> ~/.bashrc


#スクリプトとmysqlの実行
ENTRYPOINT ["prehook", "/usr/local/bin/add-server-id.sh", "--", "docker-entrypoint.sh"]

CMD ["mysqld"]


