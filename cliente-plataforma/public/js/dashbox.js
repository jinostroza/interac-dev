/**
 * Created by claudio on 21-09-15.
 */
var DashBox = {
    showtime: 10000 // 10 segundos
};

(function() {
    var srcWatcher = function(button) {
        var url = "";
        switch ($(button).attr("class")) {
            case "escuela":
                url = "http://ingenieria.ucv.cl/conocenos/conocenos_unidadesacademicas.html";
                break;
            case "flickr":
                url = "https://www.flickr.com/photos/facultadingenieriapucv/albums";
                break;
            case "maker":
                url = "http://incubadora.chrysalis.cl/makerspacevalpo/";
                break;
        }
        $("iframe.overlay").attr("src", url);
        $("iframe.overlay").show();
        setTimeout(this.hide.bind(this), this.showtime);
    };

    this.start = function() {
        var self = this;

        $("button.escuela").click(function() {
            srcWatcher.call(self, this);
        });
        $("button.flickr").click(function() {
            srcWatcher.call(self, this);
        });
        $("button.maker").click(function() {
            srcWatcher.call(self, this);
        });
    };

    this.hide = function() {
        $("iframe.overlay").hide();
    };
}).apply(DashBox);