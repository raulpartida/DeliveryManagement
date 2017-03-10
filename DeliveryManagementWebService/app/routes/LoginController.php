<?php
if(!defined("SPECIALCONSTANT")) die("Acceso denegado");

$app->post("/Login/", function() use($app)
{
	$body = $app->request->getBody();
	$data = json_decode($body,true);

	$Id_empleado = $data["id"];
	$Contrasena = $data["contrasena"];

	try{

		$connection = getConnection();
		$dbh = $connection->prepare("SELECT empleado.Id_empleado AS id_empleado ,empleado.Nombre AS nombre,empleado.APaterno AS apaterno, empleado.AMaterno AS amaterno, id_ruta, id_vehiculo FROM empleado INNER JOIN ruta ON empleado.Id_empleado=ruta.id_empleado WHERE empleado.Estatus=1 and empleado.Puesto='Repartidor'  and empleado.Id_empleado=? and Contrasena = ?");
		$dbh->bindParam(1, $Id_empleado);
		$dbh->bindParam(2, $Contrasena);
		$dbh->execute();
		//para conjunto de datos$usuario = $dbh->fetchALL(PDO::FETCH_ASSOC);
		//para solo 1 dato
		$usuario = $dbh->fetch(PDO::FETCH_ASSOC);
		$connection = null;
		if ($usuario==null) {
			$usuario = array(
				"id_empleado"=>0,
				"nombre" => "",
				"apaterno" => "",
				"amaterno" => "",
				"id_ruta" => 0,
				"id_vehiculo" => ""
				);
		}
		//quitar estos 3 renglones cuando no vayas a sacar nada de la bd
		$app->response->headers->set("Content-type", "application/json");
		$app->response->status(200);
		$app->response->body(json_encode($usuario));
	}
	catch(PDOException $e)
	{
		echo "Error: " . $e->getMessage();
	}
});

$app->put("/Login/", function() use($app)
{
});

$app->delete("/Login/:id", function($id) use($app)
{
});