package uk.me.pilgrim.eventstore;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

public class EventStoreExtension implements BeforeAllCallback, AfterAllCallback {
    private GenericContainer<?> eventStore;

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        eventStore = new GenericContainer<>(DockerImageName.parse("eventstore/eventstore:20.10.0-bionic"))
                .withExposedPorts(2113)
                .withCommand("--insecure")
                .waitingFor(Wait.forHealthcheck());

        eventStore.start();
        System.setProperty("eventstore.connectionString", "esdb://" + eventStore.getHost() + ":" + eventStore.getFirstMappedPort() + "?tls=false");
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        eventStore.stop();
    }
}
