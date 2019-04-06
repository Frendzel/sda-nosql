package com.sda;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;

import static com.mongodb.client.model.Filters.*;
import static org.junit.Assert.assertEquals;

public class MongoConnectorTest {

    private PropertyLoader propertyLoader;


    @Before
    public void setUp() {
        propertyLoader = new PropertyLoader();
        propertyLoader.init();
    }

    @Test
    public void connect() {
        assertEquals("test", propertyLoader.getUser());
    }

    @Test
    public void mongoConnector() {
        //given
        MongoConnector mongoConnector = new MongoConnector();
        //when
        MongoDatabase db = mongoConnector.connect();
        //then
        MongoCollection<Document> grades = db.getCollection("grades");
        assertEquals(grades.estimatedDocumentCount(), 804);
    }

    @Test
    public void getSize() {
        //given
        MongoConnector mongoConnector = new MongoConnector();
        //when
        Long result = mongoConnector.getSize("grades");
        //then
        assertEquals(result.longValue(), 804);
    }

    @Test
    public void showAllRecords() {
        //given
        MongoConnector mongoConnector = new MongoConnector();
        //when
        mongoConnector.showAllRecords("grades");
        //no then 😞
    }

    //findStudentsWhereIdLt10
    @Test
    public void findStudentWhereIdLt10() {
        //given
        MongoConnector mongoConnector = new MongoConnector();
        //when
        FindIterable<Document> studentsWhereIdLt10 = mongoConnector.findStudentsWhereIdLt10();
        //then
        for (Document document : studentsWhereIdLt10) {
            System.out.println(document);
        }

    }
    // tylko prace domowe
    // db.grades.find({ type: { $eq: "homework" }})

    @Test
    public void findWithSuitableClause() {
        //given
        MongoConnector mongoConnector = new MongoConnector();
        Bson bson = eq("type", "homework");
        //when
        FindIterable<Document> result = mongoConnector.findStudentsWithClause(bson);
        //then
        for (Document document : result) {
            System.out.println(document);
        }
    }

    // nie quiz i nie exam
    // > db.grades.find({ type: { $nin: ["quiz", "exam"] }})
    @Test
    public void findWithSuitableClause2() {
        //given
        MongoConnector mongoConnector = new MongoConnector();
        Bson bson = nin("type", "quiz", "exam");
        //when
        FindIterable<Document> result = mongoConnector.findStudentsWithClause(bson);
        //then
        for (Document document : result) {
            System.out.println(document);
        }
    }

    // student_id <10 && type = homework
    // db.grades.find({   $and: [{student_id: {$lt: 10}},{type: {$eq: "homework"}}]})
    @Test
    public void findWithSuitableClause3() {
        //given
        MongoConnector mongoConnector = new MongoConnector();
        Bson bson = and(
                lt("student_id", "10"),
                eq("type", "homework")
        );
        //when
        FindIterable<Document> result = mongoConnector.findStudentsWithClause(bson);
        //then
        for (Document document : result) {
            System.out.println(document);
        }
    }

    // student_id in 1,2,3 and type exam or quiz
    // db.grades.find({ $and: [{student_id: {$in: [1,2,3]}},
    // {$or: [{type: {$eq: "exam"}},{type: {$eq: "quiz" }}]}]})
    @Test
    public void findWithSuitableClause4() {
        //given
        MongoConnector mongoConnector = new MongoConnector();
        Bson bson = and(
                in("student_id", 1, 2, 3),
                or(
                        eq("type", "exam"),
                        eq("type", "quiz")
                )
        );
        //when
        FindIterable<Document> result = mongoConnector.findStudentsWithClause(bson);
        //then
        for (Document document : result) {
            System.out.println(document);
        }
    }
}