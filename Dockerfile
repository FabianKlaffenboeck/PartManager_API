FROM eclipse-temurin:8u362-b09-jre-jammy
EXPOSE 8080
RUN mkdir "/app"
COPY ./build/install/PartManager /app/
CMD /app/bin/PartManager