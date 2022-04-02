package gnu.dtools.ritglove;

/**
 * NativeGlove.java
 *
 * Version: $Id: NativeGlove.java,v 1.4 2004/03/13 00:34:44 dre9227 Exp $
 */
 
 /**
  * A native interface to General Reality's 3DT glove. For more information
  * on this glove, please refer to their website,
  * <a href="http://www.genreality.com">http://www.genreality.com</a>.
  *
  * <pre>
  * RITGlove: A Java interface to the 5DT glove.
  * Copyright (C) Damian Eads and James Tranovich, 2004-2005
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
  *
  * @author Damian Eads
  */

public class NativeGlove {
	
        static {
               System.loadLibrary("MuppetsGlove");
               initGloveArray();
        }
        
        /** Initializes static native constructs. */
        private static native void initGloveArray();
        
	/** A static constant indicating left-handedness. */
	public static final int LEFT_HANDED = 0;

	/** A static constant indicating right-handedness. */
	public static final int RIGHT_HANDED = 1;

	/** No glove installed. */
	public static final int GLOVE_TYPE_NO_GLOVE = 0;
	
	/** A 5 finger sensor, 2-tilt sensor glove. */
	public static final int GLOVE_TYPE_7 = 1;
	
	/** A 5 finger sensor, 2-tilt sensor wireless glove. */
	public static final int GLOVE_TYPE_7W = 2;
	
	/** A 16-sensor glove. */
	public static final int GLOVE_TYPE_16 = 3;
	
	/** A 16-sensor wireless glove. */
	public static final int GLOVE_TYPE_16W = 4;
	
	/** A static constant representing a fist gesture. **/
	public static final int GESTURE_FIST = 0;
	
	/** A static constant representing an index finger point
	    gesture. **/
	public static final int GESTURE_INDEX_FINGER_POINT = 1;
	
	/** A static constant representing an up yours gesture.*/
	public static final int GESTURE_UP_YOURS = 2;
	
	/** A static constant representing a two finger point gesture.*/
	public static final int GESTURE_TWO_FINGER_POINT = 3;
	
	/** A static constant representing a ring finger point gesture.*/
	public static final int GESTURE_RING_FINGER_POINT = 4;
	
	/** A static constant representing a ring index finger point
	    gesture. */
	public static final int GESTURE_RING_INDEX_FINGER_POINT = 5;
	
	/** A static constant representing a middle finger point gesture.*/
	public static final int GESTURE_RING_MIDDLE_FINGER_POINT = 6;
	
	/** A static constant representing a three finger point gesture.*/
	public static final int GESTURE_THREE_FINGER_POINT = 7;
	
	/** A static constant representing a little finger point gesture.*/
	public static final int GESTURE_LITTLE_FINGER_POINT = 8;
	
	/** A static constant representing a howzit gesture.*/
	public static final int GESTURE_HOWZIT = 9;
	
	/** A static constant representing a little middle finger point
	    gesture.*/
	public static final int GESTURE_LITTLE_MIDDLE_FINGER_POINT = 10;
	
	/** A static constant representing a not ring finger point
	    gesture.*/
	public static final int GESTURE_NOT_RING_FINGER_POINT = 11;
	
	/** A static constant representing a little ring finger point
	    gesture.*/
	public static final int GESTURE_LITTLE_RING_FINGER_POINT = 12;
	
	/** A static constant representing a not up yours gesture.*/
	public static final int GESTURE_NOT_UP_YOURS = 13;
	
	/** A static constant representing a not index finger point
	    gesture.*/
	public static final int GESTURE_NOT_INDEX_FINGER_POINT = 14;
	
	/** A static constant representing a flat hand gesture.*/
	public static final int GESTURE_FLAT_HAND = 15;
	
	/** A static constant representing the sensor number for the
	    thumb flexure measurement. **/
	public static final int SENSOR_THUMB_FLEXURE = 0;

	/** A static constant representing the sensor number for the
	    thumb flexure (second joint) measurement. Note: Not available on
	    the 5DT-5 model glove. **/
	public static final int SENSOR_THUMB_SECOND_FLEXURE = 1;

	/** A static constant representing the sensor number for the
	    thumb abduction measurement. Note: Not available on
	    the 5DT-5 model glove.**/
	public static final int SENSOR_THUMB_ABDUCTION = 2;
	
	/** A static constant representing the sensor number for the
	    index finger flexure measurement. **/
	public static final int SENSOR_INDEX_FINGER_FLEXURE = 3;
	
	/** A static constant representing the sensor number for the
	    index finger flexure (second joint) measurement. Note: Not
	    available on the 5DT-5 model glove. **/
	public static final int SENSOR_INDEX_FINGER_SECOND_FLEXURE = 4;
	
	/** A static constant representing the sensor number for the
	    index finger abduction measurement. Note: Not available on
	    the 5DT-5 model glove. **/
	public static final int SENSOR_INDEX_FINGER_ABDUCTION = 5;
	
	/** A static constant representing the sensor number for the
	    middle finger flexure measurement. **/
	public static final int SENSOR_MIDDLE_FINGER_FLEXURE = 6;
	
	/** A static constant representing the sensor number for the
	    middle finger flexure (second joint) measurement. Note: Not
	    available on the 5DT-5 model glove.**/
	public static final int SENSOR_MIDDLE_FINGER_SECOND_FLEXURE = 7;
	
	/** A static constant representing the sensor number for the
	    middle finger abduction measurement. Note: Not available on
	    the 5DT-5 model glove. **/
	public static final int SENSOR_MIDDLE_FINGER_ABDUCTION = 8;

	/** A static constant representing the sensor number for the
	    ring finger flexure measurement. **/
	public static final int SENSOR_RING_FINGER_FLEXURE = 9;
	
	/** A static constant representing the sensor number for the
	    ring finger flexure (second joint) measurement. Note: Not
	    available on the 5DT-5 model glove.**/
	public static final int SENSOR_RING_FINGER_SECOND_FLEXURE = 10;
	
	/** A static constant representing the sensor number for the
	    ring finger abduction measurement. Note: Not available on
	    the 5DT-5 model glove. **/
	public static final int SENSOR_RING_FINGER_ABDUCTION = 11;
	
	/** A static constant representing the sensor number for the
	    little finger flexure measurement. **/
	public static final int SENSOR_LITTLE_FINGER_FLEXURE = 12;
	
	/** A static constant representing the sensor number for the
	    little finger flexure (second joint) measurement. 
	    Note: Not available on the 5DT-5 model glove.
	    **/
	public static final int SENSOR_LITTLE_FINGER_SECOND_FLEXURE = 13;
	
	/** A static constant representing the sensor number for the
	    thumb translation measurement. Note: Not available on
	    the 5DT-5 model glove. Not implemented on the 5DT-16 model glove.
	    **/
	public static final int SENSOR_THUMB_TRANSLATION = 14;
	
	/** A static constant representing the sensor number for the
	    wrist translation measurement. Note: Not available on
	    the 5DT-5 model glove. Not implemented on the 5DT-16 model glove.
	    **/
	public static final int SENSOR_WRIST_TRANSLATION = 15;
	
	/** A static constant representing the sensor number for the
	    pitch angle measurement (which would be acquired from
	    the tilt sensor.) **/
	public static final int SENSOR_PITCH_ANGLE = 16;
	
	/** A static constant representing the sensor number for the
	    roll angle measurement (which would be acquired from
	    the tilt sensor.) **/
	public static final int SENSOR_ROLL_ANGLE = 17;
	
	/** Points to the location of the glove file descriptor
	    represented by the target in the native array of gloves.
	    See glove.cpp. */
	private int gloveId; 

	/**
	 * Constructs a NativeGlove.
	 */
	public NativeGlove() {
		gloveId = -1; // Means no glove has been opened.
	}
	
	/**
	 * Opens a connection to the glove. A GloveConnectionException
	 * is thrown if an error occurs while connecting to the glove.
	 *
	 * @param port The port used to connect to the device. A string
	 * containing "COMX" where X represents the COM port number used to
	 * connect to the glove of interest.
	 */
	public void open( String port ) throws GloveConnectionException {
		int id = nativeOpen( port );
		if ( id == -1 ) {
			throw new GloveConnectionException(
			     "Error opening glove." );	
		}
		gloveId = id;
	}
	
	/**
	 * Close the connection to the glove.
	 */
	public void close() throws GloveConnectionException {
		if ( gloveId == -1 ) {
			throw new GloveConnectionException(
			     "Glove is not connected anyways." );	
		}
		int status = nativeClose( gloveId );
		if ( status == 0 ) {
			throw new GloveConnectionException(
			     "Error closing glove." );
		}
		gloveId = -1;
	}
	
	
	/**
	 * This method is called prior to it being freed by the
	 * garbage collector. If the object is currently connected
	 * to a glove, the connection is closed.
	 */
	protected void finalize() {
		if ( gloveId == -1 ) {
			try {
				close();
			}
			catch ( GloveConnectionException e ) {
				e.printStackTrace();	
			}
		}
	}
	
	/**
	 * Performs the necessary C library calls to open the glove.
	 *
	 * @param port The port of the glove to open.
	 */
	private native int nativeOpen( String port );
	
	/**
	 * Performs the necessary C library calls to close the glove.
	 *
	 * @param id The id of the glove to close.
	 */
	private native int nativeClose( int id );
	
	/**
	 * Returns the total number of sensor values returned by this
	 * glove.
	 */
	public native int getNumberOfSensors();
	
	/**
	 * Returns the glove type constant.
	 *
	 * @returns The glove type constant.
	 */
	 
	public native int getGloveType();
	
	public native int getHandedness();
	
	/**
	 * Grabs the raw sensor data value from a particular sensor.
	 *
	 * @param sensorNumber
	 * @return The value returned by the sensor.
	 */
	public native short getRawSensorData(int sensorNumber)
		throws NoSuchSensorException;

	/**
	 * Grabs the raw sensor data values from the sensors.
	 *
	 * @return The value returned by the sensors.
	 */
	public native short[] getRawSensorData();
	
	/**
	 * Sets the raw sensor data value for a particular sensor.
	 *
	 * @param sensorNumber The sensor to modify.
	 * @param value        The new value of the sensor.
	 */
	public native void setRawSensorData(int sensorNumber, short value)
		throws NoSuchSensorException;
		
	/**
	 * Sets the raw sensor data values.
	 *
	 * @param values       The data values used to set the sensors.
	 */
	public native void setRawSensorData(short values[]);
	
	/**
	 * Grabs the scaled sensor data values from the sensors.
	 *
	 * @return The value returned by the sensor.
	 */
	public native float getScaledSensorData(int sensorNumber)
		throws NoSuchSensorException;
		
	/**
	 * Grabs the scaled sensor data value from a particular sensor.
	 *
	 * @param sensorNumber
	 * @return The values returned by the sensor.
	 */
	public native float[] getScaledSensorData();
	
	/**
	 * Returns the total number of gestures recognized by the glove.
	 *
	 * @return The total number of gestures.
	 */
	public native int getNumberOfGestures();
	
	/**
	 * Returns the current gesture recognized by the glove.
	 *
	 * @return The current gesture.
	 */
	public native int getGesture();
	
	/**
	 * Gets the calibration values from the glove.
	 *
	 * @return The calibration values.
	 */
	public native short[] getCalibration(int sensorNumber)
		throws NoSuchSensorException;
		
	/**
	 * Sets the calibration values for a particular sensor.
	 *
	 * @param sensorNumber The sensor to modify.
	 * @param lower        The lower value.
	 * @param upper        The upper value.
	 */
	public native void setCalibration(int sensorNumber, short lower,
					  short upper)
		throws NoSuchSensorException;
		
		
	/**
	 * Resets the calibration values.
	 */
	public native void resetCalibration();
	
	/**
	 * Retrieves the sensor maximums currently set for the glove.
	 *
	 * @return The sensor maximum data values.
	 */
	public native float[] getSensorMaximums();
	
	/**
	 * Retrieves a sensor maximum for a particular sensor.
	 *
	 * @param  sensorNumber The sensor of interest.
	 *
	 * @return The sensor maximum data value.
	 */
	public native float getSensorMaximum(int sensorNumber)
		throws NoSuchSensorException;
		
	/**
	 * Retrieves a sensor maximum for a particular sensor.
	 *
	 * @param  sensorNumber The sensor of interest.
	 *
	 * @return The sensor maximum data value.
	 */
	public native void setSensorMaximums(float maximums[]);
	
	/**
	 * Sets a sensor maximum for a particular sensor.
	 *
	 * @param sensorNumber The sensor to set.
	 * @param maximum The new maximum.
	 */
	public native void setSensorMaximum(int sensorNumber,
					    float maximum)
		throws NoSuchSensorException;
		
	/** Not implemented. */
	public native int getThreshold(float lower[], float upper[]);
	
	/** Not implemented. */
	public native int getThreshold(int sensorNumber,
				       float lower, float upper)
		throws NoSuchSensorException;
		
	/** Not implemented.*/
	public native void setThresholdAll(float lower[], float upper[])
		throws NoSuchSensorException;
		
	/** Not implemented.*/
	public native char[] getGloveInfo();
	
	/** Not implemented.*/
	public native String getDriverInfo();

}
