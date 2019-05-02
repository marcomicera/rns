FROM gitpod/workspace-full:latest

RUN wget --no-check-certificate https://pad.polito.it:8080/enginframe/dp2/material/jars/lib.tar.gz && \
    tar zxfv lib.tar.gz && \
    rm lib.tar.gz

# Give back control
USER root

RUN mkdir -p /opt/dp2/shared && \
    mv /home/gitpod/lib/ /opt/dp2/shared/