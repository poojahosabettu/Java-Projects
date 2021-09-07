package com.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringJoiner;

import org.camunda.bpm.engine.impl.util.json.JSONObject;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.StartEvent;

public class DataFetchService {

	private Map<String, Collection<SequenceFlow>> graph = new HashMap();
	private Map<String, Boolean> visited = new HashMap<String, Boolean>();
	private List<String> visitedNodes = new LinkedList();

	public void parseBPMN() throws IOException {
		String urlString = "https://n35ro2ic4d.execute-api.eu-central-1.amazonaws.com/prod/engine-rest/process-definition/key/invoice/xml";
		URL url = new URL(urlString);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET"); //GET request
        conn.connect();
        
        Scanner sc = new Scanner(url.openStream());
        String response = "";
        while (sc.hasNext()) {
            response = response + sc.nextLine();
        }
        JSONObject obj = new JSONObject(response); 
        String parseXML = obj.get("bpmn20Xml").toString(); 
        InputStream inputStream = new ByteArrayInputStream(parseXML.getBytes(Charset.forName("UTF-8")));
        BpmnModelInstance modelInstance = Bpmn.readModelFromStream(inputStream);
		StartEvent start = modelInstance.getModelElementById("StartEvent_1");
		graph.put(start.getId(), start.getOutgoing());
		Collection<SequenceFlow> outgoing = start.getOutgoing();

		for(SequenceFlow sequenceFlow : outgoing) {
			graphCreation(sequenceFlow.getTarget());
		}
		
		for(String string:graph.keySet())
			visited.put(string, false);
	}

	private void graphCreation(FlowNode flowNode) {
		if(graph.containsKey(flowNode.getId()))
			return;
		else {
			graph.put(flowNode.getId(), flowNode.getOutgoing());
			for (SequenceFlow sequenceFlow : flowNode.getOutgoing()) {
				graphCreation(sequenceFlow.getTarget());
			}
		}
	}

	public void findPath(String sourceNodeId, String destinationNodeId) {
		if(!graph.containsKey(sourceNodeId))
			System.exit(-1);
		else {
			BFS(sourceNodeId, destinationNodeId);
			if(visitedNodes.isEmpty())
				System.exit(-1);
			else {
				StringJoiner joiner = new StringJoiner(",", "[", "]");
				System.out.print("The path from "+sourceNodeId +"to "+destinationNodeId+" is:");
				for(String s:visitedNodes) {
					joiner.add(s);
				}
				System.out.println(joiner.toString());
			}
		}
	}

	private void BFS(String sourceNodeId, String destinationNodeId) {
		Queue<String> queue = new LinkedList<String>();
		queue.add(sourceNodeId);
		queue.offer(null);
		while(!queue.isEmpty()) {
			String flowNode = queue.poll();
			if(flowNode == null) {
				if(queue.isEmpty())
					break;
				queue.offer(null);
				continue;
			}
			if(visited.get(flowNode))
				continue;
			visited.put(flowNode, true);
			visitedNodes.add(flowNode);
			if(destinationNodeId.equals(flowNode)) 
				break;
			for(SequenceFlow sequenceFlow :graph.get(flowNode)) {
				queue.add(sequenceFlow.getTarget().getId());
			}
		}
	}
}
