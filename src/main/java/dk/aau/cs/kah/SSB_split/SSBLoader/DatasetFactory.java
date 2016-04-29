package dk.aau.cs.kah.SSB_split.SSBLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import dk.aau.cs.kah.SSB_split.Config;

public class DatasetFactory {
	private Set<String> customerForeignKeys = new HashSet<String>(12714);
	private Set<String> partForeignKeys = new HashSet<String>(38800);
	private Set<String> supplierForeignKeys = new HashSet<String>(51200);
	private Set<String> dateForeignKeys = new HashSet<String>(122602);
	
	public void writeDatasetToDisk() {
		try {
			System.out.println("Lineorder");
			readWriteLineorder();
			System.out.println("Customer");
			readWriteCustomer();
			System.out.println("Part");
			readWritePart();
			System.out.println("Supplier");
			readWriteSupplier();
			System.out.println("Date");
			readWriteDate();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readWriteDate() throws IOException {
		readWriteDimension(Config.getDate(),"date.tbl",dateForeignKeys);
		//copyFile(Config.getDate(),Config.getOutput()+"date.tbl");
	}

	private void readWriteSupplier() throws FileNotFoundException, UnsupportedEncodingException {
		readWriteDimension(Config.getSupplier(),"supplier.tbl",supplierForeignKeys);
	}

	private void readWritePart() throws FileNotFoundException, UnsupportedEncodingException {
		readWriteDimension(Config.getPart(),"part.tbl",partForeignKeys);
	}

	private void readWriteCustomer() throws FileNotFoundException, UnsupportedEncodingException {
		readWriteDimension(Config.getCustomer(),"customer.tbl",customerForeignKeys);
	}

	private void readWriteLineorder() throws FileNotFoundException, UnsupportedEncodingException {
		Scanner input = new Scanner(new File(Config.getLineorder()));
		PrintWriter writer = new PrintWriter(Config.getOutput()+"lineorder.tbl", "UTF-8");
		int counter = 0;
		while(input.hasNextLine() && counter < Config.getLinesFromLineorder())
		{
			String readLine = input.nextLine();
			String[] line = readLine.split("\\|");
			customerForeignKeys.add(line[2]);
			partForeignKeys.add(line[3]);
			supplierForeignKeys.add(line[4]);
			dateForeignKeys.add(line[5]);
			dateForeignKeys.add(line[15]);
			writer.println(readLine);
		    counter++;
		}
		input.close();
		writer.close();
	}
	
	private static void copyFile(String string, String string2) throws IOException {
		File from = new File(string);
		File to = new File(string2);
	    if(!to.exists()) {
	    	to.createNewFile();
	    }

	    FileChannel source = null;
	    FileChannel destination = null;

	    try {
	        source = new FileInputStream(from).getChannel();
	        destination = new FileOutputStream(to).getChannel();
	        destination.transferFrom(source, 0, source.size());
	    }
	    finally {
	        if(source != null) {
	            source.close();
	        }
	        if(destination != null) {
	            destination.close();
	        }
	    }
	}
	
	private void readWriteDimension(String inputFilePath, String outputFileName, Set<String> foreignKeys) throws FileNotFoundException,
	UnsupportedEncodingException {
	Scanner input = new Scanner(new File(inputFilePath));
	PrintWriter writer = new PrintWriter(Config.getOutput()+outputFileName, "UTF-8");
	while(input.hasNextLine())
	{
		String readLine = input.nextLine();
		String[] line = readLine.split("\\|");
		if (foreignKeys.contains(line[0])) {
			writer.println(readLine);
		}
	}
	input.close();
	writer.close();
	}
}
