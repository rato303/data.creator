package rato.data.creator.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import rato.data.creator.bo.InputValue;

/**
 * <p>引数を読み込むバッファリーダーです。</p>
 *
 * @author toshiya
 *
 */
public class ArgsReader {

    /** コマンドラインの読み込み用バッファリーダー */
    private BufferedReader argsReader;

    /** 読み込み中のコマンドライン入力情報 */
    private InputValue inputValue;

    /**
     * <p>
     * コンストラクタ
     * </p>
     *
     * @param in
     *            コマンドラインの入力情報 {@link System#in}
     */
    public ArgsReader(InputStream in) {
        this.argsReader = new BufferedReader(new InputStreamReader(in));
        this.inputValue = new InputValue();
    }

    /**
     * <p>
     * コマンドラインの情報を読み込みます。
     * </p>
     *
     * @return 読み込めた場合は「true」読み込めなかった場合は「false」を返します。
     */
    public boolean readLine() {
        try {
            this.inputValue.setValue(this.argsReader.readLine());
        } catch (IOException e) {
            this.inputValue.clear();
        }
        return this.inputValue.isNotEmpty();
    }

    /**
     * <p>ストリームを閉じます。</p>
     *
     * @see BufferedReader#close()
     *
     * @throws IOException
     */
    public void close() throws IOException {
        this.argsReader.close();
    }

    /**
     * <p>読み込み中のコマンドライン入力情報を取得します。</p>
     * @return 読み込み中のコマンドライン入力情報
     */
    public InputValue getArg() {
        return inputValue;
    }

}
