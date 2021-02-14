package uk.me.pilgrim.eventstore;

import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.EventStoreDBConnectionString;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Log4j2
@Configuration
@Import(EventStoreProperties.class)
public class EventStoreAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(EventStoreDBClient.class)
    public EventStoreDBClient connection(EventStoreProperties eventStoreProperties){
        return EventStoreDBClient.create(EventStoreDBConnectionString.parseOrThrow(eventStoreProperties.getConnectionString()));
    }
}
