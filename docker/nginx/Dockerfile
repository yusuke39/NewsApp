FROM nginx:1.17.9

ADD ./conf/nginx.conf /etc/nginx/conf.d/nginx.conf

ENTRYPOINT ["nginx", "-g", "daemon off;"]

