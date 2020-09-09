FROM alpine:3.10

ENV SPARK_VERSION="2.4.5" \
    HADOOP_VERSION="2.7" \
    SPARK_INSTALL="/usr/local" \
    APACHE_MIRROR="https://apache.inspire.net.nz" \
    PYSPARK_DRIVER_PYTHON="jupyter" \
    PYSPARK_DRIVER_PYTHON_OPTS="notebook --ip=0.0.0.0 --port=8080"\
    JAVA_HOME="/usr/lib/jvm/java-1.8-openjdk"

USER root

RUN apk --no-cache add \
    py3-pip \
    py-numpy \
    bash \
    zeromq-dev \
    openjdk8-jre

RUN ln /usr/bin/python3 /usr/bin/python

RUN apk add --no-cache --virtual .build-deps \
        build-base \
        python3-dev \
        wget \
        tar

# install jupyter via pip
RUN pip3 install --upgrade pip && \
    pip install \
        jupyter

# download spark
RUN cd $SPARK_INSTALL && \
    wget -q --show-progress --progress=bar:force:noscroll $APACHE_MIRROR/spark/spark-$SPARK_VERSION/spark-$SPARK_VERSION-bin-hadoop$HADOOP_VERSION.tgz 2>&1

# extract & symlink spark
RUN cd $SPARK_INSTALL && \
    tar xzf spark-$SPARK_VERSION-bin-hadoop$HADOOP_VERSION.tgz && \
    ln -s spark-$SPARK_VERSION-bin-hadoop$HADOOP_VERSION spark && \
    rm -f spark-$SPARK_VERSION-bin-hadoop$HADOOP_VERSION.tgz

# remove stuff that we no longer need
RUN apk del --purge --no-cache .build-deps

RUN adduser -D -u 1000 pyspark
USER pyspark

WORKDIR /home/pyspark

CMD /usr/local/spark/bin/pyspark --master "local[*]" --packages "graphframes:graphframes:0.8.0-spark2.4-s_2.11"
