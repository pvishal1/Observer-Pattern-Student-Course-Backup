package studentCoursesBackup.driver;

import java.util.ArrayList;

import studentCoursesBackup.util.*;

public class Driver {
	public static void main(String[] args) {
		if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
				|| args[3].equals("${arg3}") || args[4].equals("${arg4}")) {

			System.err.println("Error: Incorrect number of arguments. Program accepts 5 argumnets.");
			System.exit(0);
		}
		
		TreeBuilder studentRecord =  new TreeBuilder();
		Results resultI = new Results();
		
		//StdoutDisplayInterface std = resultI;
		FileDisplayInterface fdInt = resultI;

		FileProcessor inputF = new FileProcessor(args[0]);
		FileProcessor deleteF = new FileProcessor(args[1]);

		ArrayList<String> data = inputF.readLine();
		for (String record : data) {
			String[] st = record.split(":");
			studentRecord.insert(Integer.parseInt(st[0].trim()), st[1].trim());
		}

		//System.out.println("Insert");
		//std.printConsole(studentRecord);

		data = deleteF.readLine();
		for (String record : data) {
			String[] st = record.split(":");
			studentRecord.delete(Integer.parseInt(st[0].trim()), st[1].trim());
		}

		//System.out.println("Delete");
		//std.printConsole(studentRecord);
		fdInt.writeToFile(studentRecord, args[2], args[3], args[4]);
	}
}
