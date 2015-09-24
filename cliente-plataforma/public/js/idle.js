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
window.onload = function() {
    _idleSecondsCounter = 0;
};

var win=window.setInterval(CheckIdleTime, 1000);

function CheckIdleTime() {
    _idleSecondsCounter++;
    var oPanel = document.getElementById("SecondsUntilExpire");
    if (oPanel)
        oPanel.innerHTML = (IDLE_TIMEOUT - _idleSecondsCounter) + "";
    if(window.top.location.href=="http://localhost:3000"){
        _idleSecondsCounter=0;
        IDLE_TIMEOUT=3600;
    }
    else {
       if (_idleSecondsCounter >= IDLE_TIMEOUT)
     {
         _idleSecondsCounter=0;
         IDLE_TIMEOUT=60;
         window.open("http://localhost:3000","_blank");
         window.clearInterval(win);
         window.top.close();

            }}
}