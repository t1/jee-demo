Demo project for distributed tracing

contains:
- docker-compose.yml
- app-backend 
- domain application


## Docker images

Start docker-compose:

`docker-compose up --build`

Magic command to start only the backends and db and remove everything after shutdown (note as of docker-compose 3.8: for starting it would be sufficient to specify only `app-backend`, but when stopping, the other images are not stopped):

`docker-compose up --build app-backend domain-backend db && docker system prune --force && docker ps -a`

jdbc to postgres db
jdbc:postgresql://127.0.0.1:5432/orders

## Admin login for wildfly 20
 user admin 
 password Admin#007

## Jaeger and ELK:
https://medium.com/jaegertracing/jaeger-elasticsearch-and-kibana-7ecb846137b6
Jaeger GUI: http://localhost:16686/search
Kibana: http://localhost:5601
Demo: http://localhost:8080/


$ docker run --rm -it --name=elasticsearch -e "ES_JAVA_OPTS=-Xms2g -Xmx2g" -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e "xpack.security.enabled=false" docker.elastic.co/elasticsearch/elasticsearch:5.6.10

$ docker run --rm -it --link=elasticsearch --name=kibana -p 5601:5601 docker.elastic.co/kibana/kibana:5.6.10

$ docker run --rm -it --link=elasticsearch --name=jaeger -e SPAN_STORAGE_TYPE=elasticsearch -e ES_SERVER_URLS=http://elasticsearch:9200 -e ES_TAGS_AS_FIELDS_ALL=true -p 16686:16686 jaegertracing/all-in-one:1.7

$ docker run --rm -it --link=jaeger -p 8080-8083:8080-8083 jaegertracing/example-hotrod:1.7 all --jaeger-agent.host-port=jaeger:6831


## Metrics

Prometheus-UI runs on http://localhost:9090/graph

Technical metric are `application_com_example_orderdomain_boundary_OrderBoundary_getOrder_one_min_rate_per_second` or `vendor_memoryPool_G1_Old_Gen_usage_bytes`

A business metric is `application_com_example_orderdomain_boundary_OrderBoundary_get_order_total`

## test calls
http://localhost:8080/app-backend/orders/1

http://localhost:8080/app-backend/timeout
http://localhost:8080/app-backend/timeoutRetry
http://localhost:8080/app-backend/timeoutFallback
http://localhost:8080/app-backend/circuitBreak
