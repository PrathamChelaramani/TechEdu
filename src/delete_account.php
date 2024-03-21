<?php
    $id = $_POST["id"];
    $data = array();
    $success = true;

    $conn = mysqli_connect("localhost","root","","TechEdu");
    if(! $conn) {
        die("Connection Failed " . mysqli_connect_error());
    }

    $sql = "DELETE FROM user WHERE id = '$id'";
    $result = mysqli_query($conn,$sql);

    if($result) {
        $data["success"] = true;
    }
    else{
        $data["success"] = false;
    }

    echo json_encode($data);
?>