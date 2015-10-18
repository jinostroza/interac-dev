/**
 * Created by Joaco on 23/09/2015.
 */
var IDLE_TIMEOUT = 60; //seconds
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
    _idleSecondsCounter++;
    var oPanel = document.getElementById("SecondsUntilExpire");
    if (oPanel)
        oPanel.innerHTML = (IDLE_TIMEOUT - _idleSecondsCounter) + "";

    if (_idleSecondsCounter >= IDLE_TIMEOUT) {
        if(window.top.location.href=="http://localhost:3000"){
            _idleSecondsCounter=0;
            IDLE_TIMEOUT=180;
        }
        else {
            _idleSecondsCounter = 0;
            IDLE_TIMEOUT = 180;
            newWindow.targe
            window.open("http://localhost:3000", "_blank");
            window.clearInterval(win);
            window.top.close();
        } }
}