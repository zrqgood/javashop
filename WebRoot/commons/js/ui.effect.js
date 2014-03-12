/**
 * ECMall: UI Effect
 * ============================================================================
 * (C) 2005-2008 ShopEx Inc., all rights reserve.
 * Homepage:  http://www.shopex.cn
 * ============================================================================
 * $Id: ui.effect.js,v 1.1 2009/09/28 09:21:41 kingapex Exp $
 */
window.threadList = {};
ui.effect = {
  FadeTo:function(element, start, end, duration, callback){
    var self = element, step = 0;
    if(end == undefined){
        end=start;
        duration=0;
    }
    var tid = "___a2d3e" + Math.floor(Math.random()*10+1);

    if(duration<=0) start=end;
    else step=10*(end-start)/duration;
    function t(){
        start+= step;
        if((start-end)*step>=0)start=end;

        self.style.filter='alpha(opacity='+Math.round(start)+')';

        if(!self.style.filters) self.style.MozOpacity=Math.round(start)/100;
        if(start!=end)
           window.threadList[tid] = window.setTimeout(arguments.callee,10);
        else if(callback) callback.call();

    };

    window.threadList[tid] = window.setTimeout(t, 10);
    return tid;
  },
  _setOpacity:function(elem, value)
  {
    elem.style.filter='alpha(opacity='+Math.round(value)+')';
    if(!elem.style.filters) elem.style.MozOpacity=Math.round(value)/100;
  },
  ResizeTo:function(element,width, height, duration){
    var self = element, step = 0;
    var startWidth = self.offsetWidth;
    var startHeight = self.offsetHeight;

    stepW = 10 * (width-self.offsetWidth)/duration;
    stepH = 10 * (height-self.offsetHeight)/duration;

    function t(){
      startWidth += stepW;
      startHeight += stepH;

      if((startWidth-width)*stepW>=0)startWidth=width;
      if((startHeight-height)*stepH>=0)startHeight=height;

      self.style.width = parseInt(startWidth) + "px";
      self.style.height = parseInt(startHeight) + "px";

      if(startWidth!=width && startHeight != height)window.setTimeout(arguments.callee,10);
    };
    window.setTimeout(t,10);
  },
  SlideUp: function(){},
  ResizeHeightTo:function(element, height, duration, callback){
    var self = element, step = 0;
    var startHeight = self.offsetHeight;

    step = parseInt(10 * (height-self.offsetHeight)/duration);
    s = true;
    if (step>0) {
        s = false;
    }
    function t(){
        startHeight += step;
        if((s && (startHeight-height) < 0) || (!s && (startHeight-height) > 0)) {
            startHeight=height;
        }
        self.style.height = startHeight + "px";
        if(startHeight != height)window.setTimeout(arguments.callee,10);
        else if(callback) callback();
    };
    window.setTimeout(t,10);
  }
  ,MoveTo:function(element, x, y, duration){
    var self = element, step = 0;
    var startLeft = Element.getPosition(self).left;
    var startTop = Element.getPosition(self).top;

    stepL = 10 * (x-self.offsetWidth)/duration;
    stepH = 10 * (height-self.offsetHeight)/duration;

    function t(){
      startWidth += stepW;
      startHeight += stepH;

      if((startWidth-width)*stepW>=0)startWidth=width;
      if((startHeight-height)*stepH>=0)startHeight=height;

      self.style.width = parseInt(startWidth) + "px";
      self.style.height = parseInt(startHeight) + "px";

      if(startWidth!=width && startHeight != height)window.setTimeout(arguments.callee,10);
    };
    window.setTimeout(t,10);
  },
  blink : function(element, time) {
    var count = 0;
    function t()
    {
      if (count>=time)
      {
        ui.effect._setOpacity(element, 100);
        return;
      }
      count++;
      if (count%2 == 0)ui.effect._setOpacity(element, 90);else ui.effect._setOpacity(element, 30);
      window.setTimeout(arguments.callee, 120);
    }
    window.setTimeout(t,10);
  }
  ,scroll:function(direction, element, distance, duration, callback){
    var self = element;
    var step = 10*(distance)/duration;
    var scrollLeft = self.scrollLeft;
    var target = distance;
    var callback = callback ? callback : new Fucnction();
    function t() {
        switch(direction){
            case 'left':
                if(self.scrollLeft > (target+scrollLeft)) {
                  self.scrollLeft = distance+scrollLeft;
                  callback.call();
                  return;
                };
                self.scrollLeft += step;
                break;
            case 'right':
                self.scrollLeft -= step;
                if (self.scrollLeft<=0 || self.scrollLeft<=(scrollLeft - distance)) {
                  self.scrollLeft = scrollLeft - distance;
                  callback.call();
                  return;
                };
                break;
        };
        window.setTimeout(arguments.callee,10);
    };
    window.setTimeout(t,10);
  },
  shadow: function (obj) {
    var shadow = $ce('div');
    shadow.className     = 'ECM_shadow_layer';
    shadow.style.cssText = 'background:#aaaaaa none repeat scroll 0%;';
    shadow.style.zIndex= obj.style.zIndex;
    //shadow.style.width = obj.style.width;
    shadow.style.height= obj.style.height;
    shadow.style.top   = obj.style.top;
    shadow.style.left  = obj.style.left;
    shadow.style.position = 'fixed';
    if (navigator.isIE()) {
      shadow.style.position = 'absolute';
      Event.observe(window, 'scroll', function () {
        shadow.style.top= (document.body.scrollTop ? document.body.scrollTop : document.documentElement.scrollTop) + 100;
      });
    }
    obj.style.position = 'relative';
    obj.style.left    = '-3px';
    obj.style.top     = '-3px';
    shadow.appendChild(obj);
    return shadow;
  }
};