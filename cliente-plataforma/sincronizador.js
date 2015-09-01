// libs
var FtpClient = require('ftp');
var FileSystem = require('fs');
var Path = require('path');

module.exports = function Sincronizador() {
    this.Ftp = new FtpClient();
    this.server = "localhost";
    this.port = 21;
    this.user = "colivares";
    this.pass = "colivares";
    this.remoteData = "./";
    this.localData = __dirname + "/public/media/"

    this.run = function() {

        this.Ftp.connect({
            host: this.server,
            port: this.port,
            user: this.user,
            password: this.pass
        })

        var self = this;
        this.Ftp.on("ready", function() {
            console.log("conectado");
            self.Ftp.list(self.remoteData, function(error, data) {
                console.log("directorio listado", error);
                if (error) return null;
                for (var i = data.length - 1; i >= 0; i--) {
                    var remoteFile = data[i];
                    console.log("sincronizando",remoteFile.name);

                    // closure, metaprogramación
                    (function(remoteFile, pendientes) {
                        FileSystem.stat(__dirname + "/" + self.localData + remoteFile.name, function(error, stats) {
                            var obj = {};
                            if (error || stats.size != remoteFile.size || stats.mtime != remoteFile.date) {
                                self.Ftp.get(self.remoteData + remoteFile.name, function(error, stream) {
                                    console.log("descargando " + self.remoteData + remoteFile.name, error);
                                    if (error) return null;

                                    stream.once('close', function() {});
                                    stream.pipe(FileSystem.createWriteStream(self.localData + remoteFile.name));
                                });
                            }
                            if (pendientes == 0) {
                                self.Ftp.end;
                            }
                        });
                    })(remoteFile, i);
                };
            });
        });
    };
}