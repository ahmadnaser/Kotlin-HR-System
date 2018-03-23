<?php
///Developed By Ahmad Naser Turnkey Solutions LLC - Greenbackend.com
///http://greenbackend.com/developer-hosting
///www.greenbackend.com
// www.khottah.com
///greenbackend@ahmadnaser.com
///copyright © 2018 ANTS LLC. All rights reserved.

include('../connection.php');

$id=$_POST["id"];
$name=$_POST["name"];
$position=$_POST["position"];
$salary=$_POST["salary"];
$experience=$_POST["experience"];

$query="UPDATE `employee` SET `name`='$name',`position`='$position',`salary`='$salary',`experience`='$experience' WHERE id=$id";


$data = mysqli_query($conn,$query);
$response = array();

if($data){
  
    $response['updated'] = true;
  
  echo json_encode( $response);
  
}else {
  
    $response['updated'] = false;
  
  echo json_encode( $response);
}







?>