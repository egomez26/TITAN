package org.pnpl.analysis.invariant.utils;

public interface Interrupter {

	/**
	 * Determines if a task should be aborted.
	 *
	 * @return true if a task shoul be aborted
	 */
	boolean isInterruptRequested();

}