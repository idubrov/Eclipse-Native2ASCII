package com.google.code.iso88591esc;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class EscapedCharset extends Charset {
	
	public final static String NAME = "ISO-8859-1-ESCAPED";

	protected EscapedCharset() {
		super(NAME, null);
	}

	@Override
	public boolean contains(Charset cs) {
		return true;
	}

	@Override
	public CharsetDecoder newDecoder() {
		return new EscapedCharsetDecoder(this);
	}

	@Override
	public CharsetEncoder newEncoder() {
		return new EscapedCharsetEncoder(this);
	}

}
