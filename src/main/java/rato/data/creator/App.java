package rato.data.creator;

import java.io.IOException;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.config.DataBaseConfig;
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

		DataBaseConfig dataBaseConfig = null;

		try {
			ArgsReader reader = new ArgsReader(System.in);

			CommandLineService service = new JdbcConfigFileReadService();
			CommandLineServiceResultBo result = CommandLineServiceResultBo.create();

			service.question(result);

			while (reader.readLine()) {

				dataBaseConfig = result.getDataBaseConfig();
				dataBaseConfig.begin();

				result = service.execute(result, reader.getInputValue());

				if (result.hasNotNextServiceFactory()) {
					break;
				}

				service = result.getFactory().create();

				System.out.println("");
				System.out.println("");

				service.question(result);

			}

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		} finally {
			if (dataBaseConfig.getDataSource() != null) {// TODO メソッド化
				dataBaseConfig.getLocalTransaction().rollback();
				System.out.println("トランザクションをロールバックしました。");
			}
			System.out.println("\nPROGRAM END");
		}

	}

}
