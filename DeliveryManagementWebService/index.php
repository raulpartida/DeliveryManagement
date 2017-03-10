<?php
require 'Slim/Slim.php';

\Slim\Slim::registerAutoloader();

$app = new \Slim\Slim();

define("SPECIALCONSTANT", true);

$username = null;
$password = null;
$_username_ = "user";
$_password_ = "pass";

// mod_php
if (isset($_SERVER['PHP_AUTH_USER'])) {
    $username = $_SERVER['PHP_AUTH_USER'];
    $password = $_SERVER['PHP_AUTH_PW'];

// most other servers
} elseif (isset($_SERVER['HTTP_AUTHORIZATION'])) {

        if (strpos(strtolower($_SERVER['HTTP_AUTHORIZATION']),'basic')===0)
          list($username,$password) = explode(':',base64_decode(substr($_SERVER['HTTP_AUTHORIZATION'], 6)));

}


if (is_null($username)) {

    header('WWW-Authenticate: Basic realm="Binne Systems"');
    header('HTTP/1.0 401 Unauthorized');
    echo 'Text to send if user hits Cancel button';

    die();

} else {
    if(!(($username == $_username_)&&($password == $_password_))){
        die ('Acceso denegado');
    }
}



//$auth_realm = 'Binne Systems';

//require 'auth.php';

require 'app/libs/connect.php';
require 'app/routes/LoginController.php';
require 'app/routes/DireccionesController.php';
require 'app/routes/GuardarEstatusEntregaController.php';
require 'app/routes/EstatusEntregasController.php';
require 'app/routes/MercanciaEntregaController.php';
require 'app/routes/InsertarClienteEmpresaController.php';
require 'app/routes/InsertarClientePersonaController.php';
require 'app/routes/OlvidoContrasenaController.php';
require 'app/routes/CambioPalabraController.php';
$app->run();