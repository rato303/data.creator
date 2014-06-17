package rato.data.creator;

import java.io.IOException;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.ConfigurationBo;
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

		ConfigurationBo configuration = new ConfigurationBo();
		DataBaseConfig dataBaseConfig = null;

		try {
			ArgsReader reader = new ArgsReader(System.in);

			CommandLineService service = new JdbcConfigFileReadService();
			CommandLineServiceResultBo result = null;

			service.question();

			while (reader.readLine()) {

				dataBaseConfig = configuration.getDataBaseConfig();

				if (dataBaseConfig.getDataSource() != null) {// メソッド化
					if (!dataBaseConfig.getLocalTransaction().isActive()) {
						System.out.println("トランザクションを開始しました。");
						dataBaseConfig.getLocalTransaction().begin();
					}
				}

				result = service.execute(configuration, reader.getInputValue());

				if (result.hasNotNextServiceFactory()) {
					break;
				}

				service = result.getFactory().create();

				service.question();

			}

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		} finally {
			if (dataBaseConfig != null) {// メソッド化
				dataBaseConfig.getLocalTransaction().rollback();
				System.out.println("トランザクションをロールバックしました。");
			}
			System.out.println("\nPROGRAM END");
		}

	}

}
