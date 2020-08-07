<?php    
    $connection = mysqli_connect("localhost","root","","db_asm_09296");
   
    $id = $_POST['id'];
    $name = $_POST['name'];
    $mssv = $_POST['mssv'];
    $age = $_POST['age'];

    $sql = "UPDATE student SET name = '$name', mssv = '$mssv', age = '$age'  WHERE id = '$id'";

    $result = mysqli_query($connection,$sql);

    if($result) {
        //thành công
        echo "Thanh cong";
    } else {
        echo "That bai";
    }

     

?>