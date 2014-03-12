ui.tip = function(sender, forceShow) {
  this.forceShow = forceShow ? true : false;
  if (document.getCookie("no_tip") != null && !forceShow) {
    this.hide = this.show = this.setText = function(){};
    return;
  }
  this.sender = sender;
  this.box = this._getBox();
  
  this.content = this.box.getElementsByTagName("p")[0];
  this.closeBtn = this.box.getElementsByTagName("span")[0];
  this.title = this.box.getElementsByTagName("span")[1];
  this.width = 250;
  document.body.appendChild(this.box);
  var self = this;
  this.closeBtn.onclick = function(){
    self.hide();
  };
  
  this.box.onclick = function(event) {
    var evt = fixEvent(event);
    if (evt.srcElement.className == "no_tip") {
      if (evt.srcElement.checked) {
         var date = new Date();
        date.setTime(date.getTime() + 99999999);
        document.setCookie("no_tip", 1, date);
      } else {
        document.removeCookie("no_tip");
      }
    }
  }
};

ui.tip.prototype = {
  _getBox : function() {
    var box = $ce("div");
    box.className = "tip";
    Element.hide(box);
    box.innerHTML = "<div class=\"tip_top\"></div><div class=\"tip_content\"><h3><span class=\"closeBtn\">close</span><span class=\"title\"></span></h3><p></p></div><div class=\"tip_bottom\"></div>";
    
    return box;
  },
  setText : function(title, content){
    this.title.innerHTML = title;
    this.content.innerHTML = content;
    if (this.forceShow) return;
    var span = $ce("label");
    span.innerHTML = "<input class='no_tip' type='checkbox'>" + lang.no_tip;
    this.content.appendChild(span);
  },
  show : function() {
    var tip_bottom = $class("tip_bottom", this.box, "div")[0];
    var tip_top = $class("tip_top", this.box, "div")[0];
    var pos = Element.getPosition(this.sender);
    Element.show(this.box);
    
    this.box.style.width  = this.width + "px";
    this.box.style.visibility = "hidden";
    
    if (this.box.offsetHeight < pos.top) {
      if (tip_top) Element.hide(tip_top);
      var top = pos.top - this.box.offsetHeight;
      this.box.style.top = top - 2 + "px";
    } else {
      if (tip_bottom) Element.hide(tip_bottom);
      this.box.style.top = (pos.top + this.sender.offsetHeight) + "px";
      Element.hide(tip_bottom);
    }
    var p = (this.sender.offsetWidth / 2);
    var num = p;
    if ((pos.left - 10) > (document.body.scrollWidth-this.width)) {
      this.box.style.left = (pos.left-this.width +  this.sender.offsetWidth) + "px";
      num = p + (pos.left - Element.getPosition(this.box).left) - 3;
    }
    else
    {
      num = p + 3;
      this.box.style.left = (pos.left - 10) + "px";
    }
    
    tip_bottom.style.backgroundPosition = num+"px top";
    tip_top.style.backgroundPosition = num+"px bottom";
   
    this.box.style.visibility = "";
  },
  hide: function(delay, duration) {

    if (!delay) {
      Element.hide(this.box);
    } else {
      var self = this;
      window.setTimeout((function(){
        if(ui.effect) {
          if (!duration) duration = 1000;
          var tid = ui.effect.FadeTo(self.box,100 ,0, duration, function(){
            Element.hide(self.box);
          });
          self.box.onmousemove = function() {
            clearTimeout(window.threadList[tid]);
            ui.effect._setOpacity(self.box, 100);
          };
          self.box.onmouseout = function(){
            tid = ui.effect.FadeTo(self.box,100 ,0, duration, function(){
              Element.hide(self.box);
            });
          };
          
        } else {
          Element.hide(self.box);
        }
      }), delay);
    }
  }
};