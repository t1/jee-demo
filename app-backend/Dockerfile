FROM jboss/wildfly:20.0.1.Final

COPY target/app-backend.war ${JBOSS_HOME}/standalone/deployments/

COPY jboss-setup.cli /opt/jboss/jboss-setup.cli
RUN ${JBOSS_HOME}/bin/jboss-cli.sh --file=jboss-setup.cli
RUN rm -r /opt/jboss/jboss-setup.cli
