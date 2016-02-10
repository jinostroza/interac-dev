// libs
var FtpClient = require('ftp');
var FileSystem = require('fs');
var Path = require('path');

function Sincronizador() {
    this.Ftp = new FtpClient();
    this.server = "54.208.243.25";
    // this.server = "localhost";
    this.port = 21;
    this.user = "colivares";
    this.pass = "colivares";
    this.remoteData = "./";
    this.localData = __dirname + "/public/media/";
    this.firstTime = true;
}

(function() {
    // método privado
    var processData = function(data) {
        var self = this;

        if (!data.length) {
            self.Ftp.end();
            return;
        }

        var remoteFile = data.shift();

        console.log("sincronizando "+remoteFile.name);
        FileSystem.stat(self.localData + remoteFile.name, function(error, stats) {
            if (error || stats.size != remoteFile.size) {
                console.log("intentando descargar", remoteFile.name);

                self.Ftp.get(self.remoteData + remoteFile.name, function(error, stream) {
                    if (error) {
                        console.log("no se pudo descargar", error);
                        return null;
                    };
                    console.log("se ha descargado " + self.remoteData + remoteFile.name);

                    stream.once('close', function() {});
                    stream.pipe(FileSystem.createWriteStream(self.localData + remoteFile.name));
                    processData.call(self, data);
                });
            } else {
                processData.call(self, data);
            }
        });
    };

    var clearOldData = function(data) {
        var self = this;
        var ftpFiles = getFilesNames.call(this, data);

        FileSystem.readdir(this.localData, function(err, files) {
            for (var i = 0; i < files.length; i++) {
                if (ftpFiles.indexOf(files[i]) != -1) continue;
                var eliminar = self.localData + files[i];
                FileSystem.unlink(eliminar, function(err) {
                    if (err) return;
                    console.log("Eliminando", eliminar);
                });
            }
        });
    };

    var getFilesNames = function(data) {
        var retorno = [];
        for (var i = 0; i < data.length; i++) {
            retorno.push(data[i].name);
        }
        return retorno;
    };

    // método público
    this.run = function() {
        this.syncFiles = [];
        this.Ftp.connect({
            host: this.server,
            port: this.port,
            user: this.user,
            password: this.pass
        });

        if (!this.firstTime) return;

        var self = this;

        this.Ftp.on("ready", function() {
            console.log("conectado al ftp");
            self.Ftp.list(self.remoteData, function(error, data) {
                console.log("directorio remoto listado");
                if (error) return null;
                if (!data.length) return null;

                // el slice lo hago para hacer una copia del array, ya que en js los parámetros son por referencia
                clearOldData.call(self, data);
                processData.call(self, data.slice());
            });
        });

        this.Ftp.on("close", function(closed) { console.log("conexión ftp cerrada", closed); });
        this.Ftp.on("end", function() { console.log("finalizando conexión"); });
        this.Ftp.on("error", function(error) { console.log("error:", error); });

        this.firstTime = false;
    };
}).call(Sincronizador.prototype);

module.exports = Sincronizador;