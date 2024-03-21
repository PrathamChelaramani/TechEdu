import TechEdu.Utils.*;
import org.json.*;
import java.util.*;
import java.io.*;
public class Main{
    final private static User user = new User();
    final private static Scanner input = new Scanner(System.in);
    private static void printHeader() throws IOException, InterruptedException {
        clear();
        System.out.println("Welcome to TechEdu - Online Course Platform\n\n");
        System.out.flush();
    }
    private static void clear() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
    private static void wrongChoice() throws IOException {
        System.out.println("\nWrong choice. Press Enter to continue...");
        waitForKey();
    }
    private static void waitForKey() throws IOException {
        System.in.read();
    }
    private static int takeChoice() {
        System.out.print("Enter your choice : ");
        System.out.flush();
        return input.nextInt();
    }
    private static void exit(){
        System.exit(0);
    }
    private static void welcomeScreen() throws IOException, InterruptedException,JSONException {
        printHeader();
        System.out.println("1. Login");
        System.out.println("2. Register\n");
        System.out.println("9. Exit\n");
        switch (takeChoice()) {
            case 9 -> exit();
            case 1 -> loginScreen();
            case 2 -> registerScreen();
            default -> {
                wrongChoice(); welcomeScreen();
            }
        }
    }
    private static void loginScreen() throws IOException, InterruptedException,JSONException {
        printHeader();
        input.nextLine();
        System.out.print("Enter username : ");
        String username = input.nextLine();
        System.out.print("Enter password : ");
        String password = input.nextLine();
        boolean success = user.login(username, password);
        if (success){
            System.out.println("\nLogged In successfully! Press Enter to continue...");
            waitForKey();
            dashboard();
        }
        else{
            System.out.println("\nIncorrect username or password! Press Enter to continue...");
            waitForKey();
            welcomeScreen();
        }
    }
    public static void dashboard() throws IOException, InterruptedException,JSONException {
        printHeader();
        if (Objects.equals(user.getRole(), "student")) {
        System.out.println("1. View Profile");
        System.out.println("2. Edit Profile");
        System.out.println("3. View Courses");
        System.out.println("4. View Course Details");
        System.out.println("5. Enroll in course");
        System.out.println("6. Unenroll from course");
        System.out.println("7. Delete Account");
        System.out.println("8. Logout\n");
        System.out.println("9. Exit\n");
        switch (takeChoice()) {
            case 1 -> viewProfile();
            case 2 -> editProfile();
            case 3 -> viewCourses(1);
            case 4 -> viewCourseDetails();
            case 5 -> enrollInCourse();
            case 6 -> unenrollFromCourse();
            case 7 -> deleteAccount();
            case 8 -> {
                System.out.println("\nLogged Out Successfully! Press Enter to continue...");
                welcomeScreen();
            }
            case 9 -> exit();
            default -> {
                wrongChoice();
                dashboard();
            }
        }
    }
    else{
        System.out.println("1. View Profile");
        System.out.println("2. Edit Profile");
        System.out.println("3. View Your Courses");
        System.out.println("4. Add Course");
        System.out.println("5 Delete Course");
        System.out.println("6 Delete Account");
        System.out.println("7. Logout\n");
        System.out.println("9. Exit\n");
        switch (takeChoice()) {
            case 1 -> viewProfile();
            case 2 -> editProfile();
            case 3 -> viewYourCourses(1);
            case 4 -> addCourse();
            case 5 -> deleteCourse();
            case 6 -> deleteAccount();
            case 7 -> {
                System.out.println("\nLogged Out Successfully! Press Enter to continue...");
                welcomeScreen();
            }
            case 9 -> exit();
            default -> {
                wrongChoice();
                dashboard();
            }
        }
    }
}
private static void viewCourseDetails() throws IOException, InterruptedException,JSONException {
    viewCourses(0);
    System.out.print("Enter courseID : ");
    int id = input.nextInt();
    Map<String, Object> data = user.fetchCourseDetails(id);
    if (data.get("success").toString().equals("true")) {
        System.out.println("\nTitle : " + data.get("title"));
        System.out.println("Description : " + data.get("description"));
        System.out.println("Price : " + data.get("price"));
        System.out.println("\nPress Enter to continue...");
    }
    else{
        System.out.println("\nSomething went wrong! Press Enter to continue...");
    }
    waitForKey();
    dashboard(); 
}
private static void deleteCourse() throws JSONException, IOException,InterruptedException {
    viewYourCourses(0);
    input.nextLine();
    System.out.print("Enter courseID : ");
    int id = input.nextInt();
    boolean success = user.deleteCourse(id);
    if (success){
        System.out.println("\nCourse Deleted Successfully! Press Enter to continue...");
    }
    else{
        System.out.println("\nSomething went wrong! Press Enter to continue...");
    }
    waitForKey();
    dashboard();
}
private static void addCourse() throws IOException, InterruptedException,JSONException {
    printHeader();
    input.nextLine();
    System.out.print("Enter title : ");
    String title = input.nextLine();
    System.out.print("Enter description : ");
    String description = input.nextLine();
    System.out.print("Enter price : ");
    int price = input.nextInt();
    boolean success = user.addCourse(title, description, price);
    if (success){
        System.out.println("\nCourse Added Successfully! Press Enter to continue...");
    }
    else {
        System.out.println("\nSomething went wrong! Press Enter to continue...");
    }
    waitForKey();
    dashboard();
}
private static void viewYourCourses(int wait) throws IOException,InterruptedException, JSONException {
    printHeader();
    Map<String, Object> data = user.fetchOwnedCourseList();
    if(data.get("success").toString().equals("true")){
    JSONArray courses = new JSONArray(data.get("courses").toString());
    for (int i = 0; i < courses.length(); i++){
        JSONArray course = new JSONArray(courses.get(i).toString());
            System.out.println(course.get(0) + " - " + course.get(1));
        }
        if(wait == 1){
            System.out.println("\nPress Enter to continue...");
            waitForKey();
            dashboard();
        }
    }
    else{
        System.out.println("\nError fetching courses! Press Enter to continue...");
        waitForKey();
        dashboard();
    }
}
private static void deleteAccount() throws IOException, InterruptedException,JSONException {
    printHeader();
    input.nextLine();
    System.out.print("Are you sure you want to delete your account(y/n) : ");
    char conf = input.next().charAt(0);
    if (conf == 'y'){
        boolean success = user.deleteAccount();
        if (success) {
            System.out.println("\nAccount Deleted! Press Enter to continue...");
            waitForKey();
            welcomeScreen();
        }
        else{
            System.out.println("\nSomething went wrong! Press Enter to continue...");
            waitForKey();
            dashboard();
        }
    }
    else{
        dashboard();
    }
}
private static void enrollInCourse() throws JSONException, IOException,InterruptedException {
    viewCourses(0);
    System.out.println();
    input.nextLine();
    System.out.print("Enter courseID : ");
    int id = input.nextInt();
    boolean success = user.enrollInCourse(id);
    if (success){
        System.out.println("\nSuccessfully Enrolled! Press Enter to continue...");
    }
    else{
        System.out.println("\nSomething went wrong! Press Enter to continue...");
    }
    waitForKey();
    dashboard();
}
private static void unenrollFromCourse() throws JSONException, IOException,InterruptedException {
    viewCourses(0);
    System.out.println();
    input.nextLine();
    System.out.print("Enter courseID : ");
    int id = input.nextInt();
    boolean success = user.unenrollFromCourse(id);
    if (success){
        System.out.println("\nSuccessfully Unenrolled! Press Enter to continue...");
    }
    else{
        System.out.println("\nSomething went wrong! Press Enter to continue...");
    }
    waitForKey();
    dashboard();
}
private static void viewCourses(int wait) throws IOException, InterruptedException,JSONException {
    printHeader();
    Map<String, Object> data = user.fetchCourses();
    if(data.get("success").toString().equals("true")){
        JSONArray courses = new JSONArray(data.get("courses").toString());
        for (int i = 0; i < courses.length(); i++){
            JSONArray course = new JSONArray(courses.get(i).toString());
            System.out.println(course.get(0) + " - " + course.get(1));
        }
        if(wait == 1){
            System.out.println("\nPress Enter to continue...");
            waitForKey();
            dashboard();
        }
    }
    else{
        System.out.println("\nError fetching courses! Press Enter to continue...");
        waitForKey();
        dashboard();
    }
}
private static void viewProfile() throws JSONException, IOException,InterruptedException {
    printHeader();
    Map<String, Object> data = user.fetchProfile();
    if(data.get("success").toString().equals("true")){
        System.out.println("Username : " + data.get("username"));
        System.out.println("Firstname : " + data.get("first_name"));
        System.out.println("Lastname : " + data.get("last_name"));
        System.out.println("Mobile No. : " + data.get("mobile_no"));
        System.out.println("Account Type : " + data.get("role"));
        System.out.println("\nPress Enter to continue...");
    }
    else{
        System.out.println("\nSomething went wrong! Press Enter to continue...");
    }
    waitForKey();
    dashboard();
}
private static void editProfile() throws IOException, InterruptedException,JSONException {
    printHeader();
    input.nextLine();
    System.out.print("Enter firstname : ");
    String firstName = input.nextLine();
    System.out.print("Enter lastname : ");
    String lastName = input.nextLine();
    System.out.print("Enter mobile no. : ");
    String mobileNo = input.nextLine();
    boolean success = user.editProfile(firstName, lastName, mobileNo);
    if (success){
        System.out.println("\nProfile Updated Successfully! Press Enter to continue...");
    }
    else{
        System.out.println("\nSomething went wrong! Press Enter to continue...");
    }
    waitForKey();
    dashboard();
}
private static void registerScreen() throws IOException, InterruptedException,JSONException {
    printHeader();
    System.out.println("1. Register as Student");
    System.out.println("2. Register as Teacher\n");
    System.out.println("0. Go Back");
    System.out.println("9. Exit\n");
    switch(takeChoice()){
        case 1 -> register("student");
        case 2 -> register("teacher");
        case 0 -> welcomeScreen();
        case 9 -> exit();
        default -> {
            wrongChoice(); registerScreen();
        }
    }
}
private static void register(String role) throws IOException, InterruptedException,JSONException {
    printHeader();
    input.nextLine();
    System.out.print("Enter username : ");
    String username = input.nextLine();
    System.out.print("Enter password : ");
    String password = input.nextLine();
    System.out.print("Enter firstname : ");
    String firstName = input.nextLine();
    System.out.print("Enter lastname : ");
    String lastname = input.nextLine();
    System.out.print("Enter mobile no. : ");
    String mobileNo = input.nextLine();
    boolean success = user.register(firstName, lastname, mobileNo, username,password, role);
    if (success){
        System.out.println("\nRegistered successfully! Press Enter to login...");
        waitForKey();
        loginScreen();
    }
    else{
        System.out.println("\nSomething went wrong! Press Enter to continue...");
        waitForKey();
        welcomeScreen();
    }
}
public static void main(String[] args) {
    try {
        welcomeScreen();
    }
    catch(Exception e){
        e.printStackTrace();
    }
}
}