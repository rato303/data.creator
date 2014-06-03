package rato.data.creator;

import java.io.IOException;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.io.ArgsReader;
import rato.data.creator.service.CommandLineService;
import rato.data.creator.service.InputTableConfFilePathService;

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

            CommandLineService service = new InputTableConfFilePathService();
            CommandLineServiceResultBo result = null;

            service.question();

            while (reader.readLine()) {

                // TODO 終了ロジックのサービスを作成
                if ("q".equals(reader.getInputValue().getValue())) {
                    break;
                }

                /*
                 * メッセージ出力
                 */
                result = service.execute(reader.getInputValue());

                if (result.hasNotNextServiceFactory()) {
                    break;
                }

                service = result.getFactory().create();

                service.question();

                /*
                 * 出力先フォルダ
                 * ↓
                 * テーブル定義ファイルからコマンドライン引数の設問を出力
                 * ↓
                 * 設問終了時に行をさらに追加するか、終了するかを聞く
                 */

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
