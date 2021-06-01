package tools;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import BD.Database;

public class LikeTools {


	@SuppressWarnings("unchecked")
	public static void addLike(String user, String messageID) throws JSONException {
		
		MongoDatabase db = Database.getMongoDBConnection();
		MongoCollection<Document> m = db.getCollection("vg_message69");
		Document message = tools.MessageTools.getMessage(messageID);
		if(!((ArrayList<String>) message.get("likes")).contains(user)) {
			Document q = new Document();
			q.append("$push" , new Document().append("likes", user));
			Document id = new Document().append("_id", new ObjectId(messageID));
			m.updateOne(id, q);
		}


		
	
	}

	@SuppressWarnings("unchecked")
	public static void deleteLike(String user, String messageID) throws JSONException {
		
		
		
		MongoDatabase db = Database.getMongoDBConnection();
		MongoCollection<Document> m = db.getCollection("vg_message69");
		Document message = tools.MessageTools.getMessage(messageID);
		if(((ArrayList<String>) message.get("likes")).contains(user)) {
			Document q = new Document();
			q.append("$pull" , new Document().append("likes", user));
			Document id = new Document().append("_id", new ObjectId(messageID));
			m.updateOne(id, q);
		}
	}

	
	public static JSONObject getLikes(String messageID) throws JSONException {
		
		Document message = tools.MessageTools.getMessage(messageID);
		@SuppressWarnings("unchecked")
		ArrayList<String> likes = (ArrayList<String>) message.get("likes");
		JSONArray list = new JSONArray();
		JSONObject json = new JSONObject();
		json.put("messageID", messageID);

		for(String x : likes) {
			list.put(x);
		}
		json.put("likes", list);
		return json;
	}
}
