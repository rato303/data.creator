package rato.data.creator.service.select;

import java.util.ResourceBundle;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.InputValue;
import rato.data.creator.service.BaseCommandLineService;

/**
 * <p>
 * データ生成をするテーブルを選択するサービスです。
 * </p>
 *
 * @author toshiya
 *
 */
public class SelectTableInputService extends BaseCommandLineService {

	@Override
	protected String getQuestionMessage(ResourceBundle bundle) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	protected void validateProcess(CommandLineServiceResultBo beforeResult,
			InputValue inputValue) {
		// TODO 入力された値の検証
		// TODO 正の整数かどうかのチェック
		// TODO インデックスの範囲内かどうかのチェック
	}

	@Override
	protected CommandLineServiceResultBo mainProcess(
			CommandLineServiceResultBo beforeResult, InputValue inputValue) {
		// TODO カラム一覧を取得
		// TODO 選択されたテーブルのFK一覧を取得
		// TODO 関連するテーブルのデータを削除
		return null;
	}

}
