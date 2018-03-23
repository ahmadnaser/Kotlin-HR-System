<?php

///Developed By Ahmad Naser Turnkey Solutions LLC - Greenbackend.com
///http://greenbackend.com/developer-hosting
///www.greenbackend.com
// www.khottah.com
///greenbackend@ahmadnaser.com
///copyright © 2018 ANTS LLC. All rights reserved.

$host = "shareddb-xxx.hosting.stackcp.net";
$username = "greenapisdbdemo1-xxx";
$pass = "xxxx";
$db = "greenapisxxxxx";


$conn = mysqli_connect($host,$username,$pass,$db);

if(!$conn){
die("Connection Error");
}


?>