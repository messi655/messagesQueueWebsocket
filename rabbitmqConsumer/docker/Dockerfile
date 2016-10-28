FROM java:7

RUN apt-get update && \
    apt-get install -yq --no-install-recommends wget ca-certificates expect && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

RUN mkdir /srv/tomcat

ENV TOMCAT_MAJOR_VERSION 7
ENV TOMCAT_MINOR_VERSION 7.0.72
ENV CATALINA_HOME /srv/tomcat
ENV PATH $PATH:$CATALINA_HOME/bin

RUN wget -q http://mirror.downloadvn.com/apache/tomcat/tomcat-${TOMCAT_MAJOR_VERSION}/v${TOMCAT_MINOR_VERSION}/bin/apache-tomcat-${TOMCAT_MINOR_VERSION}.tar.gz && \
    tar zxf apache-tomcat-*.tar.gz && \
    rm apache-tomcat-*.tar.gz && \
    mv apache-tomcat-${TOMCAT_MINOR_VERSION}/* $CATALINA_HOME


RUN mkdir -p /apps/config
COPY server.xml $CATALINA_HOME/conf/server.xml


EXPOSE 8080

ENTRYPOINT ["/srv/tomcat/bin/catalina.sh", "run"]
