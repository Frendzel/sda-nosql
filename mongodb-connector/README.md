*BEFORE:*

Import data
```
mongoimport --db test --collection grades --drop --file grades.json
```
Creating admin user
```
use admin
db.createUser({user: "root",pwd: "password",roles: ["userAdminAnyDatabase", "dbAdminAnyDatabase", "readWriteAnyDatabase"],})
```
Creating test user
```
use test
db.createUser(
  {
    user: "test",
    pwd: "test123",
    roles: [ { role: "readWrite", db: "test" }]
  }
)
```

*TASKS:*

* ARCHITECTURE

. Create connection Tool to connect to MongoDB through JAVA
. Extract connection properties to separated file
. Prepare configuration class responsible for reading properties (can be singleton)
. Turn on log4j logging
. Try to connect to DB (remember about turning on mongod!)
* MONGODB CRUD
. INSERT COLLECTION
https://www.mkyong.com/mongodb/java-mongodb-insert-a-document/[https://www.mkyong.com/mongodb/java-mongodb-insert-a-document/]
. SHOW DOCUMENTS NUMBER INSIDE COLLECTION
. STUDENTS ID &gt; 100
. STUDENT GRADES WHERE ID &gt; 100 AND GRADE TYPE = EXAM
. STUDENT IDS WITH AVG
. SORT STUDENT IDS
. INCREMENT ALL EXAM GRADES IF THEY ARE IN RANGE &lt;10,20&gt;
https://docs.mongodb.com/manual/reference/operator/update/max/#up._S_max[https://docs.mongodb.com/manual/reference/operator/update/max/#up._S_max]

*IMPORTANT:*

* https://docs.mongodb.com/manual/crud/[https://docs.mongodb.com/manual/crud/]
* https://docs.mongodb.com/manual/tutorial/insert-documents/[https://docs.mongodb.com/manual/tutorial/insert-documents/]
* https://docs.mongodb.com/manual/tutorial/query-documents/[https://docs.mongodb.com/manual/tutorial/query-documents/]
* https://docs.mongodb.com/manual/tutorial/update-documents/[https://docs.mongodb.com/manual/tutorial/update-documents/]
* https://docs.mongodb.com/manual/tutorial/remove-documents/[https://docs.mongodb.com/manual/tutorial/remove-documents/]
* https://docs.mongodb.com/manual/aggregation/[https://docs.mongodb.com/manual/aggregation/]
* Embedded Mongo DB: https://github.com/flapdoodle-oss/de.flapdoodle.embed.mongo
* JSON Validator https://jsonlint.com/

*EXTRA*

* install docker on your OS
* add testcontainers dependency
* try to run the junit test with turned on testcontainers (for example classRule)
* turn on mongodb as a generic container
* try to connect
* try to insert collection

*HOWTO*

*How to manually generate maven project?*

```
mvn archetype:generate -DgroupId=pl.sda -DartifactId=mongodb-connector -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

```