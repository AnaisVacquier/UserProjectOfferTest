package com.atos.offer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.atos.offer.database.MongoDBConnection;
import com.mongodb.MongoClient;

@SpringBootApplication
public class OfferApplication {

	public static void main(String[] args) {
		SpringApplication.run(OfferApplication.class, args);

		// connection to the Mongo Database
		MongoDBConnection mongoDBConnection = MongoDBConnection.getInstance();
		MongoClient mongoClient = mongoDBConnection.getMongoClient();
	}
}