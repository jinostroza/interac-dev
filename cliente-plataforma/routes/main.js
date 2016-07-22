var express=require("express");
var FileSystem = require("fs");
var pg = require("pg");

var enrutador = function(App) {
    var conString = "pg://postgres:vince2315@54.208.243.25:5432/interac-dev";

    var client = new pg.Client(conString);
    client.connect();
    // la raiz del sistema
    App.get("/", function(req, res) {
        res.render("index", {
            title: "Plataforma Interac"
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
    App.get('/update',function(req,res){
        var content=req.query.data;
        /*Check if there is any row else put one row for all time*/
        var timeInMss = Date.now();

        console.log(timeInMss);
           /*add one row*/
                client.query("INSERT into content(img_name) VALUES ('"+content+"');",function(err,rows){
                    if(err)
                      {
                        console.log(err);
                        res.json({"error":"1"});
                      }
                      else
                        {
                          res.json({"yes":"1"});
                        }
                });
            
        
});
};

module.exports = enrutador;