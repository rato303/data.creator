package rato.data.creator.service.setting;

import java.io.File;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.ConfigurationBo;
import rato.data.creator.bo.InputValue;
import rato.data.creator.service.factory.DistDirectoryPathInputServiceFactory;

/**
 * <p>
 * 生成物の出力先のディレクトリパスを処理するサービスクラスです。
 * </p>
 *
 * @author toshiya
 */
public class DistDirectoryPathInputService extends SettingCommandLineService {

	/**
	 * コンストラクタ
	 */
	public DistDirectoryPathInputService(ConfigurationBo configurationBo) {
		super(configurationBo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getQuestionMessageKey() {
		return "question.dist.dir";
	}

	/**
	 * チェック処理はありません。
	 */
	@Override
	protected void validateProcess(InputValue inputValue) {
	}

	/**
	 * <p>入力された出力先ディレクトリパスを設定情報に格納します。</p>
	 *
	 * @param configurationBo アプリケーション設定情報
	 *
	 * @param inputValue 入力された出力先ディレクトリパス
	 *
	 * @return コマンドラインの処理結果
	 */
	@Override
	protected CommandLineServiceResultBo configurationMainProcess(
			ConfigurationBo configurationBo, InputValue inputValue) {

		if (inputValue.isEmpty()) {
			return new CommandLineServiceResultBo(
					new DistDirectoryPathInputServiceFactory(configurationBo));
		}

		this.createDistDirectory(inputValue.getValue());
		configurationBo.setDistDirectoryPath(inputValue.getValue());

		return new CommandLineServiceResultBo();
	}

	/**
	 * <p>
	 * 出力先ディレクトリを作成します。
	 * </p>
	 * 既に存在する場合は何もしません。
	 *
	 * @param distDirectoryPath
	 *            出力先ディレクトリパス
	 */
	private void createDistDirectory(String distDirectoryPath) {
		File distDirectory = new File(distDirectoryPath);
		distDirectory.mkdirs();
	}

}
