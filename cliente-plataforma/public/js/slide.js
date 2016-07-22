var Slide = {};

(function () {
    var showTime = 10 * 1000; // 10 segundos
    var syncTime = 120 * 1000; // 120 segundos
    var sliderTimerID = null;
    var syncTimerID = null;
    var index = 0; //index video duration
    this.start = function () {
        var self = this;
        jQuery.get("/getMedia").done(function (data) {
            var mediaFiles = JSON.parse(data);

            for (var i = mediaFiles.length - 1, display = "block"; i >= 0; i--, display = "none") {
                if (mediaFiles[i].endsWith(".mp4") || mediaFiles[i].endsWith(".ogg")) {
                    var video = jQuery("<video controls='false' style='width: 1060px;height: 1400px' ><source></source></video>");
                    video.css("display", display);
                    video.find("source").first().attr("src", mediaFiles[i]);
                    video.find("source").first().attr("type", "video/" + mediaFiles[i].substr(mediaFiles[i].lastIndexOf(".") + 1, mediaFiles[i].length));
                    video.find("source").first().attr("id", "video");
                    jQuery("div.content").append(video);
                } else {
                    var img = jQuery("<img id='img1' style='width: 1060px;height: 1400px' ></img>");
                    img.attr("src", mediaFiles[i]);
                    img.css("display", display);
                    jQuery("div.content").append(img);
                }
            }


            sliderTimerID = setTimeout(self.change.bind(self), showTime);
            syncTimerID = setTimeout(self.checkNewData.bind(self), syncTime);
        });
    };

    this.change = function () {
          var media = jQuery("div.content").find("img, video");
          var d=document.querySelectorAll('video');

        for (var i = 0; i < media.length; i++) {
           if (jQuery(media[i]).is(":not(:visible)")) continue;
            var siguiente = jQuery(media[(i + 1) % media.length]);
            //insertando data a bd imgbd
            var fecha = Date.now();
           // db.friends.add({name:siguiente.attr('src').substr(14), date: fecha});
           addinsql(siguiente.attr('src').substr(14));
            //to do : implementar webservices para guardar imagen y tiemstamp
            jQuery(media[i]).hide();
            siguiente.show();

            if (siguiente.is("video")) {
                index = index + 1;
                 if(index <= d.length) {
                    var index2 = index - 1;
                    var minutes = parseInt(d[index2].duration , 10);                
                    showTime = minutes * 1000;

                 }else {
                    index = 1;
                    var minutes = parseInt(d[index - 1].duration , 10);
                  showTime = minutes * 1000;
                 }
                siguiente.get(0).play();
            }else {

                  showTime = 10 * 1000; // 10 segundos
            }
            break;
        }
        if(d.length <= 1) {
            sliderTimerID = setTimeout(this.change.bind(this), showTime);
             showTime = 10 * 1000; // 10 segundos
        }
        else{
            sliderTimerID = setTimeout(this.change.bind(this), showTime);
        }
              

    };

    this.checkNewData = function () {
        jQuery.get("/getMedia").done(function (data) {
            var media = jQuery("div.content").find("img, video");
            var syncMedia = JSON.parse(data);
            var encontrado = false;

            for (var i = 0; i < syncMedia.length; i++) {
                for (var j = 0; j < media.length; j++) {
                    var obj = $(media[j]);
                    var src = obj.is("video") ? obj.find("source").attr("src") : obj.attr("src");

                    if (src == syncMedia[i]) {
                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado) {
                    var display = "none";
                    if (syncMedia[i].endsWith(".mp4") || syncMedia[i].endsWith(".ogg")) {
                        var video = jQuery("<video controls='false'><source></source></video>");
                        video.css("display", display);
                        video.find("source").first().attr("src", syncMedia[i]);
                        video.find("source").first().attr("type", "video/" + syncMedia[i].substr(syncMedia[i].lastIndexOf(".") + 1, syncMedia[i].length));
                        jQuery("div.content").append(video);
                    } else {
                        var img = jQuery("<img></img>");
                        img.attr("src", syncMedia[i]);
                        img.css("display", display);
                        jQuery("div.content").append(img);
                    }
                }
            }
        });
    };
    
}).apply(Slide); //(namespace proxy)