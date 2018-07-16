FROM local/c7-systemd
MAINTAINER k.nagase@gooday.co.jp

ARG RUNDECK_HOST_NAME
ARG RUNDECK_PORT

RUN yum -y install epel-release \
    && yum -y install nginx \
    && yum clean all

#RUN cp -p /etc/nginx/nginx.conf /etc/nginx/nginx.conf.orig \
#    && echo -e "\t\tproxy_pass http://app:${RUNDECK_PORT};" \
#    | sed -i -e '/^ *location \//r /dev/stdin' /etc/nginx/nginx.conf
RUN mkdir -p /etc/nginx/conf.d
COPY ./conf.d/default.conf /etc/nginx/conf.d/default.conf
RUN sed -i -e "s/{RUNDECK_HOST_NAME}/${RUNDECK_HOST_NAME}/g" \
           -e "s/{RUNDECK_PORT}/${RUNDECK_PORT}/g" \
           /etc/nginx/conf.d/default.conf

RUN systemctl enable nginx

EXPOSE 80

CMD ["/usr/sbin/init"]
