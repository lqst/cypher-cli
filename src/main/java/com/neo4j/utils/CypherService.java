package com.neo4j.utils;

import org.neo4j.driver.*;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CypherService {

    private String userName = "neo4j";
    private String password = "test1234";
    private String address = "neo4j://localhost:7687";
    private String database = "neo4j";
    private Driver driver = null;

    private Driver getDriver() {
        if ( driver == null) {
            this.driver = GraphDatabase.driver(address, AuthTokens.basic(userName, password));
        }
        return driver;
    }

    public int query(String[] args) {
        return query(args[0]);
    }

    public int query(String query) {
        try (Session session = getDriver().session(SessionConfig.builder().withDatabase(database).build())) {
            Result res = session.run(query);

            res.forEachRemaining(record ->
            {
                record.fields().forEach(field-> {
                    System.out.printf("Field: %s Value: %s%n", field.key(), field.value().toString());
                });
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

}
