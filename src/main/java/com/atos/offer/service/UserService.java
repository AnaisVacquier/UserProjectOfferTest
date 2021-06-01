package com.atos.offer.service;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.time.StopWatch;
import org.bson.Document;
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
	 * Function to check whether the user parameters are valid
	 * 
	 * @param user
	 * @return the violations found
	 */
	public static Set<ConstraintViolation<User>> checkValidators(User user) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		return constraintViolations;
	}

	/**
	 * Function to register a user
	 * 
	 * @param user
	 */
	public static String registerUser(User user) {
		StopWatch watch = new StopWatch();
		watch.start();

		MongoCollection<Document> userCollection = getUserCollection();

		Set<ConstraintViolation<User>> constraintViolations = checkValidators(user);
		String invalidatorMessage = "";

		if (constraintViolations.size() > 0) {
			invalidatorMessage = invalidatorMessage.concat("The following fields aren't correct : ");
			for (ConstraintViolation<User> contraintes : constraintViolations) {
				invalidatorMessage = invalidatorMessage
						.concat(contraintes.getPropertyPath() + " : " + contraintes.getMessage() + ". ");
			}
		} else {
			Document address = new Document("street", user.getAddress().getStreet())
					.append("zipCode", user.getAddress().getZipCode())
					.append("city", user.getAddress().getCity())
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

		watch.stop();
		System.out.println("registerUser | Time Elapsed: " + watch.getTime());
		return invalidatorMessage;

	}

	/**
	 * Function to search for a user into the mongoDB
	 * 
	 * @param id the mongoDB id
	 * @return
	 * @return
	 */
	public static String getUser(String id) {
		StopWatch watch = new StopWatch();
		watch.start();

		String userToReturn = "";
		MongoCollection<Document> userCollection = getUserCollection();

		try {
			ObjectId objectId = new ObjectId(id);

			FindIterable<Document> findUser = userCollection.find(Filters.eq("_id", objectId));
			ArrayList<Document> docs = new ArrayList<Document>();

			findUser.into(docs);

			for (Document doc : docs) {
				userToReturn = doc.toJson();
			}

			return userToReturn;

		} catch (Exception e) {
			e.getMessage();
		}

		watch.stop();
		System.out.println("getUser | Time Elapsed: " + watch.getTime());
		return userToReturn;

	}

}
