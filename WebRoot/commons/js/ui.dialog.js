/**
 * ECMall: 对话框类
 * ============================================================================
 * 版权所有 (C) 2005-2008 上海商派网络科技有限公司，并保留所有权利。
 * 网站地址:  http://www.shopex.cn
 * -------------------------------------------------------
 * 这不是一个自由软件！您只能在不用于商业目的的前提下对程序代码进行修改和使用；
 * 不允许对程序代码以任何形式任何目的的再发布。
 * ============================================================================
 * $Id: ui.dialog.js,v 1.1 2009/09/28 09:21:41 kingapex Exp $
 */

Event.observe(window, 'load', function(){
document.require("../../commons/js/ui.dialog/style.css", null, "css");
});

var DIALOG_WARNING = 0;
var DIALOG_MESSAGE = 1;
var DIALOG_CONFIRM = 2;
var DIALOG_USERDEF = 3;
var DIALOG_PROMPT  = 4;
var DIALOG_LOGINFORM = 5;
var DIALOG_TIP = 6;
var DIALOG_PROGRESS = 7;

var DIALOG_ACTION_CLOSE = 10;

Dialog = function (dType) {
  var self = this;
  this.type = dType;
  this.body       = null;
  this.className  = null;
  this.content  = 'Undefine';
  this.title    = '';
  this.width    = 300;
  this.height    = 0;
  this.isLockScreen = true;
  this.summary   = '';
  this.inputSize = 25;
  this.inputType = 'text';
  this.value = '';
  this.locker  = null;
  this.isShadow = true;
  this.isSingle = false;
  this.inDoc    = false;
  this.isDraggable = true;
  this.refer   = null;
  this.autoCloseTime = 0;
  this.timeoutID = null;
  this.fadeTime   = 0;
  this.okBtnName = lang.confirm ? lang.confirm : 'OK';
  this.cancelBtnName = lang.cancel ? lang.cancel : 'Cancel';
  this.closeBtnName = lang.close ? lang.close : 'X';
  this.value = '';
  this.components = {
    icon    : 'info',
    buttons : [],
    inputs  : [],
    summary : null,
    titleBar: null,
    titleText:null,
    closeButton:null,
    contentBody:null,
    messageBody:null,
    messageText:null,
    buttonsBar :null,
    progressBar:null
  };
  this.onChange = function () {
    self.value = this.value;
  };
  this.onClose = function () { return true; };
  this.onOK    = function () {
    self.close();
  };
  this.onLoad  = function () {

  };
  this.createDialogBody();
};
Dialog.prototype.init       = function () {
  switch (this.type) {
  case DIALOG_CONFIRM:
    this.addButton(this.okBtnName, this.onOK, true);
    this.addButton(this.cancelBtnName, DIALOG_ACTION_CLOSE);
    break;
  case DIALOG_PROMPT:
    this.value= '';
    this.content += ': ';
    this.addButton(this.okBtnName, this.onOK, true);
    this.addButton(this.cancelBtnName, DIALOG_ACTION_CLOSE);
    this.addInput(this.inputSize, this.inputType);
    this.addSummary(this.summary);
    break;
  case DIALOG_WARNING:
    this.components.icon = 'warn';
    this.addButton(this.okBtnName, this.onOK, true);
    this.addButton(this.cancelBtnName, DIALOG_ACTION_CLOSE);
    break;
  case DIALOG_MESSAGE:
    this.addButton(this.okBtnName, this.onOK, true);
    break;
  case DIALOG_USERDEF:
    break;
  case DIALOG_TIP:
    this.isLockScreen = false;
    this.isShadow     = false;
    this.isDraggable  = false;
    this.className    = 'ECM_dialog_tip';
    break;
  case DIALOG_PROGRESS:
    this.showProgressCtrl();
    break;
  default:
    break;
  }
};
Dialog.prototype.createDialogBody = function () {
  /*--- 构建对话框主体 ---*/

  /* 对话框容器 */
  this.body = $ce('div');
  /* 标题栏 */
  this.components.titleBar = $ce('h3');
  this.components.titleText = $ce('span');
  this.components.closeButton = $ce('span');
  /* 内容体 */
  this.components.contentBody = $ce('div');
  this.components.messageBody = $ce('div');
  this.components.messageText = $ce('div');
  this.components.buttonsBar  = $ce('div');

  this.body.appendChild(this.components.titleBar);
  this.body.appendChild(this.components.contentBody);

  this.components.titleBar.appendChild(this.components.titleText);
  this.components.titleBar.appendChild(this.components.closeButton);

  this.components.contentBody.appendChild(this.components.messageBody);
  this.components.messageBody.appendChild(this.components.messageText);
  this.components.contentBody.appendChild(this.components.buttonsBar);
};
Dialog.prototype.setSize   = function (w, h) {
  if (!isNaN(w)) {
    this.width = w;
  }
  if (!isNaN(h)) {
    this.height = h;
  }
};
Dialog.prototype.setStyle  = function (c) {
  if (c) {
    this.className = c;
  }
};
Dialog.prototype.setContent = function (t, c) {
  this.title = t;
  this.content = c;
};
Dialog.prototype.changeContent = function (t, c) {
  this.components.titleText.innerHTML = t;
  this.components.messageText.innerHTML = c;
};
Dialog.prototype.addButton  = function (n, e, strike, close) {
  var self = this;
  var b = $ce('input');
  b.type = 'button';
  b.value = n;

  if (strike)
  {
    b.className = 'ECM_dialog_strike_button';
  }
  switch (e) {
  case DIALOG_ACTION_CLOSE:
    b.onclick = this.close.bind(this);
    break;
  default:
    if (close) {
      b.onclick = function () {
        if(e)e();
        self.close();
      };
    }
    else {
      if(e)b.onclick = e;
    }
    break;
  }
  if (!this.inDoc) {
    this.components.buttons.push(b);
  }
  else {
    this.components.buttonsBar.appendChild(b);
    if (this.components.buttonsBar.style.display == 'none') {
      this.components.buttonsBar.style.display = '';
    }
  }
};
Dialog.prototype.addInput  = function (size, type) {
  if (!type) {
    type = 'text';
  }
  if (!size) {
    size = 25;
  }
  var _ipt = $ce('input');
  _ipt.type = type;
  _ipt.size = size;
  _ipt.onchange = this.onChange;
  this.components.inputs.push(_ipt);
};
Dialog.prototype.addSummary = function (text) {
  var _s = $ce('div');
  _s.className = 'ECM_dialog_summary';
  _s.innerHTML = text;
  this.components.summary = _s;
};
Dialog.prototype.showProgressCtrl = function () {
  this.components.progressCtrl = $ce('div');
  this.components.progressCtrl.className = 'ECM_dialog_progress_bar';
  this.components.icon = null;
  this.components.progressDetail = $ce('div');
  this.components.progressDetail.className = 'ECM_dialog_progress_detail';
  this.components.messageBody.style.textAlign = 'center';
  this.components.messageBody.appendChild(this.components.progressCtrl);
  this.components.messageBody.appendChild(this.components.progressDetail);
};
Dialog.prototype.addResult = function (n, r, e) {
  var l = $ce('div');
  var rzt = $ce('span');
  var m = $ce('span');
  l.className = 'ECM_dialog_progress_result_item';
  rzt.innerHTML = r;
  rzt.className = 'result';
  m.innerHTML = n;
  l.appendChild(rzt);
  l.appendChild(m);
  if (e) {
    e();
  }
  this.components.progressDetail.appendChild(l);
};
Dialog.prototype.hideProgressCtrl = function () {
  this.components.progressCtrl.style.display = 'none';
};
Dialog.prototype.pointTo     = function (o) {
  this.refer  = o;
};
Dialog.prototype.show      = function () {
  this.init();
  var self = this;

  /*--- 细化 ---*/
  /* 标题栏 */
  this.components.titleText.innerHTML = this.title;
  this.components.closeButton.innerHTML = this.closeBtnName;
  this.components.closeButton.title   = this.closeBtnName;
  this.components.closeButton.onclick = this.close.bind(this);

  /* 内容体 */
  this.components.messageText.innerHTML = this.content;

  /*--- 外观 ---*/
  this.body.className = this.className ? this.className : 'ECM_dialog_box';
  //标题栏样式名
  this.components.titleBar.className  = 'ECM_dialog_title';
  //关闭按钮样式
  this.components.closeButton.className = 'ECM_dialog_close_button';
  //标题文字样式
  this.components.titleText.className      = 'ECM_dialog_title_span';
  //内容体样式名
  this.components.contentBody.className    = 'ECM_dialog_body';
  //消息内容容器样式
  this.components.messageBody.className    = 'ECM_dialog_message_body';
  //消息内容样式
  this.components.messageText.className    = 'ECM_dialog_message_text';
  //按钮栏样式
  this.components.buttonsBar.className      = 'ECM_dialog_button_bar';

  if (this.components.inputs.length > 0) {
    for (var i=0; i<this.components.inputs.length; i++) {
      this.components.messageText.appendChild(this.components.inputs[i]);
    }
  }
  if (this.components.summary != null) {
    this.components.messageBody.appendChild(this.components.summary);
  }

  if (this.components.buttons.length > 0) {
    for (var i=0; i<this.components.buttons.length; i++) {
      this.components.buttonsBar.appendChild(this.components.buttons[i]);
    }
    this.components.messageBody.style.paddingBottom = '10px';
  }
  else {
    this.components.buttonsBar.style.display = 'none';
  }

  if (this.components.icon !== null) {
    this.components.contentBody.className += ' ECM_dialog_icon_'+this.components.icon;
    this.components.messageBody.style.paddingLeft = '35px';
  }

  /*--- 自定义样式 ----*/
  if (this.className) {
    this.body.className = this.className;
  }
  if (this.width) {
    this.body.style.width = this.width + 'px';
  }
  if (this.height) {
    this.body.style.height = this.height + 'px';
  }
  if (this.isShadow) {
    this.body = ui.effect.shadow(this.body);
  }

  /* 锁定屏幕 */
  if (this.isLockScreen) {
    this.body.style.zIndex = 99999;
    this.lockScreen();
  }
  else {
    this.body.style.zIndex = 999;
  }

  /* 允许被拖动 */
  if (this.isDraggable) {
    new ui.Draggable(this.body, this.components.titleBar);
  }

  if (!this.isSingle || !this.inDoc) {
    /*--- 加到文档流中 ---*/
    this.body.style.visibility = 'hidden';
    this.body.style.top = '-100000px';

    document.body.appendChild(this.body);
    this.inDoc = true;
  }
  else {
    Element.show(this.body);
  }
  /*--- 定位 ---*/
  if (this.refer) {
    this.body.style.position = 'absolute';
    var pos = Element.getPosition(this.refer);
    this.body.style.left= pos.left - this.body.offsetWidth/2 + this.refer.offsetWidth/2 + 'px';
    if ((pos.top - (document.body.scrollTop ? document.body.scrollTop : document.documentElement.scrollTop))+this.body.offsetHeight > window.innerHeight) {
      this.body.style.top = pos.top - this.body.offsetHeight + 'px';
      this.components.titleBar.className = 'ECM_dialog_title_bottom';
      this.body.appendChild(this.components.titleBar);
    }
    else {
      this.body.style.top = pos.top + this.refer.offsetHeight + 'px';
    }
  }
  else {
    this.moveToCenter();
  }
  if (this.fadeTime > 0) {
    this.body.style.opacity = 0;
    this.body.style.filter='alpha(opacity=0)';
    this.body.style.visibility = 'visible';
    this.setAutoClose();
    ui.effect.FadeTo(this.body, 0, 100, this.fadeTime * 1000);
  }
  else {
    this.body.style.visibility = 'visible';
    this.setAutoClose();
  }
  if (this.onLoad) {
    this.onLoad();
  }
};
Dialog.prototype.moveToCenter = function () {
  var _x  =  document.body.scrollWidth;
  var _y  = window.innerHeight > 0 ? window.innerHeight : document.body.clientHeight;
  var _s_h=0;
  if (this.body.style.position != 'fixed') {
    _s_h=  document.body.scrollTop ? document.body.scrollTop : document.documentElement.scrollTop;
  }
  c_x    =  _x /2 - this.width/2;
  c_y    =  _y/2 + _s_h - this.body.clientHeight/2;
  this.body.style.left = c_x + 'px';
  this.body.style.top  = c_y + 'px';
};
Dialog.prototype.close     = function () {
  if (this.body !== null) {
    var self = this;
    if (this.onClose) {
      if(!this.onClose())return;
    }
    if (this.fadeTime) {
      ui.effect.FadeTo(this.body, 100, 0, this.fadeTime * 1000, function () {
        if (!self.isSingle) {
          Element.remove(self.body);
          self.body = null;
        }
        else {
          Element.hide(self.body);
        }
      });
    }
    else {
      if (!this.isSingle) {
        Element.remove(this.body);
        this.body = null;
      }
      else {
        Element.hide(this.body);
      }
    }
    if (this.isLockScreen) {
      this.unlockScreen();
    }
  }
};
Dialog.prototype.isClosed   = function () {
  return (this.body.style.display == 'none' || !this.inDoc) ? true : false;
};
Dialog.prototype.lockScreen = function () {
  if (this.locker === null)
  {
    this.locker = new ui.utils.locker();
  }
  this.locker.lock(20);
};
Dialog.prototype.unlockScreen= function () {
  if (this.locker !== null)
  {
    this.locker.unLock();
  }
};
Dialog.prototype.setAutoClose = function () {
  if (this.autoCloseTime) {
    var self = this;
    this.body.onmouseover = function () {
      clearTimeout(self.timeoutID);
    };
    this.body.onmouseout = function () {
      self.setAutoClose();
    };
    this.timeoutID = setTimeout(function () {
      self.close();
    }, (this.autoCloseTime+this.fadeTime) * 1000);
  }
};
Dialog.prototype.focus = function () {
  if (this.components.inputs[0]) {
    this.components.inputs[0].focus();
  }
};
