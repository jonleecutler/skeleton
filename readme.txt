HTTP Server
Project 1
CS5450

Jonathan Cutler – jlc553@cornell.edu
Zen Yui - jzy6@cornell.edu

For this assignment, we were asked to create an HTTP Server using the Berkeley Sockets API.  Our service implements the select()-based pattern to handle multiple clients at once. We support the GET, HEAD and POST HTTP version 1.1 methods as well as a robust set of HTTP status codes when handling various client and server side errors.

Supported Functionality:

GET
- Get a requested resource
HEAD
- Get details of a requested resource
POST
- Send data to the service to exercise the request parser

Supported Error Types:

400 Bad Request
- The incoming http request is invalid
404 Not Found
- The requested resource does not exist
501 Method Unimplemented
- The requested method is not implemented
503 Service Unavailable
- A maximum number of clients are currently connected, temporary error
505 HTTP Version Not Supported
- The http version of the request is not supported
500 Internal Server Error
- The service encountered an unknown error

