# Intro

This is a Spring Boot microservice that represents catalog information in a retail store. It uses the Fabric8 Maven Plugin to deploy to OpenShift.

# Running on OpenShift

You'll need to have access to an OpenShift cluster and the `oc` command line interface.

1. Login to OpenShift and create a new project

```
oc login
oc new-project demo
```

1. This demo requires a PostgreSQL database. Create one by using the following command:

```
oc new-app -e POSTGRESQL_USER=catalog \
             -e POSTGRESQL_PASSWORD=mysecretpassword \
             -e POSTGRESQL_DATABASE=catalog \
             openshift/postgresql:latest \
             --name=catalog-database
```
> **NOTE:** If you change the username and password you also need to update `src/main/fabric8/credential-secret.yml`.

> **NOTE:** If you change the database or application name app you also need update `src/main/resources/application.properties` as well.

2. Use the Fabric8 Maven Plugin to launch the S2I process on the OpenShift Online machine & start the pod.

```
mvn clean fabric8:deploy -Popenshift -DskipTests
```

This will build and deploy the microservice along with a Postgres database.

3. To test the service using curl:

```
curl http://catalog-<project>.<domain>/api/catalog
```
For example:

```
% curl http://catalog-lab5.apps.127.0.0.1.nip.io/api/catalog
[{"itemId":"329299","name":"Red Fedora","desc":"Official Red Hat Fedora","price":34.99},{"itemId":"329199","name":"Forge Laptop Sticker","desc":"JBoss Community Forge Project Sticker","price":8.5},{"itemId":"165613","name":"Solid Performance Polo","desc":"Moisture-wicking, antimicrobial 100% polyester design wicks for life of garment. No-curl, rib-knit collar; special collar band maintains crisp fold; three-button placket with dyed-to-match buttons; hemmed sleeves; even bottom with side vents; Import. Embroidery. Red Pepper.","price":17.8},{"itemId":"165614","name":"Ogio Caliber Polo","desc":"Moisture-wicking 100% polyester. Rib-knit collar and cuffs; Ogio jacquard tape inside neck; bar-tacked three-button placket with Ogio dyed-to-match buttons; side vents; tagless; Ogio badge on left sleeve. Import. Embroidery. Black.","price":28.75},{"itemId":"165954","name":"16 oz. Vortex Tumbler","desc":"Double-wall insulated, BPA-free, acrylic cup. Push-on lid with thumb-slide closure; for hot and cold beverages. Holds 16 oz. Hand wash only. Imprint. Clear.","price":6.0},{"itemId":"444434","name":"Pebble Smart Watch","desc":"Smart glasses and smart watches are perhaps two of the most exciting developments in recent years.","price":24.0},{"itemId":"444435","name":"Oculus Rift","desc":"The world of gaming has also undergone some very unique and compelling tech advances in recent years. Virtual reality, the concept of complete immersion into a digital universe through a special headset, has been the white whale of gaming and digital technology ever since Geekstakes Oculus Rift GiveawayNintendo marketed its Virtual Boy gaming system in 1995.Lytro","price":106.0},{"itemId":"444436","name":"Lytro Camera","desc":"Consumers who want to up their photography game are looking at newfangled cameras like the Lytro Field camera, designed to take photos with infinite focus, so you can decide later exactly where you want the focus of each image to be.","price":44.3}]
```

