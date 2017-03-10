<?php
if(!defined("SPECIALCONSTANT")) die("Acceso denegado");

$app->post("/GuardarEstatusEntrega/", function() use($app)
{
	$body = $app->request->getBody();
	$data = json_decode($body,true);

	$Estatus = $data["estatus"];
	$ID = $data["id_pedido"];

	try{

		$connection = getConnection();
		$dbh = $connection->prepare("UPDATE pedidos SET pedidos.Estatus = ? WHERE id_pedido = ?");
		$dbh->bindParam(1, $Estatus);
		$dbh->bindParam(2, $ID);
		//print_r($dbh);
		$dbh->execute();
		//$usuario = "true";
		$connection = null;
	}
	catch(PDOException $e)
	{
		echo "Error: " . $e->getMessage();
	}
});

$app->put("/GuardarEstatusEntrega/", function() use($app)
{
});

$app->delete("/GuardarEstatusEntrega/:id", function($id) use($app)
{
});