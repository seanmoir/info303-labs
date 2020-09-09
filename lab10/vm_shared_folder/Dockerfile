# start with official Gradle/Alpine base image
FROM gradle:alpine as builder
# run install tasks as root
USER root
# install Git
RUN apk --no-cache add git
# run remainder of tasks as the unprivileged 'gradle' user
USER gradle
# clone repo
RUN git clone https://isgb.otago.ac.nz/infosci/git/INFO303/shopping.git
# add a volume for Gradle home
VOLUME "/home/gradle/.gradle"
# switch into repo folder
WORKDIR shopping
# build service
RUN gradle --no-daemon installDist
FROM openjdk:jre-alpine
COPY --from=builder /home/gradle/shopping/build/install /home/deployment
WORKDIR /home/deployment
ENV _JAVA_OPTIONS=-Xmx512M
# the port that the service is using
EXPOSE 8080
# command to start the service
CMD shopping/bin/shopping $PORT