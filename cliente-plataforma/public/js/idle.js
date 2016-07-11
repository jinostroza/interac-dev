/**
 * Created by Joaco on 23/09/2015.
 */
var IDLE_TIMEOUT = 10; //seconds
var _idleSecondsCounter = 0;
document.onclick = function() {
    _idleSecondsCounter = 0;
};
document.onmousemove = function() {
    _idleSecondsCounter = 0;
};
document.onkeypress = function() {
    _idleSecondsCounter = 0;
};
var win=window.setInterval(CheckIdleTime, 1000);

function CheckIdleTime() {
    var oPanel = document.getElementById("SecondsUntilExpire");
    if (oPanel)
        oPanel.innerHTML = (IDLE_TIMEOUT - _idleSecondsCounter) + "";
    if (_idleSecondsCounter >= IDLE_TIMEOUT) {
        if($('#conten').css('display') == 'block'){
            _idleSecondsCounter=0;
            IDLE_TIMEOUT=10;


        }
        else {
            _idleSecondsCounter = 0;
            IDLE_TIMEOUT = 10;

            document.getElementById("conten").style.display = "block";
            document.getElementById("ifr1").style.display = "none";
            window.clearInterval(win);

        } }
}