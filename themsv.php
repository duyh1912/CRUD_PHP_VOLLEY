<?php
    $connection = mysqli_connect("localhost","root","","db_asm_09296");
	
    $name = $_POST["name"];
    $mssv = $_POST["mssv"];
    $age = $_POST["age"];
    
    $sql = "INSERT INTO student(name,mssv,age) VALUES ('$name','$mssv','$age')";
    
    $result = mysqli_query($connection,$sql);
    
    if($result){
        echo "Data Inserted";
    }
    else{
        echo "Failed";
    }
    mysqli_close($connection);
          


?>