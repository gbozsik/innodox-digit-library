
Frontend built in src\main\resources\static folder.
Compile it in your ide, than you can reach the app at localhost:9000 in your browser.
Or Build it with Gradle, than run the jar file in terminal, in its folder: ~/build/libs/java -jar library-0.0.1-SNAPSHOT.jar


Users:					  Password:

sanyi.kovacs@gmail.com, 		pas
feri.sos@t-online.com,			pas
tamas.kis@gmail.com			pas

Spring Boot app.
Built with Gradle.
Spring security using, basicAuthentication.
JPA - Hibernate.
H2 in memory DB. (h2 profile)
Postgres database
Liquibase
REST
Mapstruct

If you would like to run it with postgres, you can run a db in docker:
sudo docker run -d --restart always --name ffr-db-big --network ffr-net -v ffr-db-data:/var/lib/postgresql/data -p 5433:5432 -e POSTGRES_USER=process -e POSTGRES_PASSWORD=test -e POSTGRES_DB=process postgres:12.1


You can find the frontend code in digital_library-frontEnd repository.
If you compile it, and the backend is up and running as well, you can reach it at localhost:4000 url in your browser
