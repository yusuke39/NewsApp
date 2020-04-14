#!/bin/bash -e
OCTEETS=(`hostname -i | tr -s '.' ''`)

MYSQL_SERVER_ID=`expr ${OCTETS[2]} \* 256 + ${OCTETS[3]}`
echo "server-id=$MYSQL_SERVER_ID" >> ./mysql/mysql.conf.d/mysql.cnf