setup-mac:
	brew install maven; \
	brew install tomcat7;

install:
	mvn clean install;

tests:
	mvn clean test;

build:
	mvn clean package;

run:
	mvn tomcat7:run;
