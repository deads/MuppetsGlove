package gnu.dtools.ritglove;

/**
 * GloveConnectionException.java
 *
 * Version: $Id: GloveConnectionException.java,v 1.1 2004/03/12 04:17:58 dre9227 Exp $
 */
 
 /**
  * Used to indicate that an error occured when attempting
  * to connect or disconnect the glove.
  *
  * <pre>
  * RITGlove: A Java interface to the 5DT glove.
  * Copyright (C) Damian Eads and James Tranovich, 2004-2005.
  *
  * This program is free software; you can redistribute it and/or
  * modify it under the terms of the GNU General Public License
  * as published by the Free Software Foundation; either version 2
  * of the License, or (at your option) any later version.
  *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  * 
  * You should have received a copy of the GNU General Public License
  * along with this program; if not, write to the Free Software
  * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
  * </pre>
  * @author Damian Eads
  */

public class GloveConnectionException extends Exception {

	public GloveConnectionException( String message ) {
		super( message );
	}
}
