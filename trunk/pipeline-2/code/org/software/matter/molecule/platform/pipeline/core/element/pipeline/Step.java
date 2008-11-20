package org.software.matter.molecule.platform.pipeline.core.element.pipeline;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.molecule.platform.pipeline.core.Locator;
import org.software.matter.molecule.platform.pipeline.core.element.common.Output;
import org.software.matter.molecule.platform.pipeline.core.element.common.Request;
import org.software.matter.molecule.platform.pipeline.core.element.common.Response;
import org.software.matter.molecule.platform.pipeline.core.element.soa.Server;
import org.software.matter.molecule.platform.pipeline.core.element.soa.Service;

public class Step extends Unit {

	private String primarayService;

	private String standbyService;

	private Request request;

	private Response response;

	public String toString() {
		return new ToStringBuilder(this).toString();
	}

	public String getPrimarayService() {
		return primarayService;
	}

	public void setPrimarayService(String primarayService) {
		this.primarayService = primarayService;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public String getStandbyService() {
		return standbyService;
	}

	public void setStandbyService(String standbyService) {
		this.standbyService = standbyService;
	}

	public void deal(Request request, Response response) throws Exception {
		Service service = Locator
				.locateService(this.getRoot(), primarayService);
		Server server = Locator.locateServer(this.getRoot(), primarayService);

		String host = server.getMeta().getProperty("host");
		int port = Integer.parseInt(service.getMeta().getProperty("port"));

		Socket socket = new Socket(host, port);
		PrintWriter os = new PrintWriter(socket.getOutputStream());
		
		os.println(request.getInput().toXMLType());
		os.flush();

		if (response != null) {
			BufferedReader is = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			String responseData = is.readLine();
			Output output = new Output(responseData);
			response.setOutput(output);
		}
		
		socket.close();
	}

}
