<?php
    
    define("HOST", "localhost");
    define("USER", "root");
    define("PASS", "");
    define("BD","farmacia_bd");

    $jsonArray = array();
if (isset($_GET["id"])){

    $id = $_GET["id"];
    $conexion = mysqli_connect (HOST,USER,PASS,BD);
    $consultar_mostrar = "SELECT nombre_medicamento, cantidad, precio, fecha_vencimiento FROM tbl_medicamento WHERE id= $id";
    $resultado_consulta = mysqli_query($conexion, $consultar_mostrar)or die("ERROR ".mysqli_error($conexion));

    if($registro = mysqli_fetch_array($resultado_consulta)){ 
        $json["tbl_medicamento"][]= $registro;
    }
    else{
        $resultante["id"]=0;
        $resultante["nombre_medicamento"]="";
        $resultante["cantidad"]=0;
        $resultante["precio"]=0;
        $resultante["fecha_vencimiento"]="Datos no encontrados";
        $json["tbl_medicamento"][]=$resultante;

    }
    echo json_encode($json);
    mysqli_close($conexion);
}
else{
    $resultante["id"]=0;
    $resultante["nombre_medicamento"]="ERROR";
    $resultante["cantidad"]=0;
    $resultante["precio"]=0;
    $resultante["fecha_vencimiento"]="ERROR";
    $json["tbl_medicamento"][]=$resultante;
    echo json_encode($json);
}

?>