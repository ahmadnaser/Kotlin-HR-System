<?php
///Developed By Ahmad Naser Turnkey Solutions LLC - Greenbackend.com
///http://greenbackend.com/developer-hosting
///www.greenbackend.com
// www.khottah.com
///greenbackend@ahmadnaser.com
///copyright © 2018 ANTS LLC. All rights reserved.

include('../connection.php');

  $q2 = "SELECT `id`, `name`, `position`, `salary`, `experience` FROM `employee`";
  $data2= mysqli_query($conn,$q2);


$employees = array();
if($data2){

while( $row = mysqli_fetch_array($data2)){
	$employee = array();
    $employee['id'] = $row['id'];
    $employee['name'] = $row['name'];
    $employee['position'] = $row['position'];
    $employee['experience'] = $row['experience'];
    $employee['salary'] = $row['salary'];
 
  array_push($employees,$employee);

}


  
  echo json_encode( $employees);
  
}else {
echo "Error";
}





?>