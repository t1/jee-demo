# Demo Project

contains:
- frontend: would be a Backend-For-Frontend to gateway frontend requests to the domain backends
- backend: domain/business logic plus persistence layer


## Docker

Start docker-compose:

`docker-compose up --build`

Magic command to start only the backends and db and remove everything after the shutdown (note as of docker-compose 3.8: for starting it would be sufficient to specify only `frontend`, but when stopping, the other images are not stopped):

`docker-compose up --build frontend backend db && docker system prune --force && docker ps -a`

jdbc to postgres db
jdbc:postgresql://127.0.0.1:5432/orders


## WildFly Admin Console

`frontend`: http://localhost:9990/console/index.html

`backend`: http://localhost:9991/console/index.html

 user admin 
 password Admin#007


## Test Calls

`frontend`: http://localhost:8080/frontend/orders/1

`backend`: http://localhost:8081/backend/orders/1

The `frontend` uses MP Rest Client to access the `backend`.


## Health and Config

Spec:
  Health: https://download.eclipse.org/microprofile/microprofile-health-2.2/microprofile-health-spec.html
  Config: https://download.eclipse.org/microprofile/microprofile-config-1.4/microprofile-config-spec.html



all: http://localhost:9990/health
ready: http://localhost:9990/health/ready
live: http://localhost:9990/health/live

Contains `stage` from system property and `version` from Maven resource filtering MP Config.


## Metrics

Spec: https://download.eclipse.org/microprofile/microprofile-metrics-2.3.2/microprofile-metrics-spec-2.3.2.html


Prometheus-UI runs on http://localhost:9090/graph

Technical metrics, e.g. `base_cpu_processCpuLoad` or `application_com_example_frontend_boundary_OrderBoundary_OrderBoundary_one_min_rate_per_second`

A business metric, e.g. `application_com_example_backend_boundary_OrderBoundary_get_order_total` from the backend OrderBoundary.

Note: the `@Timed` annotation should be on the `@Boundary` stereotype, but there's a bug in WildFly 20.0.1, so it's currently directly on the boundaries.


## Tracing

Spec: https://download.eclipse.org/microprofile/microprofile-opentracing-1.3.3/microprofile-opentracing-spec-1.3.3.html


https://medium.com/jaegertracing/jaeger-elasticsearch-and-kibana-7ecb846137b6

Jaeger GUI: http://localhost:16686/search

Kibana: http://localhost:5601


$ docker run --rm -it --name=elasticsearch -e "ES_JAVA_OPTS=-Xms2g -Xmx2g" -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e "xpack.security.enabled=false" docker.elastic.co/elasticsearch/elasticsearch:5.6.10

$ docker run --rm -it --link=elasticsearch --name=kibana -p 5601:5601 docker.elastic.co/kibana/kibana:5.6.10

$ docker run --rm -it --link=elasticsearch --name=jaeger -e SPAN_STORAGE_TYPE=elasticsearch -e ES_SERVER_URLS=http://elasticsearch:9200 -e ES_TAGS_AS_FIELDS_ALL=true -p 16686:16686 jaegertracing/all-in-one:1.7

$ docker run --rm -it --link=jaeger -p 8080-8083:8080-8083 jaegertracing/example-hotrod:1.7 all --jaeger-agent.host-port=jaeger:6831


## Fault Tolerance

Spec: https://download.eclipse.org/microprofile/microprofile-fault-tolerance-2.1.1/microprofile-fault-tolerance-spec.html


http://localhost:8080/frontend/timeout

http://localhost:8080/frontend/timeoutRetry

http://localhost:8080/frontend/timeoutFallback

http://localhost:8080/frontend/circuitBreak

Prometheus: `application_ft_com_example_frontend_boundary_OrderBoundary_breakCircuit_circuitbreaker_callsFailed_total`
