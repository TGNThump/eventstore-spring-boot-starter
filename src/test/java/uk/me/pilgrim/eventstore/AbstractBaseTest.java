package uk.me.pilgrim.eventstore;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(EventStoreExtension.class)
@SpringBootTest
public class AbstractBaseTest {

    @SpringBootConfiguration
    @EnableAutoConfiguration
    public static class Configuration{}
}
