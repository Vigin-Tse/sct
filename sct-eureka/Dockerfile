FROM openjdk:8-jdk-alpine

#VOLUME指向了一个/tmp的目录，由于Spring Boot使用内置的Tomcat容器，Tomcat默认使用/tmp作为工作目录。
#效果就是在主机的/var/lib/docker目录下创建了一个临时文件，并连接到容器的/tmp。
VOLUME /tmp

# 将jar包添加到容器中并更名为app.jar
COPY target/*.jar app.jar

#设置容器内时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]