var OAuthLib = require("oauth");
var OAuth2 = OAuthLib.OAuth2;

var Twitter = {};

(function() {
    var consumerKey = "01h9ML7FRJ7rrfO4ri9kZxCYp";
    var consumerSecret = "6EaxEXkxUMqbKw7XPBucESlQ4kSXRjrRAvNic0KbLdTWGLcASg";

    var getAuth = function() {
        return new OAuth2(
            consumerKey,
            consumerSecret,
            "https://api.twitter.com/",
            null,
            "oauth2/token",
            null
        );
    }

    var getToken = function() {
        var oauth2 = getAuth();

        oauth2.getOAuthAccessToken(
            "",
            {"grant_type": "client_credentials"},
            function (e, access_token, refresh_token, results) {
                console.log("bearer: ", access_token);

                oauth2.get(
                    "https://api.twitter.com/1.1/search/tweets.json?q=luis",
                    access_token,
                    function () {
                        console.log(arguments);
                    }
                );
            }
        );
    };

    this.get = function(url) {
        getToken();
    };

}).apply(Twitter);

Twitter.get();