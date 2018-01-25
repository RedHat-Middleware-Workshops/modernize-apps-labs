# Intro

This is a WildFly Swarm microservice that represents inventory information in a retail store. It uses the Fabric8 Maven Plugin to deploy to OpenShift.

# Running on OpenShift

You'll need to have access to an OpenShift cluster and the `oc` command line interface.

1. Login to OpenShift and create a new project

```
oc login
oc new-project demo
```

1. This demo requires a PostgreSQL database. Create one by using the following command:

```
oc new-app -e POSTGRESQL_USER=inventory \
             -e POSTGRESQL_PASSWORD=mysecretpassword \
             -e POSTGRESQL_DATABASE=inventory \
             openshift/postgresql:latest \
             --name=inventory-database
```
> **NOTE:** If you change the username and password you also need to update `src/main/fabric8/credential-secret.yml`.

2. Use the Fabric8 Maven Plugin to launch the S2I process on the OpenShift cluster & start the pod.

```
mvn clean fabric8:deploy -Popenshift -DskipTests
```

This will build and deploy the microservice.

3. To test the service using `curl`:

```
curl http://inventory-<project>.<domain>/services/inventory/329299
```
For example:

```
% curl http://inventory-demo.apps.example.org/services/inventory/329299

```

