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
	mvn clean package -DskipTests=true;	

build-with-tests:
	mvn clean package;

run-application:
	mvn tomcat7:run;

run: build run-application

run-with-tests: build-with-tests run-application

deploy:
	mvn tomcat7:deploy;

undeploy:
	mvn tomcat7:undeploy;
