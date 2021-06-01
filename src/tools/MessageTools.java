package tools;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import BD.Database;

public class MessageTools {

	public static void addMessage(String user, String text) {
		
		MongoDatabase db = Database.getMongoDBConnection();
		MongoCollection<Document> m = db.getCollection("vg_message69");
		Document doc = new Document();
		Calendar c = Calendar.getInstance();
		Date now = c.getTime();
		doc.append("user", user).append("date", now).append("text", text).append("likes", new ArrayList<String>());
		m.insertOne(doc);
		
	}

	public static void deleteMessage(String user, String messageID) {
		
		MongoDatabase db = Database.getMongoDBConnection();
		MongoCollection<Document> m = db.getCollection("vg_message69");
		Document requete = new Document();
		ObjectId x = new ObjectId(messageID);
		requete.append("_id", x);
		m.deleteOne(requete);
	}

	public static JSONObject getMessagesFriend(String user, String friend_user) throws JSONException {
		
		MongoDatabase db = Database.getMongoDBConnection();
		MongoCollection<Document>  m = db.getCollection("vg_message69");
		Document requete = new Document();
		requete.append("user", new Document().append("$eq", friend_user));
		JSONArray list = new JSONArray();
		MongoCursor<Document> cur = m.find(requete).iterator();
		JSONObject json = new JSONObject();
		json.put("user", user);
		json.put("friend_user", friend_user);
		while(cur.hasNext()) {
			list.put(cur.next());
		}
		json.put("messages", list);
		return json;
	}

	public static JSONObject getMessagesProfile(String user) throws JSONException {
		
		MongoDatabase db = Database.getMongoDBConnection();
		MongoCollection<Document> m = db.getCollection("vg_message69");
		Document requete = new Document();
		requete.append("user", new Document().append("$eq", user));
		JSONArray list = new JSONArray();
		MongoCursor<Document> cur = m.find(requete).iterator();
		JSONObject json = new JSONObject();
		json.put("user", user);
		while(cur.hasNext()) {
			list.put(cur.next());
		}
		json.put("messages", list);
		return json;
	}

	@SuppressWarnings("static-access")
	public static JSONObject getMessagesAll(String user) throws JSONException {
		
		MongoDatabase db = Database.getMongoDBConnection();
		MongoCollection<Document>  m = db.getCollection("vg_message69");
		Calendar c = Calendar.getInstance();
		c.add(c.HOUR, -1);
		Date old = c.getTime();
		Document requete = new Document();
		requete.append("date", new Document().append("$gt", old));
		JSONArray list = new JSONArray();
		MongoCursor<Document> cur = m.find(requete).iterator();
		JSONObject json = new JSONObject();
		json.put("user", user);
		while(cur.hasNext()) {
			list.put(cur.next());
		}
		json.put("messages", list);
		return json;
	}

	public static boolean checkExist(String messageID) throws JSONException {
		
		MongoDatabase db = Database.getMongoDBConnection();
		MongoCollection<Document>  m = db.getCollection("vg_message69");
		Document requete = new Document();
		ObjectId x = new ObjectId(messageID);
		requete.append("_id", x);
		MongoCursor<Document> cur = m.find(requete).iterator();
		return cur.hasNext();
	}
	
	public static Document getMessage(String messageID) throws JSONException {
		
		MongoDatabase db = Database.getMongoDBConnection();
		MongoCollection<Document>  m = db.getCollection("vg_message69");
		Document requete = new Document();
		ObjectId x = new ObjectId(messageID);
		requete.append("_id", x);
		MongoCursor<Document> cur = m.find(requete).iterator();
		if(cur.hasNext()) return cur.next();
		return null;
	}

}
