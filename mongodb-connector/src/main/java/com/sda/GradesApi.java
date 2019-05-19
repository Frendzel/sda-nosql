package com.sda;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Map;

public interface GradesApi {

    FindIterable<Document> find();

    FindIterable<Document> findBy(Bson param);

    void insert(Map<String, Object> document);


}
