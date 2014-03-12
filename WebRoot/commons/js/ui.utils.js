ui.utils = {};

ui.utils.tabForm  = function (formName, classActived) {
  this._curr = 0;
  if (classActived) this.actived = classActived; else this.actived = "actived";
  var _self = this;
  var form = get(formName);
  var tabBar = $class("tab-bar", form)[0];
  var tabPage = $class("tab-page", form)[0];

  get(formName).style.display = "block";
  tabBar.children[0].className = _self.actived;
  for (i=0; i<tabPage.children.length; i++)
    if (i > 0) Element.hide(tabPage.children[i]);

  tabBar.onclick = function(e) {
    var evt = fixEvent(e);
    var obj = evt.srcElement;
    if (obj.tagName == "LI") {
 
      for (i=0; i<tabBar.children.length; i++) {
        if (tabBar.children[i] == obj) {
          if (tabBar.children[i].getAttribute("disabled") == "true") return;
          tabBar.children[_self._curr].className = '';
          obj.className = _self.actived;
          Element.hide(tabPage.children[_self._curr]);
          Element.show(tabPage.children[i],'table');
          _self._curr = i;
          break;
        }
      }
    }
  };
};

ui.utils.ImagePreview = function(src, width, height)
{
  var img = new Image();
  var div = $ce("DIV");
  div.style.position = "absolute";
  div.style.top = "300px";
  div.innerHTML = "loading";
  img.src = src + "?r=" + Math.random();
  //alert(img.src);
  //img.style.display = "none"
  //img.height = 1;
  //img.width = 1;
  img.onload = function()
  {
    img.width = img.height = 0;
    _w = img.width;
    _h = img.height;
    img.style.display = "";
    //alert("test")
    ui.effect.ResizeTo(img, _w, _h,200);
  };
  div.appendChild(img);
  document.body.appendChild(div);
};

/* ��Ļ���� */
ui.utils.locker = function(color) {
  this.layer = $ce("div");
  this.innerLayer = $ce("div");
  if (!color) color = "#000";
  this.layer.style.cssText = "background:" + color + ";left:0px;position:absolute;top:0px;z-index:9999;";
  this.innerLayer.style.position = "relative";
  this.innerLayer.style.left = this.innerLayer.style.top = "0px";

  ui.effect._setOpacity(this.layer, 30);
  this.layer.appendChild(this.innerLayer);

  Element.hide(this.layer);
  this.locked = false;

  document.body.appendChild(this.layer);
};

ui.utils.locker.prototype = {
  lock : function(opa) {
    if (!opa) opa = 50;
    ui.effect._setOpacity(this.layer, opa);
    Element.show(this.layer);
    this.locked = true;
    this.resize();
    if (navigator.isIE()) {
      dropDownLists = document.getElementsByTagName("SELECT");
      Outer: for (var i = 0; i< dropDownLists.length; i++) {
        var list = dropDownLists[i];
        var elem = list;
        Inter: do {
          if (Element.getStyle(elem, "position") == "absolute") {
            continue Outer;
          }
        } while((elem = elem.parentNode) != document.body)
        list.style.visibility = "hidden";
      }
    }
  },
  resize : function() {
    if (window.self.innerHeight) {
      this.innerHeight = Math.max(window.self.innerHeight,document.body.scrollHeight);
    } else {
      if (document.documentElement && document.documentElement.clientHeight) {
        this.innerHeight = Math.max(document.documentElement.clientHeight,document.documentElement.scrollHeight);
      } else if (document.body) {
        this.innerHeight = Math.max(document.body.clientHeight,document.body.scrollHeight);
      }
    }
    var innerWidth  = Math.min(document.body.scrollWidth, self.innerWidth||document.body.clientWidth);
    this.layer.style.width = innerWidth + "px";
    this.innerLayer.style.width = this.layer.style.width;
    var h = this.innerHeight-1;
    this.layer.style.height = h + "px";
    this.innerLayer.style.height = h + "px";

  },
  unLock : function() {
    this.locked = false;
    Element.hide(this.layer);
    if (navigator.isIE()) {
      dropDownLists = document.getElementsByTagName("SELECT");
      for (var i = 0; i< dropDownLists.length; i++) {
        dropDownLists[i].style.visibility = "";
      }
    }
  },
  appendChild : function(elem){
    this.innerLayer.appendChild(elem);
  }
};

ui.inlineEditBox = {
  sender : false,
  edit : function(sender,callback) {
    var self = this;
    this.sender = sender;
    if (sender.getAttribute("editing")!=null) return;
    var cacheText = sender.innerHTML;
    sender.innerHTML = "";
    var textBox = $ce("input");
    textBox.value = cacheText;
    textBox.onblur = (function(){
      sender.innerHTML = textBox.value;
      if (textBox.value != cacheText) callback(sender);
      this.endEdit();
    }).bind(this);

    textBox.onkeypress = function(event) {
      event = fixEvent(event);
      if (event.keyCode == 13) {
        this.blur();
        self.endEdit();
      } else if (event.keyCode == 27) {
        try {
          self.sender.innerHTML = cacheText;
          self.endEdit();
        }
        catch(ex) {
          alert(ex.description);
        }
      }
    };

    sender.appendChild(textBox);
    textBox.focus();
    sender.setAttribute("editing", true);
  },
  endEdit: function(){
    this.sender.removeAttribute("editing");
  }
};

ui.Draggable = function (o, h, m) {
  var x = 0, y = 0;
  var i=0;
  var draging = function (e) {
    e = fixEvent(e);
    o.style.left = (Event.pointerX(e) - x) + "px";
    o.style.top  = (Event.pointerY(e) - y) + "px";

  };

  var endDrag = function() {
    Event.stopObserving(document.body, 'mousemove', draging);
    Event.stopObserving(document.body, 'mouseup', endDrag);
    if(m)m.style.display = 'none';
    Element.setSelectable(document.body, true);
  };

  var starDrag = function(e) {
    e.returnValue = false;
    e = fixEvent(e);
    x = Event.pointerX(e) - o.offsetLeft;
    y = Event.pointerY(e) - o.offsetTop;
    Event.observe(document.body, 'mousemove', draging);
    Event.observe(document.body, 'mouseup', endDrag);
    Element.setSelectable(document.body, false);
    if(m)m.style.display = '';
  };

  Event.observe(h, 'mousedown', starDrag);
  h.style.cursor = 'move';
};
