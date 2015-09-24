/**
 * Created by claudio on 21-09-15.
 */
var DashBox = {
   };

(function() {
    var winEscuela;
    var winFlickr;
    var winMaker;
    var winTwitter;
    var srcWatcher = function(button) {


        switch ($(button).attr("class")) {
            case "escuela":
               winEscuela= window.open("http://ingenieria.ucv.cl/conocenos/conocenos_unidadesacademicas.html","Escuela Ingenieria","width=1060,height=1900,resizable,toolbar,scrollbar,status")
                break;
            case "flickr":
               winFlickr=window.open("https://www.flickr.com/photos/facultadingenieriapucv/albums","Flickr Escuela","width=1060,height=1900,resizable,scrollbar,status")
                break;
            case "maker":
                winMaker=window.open("http://incubadora.chrysalis.cl/makerspacevalpo/","Maker Space Valparaiso","width=1060,height=1900,resizable,scrollbars,status")
                break;
            case "twitter":
                winTwitter=window.open("https://twitter.com/ingenieriapucv","Maker Space Valparaiso","width=1060,height=1900,resizable,scrollbars,status")
                break;
        }

        setTimeout(function () { winEscuela.close();}, 60000);
        setTimeout(function () { winFlickr.close();}, 60000);
        setTimeout(function () { winMaker.close();}, 60000);
        setTimeout(function () { winTwitter.close();}, 60000);

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
        $("div.twitter").click(function() {

            srcWatcher.call(self, this);
        });
    };

    this.hide = function() {
        $("iframe.overlay").hide();
    };
}).apply(DashBox);