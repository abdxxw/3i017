package tools;

import org.json.JSONException;
import org.json.JSONObject;

public class ErrorJSON {

	public static JSONObject ServiceRefused(String message, int codeErreur){
		JSONObject errJSON = new JSONObject();
		try {
			errJSON.put("Status","KO");
			errJSON.put("message", message);
			errJSON.put("id", codeErreur);
			return errJSON;
		} catch (JSONException e) {
			return ServiceRefused("Erreur JSON",6909);
		}
	}
	
	

	public static JSONObject ServiceAccepted(){
		JSONObject json = new JSONObject();
		try {
			json.put("Status","OK");
			return json;
		} catch (JSONException e) {
			return ServiceRefused("Erreur JSON",6909);
		}
	}
}
