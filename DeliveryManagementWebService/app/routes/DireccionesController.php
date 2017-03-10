<?php
if(!defined("SPECIALCONSTANT")) die("Acceso denegado");

$app->post("/Direcciones/", function() use($app)
{
	$body = $app->request->getBody();
	$data = json_decode($body,true);

	$ID = $data["id_empleado"];

	try{

		$connection = getConnection();
		$dbh = $connection->prepare("SELECT id_pedido as nentrega, cliente.direccion, 
			cliente.colonia, nombre_negocio, nombre_cliente,cliente.apaterno,cliente.amaterno, 
			pedidos.Estatus AS estatus, pedidos.Comentarios as comentarios FROM pedidos INNER JOIN cliente ON 
			pedidos.id_cliente=cliente.id_cliente INNER JOIN ruta ON ruta.id_ruta=pedidos.id_ruta 
			INNER JOIN empleado ON empleado.Id_empleado=ruta.id_empleado 
			WHERE ruta.fecha= pedidos.Fecha_entrega and ruta.fecha= DATE_FORMAT(NOW(),'%d-%m-%Y')  
			and ruta.id_empleado= ? ");
		$dbh->bindParam(1,$ID);
		$dbh->execute();
		//para conjunto de datos$usuario = $dbh->fetchALL(PDO::FETCH_ASSOC);
		//para solo 1 dato
		$usuario = $dbh->fetchALL(PDO::FETCH_ASSOC);
		$connection = null;
		if ($usuario==null) {
			$usuario = array(
				"nentrega" => 0,
				"direccion" => "",
				"colonia" => "",
				"nombre_negocio" => "",
				"nombre_cliente" => "",
				"apaterno" => "",
				"amaterno" => "",
				"estatus" => "",
                "comentarios"=>""
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

$app->put("/Direcciones/", function() use($app)
{
});

$app->delete("/Direcciones/:id", function($id) use($app)
{
});