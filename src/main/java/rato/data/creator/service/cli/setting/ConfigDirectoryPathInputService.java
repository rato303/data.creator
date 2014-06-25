package rato.data.creator.service.cli.setting;

import static java.text.MessageFormat.format;
import static rato.data.creator.util.ResourceUtil.getExecutePath;

import java.util.ResourceBundle;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.InputValue;
import rato.data.creator.service.cli.base.BaseCommandLineService;

/**
 * <p>
 * 設定ファイルの配置してあるディレクトリパスを処理するサービスクラスです。
 * </p>
 * @author toshiya
 *
 */
public class ConfigDirectoryPathInputService extends BaseCommandLineService {

	@Override
	protected String getQuestionMessage(
			CommandLineServiceResultBo beforeResult, ResourceBundle bundle) {
		return format(bundle.getString("question.conf.dir"), this.getDefaultPath());
	}

	@Override
	protected void validateProcess(CommandLineServiceResultBo beforeResult,
			InputValue inputValue) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	protected CommandLineServiceResultBo mainProcess(
			CommandLineServiceResultBo beforeResult, InputValue inputValue) {
		// TODO 自動生成されたメソッド・スタブ
		return beforeResult;
	}

	/**
	 * <p>
	 * 設定ファイルの配置してあるデフォルトパスを取得します。
	 * </p>
	 * @return 設定ファイルの配置してあるディレクトリのデフォルトパス
	 */
	private String getDefaultPath() {
		return getExecutePath("..", "config");
	}

}
