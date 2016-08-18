var express=require("express");
var FileSystem = require("fs");
var pg = require("pg");
var moment = require("moment");
var last;
var enrutador = function(App) {
    var conString = "pg://postgres@localhost:5432/interacLocal";

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
    App.get('/insert',function(req,res){
        var content=req.query.data;
        /*Check if there is any row else put one row for all time*/
        var fecha = moment().format("YYYY-MM-DD HH:mm:ss");
        
           /*add one row*/
                client.query("INSERT into slider_log(contenido_name, fec_inicio_slide, id_modulo) VALUES ('"+content+"','"+fecha+"','201')RETURNING id_slider_log ;",function(err,result){
                    last=result.rows[0].id_slider_log;
                    console.log(last);
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
        App.get('/update',function(req,res){
        var content=req.query.data;
        /*Check if there is any row else put one row for all time*/
        var fecha = moment().format("YYYY-MM-DD HH:mm:ss");
        
           /*add one row*/
           if(last!=0){
                client.query("UPDATE slider_log SET fec_fin_slide='"+fecha+"' WHERE id_slider_log='"+last+"';",function(err,rows){
                    if(err)
                      {
                        console.log(err);
                        res.json({"error":"1"});
                      }
                      else
                        {
                          last=0;
                          res.json({"yes":"1"});
                        }
                });           
              }            
        
           });

        App.get('/select',function(req,res){
        var content=req.query.data;
        /*Check if there is any row else put one row for all time*/
        client.query("SELECT insert_analitics();",function(err,rows){
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