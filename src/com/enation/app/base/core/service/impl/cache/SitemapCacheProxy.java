package com.enation.app.base.core.service.impl.cache;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.enation.app.base.core.model.SiteMapUrl;
import com.enation.app.base.core.service.ISitemapManager;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.cache.AbstractCacheProxy;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.DateUtil;
import com.enation.framework.util.FileUtil;

public class SitemapCacheProxy extends AbstractCacheProxy implements
		ISitemapManager {

	public static final String CACHE_KEY = "sitemap";

	public SitemapCacheProxy() {
		super(CACHE_KEY);
	}

	public int delete(String loc) {
		EopSite site = EopContext.getContext().getCurrentSite();
		Document document = (Document) this.cache.get(CACHE_KEY + "_"
				+ site.getUserid() + "_" + site.getId());
		document = document == null ? this.init() : document;

		List list = list = document.getRootElement().elements();
		for (Object o : list) {
			Element urlElement = (Element) o;
			String mloc = urlElement.element("loc").getText();
			if (mloc.equals(loc)) {
				document.getRootElement().remove(urlElement);
				this.cache.put(CACHE_KEY + "_" + site.getUserid() + "_"
						+ site.getId(), document);
				return 1;
			}
		}
		return 0;
	}

	public void addUrl(SiteMapUrl url) {
		EopSite site = EopContext.getContext().getCurrentSite();
		Document document = (Document) this.cache.get(CACHE_KEY + "_"
				+ site.getUserid() + "_" + site.getId());
		document = document == null ? this.init() : document;
		List list = list = document.getRootElement().elements();
		for (Object o : list) {
			Element urlElement = (Element) o;
			String mloc = urlElement.element("loc").getText();
			if (mloc.equals(url.getLoc())) {
				return;
			}
		}
		Element urlsetElement = document.getRootElement();
		Element urlElement = urlsetElement.addElement("url");
		Element locElement = urlElement.addElement("loc");
		Element lastmodElement = urlElement.addElement("lastmod");
		Element changefreqElement = urlElement.addElement("changefreq");
		Element priorityElement = urlElement.addElement("priority");
		locElement.setText(url.getLoc());
		lastmodElement.setText(DateUtil.toString((new Date(url.getLastmod())),
				"yyyy-MM-dd"));
		// changefreqElement.setText(url.getChangefreq());
		changefreqElement.setText("weekly");
		// priorityElement.setText(url.getPriority());
		priorityElement.setText("0.5");
		write(document);
		this.cache.put(CACHE_KEY + "_" + site.getUserid() + "_" + site.getId(),
				document);
	}

	public void editUrl(String loc, Long lastmod) {
		EopSite site = EopContext.getContext().getCurrentSite();
		Document document = (Document) this.cache.get(CACHE_KEY + "_"
				+ site.getUserid() + "_" + site.getId());
		document = document == null ? this.init() : document;
		List list = list = document.getRootElement().elements();
		for (Object o : list) {
			Element urlElement = (Element) o;
			String mloc = urlElement.element("loc").getText();
			if (mloc.equals(loc)) {
				urlElement.element("lastmod").setText(
						DateUtil.toString((new Date(lastmod)), "yyyy-MM-dd"));
				write(document);
				this.cache.put(CACHE_KEY + "_" + site.getUserid() + "_"
						+ site.getId(), document);
				break;
			}
		}
	}

	public String getsitemap() {
		EopSite site = EopContext.getContext().getCurrentSite();
		Document document = (Document) this.cache.get(CACHE_KEY + "_"
				+ site.getUserid() + "_" + site.getId());
		document = document == null ? this.init() : document;
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		return document.asXML().replaceAll("<loc>/",
				"<loc>http://" + request.getServerName() + "/");

	}

	private Document read() {
		Document document = null;
		EopSite site = EopContext.getContext().getCurrentSite();
		SAXReader saxReader = new SAXReader();
		try {
			if (FileUtil.exist(EopSetting.EOP_PATH + "/user/"
					+ site.getUserid() + "/" + site.getId() + "/sitemap.xml")) {
				document = saxReader.read(new File(EopSetting.EOP_PATH
						+ "/user/" + site.getUserid() + "/" + site.getId()
						+ "/sitemap.xml"));
			}

		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		}
		return document;

	}

	private void write(Document document) {
		String contextPath = EopContext.getContext().getContextPath();
		try {
			
			if(!contextPath.startsWith("/")){
				contextPath = "/"+contextPath;
			}
			
			XMLWriter output = new XMLWriter(new FileWriter(new File(
					EopSetting.EOP_PATH +  contextPath+ "/sitemap.xml")));
			output.write(document);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private Document init() {
		Document document = read();
		if (null == document) {
			String docStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
			docStr += "<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">";
			docStr += "</urlset>";
			try {
				document = DocumentHelper.parseText(docStr);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			this.write(document);
		}
		EopSite site = EopContext.getContext().getCurrentSite();
		this.cache.put(CACHE_KEY + "_" + site.getUserid() + "_" + site.getId(),
				document);
		return document;
	}

	public void clean() {
		Document document = null;
		String docStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		docStr += "<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">";
		docStr += "</urlset>";
		try {
			document = DocumentHelper.parseText(docStr);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		this.write(document);
		EopSite site = EopContext.getContext().getCurrentSite();
		this.cache.put(CACHE_KEY + "_" + site.getUserid() + "_" + site.getId(),
				document);
	}

}
