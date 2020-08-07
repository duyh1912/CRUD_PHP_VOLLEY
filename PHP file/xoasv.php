<?php 

	$connection = mysqli_connect("localhost","root","","db_asm_09296");
    
	mysqli_query($connection, "SET NAMES 'utf-8'");

    $id = $_POST['id'];

    $query = "DELETE FROM student WHERE id = '$id'";
    if(mysqli_query($connection, $query)) {
        //thành công
        echo "Thanh cong";
    } else {
        echo "That bai";
    }

 ?>