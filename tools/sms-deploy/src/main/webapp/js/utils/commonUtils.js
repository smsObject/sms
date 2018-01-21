/**
 * Created by Administrator on 2017/8/4.
 */
function convertParamsMapToUri(paramsMap) {
    var uri = "";
    var isFirst = true;
    for (var key in paramsMap) {
        var keyValue = key + "=" + paramsMap[key];
        if (isFirst) {
            isFirst = false;
        } else {
            uri += "&";
        }
        uri += keyValue;
    }
    return uri;
}

function openUrlInNewWindow(url) {
    if (url.startWith('http://')) {
        window.open(url);
    } else {
        window.open('http://' + url);
    }
}

String.prototype.startWith = function (s) {
    if (s == null || s == "" || this.length == 0 || s.length > this.length) {
        return false;
    }
    return this.substr(0, s.length) == s;
};