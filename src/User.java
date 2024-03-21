package TechEdu.Utils;
import TechEdu.Utils.*;
import org.json.JSONException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
public class User{
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String mobileNo;
    protected String username;
    protected String role;
    public boolean login(String username, String password) throws JSONException {
        Map<String, String> postData = new HashMap<String, String>();
        postData.put("username",username);
        postData.put("password", password);
        String response = Request.post("login", postData);
        Map<String, Object> data = JSON2HashMap.parse(response);
        if (data.get("success").toString().equals("true")){
            this.id = Integer.parseInt(data.get("id").toString());
            Map<String, Object> data2 = fetchProfile();
            this.firstName = data2.get("first_name").toString();
            this.lastName = data2.get("last_name").toString();
            this.mobileNo = data2.get("mobile_no").toString();
            this.username = data2.get("username").toString();
            this.role = data2.get("role").toString();
            return true;
        }
        else {
            return false;
        }
    }
    public boolean register(String firstName, String lastName, String mobileNo, String username, String password, String role) throws JSONException {
        Map<String, String> postData = new HashMap<String, String>();
        postData.put("first_name", firstName);
        postData.put("last_name", lastName);
        postData.put("mobile_no", mobileNo);
        postData.put("username", username);
        postData.put("password", password);
        postData.put("role", role);
        String response = Request.post("register", postData);
        Map<String, Object> data = JSON2HashMap.parse(response);
        return data.get("success").toString().equals("true");
    }
    public Map<String, Object> fetchProfile() throws JSONException {
        Map<String, String> postData = new HashMap<String, String>();
        postData.put("id", String.valueOf(this.id));
        String response = Request.post("fetch_profile", postData);
        Map<String, Object> data = JSON2HashMap.parse(response);
        return data;
    }
    public boolean editProfile(String firstName, String lastName, String mobileNo) throws JSONException {
        Map<String, String> postData = new HashMap<String, String>();
        postData.put("id", String.valueOf(this.id));
        postData.put("first_name", firstName);
        postData.put("last_name", lastName);
        postData.put("mobile_no", mobileNo);
        String response = Request.post("edit_profile", postData);
        Map<String, Object> data = JSON2HashMap.parse(response);
        return data.get("success").toString().equals("true");
    }
    public boolean enrollInCourse(int courseID) throws JSONException {
        Map<String, String> postData = new HashMap<String, String>();
        postData.put("course_id", String.valueOf(courseID));
        postData.put("user_id", String.valueOf(this.id));
        String response = Request.post("enroll_in_course", postData);
        Map<String, Object> data = JSON2HashMap.parse(response);
        return data.get("success").toString().equals("true");
    }
    public boolean unenrollFromCourse(int courseID) throws JSONException {
        Map<String, String> postData = new HashMap<String, String>();
        postData.put("course_id", String.valueOf(courseID));
        postData.put("user_id", String.valueOf(this.id));
        String response = Request.post("unenroll_from_course", postData);
        Map<String, Object> data = JSON2HashMap.parse(response);
        return data.get("success").toString().equals("true");
    }
    public boolean deleteAccount() throws JSONException {
        Map<String, String> postData = new HashMap<String, String>();
        postData.put("id", String.valueOf(this.id));
        String response = Request.post("delete_account", postData);
        Map<String, Object> data = JSON2HashMap.parse(response);
        return data.get("success").toString().equals("true");
    }
    public Map<String, Object> fetchCourses() throws JSONException {
        Map<String, String> postData = new HashMap<String, String>();
        postData.put("", "");
        String response = Request.post("fetch_course_list", postData);
        return JSON2HashMap.parse(response);
    }
    public Map<String, Object> fetchCourseDetails(int id) throws JSONException {
        Map<String, String> postData = new HashMap<String, String>();
        postData.put("id", String.valueOf(id));
        String response = Request.post("fetch_course_details", postData);
        return JSON2HashMap.parse(response);
    }
    public Map<String, Object> fetchOwnedCourseList() throws JSONException {
        Map<String, String> postData = new HashMap<String, String>();
        postData.put("id", String.valueOf(this.id));
        String response = Request.post("fetch_owned_course_list", postData);
        return JSON2HashMap.parse(response);
    }
    public boolean addCourse(String title, String description, int price) throws JSONException{
        Map<String, String> postData = new HashMap<String, String>();
        postData.put("teacher_id", String.valueOf(this.id));
        postData.put("title", title);
        postData.put("description", description);
        postData.put("price", String.valueOf(price));
        String response = Request.post("add_course", postData);
        Map<String, Object> data = JSON2HashMap.parse(response);
        return data.get("success").toString().equals("true");
    }
    public boolean deleteCourse(int id) throws JSONException{
        Map<String, String> postData = new HashMap<String, String>();
        postData.put("course_id", String.valueOf(id));
        String response = Request.post("delete_course", postData);
        Map<String, Object> data = JSON2HashMap.parse(response);
        return data.get("success").toString().equals("true");
    }
    public String getRole(){
        return role;
    }
}