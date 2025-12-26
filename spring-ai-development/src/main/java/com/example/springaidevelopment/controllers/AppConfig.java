package com.example.springaidevelopment.controllers;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
//import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.ai.vectorstore.redis.RedisVectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
import redis.clients.jedis.JedisPooled;

@Configuration
public class AppConfig {
    /**
     * This configuration is with Radis Vector Database.
     * Docker command: docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest
     **/
    @Bean
    public JedisPooled jedisPooled(){
        return new JedisPooled("localhost", 6379);
    }

    @Bean
    public VectorStore vectorStore(JedisPooled jedisPooled,
                                   @Qualifier("openAiEmbeddingModel") EmbeddingModel embeddingModel){
        return RedisVectorStore.builder(jedisPooled, embeddingModel)
                .indexName("product-index")
                .prefix("product")
                .initializeSchema(true)
                .build();
    }


    /**
     * This represents the first case with simple vector store that is inbuilt database. Docker is not required.
     **/
//    @Bean
//    public VectorStore vectorStore(@Qualifier("openAiEmbeddingModel") EmbeddingModel embeddingModel) {
//        return SimpleVectorStore.builder(embeddingModel).build();
//    }

    /**
     * This represents the PGVector database that is relational and will write the vector representation of
     * all the embeddings in the provided text for semantic search.
     * Docker Command: docker-compose up -d
     **/
//    @Bean
//    public VectorStore vectorStore(JdbcTemplate jdbcTemplate, @Qualifier("openAiEmbeddingModel") EmbeddingModel embeddingModel) {
//        return PgVectorStore
//                .builder(jdbcTemplate, embeddingModel)
//                .dimensions(1536)
//                .distanceType(PgVectorStore.PgDistanceType.COSINE_DISTANCE)
//                .indexType(PgVectorStore.PgIndexType.HNSW)
//                .build();
//    }
}
