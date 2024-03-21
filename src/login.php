<?php
    $username = $_POST["username"];
    $password = $_POST["password"];
    $data = true;
    $success = array();

    $conn = mysqli_connect("localhost", "root","","TechEdu");
    if(! $conn) {
        die("Connection Failed " . mysqli_connect_error());
    }
    echo "Connection Database OK!<br>";

    $sql = "SELECT * FROM Users WHERE username = '$username' AND password = '$password'";
    $result = mysqli_query($conn,$sql);

    if(mysqli_num_rows($result) == 1) {
        $data["success"] = true;
    }
    else {
        $data["success"] = false;
    }
    echo json_encode($data);
?>