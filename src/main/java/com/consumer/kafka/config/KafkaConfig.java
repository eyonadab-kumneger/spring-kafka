package com.consumer.kafka.config;
import com.consumer.kafka.model.Book;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {


//    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;


    //Producer Config
//    @Bean
//    public ProducerFactory<String, Object> producerFactory() {
//        Map<String, Object> config = new HashMap<>();
//        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        return new DefaultKafkaProducerFactory<>(config);
//    }
//
//    @Bean
//    public KafkaTemplate<String, Object> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//    public Map<String, Object> consumerConfig() {
//
//        HashMap<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
//        props.put(SaslConfigs.SASL_MECHANISM, "SCRAM-SHA-512");
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earlissest");
//        return props;
//    }
//
//    @Bean
//    ConsumerFactory<String, Object> consumerFactory() {
//        ErrorHandlingDeserializer<KafkaStreams> errorHandlingDeserializer
//                = new ErrorHandlingDeserializer<>(new JsonDeserializer<>(KafkaStreams.class));
//        return new DefaultKafkaConsumerFactory<>(consumerConfig());
//    }
//    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> factory(ConsumerFactory<String, Object> consumerFactory) {
//
//        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory);
//        return factory;
//    }


    //Consumer Config
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Book> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Book> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Book> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(
                config,
                new StringDeserializer(),
                new JsonDeserializer<>(Book.class)
        );
    }

}
