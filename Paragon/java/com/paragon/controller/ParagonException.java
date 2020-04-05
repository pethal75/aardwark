package com.paragon.controller;

/**
 * ParagonException contains errors specific to paragon maniulation operations.
 * 
 * @author peter
 *
 */
public class ParagonException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 209564541046746187L;

	public ParagonException(String message) {
		super(message);
	}

	public ParagonException(Throwable exc) {
		super(exc);
	}
}
