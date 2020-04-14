#!/bin/bash -e

#MasterとSlaveを環境変数で制御する
#MYSQL_MASTERに値が設定されているかどうかで、MASTERとして動作させるか、SLAVEとして動作させるか決める
if[! -z "$MYSQL_MASTER"]; then
  echo "this container is master"
  return 0
fi

echo "prepare as slave"

#slaveからmasterへの疎通確認をする
if[-z "$MYSQL_MASTER_HOST"]; then
  echo "mysql_master_host is not specified" 1>&2
  return 1
fi

while :
do
  if mysql -h $MYSQL_MASTER_HOST -u root -p$MYSQL_ROOT_PASSWORD -e "quit" > /dev/null 2>&1 ; then
    echo "MYSQL master is ready"
    break
  else
    echo "MYSQL master is not ready"
  fi
  sleep 3
done

#Masterにレプリケーション用のユーザーと権限の作成
IP=`hostname -i`
IFS='.'
set -- $IP
SOURCE_IP="$1.$2.%.%"
#レプリケーションの為にMasterにはSlaveのレプリケーション用ユーザー登録と権限を設定する必要がある。
mysql -h $MYSQL_MASTER_HOST -u root -p$MYSQL_ROOT_PASSWORD -e \
"CREATE USER IF NOT EXISTS '$MYSQL_REPL_USER'@'$SOURCE_IP' IDENTIFIED BY '$MYSQL_REPL_PASSWORD';"
mysql -h $MYSQL_MASTER_HOST -u root -p$MYSQL_ROOT_PASSWORD -e \
"GRANT REPLICATION SLAVE ON *.* TO '$MYSQL_REPL_USER'@'$SOURCE_IP';"


#masterのbinlogのポジションを取得
MASTER_STATUS_FILE=/tmp/master-status
mysql -h $MYSQL_MASTER_HOST -u root -p$MYSQL_ROOT_PASSWORD -e "SHOW MASTER STATUS\G" \
> $MASTER_STATUS_FILE
BINLOG_FILE=`cat $MASTER_STATUS_FILE | grep File |  | xargs | cut -d ' ' -f2`
BINLOG_POSITTION=`cat $MASTER_STATUS_FILE | grep Position | xargs | cut -d ' ' -f2`
echo "BINLOG_FILE=$BINLOG_FILE"
echo "BINLOG_POSITTION=$BINLOG_POSITTION"


#レプリケーションを開始する
mysql -u root -p$MYSQL_ROOT_PASSWORD -e
"CHANGE MASTER TO MASTER_HOST='$MYSQL_MASTER_HOST', \
                  MASTER_USER='$MYSQL_REPL_USER', \
                  MASTER_PASSWORD='MYSQL_REPL_PASSWORD, \'
                  MASTER_LOG_FILE='$BINLOG_FILE', \
                  MASTER_LOG_POS='$BINLOG_POSITTION';"
mysql -u root -p$MYSQL_ROOT_PASSWORD -e "START SLAVE"

echo "slave started"

 "