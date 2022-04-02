package gnu.dtools.ritglove;

/**
 * NoSuchSensorException.java
 *
 * Version: $Id: NoSuchSensorException.java,v 1.1 2004/03/12 04:17:59 dre9227 Exp $
 */
 
 /**
  * Used to indicate that an invalid/non-existant sensor was referenced.
  *
  * @author Damian Eads
  */

public class NoSuchSensorException extends Exception {

	public NoSuchSensorException(String message) {
		super(message);
	}

}
