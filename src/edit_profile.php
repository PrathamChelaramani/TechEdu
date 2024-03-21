<?php
    $id = $_POST["first_name"];
    $first_name = $_POST["first_name"];
    $last_name = $_POST["last_name"];
    $mobile_no = $_POST["mobile_no"];
    $data = array();
    $success = true;

    $conn = mysqli_connect("localhost","root","","TechEdu");
    if(! $conn) {
        die("Connection Failed " . mysqli_connect_error());
    }

    $sql = "UPDATE user SET first_name = '$first_name',last_name = '$last_name',mobile_no = '$mobile_no' WHERE id = '$id'";
    $result = mysqli_query($conn,$sql);

    if($result) {
        $data["success"] = true;
    }
    else{
        $data["success"] = false;
    }

    echo json_encode($data);
?>