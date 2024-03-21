<?php
    $course_id = $_POST["course_id"];
    $user_id = $_POST["user_id"];
    $data = true;
    $success = array();

    $conn = mysqli_connect("localhost","root","","TechEdu");
    if(! $conn) {
        die("Connection Failed " . mysqli_connect_error());
    }

    echo "Connection Database OK!<br>";
    $sql = "INSERT INTO ActiveCourses VALUES ('$course_id', '$user_id')";

    $result = mysqli_query($conn,$sql);

    if($result) {
        $data["success"] = true;
    }
    else{
        $data["success"] = false;
    }

    echo json_encode($data);
?>