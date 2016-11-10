package com.yang.Tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.yang.Models.BookInfo;

public class CSVFileHelper {
	private String path;

	public CSVFileHelper(String path) {
		this.path = path;
	}

	public void write(List<BookInfo> books) {
		File file = new File(this.path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			FileWriter fw = new FileWriter(this.path);
			for (BookInfo book : books) {
				fw.write(filter(book.getBookname()) + ","
						+ filter(String.valueOf(book.getEvaluates())) + ","
						+ filter(String.valueOf(book.getGoal())) + "\r\n");
			}
			fw.close();
			Log.write("write csvFileHelper success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Log.write(e.toString());
		}
	}

	//¹ýÂË¶ººÅºÍ»»ÐÐ·û
	private String filter(String str) {

		String tempDescription = str;

		if (str.contains(",")) {
			if (str.contains("\"")) {
				tempDescription = str.replace("\"", "\"\"");
			}
			tempDescription = "\"" + tempDescription + "\"";
		}
		tempDescription = tempDescription.replaceAll("(\r\n|\r|\n|\n\r)",""); 
		return tempDescription;
	}
}
