<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();

	if(isset($_POST['tag'])&& $_POST['tag']!='')
	{
		$tag=$_POST['tag'];
	
		$json=array("tag"=>$tag,"thanhcong"=>0,"loi"=>0);	
	
		if($tag=='login')
		{
			xulydangnhap($json,$db);
		}
		else if($tag=='register')
		{
			xulydangki($json,$db);
		}
		else 
		{
			echo "yeu cau khong hop le";
		}
	}
	else
	{
		echo "truy cap khong duoc";
	}
	
	
	function xulydangnhap($json,$db)
{
	$email=$_POST['email'];
	$password=$_POST['password'];
	$user=$db->getUser($email,$password);

	if($user!=false) // tim thay user
	{
		$json["thanhcong"]=1;
		$json["user"]["name"]=$user["name"];
		$json["user"]["email"]=$user["email"];
		$json["user"]["password"]=$user["password"];
		$json["user"]["ngaytao"]=$user["create_date"];
		$json["user"]["ngaycapnhat"]=$user["update_date"];
		
		echo json_encode($json);
	}
	else //tim khong thay user
	{
		$json["loi"]=1;
		$json["thongbaoloi"]="sai email hoac password";
		
		echo json_encode($json);
	}
}

function xulydangki($json,$db)
{
	$email=$_POST['email'];
	$password=$_POST['password'];
	$name=$_POST["name"];
	
	if($db->checkUser($email))//true : da ton tai
	{
		$json["loi"]=2;
		$json["thongbaoloi"]="email da ton tai roi";
		
		echo json_encode($json);
	}
	else//user chua ton tai, luu du lieu
	{
		$user=$db->storeUser($name,$email,$password);
		
		if($user!=false)//neu luu thanh cong
		{
			$json["thanhcong"]=1;
			$json["user"]["name"]=$user["name"];
			$json["user"]["email"]=$user["email"];
			$json["user"]["create_date"]=$user["create_date"];
			$json["user"]["update_date"]=$user["update_date"];
			
			echo json_encode($json);
		}
		else //neu luu that bai
		{
			$json["loi"]=1;
			$json["thongbaoloi"]="dang ki that bai";
			
			echo json_encode($json);
		}
	}
}

	
?>
