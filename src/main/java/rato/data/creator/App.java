package rato.data.creator;

import java.io.IOException;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.io.ArgsReader;
import rato.data.creator.service.CommandLineService;
import rato.data.creator.service.setting.JdbcConfigFileReadService;

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

            CommandLineService service = new JdbcConfigFileReadService();
            CommandLineServiceResultBo result = null;

            service.question();

            while (reader.readLine()) {

                result = service.execute(reader.getInputValue());

                if (result.hasNotNextServiceFactory()) {
                    break;
                }

                service = result.getFactory().create();

                service.question();
                /*
                 * 設問終了時に行をさらに追加するか、終了するかを聞く
                 */
            }

            reader.close();
            System.out.println("\nPROGRAM END");

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }

}
