package com.enation.eop.sdk.webapp.plugin;

/**
 * 插件接口<br/> 实现此接口的插件可被注册至通过IPluginBundle桩化的业务类中。<br/> 推荐插件注册的实现方法：<br/>
 * 插件实现体构造函数为：<br/> public YourConstructor(IPluginBundle pluginBundle){<br />
 * pluginAble.registerPlugin(this); <br /> }
 * 
 * 
 * 利用如此的构造函数可实现插件本身的注册, 如果通过Spring IoC 方式注入则配置如下：<br/>
 * 
 * &lt;bean id=&quot;yourPlugin&quot; class=&quot;package.PluginClass&quot;
 * lazy-init=&quot;false&quot;&gt;<br />
 * &lt;constructor-arg&gt;<br />
 * &lt;ref bean=&quot;yourPluginBundle&quot;/&gt;<br />
 * &lt;/constructor-arg&gt; <br />
 * &lt;/bean&gt;
 * 
 * @author apexking
 * 
 */
public interface IPlugin {

	/**
	 * 
	 * @return 插件类型
	 */
	public String getType();

	/**
	 * 
	 * @return 插件Id
	 */
	public String getId();

	/**
	 * 
	 * @return 插件名称
	 */
	public String getName();

	/**
	 * 
	 * @return 插件版本
	 */
	public String getVersion();
	
	/**
	 * 
	 * @return 作者
	 */
	public String getAuthor();
	
	
	
	/**
	 * 执行插件功能
	 * 
	 * @param params
	 *            业务类传递给插件的参数
	 */
	public void perform(Object... params);

}

