package dk.aau.cs.kah.SSB_split;

import dk.aau.cs.kah.SSB_split.SSBLoader.DatasetFactory;

public class App 
{
    public static void main( String[] args )
    {
    	DatasetFactory loader = new DatasetFactory();
    	loader.writeDatasetToDisk();
    }
}
