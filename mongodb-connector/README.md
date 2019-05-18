*BEFORE:*

Import data
```
mongoimport --db test --collection grades --drop --file grades.json
mongoimport --host <HOST WASZ> --ssl --username user --password user --authenticationDatabase admin --db test --collection grades --file grades.json
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

* Create connection Tool to connect to MongoDB through JAVA
* Extract connection properties to separated file
* Prepare configuration class responsible for reading properties (can be singleton)
* Turn on log4j logging
* Try to connect to DB (remember about turning on mongod!)

*IMPORTANT:*

* https://docs.mongodb.com/manual/crud
* https://docs.mongodb.com/manual/tutorial/insert-documents
* https://docs.mongodb.com/manual/tutorial/query-documents
* https://docs.mongodb.com/manual/tutorial/update-documents
* https://docs.mongodb.com/manual/tutorial/remove-documents
* https://docs.mongodb.com/manual/aggregation
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

*WORKSHOPS 1-2*

1. Use find method to extract cursor to all existing data.
2. Use find method with filter to extract specific data.
3. Use find method with projection and filter to extract specific data.
4. Use sort on above examples.
5. Use limit on above examples.
6. Use aggregate method to calculate grades avg.
7. Increment all exam grades by 1
https://docs.mongodb.com/manual/reference/operator/update/max/#up._S_max
8. Do the insert of random document.
9. Do the update of random document.
10. Do the replace of some document.
11. Delete single document.
13. Prepare random document generator and use it with strategy pattern (exam,workshop,quiz score).
14. Parametrize MongoConnector and PropertyLoader to be able to connect to mongolab and read remote connection properties file.
15. Insert collections taken from file <EXCEL> and mapped to object (Grades)
16. Implement custom CodecRegistry and map automatically Grade from collection to Java 
http://mongodb.github.io/mongo-java-driver/3.5/driver/getting-started/quick-start-pojo/
17. Use testcontainers in tests instead of mongo deamon and connect to him (only with working docker)
https://github.com/testcontainers/testcontainers-java/blob/master/core/src/test/java/org/testcontainers/junit/GenericContainerRuleTest.java
18. Perform a text search on the collection.
http://mongodb.github.io/mongo-java-driver/3.5/driver/tutorials/text-search/ 
19. http://api.icndb.com/jokes/random !!!