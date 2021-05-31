package com.atos.offer.database;

import com.mongodb.MongoClient;

public class MongoDBConnection {
	
	private MongoClient mongoClient;
	private static MongoDBConnection INSTANCE = null;
	
	/**
	 * opens a new connection to a local mongo database
	 */
	private MongoDBConnection() {
		mongoClient = new MongoClient("localhost", 27017);
	}
	
	/**
	 * this function sets the mongoDB connection as a singleton
	 * @return the unique mongoDB connection instance
	 */
	public static MongoDBConnection getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new MongoDBConnection();
		}
		return INSTANCE ;
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}

}
