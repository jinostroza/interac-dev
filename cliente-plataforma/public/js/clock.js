var Clock = {};

(function() {
    this.start = function() {
        var dateTime = new Date();

        // These 3 variables add a leading '0' to keep the date properly formatted.
        var minutesTwoDigitsWithLeadingZero = ("0" + dateTime.getMinutes()).substr(-2);
        var secondsTwoDigitsWithLeadingZero = ("0" + dateTime.getSeconds()).substr(-2);

        $("div.clock").html(dateTime.getHours() + ":" + minutesTwoDigitsWithLeadingZero + ":" + secondsTwoDigitsWithLeadingZero);
        setTimeout(this.start.bind(this), 100);
    };
}).apply(Clock);