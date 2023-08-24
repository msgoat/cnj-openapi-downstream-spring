# cnj-openapi-downstream-spring

Cloud native Java application based on Spring Boot acting as downstream service consumed by the other backends.

## Status

![Build status](https://codebuild.eu-west-1.amazonaws.com/badges?uuid=eyJlbmNyeXB0ZWREYXRhIjoiQTNvdUVXSEQ3ZytTZEFFYVJxTFFXMGdQRmo2L3pnckt3bTFQY2k2elZKS0ZSYWhJVlVkUFBmeTNjYVAvUTZ5ckpUQ2Z0cG5aMms2OUFEa0h2Sit5SDRFPSIsIml2UGFyYW1ldGVyU3BlYyI6Ind0L1JKaVNyQlljaDRIem4iLCJtYXRlcmlhbFNldFNlcmlhbCI6MX0%3D&branch=main)

## Release Information

A changelog can be found in [changelog.md](changelog.md).

## Docker Pull Command

`docker pull docker.cloudtrain.aws.msgoat.eu/cloudtrain/cnj-openapi-downstream-spring`

## Synopsis

### Static vs. generated OpenAPI files

In this showcase, [Springdoc](https://springdoc.org/#Introduction) is used to demonstrate the `Code First` approach
of documenting REST API according to the OpenAPI standard: the actual OpenAPI specification file is generated from
annotated application code. The generated OpenAPI specification file is served by Springdoc via `/v3/api-docs` in JSON format
and via `/v3/api-doc.yaml` in YAML format.

To demonstrate the `API First` or `Contract First` approach as well, a static OpenAPI specification files has been 
added (see [openapi.yml](src/main/resources/openapi.yml)) to the application code. This static OpenAPI specification file is served
by a dedicated [StaticOpenApiSpecController](src/main/java/group/msg/at/cloud/cloudtrain/adapter/rest/StaticOpenApiSpecController.java)
since Springdoc does not support static OpenAPI files or fragments.

A Swagger UI for the exposed REST API is available as well via `/swagger-ui/index.html`.

## HOW-TO build this application locally

If all prerequisites are met, just run the following Maven command in the project folder:

```shell 
mvn clean verify -P pre-commit-stage
```

Build results: a Docker image containing the showcase application.

## HOW-TO start and stop this showcase locally

In order to run the whole showcase locally, just run the following docker commands in the project folder:

```shell 
docker compose up -d
docker compose logs -f 
```

Press `Ctlr+c` to stop tailing the container logs and run the following docker command to stop the showcase:

```shell 
docker compose down
```

## HOW-TO demo this showcase

The showcase application will be accessible:
* locally via `http://localhost:38080`
* remotely via `https://train2023-dev.k8s.cloudtrain.aws.msgoat.eu/cloudtrain/cnj-downstream-spring` (if the training cluster is up and running)

The OpenAPI specification of the exposed REST API is available at URI `/openapi`.
A Swagger UI of the exposed REST API is available at URI `/swagger-ui/index.html`.