package rato.data.creator;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.config.DataBaseConfig;
import rato.data.creator.io.ArgsReader;
import rato.data.creator.service.cli.base.CommandLineService;
import rato.data.creator.service.setting.JdbcConfigFileReadService;

/**
 * アプリケーション実行用クラスです。
 *
 * @author toshiya
 *
 */
public class App {

	private Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		App app = new App();
		app.mainProcess(args);
	}

	private void mainProcess(String[] args) {
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

				this.logger.info("");
				this.logger.info("");

				service.question(result);

			}

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		} finally {
			if (dataBaseConfig.getDataSource() != null) {// TODO メソッド化
				dataBaseConfig.getLocalTransaction().rollback();
				this.logger.info("トランザクションをロールバックしました。");
			}
			this.logger.info("PROGRAM END");
		}
	}

}
