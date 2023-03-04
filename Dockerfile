FROM maven:3.8.3-amazoncorretto-17
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN mvn clean package -DskipTests
CMD ["java","-Dspring.profiles.active=prod", "-DDATASOURCE_URL=jdbc:mysql://mysqldb/vollmed_api", "-DDATASOURCE_USERNAME=root", "-DDATASOURCE_PASSWORD=root","-jar","target/medvollapi-0.0.1-SNAPSHOT.jar"]
