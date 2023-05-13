package com.jj.springbootinpractice;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class SpringBootWithMongoDB {
//    private static final String CONNECTION_STRING = "mongodb://%s:%d";
//
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @Test
//    public void givenObjectAvailableWhenSaveToCollectionTheExpectValue() {
//        // given
//        DBObject object = BasicDBObjectBuilder.start().add("Manning", "Spring Boot In Practice").get();
//        // when
//        mongoTemplate.save(object, "collection");
//        // then
//        assertThat(mongoTemplate.findAll(DBObject.class, "collection"))
//                .extracting("Manning").containsOnly("Spring Boot In Practice");
//    }
}
