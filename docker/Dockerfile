FROM gitpod/workspace-full:latest

# DP2 shared library
USER gitpod
RUN wget --no-check-certificate https://pad.polito.it:8080/enginframe/dp2/material/jars/lib.tar.gz && \
    tar zxf lib.tar.gz && \
    rm lib.tar.gz
USER root
RUN mkdir -p /opt/dp2/shared && \
    mv /home/gitpod/lib/ /opt/dp2/shared/

# Apache Ant 1.9.6
USER root
RUN wget https://archive.apache.org/dist/ant/binaries/apache-ant-1.9.6-bin.tar.gz && \
    tar zxf apache-ant-1.9.6-bin.tar.gz && \
    rm apache-ant-1.9.6-bin.tar.gz && \
    mv apache-ant-1.9.6 /usr/local/bin/
USER gitpod
ENV ANT_HOME=/usr/local/bin/apache-ant-1.9.6/
ENV PATH=${ANT_HOME}/bin:${PATH}

# Giving back control to Gitpod
USER root
