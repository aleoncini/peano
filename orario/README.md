# orario project

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
It produces the `orario-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/orario-1.0-SNAPSHOT-runner.jar`.

## build an image

The application can also be packaged into a dedicated container. Use podman to build the container image with the following command:

    podman build -t peano-orario .

The image is built using a multi-stage approach, the first step compiles and builds the Quarkus artifact, the second one insert the artifact into a compatible running environment that can be later used to run the application.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/orario-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.