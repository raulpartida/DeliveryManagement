<?php
if(!defined("SPECIALCONSTANT")) die("Acceso denegado");

$app->post("/InsertarClienteEmpresa/", function() use($app)
{
	$body = $app->request->getBody();
	$data = json_decode($body,true);

	$NN = $data["nombren"];
	$Dir = $data["direccion"];
	$Col = $data["colonia"];
	$Cp = $data["cP"];
	$Ciu = $data["ciudad"];
	$Tel = $data["telefono"];
	$RFC = $data["rFC"];
	$Cor = $data["correo"];
	$FA = $data["fecha"];
	$IDE = $data["id_empleado"];
	$NC = $data["nombrec"];
	$AP = $data["apaterno"];
	$AM = $data["amaterno"];

	try{
		$connection = getConnection();
		$dbh = $connection->prepare("INSERT INTO cliente VALUES (null,?,?,?,?,?,?,?,?,?,?,?,DATE_FORMAT(NOW(),'%d-%m-%Y') ,?,1)");
		$dbh->bindParam(1, $NN);
		$dbh->bindParam(2, $NC);
		$dbh->bindParam(3, $AP);
		$dbh->bindParam(4, $AM);		
		$dbh->bindParam(5, $Dir);
		$dbh->bindParam(6, $Col);
		$dbh->bindParam(7, $Cp);
		$dbh->bindParam(8, $Ciu);
		$dbh->bindParam(9, $Tel);
		$dbh->bindParam(10, $RFC);
        $dbh->bindParam(11, $Cor);
        $dbh->bindParam(12, $IDE);
		$dbh->execute();
		$usuario = $dbh->fetch(PDO::FETCH_ASSOC);
		$connection = null;
		$app->response->headers->set("Content-type", "application/json");
		$app->response->status(200);
		$app->response->body(json_encode(null));
	}
	catch(PDOException $e)
	{
		echo "Error: " . $e->getMessage();
	}
});

$app->put("/InsertarClienteEmpresa/", function() use($app)
{
});

$app->delete("/InsertarClienteEmpresa/:id", function($id) use($app)
{
});