
# 设置本镜像需要使用的基础镜像
FROM  openjdk:17-oracle
  
# 把jar包添加到镜像中
ADD mq/target/mq-1.0-SNAPSHOT.jar /app.jar

# 镜像暴露的端口
#test1111111111121212312
EXPOSE 8001
 
RUN bash -c 'touch /app.jar'
  
# 容器启动命令
ENTRYPOINT ["java","-Xms300m","-Xmx300m","-jar","/app.jar"]
 
# 设置时区
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone