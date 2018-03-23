<?php
///Developed By Ahmad Naser Turnkey Solutions LLC - Greenbackend.com
///http://greenbackend.com/developer-hosting
///www.greenbackend.com
// www.khottah.com
///greenbackend@ahmadnaser.com
///copyright © 2018 ANTS LLC. All rights reserved.

include('../connection.php');

$name=$_POST["name"];
$position=$_POST["position"];
$salary=$_POST["salary"];
$experience=$_POST["experience"];


//echo "<h1>HI ".$name." </h1><br />";			
//echo "You are the ".$position."<br />";		
//echo "Mashallah $".$salary."<br />";		
//echo $experience;		

$query="INSERT INTO `employee`(`name`, `position`, `salary`, `experience`) VALUES ('$name','$position','$salary','$experience')";

$data = mysqli_query($conn,$query);
$employee = array();

if($data){

  $id = mysqli_insert_id($conn);
  $q2 = "SELECT `id`, `name`, `position`, `salary`, `experience` FROM `employee` WHERE id = $id";
  $data2= mysqli_query($conn,$q2);
  $row = mysqli_fetch_assoc($data2);

    $employee['id'] = $row['id'];
    $employee['name'] = $row['name'];
    $employee['position'] = $row['position'];
    $employee['experience'] = $row['experience'];
    $employee['salary'] = $row['salary'];
  
  echo json_encode( $employee);
  
}else {
echo "Error";
}





?>