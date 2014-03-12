

Object.extend = function(destination, source){
  for (property in source)
    destination[property] = source[property];
  return destination;
};

/* HTML Element Extensions */
if (window.HTMLElement){
  HTMLElement.prototype.__defineSetter__("innerText",function(sText) { var parsedText=document.createTextNode(sText); this. TML=parsedText; return parsedText; });
  HTMLElement.prototype.__defineGetter__("innerText",function(){ var r=this.ownerDocument.createRange(); r.selectNodeContents(this); return r.toString(); });
  HTMLElement.prototype.__defineGetter__("children", function(){
    for (var i = 0; i < this.childNodes.length; i++) {
      var node = this.childNodes[i];
      if (node.nodeType == 3 && !/\S/.test(node.nodeValue))
        this.removeChild(node);
    }
    return this.childNodes;
  });
}

var Element = {
  next : function(elem) {
    var n = elem;
    do {
       n = n.nextSibling;
    } while (n && n.nodeType != 1)
    return n;
  },
  prev : function(elem) {
    var n = elem;
    do{
       n = n.previousSibling;
    } while (n && n.nodeType != 1)
    return n;
  },
  show : function(elem, arg) {
    if (navigator.isIE()) if (arg=="table" || arg=="table-row" || arg=="table-cell") arg = "block";
    elem.style.display = (typeof(arg) == "undefined") ? "" : arg;

  },
  hide : function(elem) {
    elem.style.display = "none";
  },
  remove : function(elem) {
    elem.parentNode.removeChild(elem);
  },
  addClass : function(elem, className) {
    if (!this.hasClass(elem, className)){
      var arr = elem.className.split(" ");
      arr.push(className);
      elem.className = arr.join(" ");
    }
  },
  removeClass :function(elem, className) {
    if (this.hasClass(elem, className)){
      var arr = elem.className.split(" ");
      arr.remove(className);
      elem.className = arr.join(" ");
    }
  },
  hasClass : function(elem, className) {
     var arr = elem.className.split(" ");
     return arr.inArray(className);
  },
  contains : function(elem, find){
    do {
      if (find == elem)
      {
        return true;
      }
    } while(find = find.parentNode)
    return false;
  },
  getPosition : function(elem) {
    var valueT = 0, valueL = 0;
    do {
      valueT += elem.offsetTop  || 0;
      valueL += elem.offsetLeft || 0;
      elem = elem.offsetParent;
    } while (elem);
    var pos = {top:valueT,left:valueL};
    return pos;
  },
  getStyle: function(element, style) {
    element = get(element);
    style = style == 'float' ? 'cssFloat' : style;
    var value = '';
    try {
        var value = element.style[style];
    } catch(ex) {
        return value;
    }
    if (!value) {
      if(element.currentStyle) {
        value = element.currentStyle[style];
      } else {
        var css = document.defaultView.getComputedStyle(element, null);
        value = css ? css[style] : null;
      }
    }
    if (style == 'opacity') return value ? parseFloat(value) : 1.0;
    return value == 'auto' ? null : value;
  },
  setSelectable: function(elem, selectable)
  {
    if (navigator.isFirefox()) {
      if (selectable)
        elem.style.MozUserSelect = "";
      else
        elem.style.MozUserSelect = "none";
    }
    else {
      if (selectable)
        Event.stopObserving(elem, "selectstart", this._falseFunction);
      else
        Event.observe(elem, "selectstart", this._falseFunction);
    }
  },
  _falseFunction: function() {
    return false;
  },
  cleanWhitespace: function(element) {
    for (var i = 0; i < element.childNodes.length; i++) {
      var node = element.childNodes[i];
      if (node.nodeType == 3 && !/\S/.test(node.nodeValue))
        element.removeChild(node);
    }
  }
};

/* Event Extensions */
if (window.Event) {
  Event.prototype.__defineSetter__("returnValue",function(e){ if(!e)this.preventDefault(); return e;});
  Event.prototype.__defineGetter__("srcElement",function(){ var node=this.target;while(node.nodeType!=1){node=node.parentNode};return node;});
  Event.prototype.__defineSetter__("cancelBubble",function(b){ if(b)this.stopPropagation(); return b; });
} else {
  var Event = new Object();
}

/* String Object Extensions */
Object.extend(String.prototype, {
  trim : function() { return this.replace(/^\s*(.*?)\s*$/, '$1'); },
  escape : function() { return this.replace(/&/g, '&amp;').replace(/"/g, '&quot;').replace(/</g, '&lt;').replace(/>/g, '&gt;');},
  isEmpty : function() { return (this.trim() == ''); },
isEmail : function() { var reg = /^([a-z0-9+_]|\-|\.|\-)+@([\w|\-]+\.)+[a-z]{2,4}$/i; return reg.test(this); },
  isDate : function() { var reg = /^\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\d|3[0-1])$/; return reg.test(this); },
  isTime : function() { var reg = /^([0-1]\d|2[0-3]):[0-5]\d:[0-5]\d$/; return reg.test(this); }
});

/* Navigator Extensions */
Object.extend(navigator, {
  isIE : function() { return this.userAgent.toLowerCase().indexOf("msie") != - 1; },
  isFirefox : function() { return this.userAgent.toLowerCase().indexOf("firefox") != - 1; },
  isSafari : function() { return this.userAgent.toLowerCase().indexOf("safari") != - 1; },
  isOpera : function() { return this.userAgent.toLowerCase().indexOf("opera") != - 1; }
});

/* Document Extensions */
Object.extend(document, {
  getCookie : function(sName) {
    var aCookie = this.cookie.split("; ");
    for (var i=0; i < aCookie.length; i++){
      var aCrumb = aCookie[i].split("=");
      if (sName == aCrumb[0]) return decodeURIComponent(aCrumb[1]);
    }
    return null;
  },

  setCookie : function(sName, sValue, sExpires) {
    var sCookie = sName + "=" + encodeURIComponent(sValue);
    if (sExpires != null) sCookie += "; expires=" + sExpires;
    this.cookie = sCookie;
  },

  removeCookie : function(sName) {
    this.cookie = sName + "=; expires=Fri, 31 Dec 1999 23:59:59 GMT;";
  },

  require : function(path, callback, type){
    var s,i;
    var id = path.replace(".","").replace("/","");

    if (!type || type == "js"){
      var ss = this.getElementsByTagName("script");
      for(i =0;i < ss.length; i++){
        if(ss[i].src && ss[i].src.indexOf(path) != -1)return ss[i];
      }

      s      = $ce("script");
      s.id   = id;
      s.type = "text/javascript";
      s.src  = path;
    }
    else {
      var ss = this.getElementsByTagName("link");
      for(i =0;i < ss.length; i++){
        if(ss[i].src && ss[i].src.indexOf(path) != -1)return ss[i];
      }

      s = $ce("link");
      s = document.createElement("link");
      s.rel = "stylesheet";
      s.type = "text/css";
      s.href = path;
      s.disabled = false;
    }
    var head = this.getElementsByTagName("head")[0];
    head.appendChild(s);
    if (callback) {
      if (!navigator.isIE() && type == "css") {
        window.setTimeout(function(){callback.call()}, 500);
      }
      else {
        s.onload = s.onreadystatechange= function(){
          if(this.readyState && this.readyState=="loading")return;
          callback.call();
        }
      }
    }
  }
});

/* Array Extensions */
Object.extend(Array.prototype, {
  isEmpty : function() { return (this.length == 0); },
  inArray : function(item) { return (this.itemIndex(item) > -1); },
  remove : function(item, num) { var removed = 0;
  for (var i=0; i<this.length; i++) {
    if (this[i] == item) {
      this.splice(i, 1);
      if (num > 0 && num >= removed) break;
        removed++;
      }
    }
  },
  itemIndex : function(item) { var reval = -1; for (var i=0; i<this.length; i++) { if (this[i] == item) { reval = i; break; } } return reval; }
});

Object.extend(Event, {
  pointerX: function(event) {
    return event.pageX || (event.clientX +
      (document.documentElement.scrollLeft || document.body.scrollLeft));
  },
  pointerY: function(event) {
    return event.pageY || (event.clientY +
      (document.documentElement.scrollTop || document.body.scrollTop));
  },
  observers: false,
  _observeAndCache: function(element, name, observer, useCapture) {
    if (!this.observers) this.observers = [];
    if (element.addEventListener) {
      this.observers.push([element, name, observer, useCapture]);
      element.addEventListener(name, observer, useCapture);
    } else if (element.attachEvent) {
      this.observers.push([element, name, observer, useCapture]);
      element.attachEvent('on' + name, observer);
    }
  },
  observe: function(element, name, observer, useCapture) {
    useCapture = useCapture || false;

    if (name == 'keypress' &&
        ((navigator.appVersion.indexOf('AppleWebKit') > 0)
        || element.attachEvent))
      name = 'keydown';

    this._observeAndCache(element, name, observer, useCapture);
  },
  stopObserving: function(element, name, observer, useCapture) {
    useCapture = useCapture || false;

    if (name == 'keypress' &&
        ((navigator.appVersion.indexOf('AppleWebKit') > 0)
        || element.detachEvent))
      name = 'keydown';

    if (element.removeEventListener) {
      element.removeEventListener(name, observer, useCapture);
    } else if (element.detachEvent) {
      element.detachEvent('on' + name, observer);
    }
  }
});

if (window.HTMLTableRowElement)
  HTMLTableRowElement.prototype.__defineGetter__('rowIndex', function() { var index = -1; var table = this.parentNode.parentNode; for (i = 0; i < table.rows.length; i ++ ) { if (table.rows[i] == this) { index = i; break; } } return index; });

/* Window Extensions for IE */
if (!window.HTMLElement) {
  window['innerHeight'] = { valueOf:function(){return document.documentElement.clientHeight;}, toString:function(){return document.documentElement.clientHeight;} };
  window['innerWidth'] = { valueOf:function(){return document.documentElement.clientWidth;}, toString:function(){return document.documentElement.clientWidth;} };
};


/* Custom Call Method */
if (typeof(Function.prototype.call) != "function") {
  Function.prototype.call = function (obj) {
    obj._554fcae493e564ee0dc75bdf2ebf94ca = this;
    var args = [];
    for (var i = 0; i < arguments.length - 1; i++){
      args[i] = "arguments[" + (i + 1) + "]";
    }
    var result = eval("obj._554fcae493e564ee0dc75bdf2ebf94ca(" + args.join(",") + ");");
    delete obj._554fcae493e564ee0dc75bdf2ebf94ca;

    return result;
  }
}
Function.prototype.bind = function(object) { var __method = this; return function(){__method.apply(object, arguments); }};

function get(id, win) {
  var elem;
  if (typeof win === 'undefined') win = window;
  elem = (typeof(id)=="string") ? win.document.getElementById(id) : elem = id;

  return elem;
}

function $ce(tagName, doc) {
  if (typeof doc === 'undefined') doc = document;
  var newElem = doc.createElement(tagName);
  return newElem;
}

function $class(className, parentElement, tagName) {
  var elements = new Array();
  var children = (get(parentElement) || document.body).getElementsByTagName(tagName||'*');
  for(var i=0; i<children.length; i++) {
    if (children[i].className.match(new RegExp("(^|\\s)" + className + "(\\s|$)")))
      elements.push(children[i]);
  }
  elements.item = function(idx){
   return this[idx];
  };
  return elements;
}


function fixEvent(e, win) { if (typeof win === 'undefined') win = window; var evt = (typeof e == "undefined") ? win.event : e; return evt;}
function confirm_redirect(msg, url) { if (confirm(msg)) location.href=url; }

/* declare ui namespace */
var ui = {};