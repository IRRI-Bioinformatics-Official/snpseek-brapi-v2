package org.irri.iric.portal;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.zkoss.zk.ui.Executions;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;



public class HttpPostForm {

	public void postRicenetNetworkNGA3(String genelist) throws Exception {
		AppContext.debug("postRicenetNetworkNGA3");

		if (genelist == null)
			genelist = "LOC_Os01g32460 LOC_Os01g32380 LOC_Os08g34740 LOC_Os09g30412 LOC_Os02g33180 LOC_Os12g36880 LOC_Os06g49430 LOC_Os02g54600 LOC_Os01g32660 LOC_Os03g17700 LOC_Os10g38950 LOC_Os01g47530 LOC_Os05g45420 LOC_Os01g14810 LOC_Os02g57200 LOC_Os10g28610 LOC_Os03g12470 LOC_Os01g49290 LOC_Os01g12900 LOC_Os05g02130 LOC_Os06g06090 LOC_Os01g25820";

		try {
			URL url = new URL("http://www.inetbio.org/ricenet/Network_nga_form_conv.php");

			HttpURLConnection hConnection = (HttpURLConnection) url.openConnection();
			HttpURLConnection.setFollowRedirects(true);

			hConnection.setDoOutput(true);
			hConnection.setRequestMethod("POST");

			PrintStream ps = new PrintStream(hConnection.getOutputStream());
			ps.print("modelspecies=0&amp;genes=" + genelist.replaceAll("\\s+", ","));
			ps.close();

			hConnection.connect();

			if (HttpURLConnection.HTTP_OK == hConnection.getResponseCode()) {
				InputStream is = hConnection.getInputStream();
				OutputStream os = new FileOutputStream(AppContext.getTempDir() + "output.html");
				int data;
				while ((data = is.read()) != -1) {
					os.write(data);
				}
				is.close();
				os.close();
				hConnection.disconnect();

				Executions.getCurrent().sendRedirect(AppContext.getTempDir() + "output.html", "_blank");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		AppContext.debug("postRicenetNetworkNGA3 .. done in " + AppContext.getTempDir() + "output.html");

	}

	/*
	 * public void postRicenetNetworkNGA2(String genelist) throws Exception {
	 * AppContext.debug("postRicenetNetworkNGA2");
	 * 
	 * if(genelist==null)
	 * genelist="LOC_Os01g32460 LOC_Os01g32380 LOC_Os08g34740 LOC_Os09g30412 LOC_Os02g33180 LOC_Os12g36880 LOC_Os06g49430 LOC_Os02g54600 LOC_Os01g32660 LOC_Os03g17700 LOC_Os10g38950 LOC_Os01g47530 LOC_Os05g45420 LOC_Os01g14810 LOC_Os02g57200 LOC_Os10g28610 LOC_Os03g12470 LOC_Os01g49290 LOC_Os01g12900 LOC_Os05g02130 LOC_Os06g06090 LOC_Os01g25820"
	 * ; try { List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	 * formparams.add(new NameValuePair("modelspecies", "0")); formparams.add(new
	 * NameValuePair("genes", genelist)); UrlEncodedFormEntity entity = new
	 * UrlEncodedFormEntity((Iterable<? extends org.apache.http.NameValuePair>)
	 * formparams, Consts.UTF_8); HttpPost httppost = new
	 * HttpPost("http://www.inetbio.org/ricenet/Network_nga_form_conv.php");
	 * httppost.setEntity(entity);
	 * AppContext.debug("postRicenetNetworkNGA2 .. done"); } catch (Exception ex) {
	 * ex.printStackTrace(); }
	 * 
	 * }
	 */

	public void postRicenetNetworkNGA(String genelist) throws Exception {
		AppContext.debug("postRicenetNetworkNGA .. ");
		if (genelist == null)
			genelist = "LOC_Os01g32460 LOC_Os01g32380 LOC_Os08g34740 LOC_Os09g30412 LOC_Os02g33180 LOC_Os12g36880 LOC_Os06g49430 LOC_Os02g54600 LOC_Os01g32660 LOC_Os03g17700 LOC_Os10g38950 LOC_Os01g47530 LOC_Os05g45420 LOC_Os01g14810 LOC_Os02g57200 LOC_Os10g28610 LOC_Os03g12470 LOC_Os01g49290 LOC_Os01g12900 LOC_Os05g02130 LOC_Os06g06090 LOC_Os01g25820";
		try {
			WebClient webClient = new WebClient();
			HtmlPage page1 = webClient.getPage("http://www.inetbio.org/ricenet/Network_nga_form_conv.php");
			HtmlForm form = page1.getForms().get(0);
			HtmlInput button = form.getInputByValue("Submit");
			HtmlTextArea textField = form.getTextAreaByName("genes");
			HtmlSelect selectOrg = form.getSelectByName("modelspecies");
			selectOrg.setSelectedIndex(0);
			textField.setText(genelist);
			HtmlPage page2 = button.click(); // final line
			AppContext.debug(page2.asText());
			webClient.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		AppContext.debug("postRicenetNetworkNGA .. done");

	}
	
	

}
