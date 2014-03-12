var magnifier = function(sender, width, height)
{
  this.img = sender;
  this.width =  (width != undefined) ? width : this.img.width;
  this.height = (height != undefined) ? height : this.img.height;
  this.img.parentNode.style.width = this.img.width;
  this.img.parentNode.style.height = this.img.height;
  this.img.parentNode.style.position = "relative";
  this.outer = this.img.parentNode;
  this.org = new Image();
  this.org.src = sender.getAttribute("org");
  var self = this;
  this.org.onload = function() {
    this.style.margin = "";
    if ((this.height-this.width)>0){
        this.style.marginLeft = this.style.marginRight = ((this.height-this.width)/2) + "px";
    }
    if ((this.height-this.width)<0){
        this.style.marginTop = this.style.marginBottom = (Math.abs(this.height-this.width)/2) + "px";
    }
  };

  this.delegate = new Object();
  this.delegate.select = this.select.bind(this);
  this.delegate.start = this.start.bind(this);

  Event.observe(this.img, "mousemove", this.delegate.start);

  this.box = $ce("DIV");
  this.box.className = "showBox";
  this.box.style.width =  this.width + "px";
  this.box.style.height = this.height + "px";
  this.box.style.overflow = "hidden";
  this.box.style.position = "absolute";
  this.box.style.left = this.box.style.top = "0px";
  this.box.style.border = "1px solid #0C0";
  this.box.style.background = "#fff";
  this.box.appendChild(this.org);
  Element.hide(this.box); 
  document.body.appendChild(this.box);

};

magnifier.prototype = {
  setImage : function(url) {
    var self = this;
    var img = $ce("IMG");
    img.src = url;
    img.onload = function() {
        this.style.margin = "";
        if ((this.height-this.width)>0){
            this.style.marginLeft = this.style.marginRight = ((this.height-this.width)/2) + "px";
        }
        if ((this.height-this.width)<0){
            this.style.marginTop = this.style.marginBottom = (Math.abs(this.height-this.width)/2) + "px";
        }
    };
    Element.remove(this.org);
    self.box.appendChild(img);
    this.org = img;
  },
  start: function() {
    Event.observe(document, "mousemove", this.delegate.select);
    Event.stopObserving(this.img, "mousemove", this.delegate.start);
  },
  select : function(event) {
  	 // alert( "selArea is " + this.selArea);
    if (!this.selArea) {
    	// alert("append");
      this.selArea = $ce("div");
      this.selArea.className = "selArea";
      this.selArea.style.width = "40px";

      this.selArea.style.height = parseInt(this.height / this.width * 40) + "px";
      this.selArea.style.position = "absolute";
      this.selArea.style.background = "#666";
      this.selArea.style.border = "1px solid #0F0";
      this.selArea.style.cursor = "move";
      ui.effect._setOpacity(this.selArea, 50);

      this.img.parentNode.appendChild(this.selArea);
     
    }

    var X = Event.pointerX(event);
    var Y = Event.pointerY(event);
    var pos = Element.getPosition(this.img);
    pos.right = pos.left + this.img.offsetWidth;
    pos.bottom = pos.top + this.img.offsetHeight;

    var paddingLeft = parseInt(Element.getStyle(this.outer.parentNode, "paddingLeft"));
    var paddingTop  = parseInt(Element.getStyle(this.outer.parentNode, "paddingTop"));
    var right = Element.getPosition(this.outer).left + this.outer.offsetWidth;
    this.box.style.left = (right + paddingLeft + 1) + "px";
    this.box.style.top  = (Element.getPosition(this.outer).top - paddingTop - 1) + "px";

    if (X < pos.left || Y < pos.top || X > pos.right || Y > pos.bottom){
      Element.hide(this.selArea);
      Element.hide(this.box);
      Event.stopObserving(document, "mousemove", this.delegate.select);
      Event.observe(this.img, "mousemove", this.delegate.start);
      return;
    } else {

      Element.show(this.selArea);
      Element.show(this.box);
    }

    var scaleOrg = this.org.offsetWidth / this.org.offsetHeight;
    var scaleBox = this.box.offsetWidth / this.box.offsetHeight;
    var scaleImg = this.img.parentNode.offsetWidth / this.img.parentNode.offsetHeight;

    var l = X - pos.left - 1 - (this.selArea.offsetWidth / 2);
    var t = Y - pos.top - 1 - (this.selArea.offsetHeight / 2);

    if (l <= 0) l = 0;
    if (t <= 0) t = 0;
    if ((l + this.selArea.offsetWidth) > this.img.offsetWidth) l = this.img.offsetWidth-this.selArea.offsetWidth;
    if ((t + this.selArea.offsetHeight) > this.img.offsetHeight) t = this.img.offsetHeight-this.selArea.offsetHeight;
    this.selArea.style.left = l + "px";
    this.selArea.style.top = t + "px";

    var thumbCenter = {"X":(pos.left + (this.img.offsetWidth / 2)), "Y":(pos.top + this.img.offsetHeight / 2) };
    var mouseOffset = {"X": X-thumbCenter.X, "Y": Y-thumbCenter.Y};
    var orgOffset = {"X" : mouseOffset.X/this.img.offsetWidth*this.box.scrollWidth, "Y": mouseOffset.Y/this.img.offsetHeight*this.box.scrollHeight};
    var orgCenter = { "X": this.box.scrollWidth/2, "Y": this.box.scrollHeight/2};
    var orgMoved = {"X" : orgCenter.X + orgOffset.X, "Y": orgCenter.Y + orgOffset.Y};

    this.box.scrollLeft = orgMoved.X - this.box.offsetWidth / 2;
    this.box.scrollTop = orgMoved.Y - this.box.offsetHeight / 2;
  }
};
