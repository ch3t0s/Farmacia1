<?php

define("HOST","localhost");
define("USER", "root");
define("PASS", "");
define("BD", "farmacia_bd");


$json = array();

if(isset($_GET["id"])){

    $id = $_GET["id"];
    $nombre_medicamento = $_GET["nombre_medicamento"];
    $cantidad = $_GET["cantidad"];
    $precio = $_GET["precio"];


    $fecha_vencimiento = $_GET["fecha_vencimiento"];
    $fechaN = strtotime($fecha_vencimiento);
    $fechaN = date('Y/m/d', $fechaN);

    $conexion = mysqli_connect(HOST,USER,PASS,BD);

    $consulta_actualizar = "UPDATE tbl_medicamento SET nombre_medicamento='$nombre_medicamento', cantidad=$cantidad, 
    precio=$precio, fecha_vencimiento='$fechaN' WHERE id = $id";

    $resultado_actualizar = mysqli_query($conexion, $consulta_actualizar) or die ("ERROR " . mysqli_error($conexion));
    
    $json["tbl_medicamento"]=$resultado_actualizar;
    echo json_encode($json);

    mysqli_close($conexion);
}
else {
    echo "Dato No Actualizado";
}
    

?>