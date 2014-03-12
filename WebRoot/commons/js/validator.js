
function validator_lang_exist(key){
  if (typeof(lang) != "object") return false;
  if (typeof(lang.validator) != "object") return false;
  if (typeof(lang.validator[key]) == 'string') return true;

  return false;
}

dt_string = function ()
{
  this.check = function (val) {return true};
  this.test = function (val, testStr) {return new RegExp(testStr).test(val)};
};
dt_int = function ()
{
  this.check = function (val){return parseInt(val) == val;};
  this.test = function (val, testStr) {
    var arr = testStr.split(',');
    val = parseInt(val);
    var test = arr[0].trim();
    if (test != '*' && val < parseInt(test)) return false;
    if (arr.length > 1){
      test = arr[1].trim();
      if (test != '*' && val > parseInt(test)) return false;
    }
    return true;
  };
  this.errorMsg = function () {if (validator_lang_exist('is_not_int')) {return lang.validator.is_not_int} else {return "this value is not int!"}};
};
dt_float = function ()
{
  this.check = function (val){return parseFloat(val) == val;};
  this.test = function (val, testStr) {
    var arr = testStr.split(',');
    val = parseFloat(val);
    var test = arr[0].trim();
    if (test != '*' && val < parseFloat(test)) return false;
    if (arr.length > 1){
      test = arr[1].trim();
      if (test != '*' && val > parseFloat(test)) return false;
    }
    return true;
  };
  this.errorMsg = function () {if (validator_lang_exist('is_not_float')) {return lang.validator.is_not_float} else {return "this value is not float!"}};
};
dt_date = function()
{
  var self = this;
  this.preProcess = function (input) {
    var ce = $ce('span');
    ce.innerHTML = '<img src="admin/images/calendar_link.gif" alt="calendar" />';
    if (Element.next(input)!= null)
      input.parentNode.insertBefore(ce, Element.next(input));
    else
      input.parentNode.appendChild(ce, input);
    ce.children[0].onclick = function ()
    {
      if (!input.calendar){
        input.calendar = new ui.calendar(this,input);
        input.calendar.show();
        input.calendar.onSelect = function(){
          Validator.checkFormVal(input);
        };
      }else{
        input.calendar.show();
      }
    }
  };
  this.check = function (val){return val.isDate();};
  this.errorMsg = function () {if (validator_lang_exist('is_not_date')) {return lang.validator.is_not_date} else {return "this value is not date!"}};
};

dt_email = function()
{
  this.check = function (val) {return val.isEmail();};
  this.test = function() {return true};
  this.errorMsg = function () {if (validator_lang_exist('is_not_email')) {return lang.validator.is_not_email} else {return "this value is not email!"}};
};
dt_tel_num = function()
{
  this.check = function (val) {return /^[\d|\+|\_|\-]+$/.test(val);};
  this.errorMsg = function () {if (validator_lang_exist('is_not_tel_num')) {return lang.validator.is_not_tel_num} else {return "this value is not telephone Number!"}};
};
dt_mobile = function()
{
  this.check = function (val) {return /^[\d|-|\+]{3,20}$/.test(val);};
  this.errorMsg = function () {if (validator_lang_exist('is_not_mobile')) {return lang.validator.is_not_mobile} else {return "this value is not mobile Number!"}};
};
dt_id_card = function()
{
  this.check = function (val) {return true};
  this.errorMsg = function () {if (validator_lang_exist('is_not_id_card')) {return lang.validator.is_not_id_card} else {return "this value is not IDCard Number!"}};
};
dt_post_code = function()
{
  this.check = function (val) {return /^[\d]+$/.test(val);};
  this.errorMsg = function () {if (validator_lang_exist('is_not_post_code')) {return lang.validator.is_not_post_code} else {return "this value is not postCode!"}};
};
dt_url = function()
{
  this.check = function (val) {return val.match(/^(?:^(https?):\/\/[\-A-Z0-9+&@#\/%?=~_|!:,.;]*[\-A-Z0-9+&@#\/%=~_|]$)$/i);};
  this.errorMsg = function () {if (validator_lang_exist('is_not_url')) {return lang.validator.is_not_url} else {return "this value is not url!"}};
};
dt_file = function ()
{
  this.check = function (val) {return true};
  this.test = function (val, testStr) {return true};
};


var Validator =
{
  types : {"string":new dt_string(),"int":new dt_int(),"date":new dt_date(), "email":new dt_email,"tel_num":new dt_tel_num(),
    "mobile":new dt_mobile(), "id_card":new dt_id_card(), "post_code":new dt_post_code(), "url":new dt_url(), "region":new dt_string(), "file":new dt_file(),"float":new dt_float()
  },
  lang : {'requiredNote':'*',
    'required':'this field is required!',
    'invaild':'this value of field is invaild!',
    'formSubmit':'There is %s filed is invalid!',
    'gtNote':' > ',
    'ltNote':' < ',
    'theValue':'The value range:'
    },
  debug : false,
  cusSubmits:[],
  inputs : [],
  selects : [],
  textAreas : [],
  preSubmit : null,
  addType : function (typeName, typeObj){
    if (this.types[type_name]){if (this.debug) alert("The dataType '" + typeName + "' already exist!");return;}
    this.types[typeName] = typeObj;
  },
  addInput : function (theInput) {
    theInput = get(theInput);
    var dataType = theInput.getAttribute('dataType');
    if (!dataType) return false;
    if (!this.types[dataType]){if (this.debug) alert("The dataType '" + dataType + "' is not exist!");return false;}
    theInput['dataType'] = dataType;
    var required = theInput.getAttribute('required');
    if (typeof(required) == 'string' && (required.toUpperCase() == 'TRUE' || required.toUpperCase() == 'REQUIRED'))
      theInput['required'] = true;
    else
      theInput['required'] = false;
    var testStr = theInput.getAttribute('test');
    if (testStr) {
      if (typeof(this.types[theInput.dataType].test) == 'function') {
        theInput.testStr = testStr;
      }else {
        if (this.debug) alert("the dataType'" + theInput.dataType + "' is not support method of test!");
      }
    }
    var inputFun = theInput.getAttribute('fun');
    if (inputFun){
      eval('result= typeof(' + inputFun + ') == "function"');
      if (result == true) {
        theInput.fun = eval(inputFun);
        theInput.funParam = theInput.getAttribute('funParam');
      }else{
        if (this.debug) alert("The function '" + inputFun + "' is not exist!");
      }
    }
    var Tips = $class('note', theInput.parentNode);
    if (Tips.length == 0 && (theInput.required || ((theInput.dataType == 'int' || theInput.dataType == 'float') && theInput.testStr))) {
      var tip = $ce('SPAN');
      tip.className = "note";
      if (((theInput.dataType == 'int' || theInput.dataType == 'float') && theInput.testStr)){
        var rang_note = '';
        var arr =theInput.testStr.split(',');
        if (arr[0] != '*')
        {
          rang_note += validator_lang_exist ('gtNote') ? lang.validator.gtNote : this.lang.gtNote;
          rang_note += arr[0];
        }
        if (arr.length > 1){
          if (arr[1] != '*'){
            rang_note += validator_lang_exist ('ltNote') ? lang.validator.ltNote : this.lang.ltNote;
            rang_note += arr[1];
          }
        }
        tip.innerHTML = validator_lang_exist('theValue') ? lang.validator.theValue : this.lang.theValue;
        tip.innerHTML += rang_note;
      }else
        tip.innerHTML = validator_lang_exist('requiredNote') ? lang.validator.requiredNote : this.lang.requiredNote;
      if (Element.next(theInput)!= null) {
        theInput.parentNode.insertBefore(tip, Element.next(theInput))
      }else{
        theInput.parentNode.appendChild(tip);
      }
      Tips.push(tip);
    }
    if (Tips.length > 0) theInput.tip = get(Tips[0]);
    var Msgs = $class('msg', theInput.parentNode);
    if (Msgs.length == 0) {
      var msg = $ce('SPAN');
      msg.className = "msg";
      if (theInput.tip) {
        theInput.parentNode.insertBefore(msg, theInput.tip);
      }else{
        if (Element.next(theInput)!= null) {
          theInput.parentNode.insertBefore(msg, Element.next(theInput))
        }else{
          theInput.parentNode.appendChild(msg);
      }}
      Msgs.push(msg);
    }
    theInput.msg = get(Msgs[0]);
    if (theInput.msg) Element.hide(theInput.msg);
    this.inputs.push(theInput);
  },
  addSelect : function (theSelect){
    theSelect = get(theSelect);
    var dataType = theSelect.getAttribute('dataType');
    if (!dataType) return false;
    theSelect['dataType'] = dataType;
    var required = theSelect.getAttribute('required');
    if (typeof(required) == 'string' && (required.toUpperCase() == 'TRUE' || required.toUpperCase() == 'REQUIRED'))
      theSelect['required'] = true;
    else
      theSelect['required'] = false;
    var Tips = $class('note', theSelect.parentNode);
    if (Tips.length == 0 && theSelect.required) {
      var tip = $ce('SPAN');
      tip.className = "note";
      tip.innerHTML = validator_lang_exist('requiredNote') ? lang.validator.requiredNote : this.lang.requiredNote;
      if (Element.next(theSelect)!= null) {
        theSelect.parentNode.insertBefore(tip, Element.next(theSelect))
      }else{
        theSelect.parentNode.appendChild(tip);
      }
      Tips.push(tip);
    }
    if (Tips.length > 0) theSelect.tip = get(Tips[0]);
    var Msgs = $class('msg', theSelect.parentNode);
    if (Msgs.length == 0) {
      var msg = $ce('SPAN');
      msg.className = "msg";
      if (theSelect.tip) {
        theSelect.parentNode.insertBefore(msg, theSelect.tip);
      }else{
        if (Element.next(theSelect)!= null) {
          theSelect.parentNode.insertBefore(msg, Element.next(theSelect))
        }else{
          theSelect.parentNode.appendChild(msg);
      }}
      Msgs.push(msg);
    }
    theSelect.msg = get(Msgs[0]);
    if (theSelect.msg) Element.hide(theSelect.msg);
    var selectFun = theSelect.getAttribute('fun');
    if (selectFun){
      eval('result= typeof(' + selectFun + ') == "function"');
      if (result == true) {
        theSelect.fun = eval(selectFun);
        theSelect.funParam = theSelect.getAttribute('funParam');
      }else{
        if (this.debug) alert("The function '" + selectFun + "' is not exist!");
      }
    }
    this.selects.push(theSelect);
  },
  addTextArea : function (theTextArea){
    theTextArea = get(theTextArea);
    var dataType = theTextArea.getAttribute('dataType');
    if (!dataType) return false;
    theTextArea['dataType'] = dataType;
    var required = theTextArea.getAttribute('required');
    if (typeof(required) == 'string' && (required.toUpperCase() == 'TRUE' || required.toUpperCase() == 'REQUIRED'))
      theTextArea['required'] = true;
    else
      theTextArea['required'] = false;
    var Tips = $class('note', theTextArea.parentNode.parentNode);
    if (Tips.length == 0 && theTextArea.required) {
      var tip = $ce('SPAN');
      tip.className = "note";
      tip.innerHTML = validator_lang_exist('requiredNote') ? lang.validator.requiredNote : this.lang.requiredNote;
      if (Element.next(theTextArea)!= null) {
        theTextArea.parentNode.insertBefore(tip, Element.next(theTextArea))
      }else{
        theTextArea.parentNode.appendChild(tip);
      }
      Tips.push(tip);
    }
    if (Tips.length > 0)
    {
        theTextArea.tip = get(Tips[0]);
        theTextArea.tip.style.cssFloat = "left";
        theTextArea.tip.style.styleFloat = "left";
    }
    var Msgs = $class('msg', theTextArea.parentNode.parentNode);
    if (Msgs.length == 0) {
      var msg = $ce('SPAN');
      msg.className = "msg";
      if (theTextArea.tip) {
        theTextArea.parentNode.insertBefore(msg, theTextArea.tip);
      }else{
        if (Element.next(theTextArea)!= null) {
          theTextArea.parentNode.insertBefore(msg, Element.next(theTextArea))
        }else{
          theTextArea.parentNode.appendChild(msg);
      }}
      Msgs.push(msg);
    }
    theTextArea.msg = get(Msgs[0]);
    theTextArea.msg.style.cssFloat = "left";
    theTextArea.msg.style.styleFloat = "left";


    if (theTextArea.msg) Element.hide(theTextArea.msg);
    var textAreaFun = theTextArea.getAttribute('fun');
    if (textAreaFun){
      eval('result= typeof(' + textAreaFun + ') == "function"');
      if (result == true) {
        theTextArea.fun = eval(textAreaFun);
        theTextArea.funParam = theTextArea.getAttribute('funParam');
      }else{
        if (this.debug) alert("The function '" + textAreaFun + "' is not exist!");
      }
    }

    theTextArea.style.cssFloat = "left";
    theTextArea.style.styleFloat = "left";
    this.textAreas.push(theTextArea);
  },
  addForm : function (formName){
    if (!document.forms[formName]) {if (this.debug) alert("The Form '" + formName + "' is not exist");return false;}
    var frm = document.forms[formName];
    var self = this;
    for (var i=0;i < frm.elements.length ; i++){
      if (frm.elements[i].tagName == 'INPUT'){
	      	if(frm.elements[i].type=="submit"){ //王峰加入表提提交按钮的处理 
	      		this.submit_btn = frm.elements[i];
	      	}
       	 this.addInput(frm.elements[i]);
        }
      else if (frm.elements[i].tagName == 'SELECT'){
        this.addSelect(frm.elements[i]);
      }else if (frm.elements[i].tagName == 'TEXTAREA'){
        this.addTextArea(frm.elements[i]);
      }
    }
    frm.onsubmit = function (e){
     var evt = fixEvent(e);
     evt.cancelBublle = false;
     if (typeof(self.preSubmit) == 'function'){
       if (!self.preSubmit()) return false;
     }
     
     //王峰加入表提提交按钮的处理 
     	var r  = self.checkAll(formName);
    	self.submit_btn.disabled=true;
    	if(self.cusSubmits){
    		 
    		 for (var f in self.cusSubmits){
    		 	var fun = self.cusSubmits[f];
    		 	if (typeof(fun) == 'function'){
    		 		
    		 		self.cusSubmits[f]();
    		 	}
    		 	
    		 }
    	}
    	if(!r){
    	  self.submit_btn.disabled=false;
    	}
    	
      return r;
    }
  },
  run : function (formName){
    var self = this;
    var img = new Image();
    img.src = "admin/images/check_right.gif"; //pre loading this image
    if (formName) this.addForm(formName);
    for (var key in this.inputs ){
      if (typeof(this.inputs[key]) != 'object') continue;
      var dataType = this.inputs[key].dataType;
      if (typeof(this.types[dataType]['preProcess']) == "function") this.types[dataType].preProcess(this.inputs[key]);

      Event.observe(this.inputs[key], 'focus', (function (obj) {return function(){self.activeTip(obj)}})(this.inputs[key]));
      Event.observe(this.inputs[key], 'blur', (function (obj) {return function(){self.checkFormVal(obj)}} )(this.inputs[key]));
    }
    for (var key in this.selects){
      if (typeof(this.selects[key]) != 'object') continue;

      Event.observe(this.selects[key], 'focus', (function (obj) {return function(){self.activeTip(obj)}})(this.selects[key]));
      Event.observe(this.selects[key], 'change', (function (obj) {return function(){self.checkSelectVal(obj)}})(this.selects[key]));
    }
    for (var key in this.textAreas){
      if (typeof(this.textAreas[key]) != 'object') continue;
      Event.observe(this.textAreas[key], 'focus', (function (obj) {return function(){self.activeTip(obj)}})(this.textAreas[key]));
      Event.observe(this.textAreas[key], 'blur', (function (obj) {return function(){self.checkFormVal(obj)}})(this.textAreas[key]));
    }
  },
  activeTip : function (theInput){
    if (theInput.disabled){return true;};
    theInput.validity = null;
    if (theInput.msg) Element.hide(theInput.msg);
    if (!theInput.tip) return;
    Element.addClass(theInput.tip, "active");
    if (theInput.tagName.toUpperCase() == 'TEXTAREA'){
     Element.show(theInput.tip, 'block');
    }else{
     if (theInput.is_block){
       Element.show(theInput.tip, 'block');
     }else{
       Element.show(theInput.tip, 'inline');
     }

    }
  },
  hideFormTip : function (theInput){
    if (!theInput.tip) return;
    if (theInput.style.display == 'block'){
      theInput.is_block = true;
    }
    Element.hide(theInput.tip);
  },
  checkFormVal : function (theInput){
    if (theInput.disabled){return true;};
    this.hideFormTip(theInput);

    theInput.msg.innerHTML = "Checking....";
    if (theInput.tagName.toUpperCase() == 'TEXTAREA'){
     Element.show(theInput.msg, 'block');
    }else{
     Element.show(theInput.msg, 'inline');
    }
    if (theInput.required && theInput.value.isEmpty())
    {
      theInput.msg.innerHTML = validator_lang_exist('required') ? lang.validator.required : this.lang.required;
      this.showError(theInput);
      return false;
    }
    if ((!theInput.required) && theInput.value.isEmpty()){theInput.validity = true;this.showOk(theInput); return true};

    if (!this.types[theInput.dataType].check(theInput.value)){
      theInput.msg.innerHTML = this.types[theInput.dataType].errorMsg();
      this.showError(theInput);
      return false;
    }
    if (theInput.testStr && (!this.types[theInput.dataType].test(theInput.value, theInput.testStr))){
      theInput.msg.innerHTML = validator_lang_exist('invaild') ? lang.validator.invaild : this.lang.invaild;
      this.showError(theInput);
      return false;
    }

    if (theInput.fun){
      var result=theInput.fun(theInput, theInput.funParam);
      if (result !== true){
        theInput.msg.innerHTML = result;
        this.showError(theInput);
        return false;
    }}

    theInput.validity = true;
    this.showOk(theInput);
  },
  checkSelectVal : function (theSelect){
    if (theSelect.disabled){return true;};
    this.hideFormTip(theSelect);
    theSelect.msg.innerHTML = "Checking....";
    Element.show(theSelect.msg, 'inline');
    if (theSelect.required && (theSelect.value == "" || theSelect.value == "0"))
    {
      theSelect.msg.innerHTML = validator_lang_exist('required') ? lang.validator.required : this.lang.required;
      this.showError(theSelect);
      return false;
    }

    if (theSelect.fun){
      var result=theSelect.fun(theSelect, theSelect.funParam);
      if (result !== true){
        theSelect.msg.innerHTML = result;
        this.showError(theSelect);
        return false;
    }}

    theSelect.validity = true;
    this.showOk(theSelect);
  },
  checkAll : function (formName) {
    var count = 0;
    var is_first = true;
    for (var i in this.inputs ) {
      if (typeof(this.inputs[i]) == 'function') continue;
      if (this.inputs[i].form && this.inputs[i].form.name == formName) {
        if (this.inputs[i].validity == undefined) this.checkFormVal(this.inputs[i]);
        if (!this.inputs[i].validity && !this.inputs[i].disabled) {if (is_first) { try{this.inputs[i].focus();}catch(e){}; is_first = false;};if (this.debug) alert(this.inputs[i].name); count ++};
    }}

    for (var i in this.selects ) {
      if (typeof(this.selects[i]) == 'function') continue;
      if (this.selects[i].form && this.selects[i].form.name == formName) {
        if (this.selects[i].validity == undefined) this.checkSelectVal(this.selects[i]);
        if (!this.selects[i].validity && !this.selects[i].disabled) {if (this.debug) alert(this.inputs[i].name); count ++};
    }}

    for (var i in this.textAreas ) {
      if (typeof(this.textAreas[i]) == 'function') continue;
        if (this.textAreas[i].form && this.textAreas[i].form.name == formName) {
        if (this.textAreas[i].validity == undefined) this.checkFormVal(this.textAreas[i]);
        if (!this.textAreas[i].validity && !this.textAreas[i].disabled) {if (this.debug) alert(this.textAreas[i].name); count ++};
    }}

    if (count > 0){
      var formSubmit = validator_lang_exist('formSubmit') ? lang.validator.formSubmit : this.lang.formSubmit;
      alert(formSubmit.replace(/%s/, count));
      return false;
    }else{
      return true;
    }
  },
  showError : function (theInput) {
    Element.removeClass(theInput.msg, 'ok');
    Element.addClass(theInput.msg, 'error');
    if (theInput.tagName.toUpperCase() == 'TEXTAREA'){
     Element.show(theInput.msg, 'block');
    }else{
     Element.show(theInput.msg, 'inline');
    }
  },
  showOk : function (theInput) {
//    theInput.msg.innerHTML = '<div class="msgok"> </div>';
	theInput.msg.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	// theInput.parentNode.className = "msg";
	 theInput.msg.className = "msg ok";
     Element.removeClass(theInput.msg, 'error');
    if (theInput.tagName.toUpperCase() == 'TEXTAREA'){
     Element.show(theInput.msg, 'block');
    }else{
     Element.show(theInput.msg, 'inline');
    }
  }
};
