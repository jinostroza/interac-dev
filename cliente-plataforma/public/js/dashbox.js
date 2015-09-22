/**
 * Created by claudio on 21-09-15.
 */
var DashBox = {
    showtime: 10000 // 10 segundos

};

(function() {
    var newWindow;
    var srcWatcher = function(button) {


        switch ($(button).attr("class")) {
            case "escuela":
               newWindow= window.open("http://ingenieria.ucv.cl/conocenos/conocenos_unidadesacademicas.html","Escuela Ingenieria","width=1060,height=1900,resizable,scrollbars,status")
                break;
            case "flickr":
               newWindow=window.open("https://www.flickr.com/photos/facultadingenieriapucv/albums","Flickr Escuela","width=1060,height=1900,resizable,scrollbar,status")
                break;
            case "maker":
                newWindow=window.open("http://incubadora.chrysalis.cl/makerspacevalpo/","Maker Space Valparaiso","width=1060,height=1900,resizable,scrollbars,status")
                break;
        }

        setTimeout(function () { newWindow.close();}, 300000);
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