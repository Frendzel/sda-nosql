package com.sda;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.conversions.Bson;

public interface GradesQuery {

    FindIterable<Document> find();

    FindIterable<Document> findBy(Bson param);
}
