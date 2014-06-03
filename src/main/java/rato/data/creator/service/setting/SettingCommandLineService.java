package rato.data.creator.service.setting;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.ConfigurationBo;
import rato.data.creator.bo.InputValue;
import rato.data.creator.service.BaseCommandLineService;

/**
 * <p>設定情報を処理するサービスの基底クラスです。</p>
 *
 * @author toshiya
 */
public abstract class SettingCommandLineService extends BaseCommandLineService {

    /** アプリケーションの設定情報 */
    private ConfigurationBo configurationBo;

    /**
     * コンストラクタ
     *
     * @param configurationBo アプリケーションの設定情報
     */
    public SettingCommandLineService(ConfigurationBo configurationBo) {
    	this.configurationBo = configurationBo;
    }

    /* (non-Javadoc)
     * @see rato.data.creator.service.BaseCommandLineService#mainProcess(rato.data.creator.bo.InputValue)
     */
    @Override
    protected final CommandLineServiceResultBo mainProcess(InputValue inputValue) {
      return this.configurationMainProcess(configurationBo, inputValue);
    }

    /**
     * 入力された値をアプリケーションの設定情報に格納します。
     *
     * @param configurationBo アプリケーションの設定情報
     *
     * @param inputValue 入力された値
     *
     * @return コマンドラインの処理結果
     */
    protected abstract CommandLineServiceResultBo configurationMainProcess(ConfigurationBo configurationBo, InputValue inputValue);

}
