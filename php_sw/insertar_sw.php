<?php

//constatnes de conexion a la BD

define("__HOST__", "localhost");
define("__USER__", "root");
define("__PASS__", "");
define("__DB__", "farmacia_bd");

//contrendra informacion que sera traslada por medio de un array un JSON
$jsonArray = array();

//isset este comprueba si una variable esta definida
if(isset($_GET["nombre_medicamento"]) && isset($_GET["cantidad"]) 
&& isset($_GET["precio"]) && isset($_GET["fecha_vencimiento"])){

    $nombre_medicamento = $_GET['nombre_medicamento'];
    $cantidad = $_GET['cantidad'];
    $precio = $_GET['precio'];

    $fecha_vencimiento = $_GET['fecha_vencimiento'];
    $fechaN = strtotime ($fecha_vencimiento);
    $fechaN = date('Y/m/d', $fechaN);

$conexion = mysqli_connect(__HOST__,__USER__,__PASS__,__DB__);

$insertar = "INSERT INTO tbl_medicamento (nombre_medicamento, cantidad, precio, fecha_vencimiento) VALUES ('{$nombre_medicamento}', {$cantidad}, {$precio}, '{$fechaN}')";


$resultado_insertar = mysqli_query($conexion,$insertar) or die ('Error '.nysqli_error($conexion));


$jsonArray["tbl_medicamento"] = $resultado_insertar;



mysqli_close($conexion);
echo json_encode($jsonArray);


}
else{

    $resultante["nombre_medicamento"] = "ERROR";
    $resultante["cantidad"] = "ERROR";
    $resultante["precio"] = "ERROR";
    $resultante["fecha_vencimiento"] = "ERROR";

    $jsonArray["tbl_medicamento"][] = $resultante;
    echo json_encode($jsonArray);


}





?>