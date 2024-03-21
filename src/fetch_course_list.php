<?php
    $data = array();
    $success = true;

    $conn = mysqli_connect("localhost","root","","TechEdu");
    if(! $conn) {
        die("Connection Failed " . mysqli_connect_error());
    }

    $sql = "SELECT id,title FROM course";
    $result = mysqli_query($conn,$sql);

    if($result) {
        $data["success"] =true;
        $data["course"] = mysqli_fetch_all($result);
    }
    else{
        $data["success"] = false;
    }

    echo json_encode($data);
?>