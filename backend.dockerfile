FROM ubuntu:20.04

RUN apt-get update
RUN apt-get install -y vim
RUN apt-get install -y net-tools
RUN apt-get install -y nginx
RUN apt-get install -y git
RUN apt-get install -y wget
RUN apt-get install -y dpkg

RUN apt-get update
RUN apt install -y libc6-i386 libc6-x32 libxi6 libxtst6 libxrender1
RUN wget https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.deb && dpkg -i jdk-17_linux-x64_bin.deb
ENV JAVA_HOME=/usr/lib/jvm/jdk-17/
ENV PATH=$PATH:$JAVA_HOME/bin

COPY ./dummy.txt .