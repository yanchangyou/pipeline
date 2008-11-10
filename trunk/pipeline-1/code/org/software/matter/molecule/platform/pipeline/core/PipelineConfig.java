package org.software.matter.molecule.platform.pipeline.core;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.xml.sax.SAXException;

public class PipelineConfig {

	private final static String VALIDATOR_RULES = "pipeline-digester-rules.xml";

	private final static String PIPELINE_CONFIG_PATH = "pipeline.xml";

	public PipelineConfig(String path) {
		this.configPipeline(path);
	}

	public PipelineConfig() {
	}

	/**
	 * Initialize the digester.
	 */
	public static Digester initDigester() {
		URL rulesUrl = PipelineConfig.class.getResource(VALIDATOR_RULES);

		Digester digester = DigesterLoader.createDigester(rulesUrl);

		return digester;
	}

	public Pipeline getPipeline(String pipelineName) throws IOException,
			SAXException {
		if (pipelineMap == null) {
			configPipeline(PIPELINE_CONFIG_PATH);
		}
		return (Pipeline) pipelineMap.get(pipelineName);
	}

	public static void main(String[] args) throws Exception {

		String pipelineName = "add";

		PipelineConfig config = new PipelineConfig(PIPELINE_CONFIG_PATH);
		Pipeline aPipeline = config.getPipeline(pipelineName);

		aPipeline.deal();
		// pathTest();

	}

	public static void pathTest() {
		System.out.println("Pipeline.class.getResource(\".\") : "
				+ PipelineConfig.class.getResource(VALIDATOR_RULES));
		System.out
				.println("Pipeline.class.getClassLoader().getResource(\".\") : "
						+ PipelineConfig.class.getClassLoader()
								.getResource("."));
		System.out
				.println("Pipeline.class.getClassLoader().getResource(\".\") : "
						+ PipelineConfig.class.getClassLoader().getResource(
								PIPELINE_CONFIG_PATH));
	}

	public PipelineConfig configPipeline(String path) {

		System.out.println(path);

		PipelineConfig pipelineConfig = null;

		Digester digester = initDigester();
		try {
			pipelineConfig = (PipelineConfig) digester.parse(new File(
					PipelineConfig.class.getClassLoader().getResource(
							path.trim()).getFile()));

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

		this.pipelineMap.putAll(pipelineConfig.pipelineMap);
		return pipelineConfig;
	}

	public void dealImport(String path) {
		configPipeline(path);
	}

	Map pipelineMap = new HashMap();

	public void addPipeline(Pipeline pipeline) {

		pipelineMap.put(pipeline.getName(), pipeline);

		if (pipeline.getShare() != null
				&& pipeline.getShare().trim().length() != 0) {
			addSharedPipeline(pipeline.getShare(), pipeline);
		}
	}

	static Map sharedPipelineMap = new HashMap();

	public static void addSharedPipeline(String sharedName, Pipeline pipeline) {
		sharedPipelineMap.put(sharedName, pipeline);
	}

	public static Pipeline getSharedPipeline(String sharedName) {
		return (Pipeline) sharedPipelineMap.get(sharedName);
	}
}
