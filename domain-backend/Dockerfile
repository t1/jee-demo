FROM jboss/wildfly:20.0.1.Final

COPY jboss-setup.cli /opt/jboss/jboss-setup.cli
RUN ${JBOSS_HOME}/bin/jboss-cli.sh --file=jboss-setup.cli
RUN rm -r \
    /opt/jboss/jboss-setup.cli \
    ${JBOSS_HOME}/standalone/configuration/standalone_xml_history

# add modules
COPY modules $JBOSS_HOME/modules

# COPY target/app-backend.war ${JBOSS_HOME}/standalone/deployments/
