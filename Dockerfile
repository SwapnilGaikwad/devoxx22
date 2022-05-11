FROM ubuntu:20.04

SHELL ["/bin/bash", "-c"]
RUN echo "Update APT Packages" \
    && apt-get update \
    && apt-get -y upgrade \
    && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install zlib1g-dev git python3 gcc g++ build-essential python3-pip python-setuptools jq maven\
    && ln -s /usr/bin/python3 /usr/bin/python \
    && echo "Install MX tool" \
    && git clone --depth 1 https://github.com/graalvm/mx.git /opt/mx \
    && ln -s /opt/mx/mx /usr/bin/mx \
    && cat /opt/mx/common.json | jq -r '.deps.common.packages | to_entries[] | select(.key | startswith("pip:")) | (.key | split(":")[1]) + .value' | xargs pip3 install \
    && echo "Download labsjdk (Graal enabled)" \
    && yes | mx fetch-jdk --jdk-id labsjdk-ce-11 --to /usr/lib/jvm --alias /usr/lib/jvm/labsjdk11-latest-jvmci \
    && echo "Download Graal" \
    && git clone --depth 1 https://github.com/oracle/graal.git /root/graal/ \
    && pushd /root/graal/compiler \
    && mx --java-home /usr/lib/jvm/labsjdk11-latest-jvmci build

COPY ./demo /root/demo

RUN ["/bin/bash", "/root/demo/setup.sh"]
