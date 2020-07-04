<?php 

//constatnes de conexion a la BD

define("__HOST__", "localhost");
define("__USER__", "root");
define("__PASS__", "");
define("__DB__", "farmacia_bd");

//isset este comprueba si una variable esta definida
if(isset($_GET["id"])){

    $id = $_GET['id'];

$conexion = mysqli_connect(__HOST__,__USER__,__PASS__,__DB__);

$eliminar = "DELETE FROM tbl_medicamento Where id = $id ";

$resultado_eliminar = mysqli_query($conexion,$eliminar) or die ('Error '.mysqli_error($conexion));

$json["tbl_medicamento"]=$resultado_eliminar;
    echo json_encode($json);

mysqli_close($conexion);
}
else{
    echo "Datos no eliminados";
}

?>