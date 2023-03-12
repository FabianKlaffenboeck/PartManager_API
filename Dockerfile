FROM eclipse-temurin:17-alpine
EXPOSE 8080
RUN mkdir "/app"
COPY ./build/install/PartManager /app/
CMD /app/bin/PartManager