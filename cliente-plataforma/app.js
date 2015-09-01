// librerías
var Express = require("express");
var BodyParser = require("body-parser");
var App = Express();
var Sincronizador = require("./sincronizador.js");

// variables
var serverPort = 3000
var sleepTime = 10000;

// motor de plantillas
App.set("view engine", "jade");

// para poder procesar solicitudes POST
App.use(BodyParser.urlencoded({ extended: false }));
App.use(BodyParser.json());

// middleware para inteceptar los errores internos
App.use(function(err, req, res, next) {
    res.render("error");
});

// definimos la carpeta de recursos estaticos
App.use("/public", Express.static("public"));

// el inicio de nuestro sitio
App.get("/", function (req, res) {
    res.render("index", { title: "Plataforma Interac"});
});

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