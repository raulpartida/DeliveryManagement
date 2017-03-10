<?php
if(!defined("SPECIALCONSTANT")) die("Acceso denegado");

$app->post("/InsertarClientePersona/", function() use($app)
{
	$body = $app->request->getBody();
	$data = json_decode($body,true);

	
	$NC = $data["nombreC"];
	$AP = $data["aPaterno"];
	$AM = $data["aMaterno"];
	$Dir = $data["direccion"];
	$Col = $data["colonia"];
	$Cp = $data["cP"];
	$Ciu = $data["ciudad"];
	$Tel = $data["telefono"];
	$RFC = $data["rFC"];
	$Cor = $data["correo"];
	$FA = $data["fecha"];
	$IDE = $data["id_empleado"];

	try{
		$connection = getConnection();
		$dbh = $connection->prepare("INSERT INTO cliente VALUES (null,'',?,?,?,?,?,?,?,?,?,?,?,?,1)");
		$dbh->bindParam(1, $NC);
		$dbh->bindParam(2, $AP);
		$dbh->bindParam(3, $AM);
		$dbh->bindParam(4, $Dir);
		$dbh->bindParam(5, $Col);
		$dbh->bindParam(6, $Cp);
		$dbh->bindParam(7, $Ciu);
		$dbh->bindParam(8, $Tel);
		$dbh->bindParam(9, $RFC);
        $dbh->bindParam(10, $Cor);
		$dbh->bindParam(11, $FA);
        $dbh->bindParam(12, $IDE);
		$dbh->execute();
		$connection = null;
	}
	catch(PDOException $e)
	{
		echo "Error: " . $e->getMessage();
	}
});

$app->put("/InsertarClientePersona/", function() use($app)
{
});

$app->delete("/InsertarClientePersona/:id", function($id) use($app)
{
});