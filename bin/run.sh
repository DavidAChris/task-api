#!/bin/sh
./mvnw clean test package
java -jar target/*.jar
