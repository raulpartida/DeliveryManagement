<?php
if(!defined("SPECIALCONSTANT")) die("Acceso denegado");

$app->post("/IngresoConfiguracion/", function() use($app)
{
	$body = $app->request->getBody();
	$data = json_decode($body,true);

	$Palabra = $data["palabra_clave"];

	try{
		$connection = getConnection();
		$dbh = $connection->prepare("SELECT Id_empleado FROM empleado WHERE empleado.Palabra=?");
		$dbh->bindParam(1, $Palabra);
		$dbh->execute();
		//para conjunto de datos$usuario = $dbh->fetchALL(PDO::FETCH_ASSOC);
		//para solo 1 dato
		$usuario = $dbh->fetch(PDO::FETCH_ASSOC);
		$connection = null;
		if ($usuario==null) {
			$usuario = array(
				"Id_empleado"=>0
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

$app->put("/IngresoConfiguracion/", function() use($app)
{
});

$app->delete("/IngresoConfiguracion/:id", function($id) use($app)
{
});