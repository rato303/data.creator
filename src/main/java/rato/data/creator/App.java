package rato.data.creator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("INPUT : ");
		try {
			String line;
			while ((line = reader.readLine()) != null) {	// ユーザーの一行の入力を待つ
				if ("q".equals(line)) {
					break;
				}

				System.out.println("OUTPUT : " + line);
				System.out.println("\n INPUT : ");
			}
			reader.close();
			System.out.println("\nPROGRAM END");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
