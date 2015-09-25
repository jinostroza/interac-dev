
var FileSystem = require("fs");

var enrutador = function(App) {
    // la raiz del sistema
    App.get("/", function(req, res) {
        res.render("index", {
            title: "Plataforma Interac",
        });
    });



    App.get("/getMedia", function(req, res) {
        FileSystem.readdir(req.appPath + "/public/media/", function(error, files){
            var media = [];

            if (error) res.send(JSON.stringify(media));

            for (var i = files.length - 1; i >= 0; i--) {
                media.push("/public/media/"+files[i]);
            };

            res.send(JSON.stringify(media));
        });
    });
};

module.exports = enrutador;