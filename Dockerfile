FROM eclipse-temurin:21-jre
EXPOSE 8080
RUN mkdir "/app"
COPY ./build/install/Myridan /app/
CMD /app/bin/Myridan
