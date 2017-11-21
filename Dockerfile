FROM maven:3.3.9-jdk-8
RUN mkdir code
#设置工作路径
WORKDIR /code
ADD ./ /code/
#mvn打包
RUN mvn clean package -fn -DskipTests=true