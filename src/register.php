<?php
    $first_name = $_POST["first_name"];
    $last_name = $_POST["last_name"];
    $mobile_no = $_POST["mobile_no"];
    $username = $_POST["username"];
    $password = $_POST["password"];
    $role = $_POST["role"];
    $data = true;
    $success = array();

    $conn = mysqli_connect("localhost","root","","TechEdu");
    if(! $conn) {
        die("Connection Failed " . mysqli_connect_error()); 
    }
    echo "Connnection Database OK!<br>";

    $sql = "INSERT INTO User (first_name,last_name,mobile_no,username,password,role) VALUES ('$first_name','$last_name','$mobile_no','$username','$password','$role')";
    if(mysqli_query($conn,$sql)) {
        $data["success"] = true;
    }
    else {
        $data["success"] = false;
    }
    
    echo json_encode($data);
?>