# Java web Crawler

## Building the application

1. Navigate to the root direcoty `web-clawler`.
2. On Linux or Mac, run `./gradlw clean build`. For windows `gradlew.bat clean build`. This will build the application.
3. Once the build is done, a uber jar will be created in `build/libs`.

## Running the application

We can run the application by using `java -jar build/libs/web-clawler-0.0.1-SNAPSHOT.jar`.

It will start the embedded Tomcat server on port `8080`.

The APIs can be quickly tested using Swagger which is available at `http://localhost:8080/swagger-ui.html`.

## APIs

The application exposes two REST endpoints.

1. `/api/v1/crawler`

| Request | Http Method     | Response           | Limitations  |
| --------| :----- |:-------------:| -----:|
| Single `url` as body | POST     | Please see below for response details | The api take single `url` as request  |


Response
~~~~
{
  "error": "string",
  "message": "string",
  "status": 0
}
~~~~

2. `/api/v1/crawler/result`

| Request | Http Method       | Response           | Limitations  |
| --------|:----- |:-------------:| -----:|
| Single `url` as request Param | GET      | Please see below for response details |   |

Response:
~~~~
{
	"url": "https://www.scout24.com/en/Home.aspx",
	"links": {
		"total": 132,
		"internal": {
			"total": 124,
			"details": [{
				"info": {
					"url": "https://www.scout24.com/en/desktopdefault.aspx/tabid-74/",
					"reachable": true,
					"protocol": "https",
					"redirection_details": {
						"statusCode": 301,
						"message": "Moved Permanently",
						"redirect": true,
						"redirectUrl": "https://www.scout24.com/en/Home.aspx"
					}
				}
			}]
		},
		"external": {
			"total": 5,
			"details": [{
				"info": {
					"url": "https://www.facebook.com/Scout24/",
					"reachable": true,
					"protocol": "https",
					"redirection_details": {
						"statusCode": 200,
						"message": "OK",
						"redirect": false,
						"redirectUrl": null
					}
				}
			}]
		}
	}
}
~~~~

### Assumptions
1. Since I was not very clear about "Consider the effect of redirection and report accordingly", the API does not follow the redirection. It only checks about the redirection details and report them.
2. As the fetching details about links takes time, the `/api/v1/crawler` triggers an `async` service.

## Future Improvements

1. We can make the `/api/v1/crawler` to take the multiple urls(delimited values) as request parameter. Split the input based on the delimiter and run the crawling in parallel.
2. For now the fetching details about the links present on the page is sequential. We can make it to work in parallel. However, it is important to consider that since most of the links are internal to the page, make them parallel and hitting the server with lot of requests simultaneous is not a good idea. The server may block the request as they are coming from same origin. 
