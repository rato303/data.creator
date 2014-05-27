package rato.data.creator.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import rato.data.creator.bo.Arg;

/**
 * 引数を読み込むバッファリーダーです。
 *
 * @author toshiya
 *
 */
public class ArgsReader {

	/** コマンドラインの読み込み用バッファリーダー */
	private BufferedReader argsReader;

	/** 読み込み中のコマンドライン入力情報 */
	private Arg arg;

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
		this.arg = new Arg();
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
			this.arg.setValue(this.argsReader.readLine());
		} catch (IOException e) {
			this.arg.clear();
		}
		return this.arg.isNotEmpty();
	}

	/**
	 * ストリームを閉じます。
	 *
	 * @see BufferedReader#close()
	 *
	 * @throws IOException
	 */
	public void close() throws IOException {
		this.argsReader.close();
	}

	/**
	 * 読み込み中のコマンドライン入力情報を取得します。
	 * @return 読み込み中のコマンドライン入力情報
	 */
	public Arg getArg() {
	    return arg;
	}

}
