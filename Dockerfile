FROM eclipse-temurin:17-alpine
EXPOSE 8080
RUN mkdir "/app"
COPY ./build/distributions/PartManager-0.0.1 /app
CMD /app/bin/PartManager