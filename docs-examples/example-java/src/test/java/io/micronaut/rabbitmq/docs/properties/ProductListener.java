package io.micronaut.rabbitmq.docs.properties;

// tag::imports[]
import io.micronaut.rabbitmq.annotation.Queue;
import io.micronaut.rabbitmq.annotation.RabbitListener;
import io.micronaut.rabbitmq.annotation.RabbitProperty;
import io.micronaut.context.annotation.Requires;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// end::imports[]

@Requires(property = "spec.name", value = "PropertiesSpec")
// tag::clazz[]
@RabbitListener
public class ProductListener {

    List<String> messageProperties = Collections.synchronizedList(new ArrayList<>());

    @Queue("product")
    @RabbitProperty(name = "x-priority", value = "10", type = Integer.class) // <1>
    public void receive(byte[] data,
                        @RabbitProperty("userId") String user,  // <2>
                        @Nullable @RabbitProperty String contentType,  // <3>
                        String appId) {  // <4>
        messageProperties.add(user + "|" + contentType + "|" + appId);
    }
}
// end::clazz[]
