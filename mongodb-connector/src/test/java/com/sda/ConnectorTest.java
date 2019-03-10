package com.sda;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BsonField;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;

public class ConnectorTest {

    private Connector connector;
    private MongoDatabase db;

    @Before
    public void init() throws IOException {
        this.connector = new Connector();
        this.db = connector.connect();
    }

    @Test
    public void showGrades() {
        MongoCollection<Document> grades = db
                .getCollection("grades");
        System.out.println(grades.count());
        FindIterable<Document> documents = grades.find();
        for (Document document : documents) {
            System.out.println(document);
        }
    }

    @Test
    public void showGradesWhenStudentIdGt190() {
        MongoCollection<Document> grades = db
                .getCollection("grades");
        Bson $gt = new Document("$gt", 190);
        Bson filter = new Document("student_id", $gt);
        FindIterable<Document> documents = grades
                .find(filter);
        for (Document document : documents) {
//            System.out.println(document);
        }
        Bson filter2 = gt("student_id", 100);
        FindIterable<Document> documents2 = grades
                .find(filter2)
                .sort(new Document("score", -1))
                .limit(5);
        for (Document document : documents2) {
            System.out.println(document);
        }

    }

    //STUDENT GRADES WHERE ID &gt; 100 AND GRADE TYPE = EXAM
    @Test
    public void gradesGt100AndTypeEqExam() {
        //given
        MongoCollection<Document> grades = db
                .getCollection("grades");
        Bson $gt = new Document("$gt", 100);
        Bson st_id = new Document("student_id", $gt);
        Bson type = new Document("type", "exam");
        Bson and = and(st_id, type);
        //when
        FindIterable<Document> documents2 = grades
                .find(and);
        for (Document document : documents2) {
            System.out.println(document);
        }
        //given2
        Bson and1 = and(
                gt("student_id", 100),
                eq("type", "exam")
        );

    }

    /**
     * db.grades.aggregate
     * ([{$group :
     * {"_id" : "$student_id",
     * average: {$avg : "$score"}}}])
     */
    @Test
    public void calculateStudentsScoreAvg() {
        //given
        MongoCollection<Document> grades = db
                .getCollection("grades");
        Bson match = match(new Document());
        Bson group = group("$student_id", new BsonField("avg",
                new Document("$avg", "$score")));
        ArrayList<Bson> bsons = new ArrayList<Bson>();
        bsons.add(match);
        bsons.add(group);
        AggregateIterable<Document> aggregate = grades.aggregate(bsons);
        for (Document anAggregate : aggregate) {
            System.out.println(anAggregate);
        }

    }


    @Test
    public void connect() throws IOException {
        //given
        Connector connector = new Connector();
        //when
        MongoDatabase db = connector.connect();
        //then
        //ASERCJA!!!
        System.out.println(db);
    }
}