fifo-queue
==========

Sample: implementation simple queue. Ingredients: Spring Boot, Spring MVC with embeded Tomcat under Maven sauce.

Build:
    mvn clean install
Run:
    mvn spring-boot:run


Commands:

	Get Stats:
		curl -X GET 'http://127.0.0.1:8080/queue/'

	Register Queue:
		curl -H 'Content-type: application/json' -X POST -d '{"id":"Q1"}' 'http://127.0.0.1:8080/queue/'
	
	Delete Q:
		curl -X DELETE 'http://127.0.0.1:8080/queue/Q1'

	Push message into Q:
		curl -H 'Content-type: application/json' -X PUT -d '{"message":"XXX"}' 'http://127.0.0.1:8080/queue/Q1'

	Pop message from Q:
		curl -H 'Content-type: application/json' -X GET 'http://127.0.0.1:8080/queue/Q1'


