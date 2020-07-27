Demo project for distributed tracing

contains:
- docker configuration docker-compose.yml
    -- wildfly 20
    -- postgres
    -- jäger monitoring container
- app application 
- domain application



Jaeger and ELK:
================================================================================
https://medium.com/jaegertracing/jaeger-elasticsearch-and-kibana-7ecb846137b6
Jaeger GUI: http://localhost:16686/search
Kibana: http://localhost:5601
Demo: http://localhost:8080/


$ docker run --rm -it --name=elasticsearch -e "ES_JAVA_OPTS=-Xms2g -Xmx2g" -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e "xpack.security.enabled=false" docker.elastic.co/elasticsearch/elasticsearch:5.6.10

$ docker run --rm -it --link=elasticsearch --name=kibana -p 5601:5601 docker.elastic.co/kibana/kibana:5.6.10

$ docker run --rm -it --link=elasticsearch --name=jaeger -e SPAN_STORAGE_TYPE=elasticsearch -e ES_SERVER_URLS=http://elasticsearch:9200 -e ES_TAGS_AS_FIELDS_ALL=true -p 16686:16686 jaegertracing/all-in-one:1.7

$ docker run --rm -it --link=jaeger -p 8080-8083:8080-8083 jaegertracing/example-hotrod:1.7 all --jaeger-agent.host-port=jaeger:6831
