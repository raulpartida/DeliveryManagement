<?php
if(!defined("SPECIALCONSTANT")) die("Acceso denegado");

$app->post("/CambioPalabra/", function() use($app)
{
	$body = $app->request->getBody();
	$data = json_decode($body,true);

	$id = $data["id"];
	$palabra_clave = $data["palabra"];
	 $password = $data["password"];

	try{

		$connection = getConnection();
	$dbh1 = $connection->prepare("UPDATE empleado SET Palabra=?  WHERE id_empleado=? and Contrasena=?");	  
			$dbh1->bindParam(1, $palabra_clave);
			$dbh1->bindParam(2, $id);
			$dbh1->bindParam(3, $password);		
			$dbh1->execute();
			$connection = null;
	}
	catch(PDOException $e)
	{
		echo "Error: " . $e->getMessage();
	}
});

$app->put("/CambioPalabra/", function() use($app)
{
});

$app->delete("/CambioPalabra/:id", function($id) use($app)
{
});