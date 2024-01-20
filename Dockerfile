FROM amazoncorretto:17

EXPOSE 8080

WORKDIR /home

RUN yum update && yum upgrade
COPY . .
RUN chmod +x bin/run.sh

CMD "./bin/run.sh"
