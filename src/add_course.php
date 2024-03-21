<?php
    $title = $_POST["title"];
    $description = $_POST["description"];
    $price = $_POST["price"];
    $data = true;
    $success = array();

    $conn = mysqli_connect("localhost","root","","TechEdu");
    if(! $conn) {
        die("Connection Failed " . mysqli_connect_error());
    }

    echo "Connection Database OK!<br>";
    $sql = "INSERT INTO course(title,description,price,enrolled_students) VALUES ('$title','$description','$price',0)";
    $result = mysqli_query($conn,$sql);
    if($result) {
        $data["success"] = true;
    }
    else{
        $data["success"] = false;
    }
    echo json_encode($data);
?>