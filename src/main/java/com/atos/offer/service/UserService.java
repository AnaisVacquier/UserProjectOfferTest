package com.atos.offer.service;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.atos.offer.database.MongoDBConnection;
import com.atos.offer.model.User;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

@Service
public class UserService {

	/**
	 * goes to the userCollection into the Database "OfferDB" and creates it if
	 * doesn't already exists
	 * 
	 * @return the collection "users"
	 */
	private static MongoCollection<Document> getUserCollection() {
		MongoDBConnection mongoDBConnection = MongoDBConnection.getInstance();
		MongoClient mongoClient = mongoDBConnection.getMongoClient();
		MongoDatabase offerDB = mongoClient.getDatabase("offerDB");
		return offerDB.getCollection("users");
	}

	/**
	 * Function to register a user
	 * 
	 * @param user
	 */
	public static void registerUser(User user) {

		MongoCollection<Document> userCollection = getUserCollection();

		Document address = new Document("street", user.getAddress().getStreet())
				.append("zipCode", user.getAddress().getZipCode()).append("city", user.getAddress().getCity())
				.append("country", user.getAddress().getCountry());

		Document document = new Document();
		document.append("firstName", user.getFirstName());
		document.append("lastName", user.getLastName());
		document.append("age", user.getAge());
		document.append("email", user.getEmail());
		document.append("phoneNumber", user.getPhoneNumber());
		document.append("address", address);

		userCollection.insertOne(document);
	}

	/**
	 * Function to search for a user into the mongoDB
	 * 
	 * @param id the mongoDB id
	 * @return
	 * @return
	 */
	public static String getUser(String id) {

		String userToReturn = "";
		MongoCollection<Document> userCollection = getUserCollection();

		try {
			ObjectId objectId = new ObjectId(id);

			Bson bsonFilter = Filters.eq("_id", objectId);
			FindIterable<Document> findUser = userCollection.find(bsonFilter);
			ArrayList<Document> docs = new ArrayList<Document>();

			findUser.into(docs);

			for (Document doc : docs) {

				userToReturn = doc.toJson();
			}

			return userToReturn;

		} catch (Exception e) {
			e.getMessage();
		}

		return userToReturn;

	}

}
