package com.bpmn;

import java.io.IOException;

import com.services.DataFetchService;

/**
 * Hello world!
 *
 */
public class App 
{
	private static DataFetchService dataFetchService = new DataFetchService();
    public static void main( String[] args ) throws IOException
    {
    	String sourceNodeId =  args[0].toString();
        String destinationNodeId = args[1].toString();
        dataFetchService.parseBPMN();
        dataFetchService.findPath(sourceNodeId,destinationNodeId);
    }
}
