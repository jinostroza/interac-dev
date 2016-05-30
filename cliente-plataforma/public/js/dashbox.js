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
    var winHistoria;
    var winPucv;
    var winFlckHist;
    var winForm;
    var winFacebook;
    var winWeather;
    var winestudiantes;
    var winDpd;
    var winBib;
    var winDdcyf;
    var winVice;
    var winRed1;
    var winRed2;
    var winRed3;
    var winRed4;
    var srcWatcher = function(button) {

        switch ($(button).attr("class")) {
            case "ingenieria":
               winEscuela=window.open("http://ingenieria.ucv.cl/conocenos/conocenos_unidadesacademicas.html","Escuela Ingenieria","width=1060,height=1900,resizable,toolbar,scrollbar,status")
                break;
            case "historiabtn":
                winHistoria=window.open("http://www.ihistoriapucv.cl/","Instituto Historia","width=1060,height=1900,resizable,toolbar,scrollbar,status")
                break;
            case "flickrIngenieria":
               winFlickr=window.open("https://www.flickr.com/photos/facultadingenieriapucv/albums","Flickr Escuela","width=1060,height=1900,resizable,scrollbar,status")
                break;
            case "flickrhistoria":
                winFlckHist=window.open("https://www.flickr.com//photos/141005653@N06/","Flickr Instituo Historia","width=1060,height=1900,resizable,scrollbar,status")
                break;
            case "maker":
                winMaker=window.open("http://incubadora.chrysalis.cl/makerspacevalpo/","Maker Space Valparaiso","width=1060,height=1900,resizable,scrollbars,status")
                break;
            case "pucvbtn":
                winPucv=window.open("http://www.pucv.cl","Pontificia Universidad Catolica","width=1060,height=1900,resizable,scrollbar,status")
                break;
            case "twitter":
                winTwitter=window.open("https://twitter.com/ingenieriapucv","FIN PUCV","width=1060,height=1900,resizable,scrollbars,status")
                break;
            case "facebook":
                winFacebook=window.open("https://www.facebook.com/pages/Instituto-de-Historia-PUCV/1393254430944455","facebook","width=1060,height=1900,resizable,scrollbars,status")
                break;
            case "weather":
                winWeather=window.open("http://www.tiempo.com/valparaiso.htm","tiempo","width=1060,height=1900,resizable,scrollbars,status")
                break;
            case "estudiantes":
                winestudiantes=window.open("http://www.pucv.cl/pucv/site/edic/base/port/estudiantes.html","Estudiantes","width=1060,height=1900,resizable,scrollbars,status")
                break;
            case "academicos":
                winDpd=window.open("http://www.pucv.cl/pucv/site/edic/base/port/academicos.html","DPD","width=1060,height=1900,resizable,scrollbars,status")
                break;
            case "investigacion":
                winBib=window.open("http://www.pucv.cl/pucv/site/edic/base/port/investigacion.html","Biblioteca","width=1060,height=1900,resizable,scrollbars,status")
                break;
            case "internacional":
                winDdcyf=window.open("http://www.pucv.cl/pucv/site/edic/base/port/pucv_internacional.html","Ddycf","width=1060,height=1900,resizable,scrollbars,status")
                break;
            case "vice":
                winVice=window.open("http://www.pucv.cl","Pucv","width=1060,height=1900,resizable,scrollbars,status")
                break;
            case "red1":
                winRed1=window.open("https://www.facebook.com/catolicadevalparaiso","Pucv","width=1060,height=1900,resizable,scrollbars,status")
                break;
             case "red2":
                winRed2=window.open("https://twitter.com/comunidadpucv","Pucv","width=1060,height=1900,resizable,scrollbars,status")
                break;
            case "red3":
                winRed3=window.open("https://www.youtube.com/user/pucvprensa","Pucv","width=1060,height=1900,resizable,scrollbars,status")
                break;
            case "red4":
                winRed4=window.open("https://www.flickr.com/photos/pucv/","Pucv","width=1060,height=1900,resizable,scrollbars,status")
                break;
            case "form":
                winForm=window.open("http://www.pucv.cl/pucv/site/tax/port/fil_evento/taxport_28___1.html","Talleres","width=1060,height=1900,resizable,scrollbars,status")
               // setTimeout(function() {winForm.location.href = 'http://54.208.243.25:8080/interac/lista.jsf';}, 1000);

                break;
        }

        setTimeout(function () { winEscuela.close();}, 60000);
        setTimeout(function () { winFlickr.close();}, 60000);
        setTimeout(function () { winMaker.close();}, 60000);
        setTimeout(function () { winTwitter.close();}, 60000);
        setTimeout(function () { winFlckHist.close();}, 60000);
        setTimeout(function () { winPucv.close();}, 60000);
        setTimeout(function () { winHistoria.close();}, 60000);
        setTimeout(function () { winFacebook.close();}, 60000);
        setTimeout(function () { winWeather.close();}, 60000);
        setTimeout(function () { winestudiantes.close();}, 60000);
        setTimeout(function () { winDpd.close();}, 60000);
        setTimeout(function () { winBib.close();}, 60000);
        setTimeout(function () { winDdcyf.close();}, 60000);
        setTimeout(function () { winVice.close();}, 60000);
        setTimeout(function () { winForm.close();}, 300000);

    };

    this.start = function() {
        var self = this;

        $("button.ingenieria").click(function() {
            srcWatcher.call(self, this);

        });
        $("button.historiabtn").click(function() {
            srcWatcher.call(self, this);

        });
        $("button.pucvbtn").click(function() {
            srcWatcher.call(self, this);

        });
        $("div.vice").click(function() {
            srcWatcher.call(self, this);

        });
        $("div.red1").click(function() {
            srcWatcher.call(self, this);

        });
        $("div.red2").click(function() {
            srcWatcher.call(self, this);

        });
        $("div.red3").click(function() {
            srcWatcher.call(self, this);

        });
        $("div.red4").click(function() {
            srcWatcher.call(self, this);

        });
        $("button.flickrhistoria").click(function() {
            srcWatcher.call(self, this);

        });
        $("button.flickrIngenieria").click(function() {
            srcWatcher.call(self, this);
        });
        $("button.maker").click(function() {
            srcWatcher.call(self, this);
        });
        $("div.twitter").click(function() {
            srcWatcher.call(self, this);
        });
         $("div.facebook").click(function() {
            srcWatcher.call(self, this);
        });
         $("div.weather").click(function() {
            srcWatcher.call(self, this);
        });
        $("button.form").click(function() {
            srcWatcher.call(self, this);
        });
        $("button.estudiantes").click(function() {
            srcWatcher.call(self, this);
        });
        $("button.academicos").click(function() {
            srcWatcher.call(self, this);
        });
        $("button.investigacion").click(function() {
            srcWatcher.call(self, this);
        });
        $("button.internacional").click(function() {
            srcWatcher.call(self, this);
        });
    };

    this.hide = function() {
        $("iframe.overlay").hide();
    };
}).apply(DashBox);
