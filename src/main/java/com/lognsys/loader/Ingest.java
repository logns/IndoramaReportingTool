package com.lognsys.loader;

import java.io.FileNotFoundException;


public interface Ingest
{

	public void parseData() throws FileNotFoundException;
	
	public void printReport();

	public int run();

}
