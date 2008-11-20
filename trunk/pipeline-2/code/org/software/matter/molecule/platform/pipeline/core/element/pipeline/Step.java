package org.software.matter.molecule.platform.pipeline.core.element.pipeline;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.molecule.platform.pipeline.core.Locator;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Input;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Output;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Request;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Response;
import org.software.matter.molecule.platform.pipeline.core.element.soa.Server;
import org.software.matter.molecule.platform.pipeline.core.element.soa.Service;

public class Step extends Unit {

	public Step(){ 
		this.setUnitList(null);
	}
	
	private String primarayService;

	private String standbyService;

	private Input input;
	
	private Output output;
	
	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	public Output getOutput() {
		return output;
	}

	public void setOutput(Output output) {
		this.output = output;
	}

	public String toString() {
		return new ToStringBuilder(this).toString();
	}

	public String getPrimarayService() {
		return primarayService;
	}

	public void setPrimarayService(String primarayService) {
		this.primarayService = primarayService;
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

		String host = server.getHost();
		int port = Integer.parseInt(service.getPort());

		Socket socket = new Socket(host, port);
		
//		while (socket.isConnected()) {
			if (request != null) {
				PrintWriter os = new PrintWriter(socket.getOutputStream());
				
				os.println(request.getInput().toXMLType());
				os.flush();
			}

			if (response != null) {
				BufferedReader is = new BufferedReader(new InputStreamReader(socket
						.getInputStream()));
				String responseData = is.readLine();
				Output output = new Output(responseData);
				response.setOutput(output);
			}
//		}
	}

}
