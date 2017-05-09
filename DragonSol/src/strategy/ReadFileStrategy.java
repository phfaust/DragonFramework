package strategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReadFileStrategy implements ReadStrategy{

	@Override
	public void read(Main m) {
		BufferedReader br = null;
		FileReader fr = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Input file path: ");
		String path = sc.next();
		
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String sCurrentLine;
			br = new BufferedReader(new FileReader(path));

			while ((sCurrentLine = br.readLine()) != null) {
				sCurrentLine = sCurrentLine.trim();
				System.out.println(sCurrentLine);
				
				try{
					m.in(sCurrentLine);
				} catch (Exception e){
					e.printStackTrace();
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (br != null) br.close();
				if (fr != null) fr.close();
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}	
		sc.close();
	}
}
