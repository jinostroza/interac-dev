// librerías
var Express = require("express");
var BodyParser = require("body-parser");
var GoogleAnalytics = require('ga');
var App = Express();
var Sincronizador = require("./sincronizador.js");
var Path = require("path");


// variables
var serverPort = 3000
//var sleepTime = 6 * 60 * 60 * 1000; // cada 6 horas
var sleepTime = 60 * 1000; // cada 20 segundos
//postgres connect


// motor de plantillas
App.set("view engine", "jade");

// definimos la carpeta de recursos estaticos
App.use("/public", Express.static("public"));

// middleware para inteceptar los errores internos
App.use(function(err, req, res, next) {
    res.render("error");
});

// middleware para mantener el appPath
App.use(function(req, res, next) {
    req.appPath = __dirname;
    next();
});

// enrutador de solicitudes de la aplicación
var Routes = require("./routes/main.js")(App);

// para poder procesar solicitudes POST
App.use(BodyParser.urlencoded({ extended: false }));
App.use(BodyParser.json());
//Database crud


// levantamos el servidor
var server = App.listen(serverPort, function () {
    var host = server.address().address;
    var port = server.address().port;

    console.log("Servidor escuchando en http://%s:%s", host, port);
});


// lanzamos el demonio sincronizador
function sincronizar() {
    console.log("Hilo sincronizador lanzado");
    this.run();
    setTimeout(sincronizar.bind(this), sleepTime);
}

sincronizar.call(new Sincronizador());