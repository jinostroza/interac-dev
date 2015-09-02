
var enrutador = function(App) {
    // la raiz del sistema
    App.get("/", function(req, res) {
        res.render("index", {
            title: "Plataforma Interac",
        });
    });

    App.get()
};

module.exports = enrutador;