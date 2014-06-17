package rato.data.creator.service.setting;

import java.io.File;
import java.util.ResourceBundle;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.InputValue;
import rato.data.creator.service.BaseCommandLineService;
import rato.data.creator.service.factory.DistDirectoryPathInputServiceFactory;
import rato.data.creator.service.factory.FindSelectTableInputServiceFactory;

/**
 * <p>
 * 生成物の出力先のディレクトリパスを処理するサービスクラスです。
 * </p>
 *
 * @author toshiya
 */
public class DistDirectoryPathInputService extends BaseCommandLineService {

	@Override
	protected String getQuestionMessage(ResourceBundle bundle) {
		return bundle.getString("question.dist.dir");
	}

	/**
	 * チェック処理はありません。
	 */
	@Override
	protected void validateProcess(InputValue inputValue) {
	}

	/**
	 * <p>
	 * 入力された出力先ディレクトリパスを設定情報に格納します。
	 * </p>
	 *
	 * @param beforeResult
	 *            TODO
	 *
	 * @param inputValue
	 *            入力された出力先ディレクトリパス
	 *
	 * @return コマンドラインの処理結果
	 */
	@Override
	protected CommandLineServiceResultBo mainProcess(
			CommandLineServiceResultBo beforeResult, InputValue inputValue) {

		if (inputValue.isEmpty()) {
			return new CommandLineServiceResultBo(
					beforeResult.getConfigurationBo(),
					new DistDirectoryPathInputServiceFactory());
		}

		this.createDistDirectory(inputValue.getValue());
		beforeResult.getConfigurationBo().setDistDirectoryPath(
				inputValue.getValue());

		return new CommandLineServiceResultBo(
				beforeResult.getConfigurationBo(),
				beforeResult.getDataBaseConfig(),
				new FindSelectTableInputServiceFactory());
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
