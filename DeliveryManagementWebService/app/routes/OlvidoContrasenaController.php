<?php
if(!defined("SPECIALCONSTANT")) die("Acceso denegado");

$app->post("/OlvidoContrasena/", function() use($app)
{
	$body = $app->request->getBody();
	$data = json_decode($body,true);

	$id = $data["id"];
	$palabra_clave = $data["palabra"];
    $password = $data["password"];

	try{

		$connection = getConnection();
			$dbh1 = $connection->prepare("UPDATE empleado SET Contrasena=? , Contrasena2=? WHERE Palabra=? and Id_empleado=?");	
			$dbh1->bindParam(1, $password);
			$dbh1->bindParam(2, $password);
			$dbh1->bindParam(3, $palabra_clave);
			$dbh1->bindParam(4, $id);		
			$dbh1->execute();
			$connection = null;
	}
	catch(PDOException $e)
	{
		echo "Error: " . $e->getMessage();
	}
});

$app->put("/OlvidoContrasena/", function() use($app)
{
});

$app->delete("/OlvidoContrasena/:id", function($id) use($app)
{
});