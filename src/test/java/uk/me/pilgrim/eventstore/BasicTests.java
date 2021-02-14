package uk.me.pilgrim.eventstore;


import com.eventstore.dbclient.*;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class BasicTests extends AbstractBaseTest {

    @Autowired
    EventStoreDBClient client;

    @Test
    public void basicEventWriteReadTest() throws ExecutionException, InterruptedException, IOException {
        TestEvent event = new TestEvent();
        event.setId(UUID.randomUUID().toString());
        event.setImportantData("I wrote my first event!");

        EventData eventData = EventData
                .builderAsJson("TestEvent", event)
                .build();

        client.appendToStream("some-stream", eventData).get();

        ReadStreamOptions options = ReadStreamOptions.get()
                .forwards()
                .fromStart();

        ReadResult result = client.readStream("some-stream", 10, options)
                .get();

        List<ResolvedEvent> events = result.getEvents();
        ResolvedEvent resolvedEvent = events.get(0);
        assertThat(resolvedEvent.getOriginalEvent().getEventDataAs(TestEvent.class)).isEqualTo(event);
    }

    @Data
    private static class TestEvent{
        String id;
        String importantData;
    }
}
