// libs
var FtpClient = require('ftp');
var FileSystem = require('fs');
var Path = require('path');

function Sincronizador() {
    this.Ftp = new FtpClient();
    this.server = "localhost";
    this.port = 21;
    this.user = "colivares";
    this.pass = "colivares";
    this.remoteData = "./";
    this.localData = __dirname + "/public/media/";
    this.firstTime = true;
}

(function() {
    this.run = function() {
        this.Ftp.connect({
            host: this.server,
            port: this.port,
            user: this.user,
            password: this.pass
        });

        if (!this.firstTime) return;

        var self = this;

        this.Ftp.on("ready", function() {
            console.log("conectado");
            self.Ftp.list(self.remoteData, function(error, data) {
                console.log("directorio listado");
                if (error) return null;

                (function(archivos) {
                    while (archivos.length) {
                        remoteFile = archivos.shift();
                        console.log("Sincronizando "+remoteFile.name);
                        FileSystem.stat(__dirname + "/" + self.localData + remoteFile.name, function(error, stats) {
                            var obj = {};
                            if (error || stats.size != remoteFile.size || stats.mtime != remoteFile.date) {

                                self.Ftp.get(self.remoteData + remoteFile.name, function(error, stream) {
                                    console.log("descargando " + self.remoteData + remoteFile.name, stream);
                                    if (error) return null;

                                    stream.once('close', function() {});
                                    stream.pipe(FileSystem.createWriteStream(self.localData + remoteFile.name));
                                });
                            }
                        });
                    }
                    self.Ftp.end();
                })(data);
            });
        });

        this.Ftp.on("close", function(closed) { console.log("Cerrando conexiones", closed); });
        this.Ftp.on("end", function() { console.log("Finalizando conexiones"); });
        this.Ftp.on("error", function(error) { console.log("Error:", error); });

        this.firstTime = false;
    };
}).call(Sincronizador.prototype);

module.exports = Sincronizador;