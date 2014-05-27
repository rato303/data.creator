package rato.data.creator;

import java.io.IOException;

import rato.data.creator.io.ArgsReader;

/**
 * アプリケーション実行用クラスです。
 *
 * @author toshiya
 *
 */
public class App {

	public static void main(String[] args) {

		try {
			ArgsReader reader = new ArgsReader(System.in);

			System.out.println("出力ファイルタイプを選択してください。");
			System.out.println("c:CSV x:XML q:終了する");

			while (reader.readLine()) { // ユーザーの一行の入力を待つa
				if ("q".equals(reader.getArg().getValue())) {
					break;
				}

				System.out.println("OUTPUT : " + reader.getArg().getValue());
			}
			reader.close();
			System.out.println("\nPROGRAM END");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

}
