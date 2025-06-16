FROM eclipse-temurin:21.0.7_6-jre
EXPOSE 8080
RUN mkdir "/app"
COPY ./build/install/PartManager /app/
CMD /app/bin/PartManager
