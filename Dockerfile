FROM anapsix/alpine-java

COPY ./build/libs/gradleexample-all.jar /home/gradleexample-all.jar

EXPOSE 8080

CMD java -jar /home/gradleexample-all.jar