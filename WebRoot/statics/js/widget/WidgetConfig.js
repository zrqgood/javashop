var enation={};
enation.eop={};
var WidgetConfig = enation.eop.WidgetConfig={};
WidgetConfig.LAYOUT_TYPE = "auto";//自动式
WidgetConfig.WIDGET_RESIZE_MINHEIGHT =20;  //挂件缩放时最小高度
WidgetConfig.WIDGET_RESIZE_MINWIDTH =20;  //挂件缩放时最小宽度
WidgetConfig.WIDGET_WRAPHELPER_BORDERWIDTH =1;  //挂件缩放时最小宽度
WidgetConfig.HOST="/eop/";



var Selector =  enation.eop.WidgetConfig.Selector={};
var ClassName = enation.eop.WidgetConfig.ClassName={};
var EopTypes =  enation.eop.WidgetConfig.Types={};


EopTypes.NAME ="eop_type";
EopTypes.WIDGET ="widget"; //挂件
EopTypes.LAYOUT ="layout"; //布局
EopTypes.WIDGET_BUNDLE ="widgetHandle";//挂件束



/*样式名*/
ClassName.WIDGET_HANDLE="handle"; //挂件handle
ClassName.WIDGET_WRAPHELPER="wrapHelper"; //挂件handle
ClassName.WIDGET_HOVER="hover";   //鼠标移到挂件上的样式
ClassName.WIDGET_DRAGIN="draging";//挂件拖动中的样式
ClassName.WIDGET_HELPER="widget_helper"; //挂件helper的样式
ClassName.WIDGET_GHOST="ghost";   //拖动时产生的挂件虚框的样式
ClassName.LAYOUT_ACTIVE="state-highlight"; //布局高亮时的新式
ClassName.LOCKWIDTH = "lockwidth";

/*选择器*/
Selector.WIDGET						="div[eop_type='"+EopTypes.WIDGET +"']"; //挂件
Selector.WIDGET_CHILD_HANDLE 		= "div."+ClassName.WIDGET_HANDLE; //从挂件选择子Handle
Selector.WIDGET_CHILD_WRAPHELPER 	= "div."+ClassName.WIDGET_WRAPHELPER; //从挂件选择子Handle
Selector.WIDGET_HANDLE				=Selector.WIDGET+">"+Selector.WIDGET_CHILD_HANDLE; //挂件handle
Selector.WIDGET_WRAPHELPER			=Selector.WIDGET+">"+Selector.WIDGET_CHILD_WRAPHELPER; 
Selector.WIDGET_DROP_ACCPET			="#eop_widget_tool .widget_types>li,"+Selector.WIDGET; //挂件拖动可接受
Selector.LAYOUT						="div[eop_type='"+  EopTypes.LAYOUT  +"']"; //布局
Selector.PAGEMAIN_HEIGHT            =288;//布局工具箱的高度


