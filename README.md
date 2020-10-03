# java-spring-simple-aws

Simple spring application. 

## API 
Responsible for receiving the request from HTTP then sends payload to Amazon SQS.


## Processor
Polls the SQS using Spring Cloud's SqsListener annotation then writes to Amazon DynamoDB
