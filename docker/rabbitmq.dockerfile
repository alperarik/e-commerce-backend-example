FROM rabbitmq:3-management-alpine
#If the --offline flag is specified, the tool will not contact any nodes 
#and instead will modify the file containing the list of enabled plugins 
#(appropriately named enabled_plugins) directly. This option is often optimal for node provisioning automation.

# rabbitmq_mqtt rabbitmq_federation_management 
RUN rabbitmq-plugins enable --offline rabbitmq_management

ADD rabbitmq.config /etc/rabbitmq/
ADD definitions.json /etc/rabbitmq/