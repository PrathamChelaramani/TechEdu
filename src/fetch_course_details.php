<?php
    $id = $_POST["id"];
    $data = array();
    $success = true;

    $conn = mysqli_connect("localhost","root","","TechEdu");
    if(! $conn) {
        die("Connection Failed " . mysqli_connect_error());
    }

    $sql = "SELECT * FROM course WHERE id = '$id'";
    $result = mysqli_query($conn,$sql);

    if($result) {
        $data["success"] = true;
        while($row = mysqli_fetch_array($result)) {
            $data["title"] = $row[1];
            $data["description"] = $row[2];
            $data["price"] = $row[3];
            $data["enrolled_students"] = $row[4];
        }
    }
    else{
        $data["success"] = false;
    }

    echo json_encode($data);
?>