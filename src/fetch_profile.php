<?php
    $id = $_POST["id"];
    $data = array();
    $success = true;

    $conn = mysqli_connect("localhost","root","","TechEdu");
    if(! $conn){
        die("Connection Failed " . mysqli_connect_error());
    }

    $sql = "SELECT * FROM user WHERE id = '$id'";
    $result = mysqli_query($conn,$sql);

    if($result) {
        $data["success"] = true;
        while($row = mysqli_fetch_array($result)) {
            $data["first_name"] = $first_name;
            $data["last_name"] = $last_name;
            $data["mobile_no"] = $mobile_no;
            $data["username"] = $username;
            $data["role"] = $role;
        }
    }
    else{
        $data["success"] = false;
    }

    echo json_encode($data);
?>