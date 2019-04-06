package com.sda;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.MongoCredential.createCredential;
import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;
import static com.sda.PropertyLoader.getInstance;

//TODO create factory which will produce 2 connectors (local/remote)
class LocalMongoConnector implements MongoConnector {
    private PropertyLoader propertyLoader = getInstance();

    @Override
    public MongoDatabase connect() {
        propertyLoader.init();
        ServerAddress serverAddress = new ServerAddress(propertyLoader.getAddress());
        MongoCredential mongoCredential =
                createCredential(propertyLoader.getUser(),
                        propertyLoader.getDB(),
                        propertyLoader.getPassword().toCharArray());

        MongoClient mongoClient = new MongoClient(serverAddress, mongoCredential, MongoClientOptions.builder().build());
        return mongoClient.getDatabase(propertyLoader.getDB());
    }

    public Long getSize(String collectionName) {
        MongoDatabase db = connect();
        MongoCollection<Document> collection = db.getCollection(collectionName);
        return collection.estimatedDocumentCount();
    }

    public FindIterable<Document> getAllRecords(String collectionName) {
        MongoDatabase db = connect();
        MongoCollection<Document> collection = db.getCollection(collectionName);
        return collection.find();
    }

    public void showAllRecords(String collectionName) {
        FindIterable<Document> collection = getAllRecords(collectionName);
        for (Document d : collection) {
            System.out.println(d.toJson(JsonWriterSettings
                    .builder()
                    .indent(true)
                    .build()));
        }
    }

    /**
     * db.grades.find({ student_id: { $lt: 10 }})
     */

    public FindIterable<Document> findStudentsWhereIdLt10() {
        MongoDatabase connect = connect();
        MongoCollection<Document> grades = connect.getCollection("grades");
        Bson filter = new Document("student_id", new Document("$lt", 10));
        return grades.find(lt("student_id", 10));
    }

    //db.grades.find({ $and: [{student_id: {$in: [1,2,3]}},
// {$or: [{type: {$eq: "exam"}},
// {type: {$eq: "quiz" }}]}]}).sort({score: -1}).limit(1)
    public FindIterable<Document> findStudentsWithClause(Bson bson) {
        MongoDatabase connect = connect();
        MongoCollection<Document> grades = connect.getCollection("grades");
        return grades.find(bson).sort(eq("score", 1)).limit(1);
    }

    //db.grades.aggregate([{$match: {}},{$group: {_id: "$student_id",
    // sum:{$sum: "$score"}}},{$sort: {sum: -1}}, {$limit: 1}])
    public AggregateIterable<Document> calculateSumForEachStudent() {
        MongoDatabase connect = connect();
        MongoCollection<Document> grades = connect.getCollection("grades");
        List<Bson> params = new ArrayList<>();
        params.add(match(new Document()));
        params.add(group("$student_id", sum("sum", "$score")));
        params.add(sort(eq("sum", -1)));
        params.add(limit(1));
        return grades.aggregate(params);
    }
}






