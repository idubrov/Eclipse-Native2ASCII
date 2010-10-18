package com.google.code.iso88591esc;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

public class EscapedCharsetDecoder extends CharsetDecoder {
	
	protected EscapedCharsetDecoder(Charset cs) {
		super(cs, 1, 1);
	}

	@Override
	protected CoderResult decodeLoop(ByteBuffer in, CharBuffer out) {
		while(in.remaining() > 0 && out.remaining() > 0) {
			byte b = in.get(in.position()); 
			if(b != '\\') {
				out.put((char) in.get());
				continue;
			}
			
		
			if(in.remaining() == 1) {
				return CoderResult.UNDERFLOW;
			}
			
			if(in.get(in.position() + 1) != 'u') {
				out.put((char) in.get());
				out.put((char) in.get());
				continue;
			}
			
			if(in.remaining() < 6) {
				return CoderResult.UNDERFLOW;
			}
			// Skip \ and u
			in.position(in.position() + 2);
			
			int codepoint = 0;
			for(int i = 0; i < 4; ++i) {
				codepoint <<= 4;
				b = in.get();
				codepoint += Character.digit((char) b, 16);
			}
			out.put((char) codepoint);
		}
		return in.remaining() == 0 ? CoderResult.UNDERFLOW : CoderResult.OVERFLOW; 
	}

}
