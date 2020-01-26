From openjdk:13
copy ./target/service-manual-0.1.0.jar service-manual-0.1.0.jar
CMD ["java","-jar","service-manual-0.1.0.jar"]