<?php
    
    define("HOST", "localhost");
    define("USER", "root");
    define("PASS", "");
    define('BD','farmacia_bd');

    $json =array();

    $conexion = mysqli_connect (HOST,USER,PASS,BD);

    $consultar_mostrar = "SELECT * FROM tbl_medicamento";

    $resultado_consulta = mysqli_query($conexion, $consultar_mostrar)or die("ERROR ".mysqli_error($conexion));

    if($resultado_consulta==true){
        while($registro = mysqli_fetch_array($resultado_consulta)){
            $json["tbl_medicamento"][]=$registro;
        }
        echo json_encode($json);
        mysqli_close($conexion);
    }else{
    $resultante["nombre_medicamento"] = 0;
    $resultante["cantidad"] = "ERROR";
    $resultante["precio"] = "ERROR";
    $resultante["fecha_vencimiento"] = "ERROR";

    $jsonArray["tbl_medicamento"][] = $resultante;
    echo json_encode($json);
    }


?>