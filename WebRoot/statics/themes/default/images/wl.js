

// JScript 文件

document.write('<script src=http://wl.360buy.com/wl.aspx?url='+escape(document.location)+'&title='+escape(document.title));
document.write('></script>');

//call uri
var calluri = "http://fairyservice.360buy.com/WebService.asmx/MarkEx?callback=?";

//callback function
callback1 = function(data) {
    ;
}

