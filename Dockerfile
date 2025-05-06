FROM eclipse-temurin:21-jre-jammy
EXPOSE 8080
RUN mkdir "/app"
COPY ./build/install/PartManager /app/
CMD /app/bin/PartManager
