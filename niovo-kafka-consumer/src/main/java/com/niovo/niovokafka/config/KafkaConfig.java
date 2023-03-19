package com.niovo.niovokafka.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

//    @Value(value = "${kafka.bootstrapServers:localhost:9092}")
//    private String bootstrapServers;
//
//    @Bean
//    public KafkaAdmin kafkaAdmin() {
//        Map<String, Object> configs = new HashMap<>();
//        // Depending on you Kafka Cluster setup you need to configure
//        // additional properties!
//        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        return new KafkaAdmin(configs);
//    }

//    @Bean
//    public NewTopic niovoTaskTopic(){
//        return TopicBuilder.name(AppConstants.TOPIC_NAME_TASK)
//                .build();
//    }
//    @Bean
//    public NewTopic niovoUserTopic(){
//        return TopicBuilder.name(AppConstants.TOPIC_NAME_USER)
//                .build();
//    }
//    @Bean
//    public NewTopic niovoTopic() {
//        return TopicBuilder.name("niovo_topic")
//                .partitions(6)
//                .replicas(1)
//                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "gzip")
//                .build();
//    }
}
