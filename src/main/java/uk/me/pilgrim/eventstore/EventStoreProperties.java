package uk.me.pilgrim.eventstore;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("eventstore")
public class EventStoreProperties {
    String connectionString;
}
