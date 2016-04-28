package dk.aau.cs.kah.SSB_split;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import dk.aau.cs.kah.SSB_split.SSBLoader.DatasetFactory;

public class App 
{
    public static void main( String[] args )
    {
    	CommandLineParser parser = new DefaultParser();
	
		// create the Options
		Options options = new Options();
		options.addOption("h", "help", 		false, 	"Display this message." );
		options.addOption("l", "lineorder", true, 	"the location of the lineorder tbl file");
		options.addOption("o", "output", 	true, 	"the path to the output location, default is current folder.");
		options.addOption("s", "supplier", 	true, 	"the location of the supplier tbl file");
		options.addOption("p", "part", 		true, 	"the location of the part tbl file");
		options.addOption("c", "customer", 	true, 	"the location of the customer tbl file");
		options.addOption("d", "date", 		true, 	"the location of the date tbl file");
		options.addOption("sp","split", 	true, 	"Number of line that should be used from the lineorder dataset.");
	
		try {
		    CommandLine line = parser.parse( options, args );
		    
		    if (line.hasOption( "help" )) {
		    	printHelp(null,options);
		    	System.exit(0);
			} 
		    
	    	if (line.hasOption( "output" ))  {
	    		Config.setOutput(line.getOptionValue("output"));
			}
	    	if (line.hasOption( "lineorder" ))  {
	    		Config.setLineorder(line.getOptionValue("lineorder"));
			}
	    	if (line.hasOption( "customer" ))  {
	    		Config.setCustomer(line.getOptionValue("customer"));
			}
	    	if (line.hasOption( "date" ))  {
	    		Config.setDate(line.getOptionValue("date"));
			}
	    	if (line.hasOption( "supplier" ))  {
	    		Config.setSupplier(line.getOptionValue("supplier"));
			}
	    	if (line.hasOption( "part" ))  {
	    		Config.setPart(line.getOptionValue("part"));
			}
	    	if (line.hasOption( "split" ))  {
	    		Config.setLinesFromLineorder(line.getOptionValue("split"));
			}
		}
		catch( ParseException exp ) {
			printHelp(exp, options);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	
    	DatasetFactory loader = new DatasetFactory();
    	loader.writeDatasetToDisk();
    }
    
    private static void printHelp(ParseException exp, Options options) {
		String header = "";
		HelpFormatter formatter = new HelpFormatter();
		if (exp != null) {
			header = "Unexpected exception:" + exp.getMessage();
		}
		formatter.printHelp("myapp", header, options, null, true);
	}
}
