/**
 * Name: Kevin Chen
 * Assignment: Lab 8
 * Date: 04/17/2023
 * Notes: Class that implements Appendable intertface to throw error messages if append fails
 */

package cs5004.tictactoe;

import java.io.IOException;

public class FailingAppendable implements Appendable {

	@Override
	public Appendable append(CharSequence charSequence, int startIndex, int endIndex) throws IOException {
		throw new IOException("Failed to append sequence from index " + startIndex + " to index " + endIndex);
	}

	@Override
	public Appendable append(char c) throws IOException {
		throw new IOException("Failed to append character " + c);
	}

	@Override
	public Appendable append(CharSequence charSequence) throws IOException {
		throw new IOException("Failed to append character sequence " + charSequence);
	}
}
