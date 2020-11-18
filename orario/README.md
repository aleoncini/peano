# Engine Module

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

The logic of this project is based on Optaplanner, a business optimizer framework developed by Red Hat.
For more information on Optaplanner please visit its website: https://www.optaplanner.org/

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging locally and running the application

The application can be packaged using `./mvnw package`.
It produces the `orario-<version>-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/orario-<version>-runner.jar`.

## build an image

The application can also be packaged into a dedicated container. Use podman to build the container image with the following command:

    podman build -t peano-orario .

The image is built using a multi-stage approach, the first step compiles and builds the Quarkus artifact, the second one insert the artifact into a compatible running environment that can be later used to run the application.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/orario-<version>-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.

## Deploy the ENGINE module on OCP
The deploy of this module on OCP is based on a *Dockerfile Strategy*, it points to a GIT repo containing the Dockerfile to build the container and run the application. To do that way execute the following steps:

1. Access the OCP cluster where you want to deploy the application and switch to the project.
2. From the developer console open the Topology view.
3. Click on **+Add** button.
4. Select "From Dockerfile" tab.
5. Enter the GIT url, the application name, choose Deployment as resource type, check Create Route and then click Create.

The platform will start to build the container and then the deployment process.
[Note:] The build process to create the container where you will run the app is based on a ubi8/openjdk-11 OpenJDK base image from Red Hat official Registry. To access the resource authentication is required. If you haven't done in advance it is required that you create a secret (using the admin console for the project) with a Service Account security token and that you set the secret to the deployment configuration. To add the secret to the Deployment configuration go to **Builds** view of the Developer Console, click on the backend build, and open the YAML tab. Edit the YAML to insert the reference to the secret for the pull strategy of the images. Something like:

    strategy:
        type: Docker
        dockerStrategy:
            pullSecret:
                name: redhat-registry
            dockerfilePath: Dockerfile

in the example the "redhat-registry" is the name of the secret that contains the token to access the Red Hat Registry.

There is one final step to make the backend module up and running: the Mongo DB service URL.
The Quarkus application running in the backend module needs to know the URL to access the mongo instances and expects to find it as an Environment Variable: *MONGOCONNSTRING*. The URL is in the format:

    mongodb://dbusername:dbuserpassword@servicename:port/databasename 

The service name can be verified from the developer console in the Topology view clicking on the pod icon of the Mongo database that open a lateral information panel. In the Resources tab of the lateral panel look at the Services paragraph.
For example the URL could be:

    mongodb://usr:pwd@mongodb:27017/orario

To apply this environment variable to the deployment configuration using the developer console click on backend configuration in the Build view, click on Environment tab, enter **MONGOCONNSTRING** in the *NAME* field and the URL discussed above in the *VALUE* field, then Save. 

## deploy the application locally using podman

Git clone the project locally, cd backend and type:

    podman build  -t peano-engine .

then you can run the app with the following command:

    podman run --rm --name engine -d -p 8080:8080 --env MONGOCONNSTRING=mongodb://<host_running-mongodb>:27017/orario peano-engine
