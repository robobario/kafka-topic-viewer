# kafka-topic-viewer

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

Start 
Run up a kafka broker on localhost:9092 using the apache kafka binary distribution

Push some messages to a topic named `ktable`:
```
./bin/kafka-console-producer.sh --topic ktable --property "parse.key=true" --property "key.separator=:" --bootstrap-server localhost:9092
>a:b
>a:c
>d:hello
>d:world
```

You can then run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

Then you can request `http://localhost:8082/test/a` to get all records for a key. The
app is configured to not commit offsets and reset to earliest offset so it should consume
the entirety of the topic every time it starts.

The StateStore is naively threadsafe by synchronizing it's methods, there's bound to be a
nicer way to do this with Quarkus. Also it has no concept of expiry and will grow while the
process lives.

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8082/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/kafka-topic-viewer-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- SmallRye Reactive Messaging - Kafka Connector ([guide](https://quarkus.io/guides/kafka-reactive-getting-started)): Connect to Kafka with Reactive Messaging

## Provided Code

### Reactive Messaging codestart

Use SmallRye Reactive Messaging

[Related Apache Kafka guide section...](https://quarkus.io/guides/kafka-reactive-getting-started)

