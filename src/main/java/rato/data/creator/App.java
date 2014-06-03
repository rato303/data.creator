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

            /* TODO
             * ArgsSelectServiceインタフェースの作成
             * ArgsInputServiceインタフェースの作成
             * └FilePathInputServiceクラスの作成
             */
            System.out.println("出力ファイルタイプを選択してください。");
            System.out.println("c:CSV q:終了する");

            while (reader.readLine()) {

                // TODO 終了ロジックのサービスを作成
                if ("q".equals(reader.getInputValue().getValue())) {
                    break;
                }

                System.out.println("テーブル定義ファイルのファイルパスを入力してください。");

                System.out.println("OUTPUT : " + reader.getInputValue().getValue());
            }
            reader.close();
            System.out.println("\nPROGRAM END");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }

}
