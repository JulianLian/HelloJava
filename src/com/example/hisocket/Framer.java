package com.example.hisocket;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Julian on 2015/7/9.
 */
public interface Framer {
	void frameMsg(byte[] message, OutputStream out) throws IOException;
	byte[] nextMsg() throws IOException;
}
