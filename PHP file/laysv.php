<?php 

	$connection = mysqli_connect("localhost","root","","db_asm_09296");
    mysqli_query($connection, "SET NAMES 'utf-8'");
    
	$result = array();
	$result['student'] = array();
	$select= "SELECT *from student";
	$responce = mysqli_query($connection,$select);
	
	while($row = mysqli_fetch_array($responce))
		{
			$index['id']      = $row['0'];
			$index['name']    = $row['1'];
			$index['mssv']   = $row['2'];
			$index['age'] = $row['3'];
			
			array_push($result['student'], $index);
		}
			
			$result["success"]="1";
			echo json_encode($result);
			mysqli_close($connection);

 ?>