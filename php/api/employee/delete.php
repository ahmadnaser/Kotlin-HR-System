<?php
///Developed By Ahmad Naser Turnkey Solutions LLC - Greenbackend.com
///http://greenbackend.com/developer-hosting
///www.greenbackend.com
// www.khottah.com
///greenbackend@ahmadnaser.com
///copyright © 2018 ANTS LLC. All rights reserved.

include('../connection.php');

$id=$_POST["id"];

$query="DELETE FROM `employee` WHERE id = $id";


$data = mysqli_query($conn,$query);
$reponse = array();

if($data){
  
    $reponse['deleted'] = true;
  
  echo json_encode( $reponse);
  
}else {
  
    $reponse['deleted'] = false;
  
  echo json_encode( $reponse);
}







?>