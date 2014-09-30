setup-mac:
	brew install maven; \
	brew install tomcat7;

install:
	mvn clean install;

compile:
	mvn clean compile;

tests:
	mvn clean test;

build:
	mvn clean package;

run-application:
	mvn tomcat7:run;

run: build run-application

run-with-tests: build run-application
