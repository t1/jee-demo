FROM jboss/wildfly:20.0.1.Final

COPY modules $JBOSS_HOME/modules
COPY jboss-setup.cli /opt/jboss/jboss-setup.cli
RUN ${JBOSS_HOME}/bin/jboss-cli.sh --file=jboss-setup.cli
RUN rm -r \
    /opt/jboss/jboss-setup.cli \
    ${JBOSS_HOME}/standalone/configuration/standalone_xml_history

# add admin login
RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#007 --silent

COPY target/domain-backend.war ${JBOSS_HOME}/standalone/deployments/

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
