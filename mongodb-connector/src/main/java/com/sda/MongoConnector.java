package com.sda;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;

import static com.mongodb.MongoClientOptions.builder;
import static com.mongodb.MongoCredential.createCredential;
import static com.mongodb.client.model.Filters.lt;

//TODO create factory which will produce 2 connectors (local/remote)
class MongoConnector {
    private PropertyLoader propertyLoader = new PropertyLoader();

    MongoDatabase connect() {
        propertyLoader.init();
        ServerAddress serverAddress = new ServerAddress(propertyLoader.getAddress());
        MongoCredential mongoCredential =
                createCredential(propertyLoader.getUser(),
                        propertyLoader.getDB(),
                        propertyLoader.getPassword().toCharArray());

        MongoClient mongoClient = new MongoClient(serverAddress, mongoCredential, builder().build());
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

    public FindIterable<Document> findStudentsWithClause(Bson bson) {
        MongoDatabase connect = connect();
        MongoCollection<Document> grades = connect.getCollection("grades");
        return grades.find(bson);
    }
}






