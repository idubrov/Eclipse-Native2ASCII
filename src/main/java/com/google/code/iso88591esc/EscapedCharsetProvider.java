package com.google.code.iso88591esc;

import java.nio.charset.Charset;
import java.nio.charset.spi.CharsetProvider;
import java.util.Collections;
import java.util.Iterator;

public class EscapedCharsetProvider extends CharsetProvider {
	
	private final static Charset CHARSET = new EscapedCharset();

	@Override
	public Charset charsetForName(String charsetName) {
		if(EscapedCharset.NAME.equals(charsetName)) {
			return CHARSET;
		}
		return null;
	}

	@Override
	public Iterator<Charset> charsets() {
		return Collections.singletonList(CHARSET).iterator();
	}
}
