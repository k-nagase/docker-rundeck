FROM local/c7-systemd
MAINTAINER k.nagase@gooday.co.jp

ARG RUNDECK_REPO
ARG RUNDECK_HOST_NAME
ARG RUNDECK_PORT
ARG JAVA_VERSION
ARG MYSQL_HOST
ARG MYSQL_DATABASE
ARG MYSQL_USER
ARG MYSQL_PASSWORD
ARG SMTP_HOST
ARG SMTP_PORT
ARG SMTP_USERNAME
ARG SMTP_PASSWORD
ARG SMTP_PROPS

RUN yum -y install ${RUNDECK_REPO} \
    && yum -y install java-${JAVA_VERSION} rundeck \
    && yum clean all

# Configuration /etc/rundeck/rundeck-config.groovy
COPY ./groovy/rundeck-config.groovy /etc/rundeck
RUN mv /etc/rundeck/rundeck-config.properties /etc/rundeck/rundeck-config.properties.orig \
    && sed -i -e "s/{MYSQL_HOST}/${MYSQL_HOST}/g" \
              -e "s/{MYSQL_DATABASE}/${MYSQL_DATABASE}/g" \
              -e "s/{MYSQL_USER}/${MYSQL_USER}/g" \
              -e "s/{MYSQL_PASSWORD}/${MYSQL_PASSWORD}/g" \
              -e "s/{RUNDECK_HOST_NAME}/${RUNDECK_HOST_NAME}/g" \
              -e "s/{RUNDECK_PORT}/${RUNDECK_PORT}/g" \
              -e "s/{SMTP_HOST}/${SMTP_HOST}/g" \
              -e "s/{SMTP_PORT}/${SMTP_PORT}/g" \
              -e "s/{SMTP_USERNAME}/${SMTP_USERNAME}/g" \
              -e "s/{SMTP_PASSWORD}/${SMTP_PASSWORD}/g" \
              -e "s/{SMTP_PROPS}/${SMTP_PROPS}/g" \
              -e "s/\'//g" \
              /etc/rundeck/rundeck-config.groovy \
    && cp -p /etc/rundeck/profile /etc/rundeck/profile.orig \
    && sed -i -e 's/rundeck-config\.properties/rundeck-config\.groovy/g' /etc/rundeck/profile

# Configuration /etc/rundeck/framework.properties
RUN cp -p /etc/rundeck/framework.properties /etc/rundeck/framework.properties.orig \
    && sed -i -e 's/^framework.server/#&/g' /etc/rundeck/framework.properties \
   && echo "framework.server.name = ${RUNDECK_HOST_NAME}" >> /etc/rundeck/framework.properties \
   && echo "framework.server.hostname = ${RUNDECK_HOST_NAME}" >> /etc/rundeck/framework.properties \
   && echo "framework.server.port = ${RUNDECK_PORT}" >> /etc/rundeck/framework.properties \
   && echo "framework.server.url = http://${RUNDECK_HOST_NAME}" >> /etc/rundeck/framework.properties \
   && echo "framework.rundeck.url = http://${RUNDECK_HOST_NAME}" >> /etc/rundeck/framework.properties

RUN systemctl enable rundeckd

EXPOSE ${RUNDECK_PORT}

CMD ["/usr/sbin/init"]
