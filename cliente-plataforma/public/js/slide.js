var Slide = {
    mediaFiles: []
};

(function() {
    this.start = function() {
        console.log("tengo que hacer un ajax'zaso para enterarme de los files");
    };

    this.checkNewData = function() {
        console.log("otro ajax'zaso, pero ahora mandamos los files actuales para concatenarle los nuevos");
    };
}).apply(Slide);