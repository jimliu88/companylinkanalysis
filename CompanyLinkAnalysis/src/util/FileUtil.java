package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FileUtil {

	private static ArrayList<String> fileContent;
	//private static String file;
	private static String[] fileContentArray;
	
	public static void read(String fileName){
		if(fileName!=null && !fileName.isEmpty()){
			fileContent=new ArrayList<String>();
			File file=new File(fileName);
			if(!file.isDirectory()){
				try {
					InputStreamReader isReader=new InputStreamReader(new FileInputStream(file));
					BufferedReader bReader=new BufferedReader(isReader);
					String lineContent=null;
					try {
						while((lineContent=bReader.readLine())!=null){
							if(!lineContent.isEmpty())
								fileContent.add(lineContent);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else{
				for(File innerFile: file.listFiles()){
					read(file.getName());
				}
			}
			
			fileContentArray=new String[fileContent.size()];
			for(int index=0;index<fileContentArray.length;index++){
				fileContentArray[index]=fileContent.get(index);
			}
		}
	}
	
	
	public static void write(String fileName, StringBuffer content){
		if(fileName!=null || !fileName.isEmpty()){
			File file=new File(fileName);
			if(file.exists())
				file.delete();
			//TODO���ļ���׺���жϣ�����
			try {
				OutputStreamWriter osWriter=new OutputStreamWriter(new FileOutputStream(file,false));
				BufferedWriter bWriter=new BufferedWriter(osWriter);
				try {
					bWriter.write(content.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					try {
						bWriter.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//not append
			
		}
	}
	
	
	

	public static ArrayList<String> getFileContent() {
		return fileContent;
	}

	public static String[] getFileContentArray() {
		return fileContentArray;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
