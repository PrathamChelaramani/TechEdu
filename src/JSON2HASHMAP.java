package TechEdu.Utils;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
public class JSON2HashMap {
 	public static Map<String, Object> parse(String jsonString) throws JSONException {
 		Map<String, Object> hashMap = new HashMap<>();
 		try{
 			JSONObject jsonObject = new JSONObject(jsonString);
 			Iterator<String> keys = jsonObject.keys();
 			while (keys.hasNext()) {
 				String key = keys.next();
 				Object value = jsonObject.get(key);
 				hashMap.put(key, value);
 	        }
	    }
        catch (Exception e){
            System.out.println(jsonString);
        }
        return hashMap;
    }
}