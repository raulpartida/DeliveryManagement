<?php
if(!defined("SPECIALCONSTANT")) die("Acceso denegado");

$app->post("/MercanciaEntrega/", function() use($app)
{
	$body = $app->request->getBody();
	$data = json_decode($body,true);

	$ID = $data["id_pedido"];


	try{

		$connection = getConnection();
		$dbh = $connection->prepare("SELECT mercancia.nombre,mercancia.marca AS mercancia,mercancia.cantidad,mercancia.Estatus AS estado FROM mercancia INNER JOIN pedidos ON mercancia.id_pedido=pedidos.id_pedido INNER JOIN ruta ON pedidos.id_ruta=ruta.id_ruta WHERE pedidos.estado=1 and mercancia.Estatus= 1 and pedidos.id_pedido=? ");
		$dbh->bindParam(1, $ID);
		$dbh->execute();
		//para conjunto de datos$usuario = $dbh->fetchALL(PDO::FETCH_ASSOC);
		//para solo 1 dato
		$usuario = $dbh->fetchALL(PDO::FETCH_ASSOC);
		$connection = null;
		if ($usuario==null) {
			$usuario = array(
				"nombre" => "",
				"mercancia" => "",
				"cantidad" => "",
				"estado" => "0"
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

$app->put("/MercanciaEntrega/", function() use($app)
{
});

$app->delete("/MercanciaEntrega/:id", function($id) use($app)
{
});