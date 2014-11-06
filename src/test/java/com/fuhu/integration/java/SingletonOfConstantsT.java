package com.fuhu.integration.java;

import java.util.HashMap;
import java.util.Map;

import org.vertx.java.core.buffer.Buffer;

import com.fuhu.EnumOfAPIStatus;

public class SingletonOfConstantsT {
	/* Setup for Singleton pattern */
	private static SingletonOfConstantsT instance = null;

	private SingletonOfConstantsT() {

	}

	public static SingletonOfConstantsT getInstance() {
		if (instance == null) {
			instance = new SingletonOfConstantsT();
		}
		return instance;
	}
	
	/* Constant for state-map */
	protected final static Map<StatesOfClient, Boolean> mapStates;
	static {
		mapStates = new HashMap<StatesOfClient, Boolean>();
		for (StatesOfClient s : StatesOfClient.values()) {
			mapStates.put(s, false);
		}
	}

	/* Constants for HTTP request methods */
	protected final String POST_REQUEST = "POST";
	protected final String GET_REQUEST = "GET";
	protected final String PUT_REQUEST = "PUT";
	protected final String DELETE_REQUEST = "DELETE";
	/* Constants of API path */
	protected final String PATH_OF_PER_PACKAGE_UPSERT_AND_DELETE = "/cloud/com.fuhu.nabi.radio/stream/stations";
	protected final String PATH_OF_PER_PACKAGE_GET = "/cloud/com.fuhu.nabi.radio/stream/stations/timestamp?ts=";
	protected final String PATH_OF_PER_PACKAGE_AND_USER_UPSERT_AND_DELETE = "/cloud/user/:userKey/:packageName/stream/:streamKey";
	protected final String PATH_OF_PER_PACKAGE_AND_USER_GET = "/cloud/user/:userKey/:packageName/stream/:streamKey/timestamp?ts=";
	/* Constants for print command */
	protected final String END_SMALL = "----------------------------------------------------------------------------------}";
	protected final String END_BIG = "EndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEnd";
	/* Constants of testing data*/
	protected final byte[] byteArray_post = {-119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82, 0, 0, 0, 64, 0, 0, 0, 64, 8, 6, 0, 0, 0, -86, 105, 113, -34, 0, 0, 0, 4, 115, 66, 73, 84, 8, 8, 8, 8, 124, 8, 100, -120, 0, 0, 19, 63, 73, 68, 65, 84, 120, -100, -27, -101, 121, -76, 101, 71, 117, -34, 127, -69, -86, -50, 116, -17, 125, -9, -51, -35, -3, -44, 19, -35, 72, 66, -120, -95, 109, 4, 45, 100, 9, 7, -59, 2, 99, -127, -116, 99, -31, 33, -117, -104, -60, -106, -19, 68, 36, 43, -80, 18, -116, -19, -112, 4, -117, 44, 59, 56, -114, 29, 98, 3, -122, -59, 50, -74, -27, 1, -80, 69, 36, -52, 32, 107, 0, -87, 45, 36, 33, -119, 33, -102, 104, 13, 72, -22, 126, 61, -68, 121, -68, -61, -71, 103, -88, 42, -1, 113, -18, 123, -35, 106, 108, -116, -33, 123, -35, 100, -83, -44, 90, -75, -50, -71, 119, -99, 123, 106, 127, 95, -19, -67, 107, -17, 93, 117, -31, -1, -127, 118, -31, -17, -60, 63, -7, -67, -106, -31, -100, -75, -26, 117, -100, -65, -2, 97, -128, 23, -67, -19, -31, 113, -1, -26, -65, 30, -98, -71, -28, 15, 27, -97, -6, 94, -56, 35, -25, 122, -64, -41, -4, -97, -26, 77, -95, -27, 77, -125, -37, -126, -69, 2, -89, -34, 48, -66, 45, 32, 45, 28, 83, -57, 122, -19, 7, -17, 94, -3, -83, 11, 95, 81, 59, -1, -66, -73, -74, -33, 118, -82, -28, 57, -89, 4, 28, -8, 72, -19, -106, -3, 47, -114, 95, 55, 49, 22, -43, 40, 21, -74, 11, -74, 16, 68, 123, 36, -14, -88, 17, -49, -3, -73, -83, -4, -14, -61, -41, 119, -1, -57, -71, -110, -23, -100, 18, 112, -51, 29, 35, 110, 98, 36, 18, -105, 42, 108, 87, -31, 51, -63, -39, -118, 0, -107, 56, 76, -45, -79, -40, -55, 87, -25, 103, -13, -29, -121, 126, 106, -11, 37, -25, 66, 38, 125, 46, 6, 121, -3, 23, -122, -90, 46, -6, -25, -55, 123, -58, 70, -94, -48, -75, -75, 42, 87, 52, -74, -83, -80, 105, -97, -124, 92, 112, -91, -32, 45, -44, 7, 85, -92, 13, -29, 92, -127, -97, -5, 108, 113, 15, -32, -49, -90, 108, -22, 108, -66, 124, -67, 21, 60, -78, 115, 79, 50, 76, 71, -21, 98, 89, 83, -74, 20, -82, -85, 112, -103, -32, 10, -63, 101, -126, -19, 10, -59, -118, 38, 91, 80, 36, 65, -64, -59, 47, -87, -35, 0, -72, -77, 45, -38, 89, 39, -32, 53, -97, 108, 126, 108, -9, -2, -28, -11, -67, 25, -95, 88, -46, -40, 118, 5, -40, -107, -32, -35, -87, -18, 114, -63, -91, 80, -82, 40, -24, 42, 26, 81, -64, 21, -97, 108, -2, -15, -39, -106, -17, -20, -7, -128, 107, 121, -27, 27, 126, 126, -8, 119, -74, -17, -120, 94, 99, -25, 52, 118, 69, -31, 83, -123, 47, 1, 47, -107, 98, 123, 1, -4, -70, 20, 94, -88, 28, 98, -30, -48, -125, -106, 112, -62, 115, -20, 104, -6, -107, 59, 127, 116, -7, -78, -77, 37, -26, 89, 33, -32, -27, 31, -87, -65, -29, -30, 75, -21, 31, -120, 10, 77, -79, 40, -72, -106, -126, 92, 85, -32, 52, -32, -64, 101, 10, 95, 10, -120, 71, 12, -88, -48, -127, 120, 124, 41, 56, 39, 16, 58, -44, -128, 35, -36, -31, 57, 54, -103, 126, -27, 75, 111, 62, 59, 36, 108, -71, 9, 28, -8, 112, -19, -105, 95, 122, 89, -29, 3, 106, 69, -47, -101, 86, -108, -53, 10, 74, 8, -122, 74, 74, 60, -66, -116, -79, 40, -126, -111, 28, 93, -73, -24, -70, -61, 12, -27, 88, -81, -15, 46, -63, 6, -114, -96, 105, -15, 69, 101, 14, -67, -109, -62, -50, -35, -15, -85, 95, -13, -105, -51, 47, 109, -75, -84, -80, 5, 4, 92, -12, 123, -75, -1, -78, 118, 127, -63, 111, 71, -1, -22, -94, 75, 27, -17, -73, -77, 66, 62, -89, 40, -105, 5, 95, -128, 25, -76, -52, 31, -79, -108, 109, -121, -75, 93, 108, -81, 100, 126, 50, 39, 58, -81, -121, 25, 75, -103, 123, 46, 39, 79, 123, 88, -37, -59, -75, 12, 43, -77, 37, -31, -96, -61, -25, 80, 46, 11, -39, 73, 97, -49, -2, -38, -107, -81, -67, 101, -16, -34, -75, -79, 94, -4, -95, -28, -19, -81, -4, -60, -64, -69, 55, 43, -1, -90, 77, -32, 77, 95, 29, -15, -74, -27, -89, -116, 86, 19, 113, -92, 97, 89, 97, -105, 52, -82, 45, -120, -121, 100, -36, 115, 108, -14, 4, -33, 63, 114, 29, 111, -4, -2, -97, 99, 98, 104, 55, 101, -39, -27, -21, -49, -35, -53, -97, 29, -71, 14, -65, 58, -56, -11, 7, 63, -50, 69, 59, 15, -128, 24, 38, 23, -97, -31, -26, -81, -3, 47, 78, -16, 57, -122, -22, 59, -55, 87, 5, 34, -113, 30, 116, -104, 113, 79, 39, 43, -79, -34, 51, -72, 83, 51, -5, 100, 62, -13, -59, 107, -106, 119, 124, 79, 9, 56, -8, -15, -127, 79, -17, 63, 80, -5, -15, 114, 5, 92, -69, -78, 119, -41, 22, 40, 21, 65, -35, -45, -115, -113, -13, -117, 123, 111, -27, -97, 30, 124, 3, 89, 1, 70, 32, -42, -112, 104, 120, -12, -71, 111, 49, 62, 60, -50, -114, -95, 65, 82, 11, -103, 5, 11, 4, 26, -2, -20, -114, -33, -25, -74, -12, -19, -88, -91, 93, -40, -36, 35, 9, -88, 1, -121, -86, 121, 8, 61, 18, -63, -55, -93, -23, 125, -9, -67, 117, -11, -14, -51, -56, -65, -39, 64, -24, 124, -10, 51, -76, 99, 103, 114, 85, 57, -89, -80, 43, 26, -41, 17, 124, 46, -120, 8, 52, -90, -7, -31, -35, 55, -16, 99, -105, -2, 28, 75, 105, -119, -11, 14, -63, -93, -88, 22, -127, -13, 70, 70, -87, -57, 49, -103, -11, 20, -42, -109, 91, 71, 102, 61, -67, -46, 113, -7, -59, -105, -14, -11, -25, 30, 102, 41, 123, 26, -105, 70, -72, -36, 87, -53, 103, 87, -80, 105, 101, 90, -51, 23, -24, -35, -121, -25, 58, 95, -29, 9, -98, -38, 40, 0, -77, 25, -12, 47, -1, 96, -3, -3, 59, 47, -118, -81, -75, 43, -126, 93, 21, 124, -113, 42, 116, -15, 32, -127, -61, -59, 37, 7, -58, 95, -57, 106, 6, -123, -13, 24, 17, -100, 86, 88, 17, 10, -25, 112, -3, 24, -49, 122, -113, 19, -123, 19, 79, -23, 45, -42, -61, 74, 15, 46, 59, -17, 39, 120, -90, 117, 11, -98, 26, -74, 0, 41, -63, -117, -128, -87, 102, 110, -23, -31, -68, -59, 45, 124, 110, 51, 24, 54, -27, 4, 31, -7, 119, -99, -73, 28, -65, 63, -3, 20, -128, 43, -63, 21, -43, -43, 123, -113, 40, 79, 9, 76, 52, 47, 32, -77, -106, -46, -127, 21, -59, -12, -44, 73, -114, 77, 30, -91, 68, -111, 89, 79, 102, 61, 22, -59, -28, -28, 81, -90, -90, 78, 98, 81, 20, -82, -6, 126, 87, -29, -59, -8, -128, 106, 121, -76, -3, 49, 114, -16, -71, 96, 83, 104, 110, 11, 7, -82, 126, 112, -52, -13, 67, 92, -75, 81, 12, 27, -42, -128, 75, -2, -68, -15, 43, 74, -79, -3, -95, -33, 109, 127, 98, -17, -117, -101, 63, 5, -32, 61, -107, 110, 59, 64, 32, 79, 97, -80, 57, -62, 66, 94, 80, 120, 40, 122, 25, -65, 119, -61, -81, -46, -21, 101, -36, -16, -63, -113, 49, 80, 111, 0, -48, 106, -83, -14, -101, -17, 121, 55, 81, 28, -15, 31, 126, -5, -93, -120, 50, -28, -50, -45, 8, -57, -16, -3, 41, -14, -89, -67, -33, 59, 80, 37, -8, 82, 56, 126, 123, -9, 78, -66, -56, -99, 27, -59, -79, 97, 13, 8, 98, 57, -80, -9, 64, -14, -50, -85, -34, 53, -6, 23, -66, 0, -17, 60, 107, -8, -41, -69, -85, 70, 40, 61, 88, 47, -100, 56, 54, -55, -12, -12, 52, -45, 51, 51, 76, 30, 57, 66, -31, -123, 2, 97, -14, -56, 17, -90, -90, -89, -103, -99, -98, -31, -60, -28, 36, 22, -63, 58, -113, 72, -128, -9, 103, -128, -89, -46, 48, -128, -43, 19, 37, -113, -4, -25, -18, -21, 54, -118, 97, 83, 4, 124, -27, -33, -73, 126, 99, -15, -55, -110, 90, 108, 66, -41, 3, 111, -5, 113, -67, -81, -70, -77, -96, 12, -84, 118, 123, 116, 74, 79, -89, -12, 100, 94, -13, -123, -37, -18, -32, 75, 119, 31, 98, 46, -75, 28, 94, 46, 120, 120, 33, 103, -86, 91, 114, -41, -95, -65, -31, -13, -73, -35, 78, -41, 43, 90, 69, -11, 124, 59, 107, 33, 30, -36, -38, 123, -41, -64, -9, -57, 8, 27, -46, 125, -19, 45, 67, -9, -65, -23, -55, 81, 15, -68, -22, -100, 18, -64, 113, 30, 93, 120, -70, -4, 3, -33, 19, 92, 23, 92, 33, 120, -17, -5, -67, -78, -41, 48, -128, -7, -91, -29, 40, 81, 44, -11, 10, -4, -10, 125, -68, -12, -110, -125, -20, -39, -77, -121, -38, -66, -105, 80, 56, -64, 123, -102, -5, 95, -54, -34, 61, 123, 120, -23, 37, 7, 81, 59, -10, -77, -102, -107, 40, 81, 44, 100, 71, 113, 89, -97, -36, -2, -69, 79, -41, -126, 120, 80, -43, 52, -22, -43, 39, 14, -11, 62, 2, 60, -76, 17, 24, -101, 90, 5, 38, 31, 109, 125, 122, -37, -10, -8, 58, -79, -90, 82, -9, -66, 87, -9, 120, 92, 9, -110, -62, -77, 43, 15, -16, 125, 99, -25, -77, -84, 11, -54, 50, -29, -65, 125, -6, 110, 2, -15, -44, -115, 48, 61, -3, 40, 35, -51, -35, -104, 100, -112, 15, -34, 115, -104, -62, 11, 20, 25, 81, -96, 104, -122, -118, 47, -50, -33, 13, -67, 74, -101, -98, 87, 21, -16, 85, -2, 48, -9, 108, -2, -40, -125, -65, -76, -4, -78, -51, 96, -40, -44, 42, -80, 116, 35, -73, -50, 60, -34, 59, 36, 81, 101, -96, 126, 77, -56, -66, 6, -112, -114, -14, -71, 103, -34, -57, 104, 4, -51, 64, -47, 48, 66, -35, -64, 80, -92, -39, 85, 15, 120, 104, -7, -65, -13, -40, -52, -57, -103, -88, -123, 12, -122, -102, -70, -122, -122, 17, 6, 67, -115, 41, 115, 110, 63, -7, -21, -48, -37, 81, 101, -112, -84, -103, 87, 63, 123, 20, -120, -122, -44, -60, 102, -28, -121, 45, -88, 8, -51, -36, -101, 63, -66, -21, -121, -109, 95, 48, 40, -4, -6, 76, 85, -23, -82, 120, 77, 47, 57, 74, -40, -35, -55, -63, 23, 30, 4, -124, 122, -96, -39, -39, 8, 121, -24, -16, 45, 28, 90, 121, 47, -33, 106, -35, -55, -85, -22, 111, 97, -9, -40, 121, 24, 17, -102, -111, 97, -9, -128, -31, 99, -9, -4, 71, 78, -74, 30, -64, -82, 54, -79, 22, -42, -39, -11, -128, 2, -119, 32, 24, -91, 54, -4, 102, -45, 124, -31, -65, -114, 63, 48, -7, -11, -20, 94, -26, -104, 62, -25, 4, 92, 113, -29, -48, -31, 70, 51, -120, 108, 7, 124, 81, -59, -1, -21, -7, -67, 23, -76, 109, -16, 127, -45, 79, 32, -83, 38, 23, 14, -67, 8, 83, -90, -36, -10, -40, -57, -72, -15, -39, -97, 69, 47, -20, 64, -11, -22, -36, 57, -13, 91, 108, 119, 23, -79, 111, 96, 15, 54, 93, -30, -113, 30, -8, -81, -36, -65, -16, -65, -15, 75, 19, -40, -116, -11, 101, 101, -115, 3, -47, 21, 1, -90, 1, -125, 123, -43, 101, -35, 37, 91, 28, -5, 96, -74, -95, -60, 104, -45, -71, -64, -91, 31, 106, -34, 51, 58, -98, 92, -111, -97, -84, -62, -44, -11, 34, -106, -128, 72, -75, 18, -24, -40, 67, 99, -102, 34, -88, 6, 52, 5, -48, -103, 32, -21, 1, 120, -62, -102, -121, -6, 12, -91, -86, 126, 99, 50, 13, -19, 113, -54, 76, -16, -27, 41, -32, -21, -17, 13, 60, 122, 8, 78, 60, -41, -6, -38, -29, 55, 119, -2, 19, 79, 112, -5, 70, -27, -33, -108, 19, 4, -24, 44, -40, -57, 70, -122, -71, 2, 87, 97, -17, 121, -121, 66, 8, 17, -108, 7, -101, -125, -77, -126, -22, 77, -96, 13, 88, 60, 75, -91, 37, -18, -27, 124, 95, 52, 76, -96, 13, -49, 46, 46, 113, -76, 51, 70, 83, 107, 2, 20, -74, -84, 28, -97, -17, -109, -23, -127, 28, 79, -23, 61, -111, 18, 66, 13, 42, -127, 104, 88, -7, -51, -128, -33, 18, 2, -102, -69, -125, -97, 16, 13, 94, 67, -45, 43, 126, -95, 62, -58, -110, -75, 124, -90, -77, -60, -108, 118, 24, 4, 74, -113, 47, 60, -71, 45, -7, 1, -35, -32, -38, 109, -69, 121, -19, -117, 38, -64, 24, -16, -96, 113, 60, 50, 59, -49, -89, 103, 38, -7, 66, -70, 8, -58, -96, -92, -14, 116, 78, 32, 40, 28, -41, 38, -61, -68, 48, 12, -71, 43, 107, -15, 68, -36, 3, -79, 124, -3, -35, -83, 13, -83, -3, -89, -73, -51, -105, -60, -82, -28, -46, 125, 23, 69, 111, -118, -9, 55, -34, 115, 91, 125, -73, -44, -35, 50, -120, 66, 7, 13, 22, 51, -24, -71, 2, -89, 12, 38, -86, 49, 94, 31, 36, 105, 54, -79, 58, -62, -23, -96, -78, 15, -64, 59, -117, 114, 57, -70, -52, -80, -35, 14, 51, 43, 75, -12, -78, 14, -54, 102, -124, 42, 98, 36, 82, -120, -19, 96, 109, 65, 45, 30, -26, -38, -71, -109, -99, -109, -90, 119, -88, -74, 71, -82, 126, -18, -18, -10, 53, 51, 31, -79, 27, 78, -120, -74, -82, 38, -8, 102, -82, 57, -4, 67, 47, -6, -85, 90, -73, -51, 106, -22, 24, 27, 53, 32, 10, 49, 9, 42, -84, -95, -94, 26, 4, 117, 36, 76, 80, 65, -116, -104, 8, 81, 122, -99, 0, 95, -26, -8, -94, -121, 43, -70, -112, 119, 113, 121, 7, -97, -89, -72, 34, -59, 123, 75, -73, -21, 72, -69, -106, 23, -20, 24, -30, -11, -35, -103, -27, -5, -33, -75, 52, -68, 21, 98, 111, -35, -58, -56, -109, 60, -11, 100, -93, -73, -112, -98, 95, -69, -6, -85, 79, -73, -103, -80, -62, 96, 83, -9, 67, -40, 53, -98, 29, -72, 18, -17, 74, -68, -51, -15, 101, 86, -127, -18, 3, 118, 121, 7, 95, -92, -72, -94, 87, -11, 50, -61, 59, -117, 120, -31, 67, 119, -51, 114, 116, 127, -125, 63, 106, 116, -7, -62, -109, -117, -17, -27, 65, 127, -17, 119, -108, -25, -69, 108, 91, 95, 21, -66, -114, 119, 124, 110, -41, -10, 15, 92, 58, 30, -109, -105, 22, 124, -119, -104, 16, 101, 66, 84, -44, 36, -36, -2, 10, 8, -21, 120, 95, 21, 70, -112, -45, -68, -68, -77, 20, -13, -33, -60, -74, -114, -31, -54, 28, 111, 115, 64, -95, -108, -95, -109, 57, 126, -28, -10, -29, -17, 122, -6, 14, 62, 58, -8, 58, -58, 87, -18, -32, -71, -83, 16, 119, -53, 9, -8, -52, -49, -60, 31, 126, -19, 101, -61, -41, 119, -14, 18, -99, -20, -60, -116, 31, 32, 45, 60, 5, 10, 103, 75, -36, -4, -61, 24, -65, 74, 96, 12, -34, 87, -114, 78, -108, -57, 57, 75, 86, 10, 106, -8, 0, 42, 30, 66, 99, -119, -115, -96, -70, -45, -28, -13, 95, 37, 12, 2, -98, 61, -106, 77, 30, -4, -51, -91, -67, 91, 41, -17, -90, 87, -127, 51, -101, 58, -17, -43, 59, 22, -122, 95, 64, 59, -75, -76, 87, -105, -23, 62, 116, 11, 66, 72, -73, 93, -46, 109, -107, 116, 83, 77, -38, 83, -28, -91, 70, 7, 17, -94, 20, 54, -49, 80, -28, 36, 49, -44, -110, 59, -87, 53, 52, -75, 1, 67, 96, 44, -86, 126, 33, -51, -19, 87, -45, -120, -123, 110, 123, 97, 4, 62, -65, -91, -14, 110, 57, 1, -9, 4, -33, -56, 123, -73, -33, -61, -46, 104, 66, -69, 16, -62, 39, 74, -84, 109, -111, -60, -122, -92, -106, -48, 108, 52, -39, 62, 49, 76, -46, 28, 37, -86, 13, 34, 74, -109, -89, -85, -12, 90, -117, 116, 87, 23, 73, 91, 43, 44, -100, -24, 112, 60, 43, -80, -91, 66, -10, 125, 19, -78, -61, 108, 63, -98, 114, -94, 30, -52, 109, -75, -68, 91, 106, 2, 63, -3, -27, -3, 79, -123, 53, 115, -63, -14, -100, 35, 93, -76, -20, 122, 34, -27, 39, -97, -24, 33, 49, 24, -93, 8, -30, 58, 81, 109, -104, 60, 108, -80, -84, 98, -106, 81, 56, -124, -90, 56, -122, 92, 78, -83, 104, 83, 116, -105, -55, -45, 22, -42, 90, 92, -26, 121, 96, 60, -32, -48, -91, 117, 6, -58, 12, -115, 109, -118, 114, -75, 120, -32, 83, 63, -8, -36, -85, -73, 74, -26, 45, 35, -32, -97, -35, -66, -25, -61, -51, -111, -8, -6, -12, -120, 35, 95, -12, -108, 61, 71, 55, -9, 12, -76, 115, -58, 86, 74, -14, -68, 100, -34, 58, -98, -74, 61, 102, 124, 15, 109, 65, 28, -88, 126, -72, -20, -115, -95, -95, 2, 46, 80, 49, -37, 16, 34, -91, 105, -123, -62, -119, 17, -61, 96, 18, 16, 53, 12, -47, -88, -112, -20, 85, 44, -51, -92, -17, -5, -52, -43, -57, -34, -69, 21, 114, 111, 9, 1, 111, -8, -28, -50, 95, 28, -33, 87, -5, 104, 49, 11, -7, -116, -57, -10, -86, -108, -51, -106, -112, -10, 74, 58, -35, 30, 69, 94, -30, -83, -59, 104, -123, -110, 106, 104, -91, -86, -124, 65, 41, 65, 16, -100, -9, -108, -50, 82, -108, -126, -120, 38, -119, 53, 113, 44, 24, 35, 4, -95, 38, -88, 107, -94, 9, 65, 13, -71, 114, -2, -39, -18, -65, -68, -19, 95, 76, -3, -7, 102, 101, -33, 116, 28, 112, -43, -115, 59, 126, 124, -17, 43, 7, -1, 36, 59, 1, -7, 60, -40, -44, 35, 74, 19, -104, 0, 101, 2, -60, 105, -116, 18, -110, -40, -96, 21, 68, -111, 97, 108, -5, 32, 67, 35, 3, 40, -91, 24, -33, 62, 72, 24, 5, 20, 101, -63, -64, 96, -126, 86, 66, -96, 13, -11, 90, 66, 28, 7, -124, -111, 70, -42, 54, 18, 60, 80, 42, 76, -88, -44, -24, -59, -55, -75, 118, -76, 115, -21, -52, -95, -14, -60, 102, -28, -33, -12, -34, -32, -16, -50, -28, -33, 100, -77, -98, 124, -63, 99, -69, 14, 65, 97, -116, 1, 52, 81, 35, -92, 62, -98, 80, -85, -57, -3, 89, 84, -116, 79, 12, 51, -74, 125, -104, -42, 74, -101, 78, -69, -115, -46, -102, 78, -69, -51, -64, 80, -11, -36, -32, 72, -125, 48, -126, -26, 112, -115, -58, -74, 26, -15, 64, -116, 18, -125, 40, -123, -77, 80, 118, 60, -7, 60, 20, -117, -98, 11, 46, -33, -2, -50, -51, -54, -65, 41, 13, -72, -26, -81, 118, -65, 115, 100, 79, -14, -10, 116, -46, 83, -82, 120, 124, 9, -38, 104, 76, 16, 34, 98, -104, -99, 92, -64, 102, 5, 105, -89, -123, 54, -118, 36, 9, 25, 24, 108, 80, 102, 22, -21, 74, -102, 67, 85, 64, -44, 104, 38, -60, 73, 84, 9, -92, 21, 74, 11, -67, 110, 7, 95, 10, -19, -123, 46, -11, 102, -126, 82, -126, -9, -18, 84, -39, 29, 97, 96, 95, -16, -78, -119, 31, 12, 15, 63, 125, 83, -21, -15, -115, 98, -40, -108, 6, -44, -122, -52, -21, -13, 121, -121, 109, 121, 108, -31, 64, 4, -83, 13, 74, 52, 58, 82, -44, 6, 107, 44, -51, -49, -93, -76, 34, 12, 13, 38, 48, 116, -37, 93, 122, 89, -113, -92, 22, 17, 37, 33, 97, 108, 8, -61, 0, -83, 21, -38, 40, 68, -124, 32, 52, 4, -127, 97, 101, 97, -111, -6, 72, 29, 29, 6, 104, 109, -48, -58, -32, 1, -101, 123, -54, 85, 79, -79, -24, 104, 108, 11, -81, -39, 12, -122, 77, 17, 16, 38, -6, 71, -14, -27, -54, -29, -69, -46, 87, -5, -127, -88, 42, -72, 41, 60, -50, 121, 68, 41, -108, 18, 84, 127, 102, 17, 80, 74, -48, 70, -93, -108, -62, 104, -125, -46, 26, 17, -123, 82, -43, 51, 74, 41, 68, 9, 74, -23, -22, -67, 40, 84, 96, -64, 87, 21, 19, 103, 61, -74, -25, -56, -105, 61, 38, 86, 63, -3, 61, 33, -32, 45, 119, -18, -3, -97, -30, -123, -78, -27, 112, 121, 85, -78, 86, 74, -31, 93, -75, -87, 49, 127, 108, -119, -71, -29, 39, -87, 53, 98, -30, 36, 92, 7, -84, -115, -58, -12, -17, -107, 40, -108, -82, -64, -118, -106, -25, -111, 21, -59, 33, -11, -127, -104, -59, -23, 89, 22, -89, 86, -42, -9, 27, -124, -86, -4, 94, 105, -127, 35, 12, -75, -7, -47, -49, -18, -6, -91, 115, 78, -128, -114, -28, -14, -78, -27, -80, -87, -57, -106, -82, 18, 78, 105, -78, -76, 68, -59, -62, -4, -20, 44, 65, 20, 18, 39, 17, 65, 20, 84, 4, 104, -35, -73, -15, 83, -67, -102, 105, 65, 73, 95, 75, 84, -11, 76, 16, 24, -30, 90, 68, 24, -121, 44, -51, 45, 98, 106, -118, 34, 115, -120, 84, -53, -91, 43, 61, 54, -11, 20, 45, 71, 60, -88, -1, -55, 70, 113, 108, 56, 20, -74, -91, -73, -28, -32, -6, -86, -18, 108, 95, -35, -115, 103, 121, -82, -51, -64, -48, 32, 72, 78, 16, 26, -116, -47, 104, -83, -111, -66, 122, 107, -91, -6, -9, 125, 117, -105, -86, -94, 36, -42, -31, 80, -96, 4, 17, 69, -34, -21, -94, -108, 48, 60, 49, -60, -62, -55, 22, 73, 28, 34, -86, 50, 55, -108, -57, 22, 10, -101, 11, 46, 88, 43, -100, -1, -29, -37, -122, 53, 64, -60, 15, -69, -62, 98, -83, -61, 91, 79, -111, 89, -84, 119, -76, -106, 123, -100, 120, -14, 56, -50, -91, 12, -115, 14, 84, 26, 16, 6, 88, -21, -86, -39, 53, 26, 29, 84, 102, 96, -76, 65, 43, 77, 16, 4, 116, 86, 122, -43, 103, -93, 112, -91, -93, -41, -51, 112, -34, 18, -41, 66, -108, 47, -103, 63, 54, 71, 123, -91, 71, 89, 58, -68, -81, 8, 119, -42, -31, 74, 7, -8, -15, -115, -30, -40, -80, 6, 120, -27, -21, -82, -20, 11, -30, 28, 65, 77, -15, -20, 55, -90, 41, 125, -105, -63, -47, 58, 73, 61, -94, -34, 72, -120, -30, -112, -59, -7, 101, -100, 117, 68, 89, -56, -24, -74, 97, 86, -105, -37, -52, 79, 47, -79, -17, -62, 93, 40, -91, 120, -26, -16, 113, -100, -73, -28, -67, 33, -58, -49, 27, -94, -37, -50, 72, -69, 41, 97, 24, 96, 75, 75, 109, 56, 33, -53, 114, -26, -89, 103, -111, 19, 17, 19, -25, -41, 41, 123, 22, 103, 21, -74, 116, 0, -11, -115, -30, -40, -80, 6, -108, -123, 77, -99, 119, 56, 87, -51, 72, 119, 37, -89, -101, -74, 8, -109, -128, -92, 22, 97, 2, 67, 20, 71, 44, -50, 45, -125, 7, 99, 52, 113, 18, -79, 48, -77, 76, -89, -43, -91, -47, 76, -104, 58, 62, 87, 109, 114, -58, -118, -31, -79, 1, 58, -99, 118, -27, 24, 69, 48, -95, 65, -92, 58, 105, -30, -67, -89, -42, 72, 72, -22, 1, -67, 94, -101, -18, 106, 81, -7, 1, -21, 42, -13, 115, 46, -35, 40, -114, 13, 107, -128, -13, 110, 65, 68, 85, -22, -24, 60, 58, 20, -108, 22, 2, -93, 81, -90, -78, -21, 78, 59, -59, 57, -117, -42, 6, 81, -126, 115, 14, -25, 45, -11, -127, 24, -91, 53, 89, -102, -47, -19, -90, 52, -121, 27, 24, -93, -15, -34, -45, 90, -19, -110, 101, 57, -86, -1, 110, 81, 66, 89, -106, -107, 99, 12, 13, -94, 5, 19, 43, 92, 81, -99, 48, -15, -54, 97, -83, -35, 112, -102, -68, 113, 13, -56, -19, 55, 9, 29, 94, 60, -42, -71, -18, -36, -47, -42, -67, 97, 98, -120, -110, 16, -83, 21, -126, -112, -91, 89, -75, -45, 1, -120, 8, 121, 86, 16, 4, 6, -91, 52, 74, 9, 113, 45, -62, -38, 18, 19, 84, 113, 64, 24, 5, -92, -99, 110, 63, 14, 56, 67, 80, 37, 68, 113, 72, 92, 11, -104, 122, 102, 37, -11, -72, -36, -117, -121, -48, -111, 103, -10, -47, 115, 78, -64, -44, 35, -99, -101, -91, -26, 65, 123, 60, 78, -106, 23, 58, -97, -41, -127, 34, -116, 12, 74, 43, 16, 112, -18, -7, 103, -99, 69, 9, -94, 78, 37, -96, 90, 107, -62, 40, 64, -11, -47, -82, 5, 64, -50, -39, 111, 75, 83, 69, 87, 4, 5, -79, -95, -37, 106, 125, 25, -48, 24, 15, -79, -29, -74, 107, -26, 62, -66, 81, 28, 27, 38, -32, 27, -65, -46, -67, 53, 79, 75, 36, -14, 68, 3, 38, -119, 107, -22, 114, 111, 43, 115, -64, -9, 39, -2, 121, 40, -4, -73, -25, -34, 82, 5, 54, -43, -61, 85, 36, -71, 22, 48, -83, 105, -50, 105, 63, -57, 59, -113, 43, 29, 81, -100, -20, 14, -21, 74, 75, -20, -23, -75, -54, 22, -16, -52, 70, 113, 108, 42, 20, -106, -56, -29, -107, -89, -69, 84, -34, -100, 52, -29, 125, 90, 105, 4, -11, -68, -67, 124, 89, -121, -3, -99, 74, 15, -89, -10, -43, -27, -52, -17, -28, -44, 71, -17, 65, -119, 34, 105, 70, -25, -75, -26, -14, -101, 85, 8, 18, -6, 77, -107, -11, 54, 71, 64, 0, 38, 86, 124, -7, -122, -39, 95, 107, -49, 22, -73, -122, 81, -128, -42, -70, 127, 10, 92, -50, -104, -60, 127, -24, 127, 15, -14, 29, 110, -91, 15, 94, 19, 70, 33, -23, 114, 118, -33, -111, -5, 87, -1, 80, 5, 2, 16, 108, 6, -61, 38, -21, 1, 66, -34, -79, -68, -30, -99, 67, -1, -10, -56, 77, -59, -105, 93, 33, 56, -53, -70, 25, 32, -14, 93, -42, -100, -2, -82, -121, -28, 121, 118, -28, 29, -3, -124, 75, 113, -4, -66, -12, -18, 93, 7, 27, 111, -51, 91, -106, -17, 118, -124, -65, -81, 109, 84, 125, 4, -16, 74, -61, 23, -81, -97, -67, 16, -72, 4, -40, -45, 61, -23, -18, 106, 12, -54, -107, 54, -9, 16, 74, -59, -82, 94, 19, -15, -37, -100, -62, -87, -26, 61, -66, -81, 33, -21, -89, -63, -42, -50, 27, -71, -118, 80, 91, 122, -84, -11, 20, 109, -33, 106, 63, -59, -46, -125, -17, 95, -68, 9, -8, -45, 55, -34, 57, -10, -39, 13, 98, 0, 54, 94, 16, 9, -127, -6, -109, -9, 116, 111, 101, -111, 58, 48, 6, -44, 103, 31, 75, -97, -34, -10, -14, -28, 85, 58, -46, -119, 43, -3, 58, 24, 17, 65, 73, 127, 21, -24, 7, 55, 114, -122, -75, -5, 126, 62, 97, -83, -61, -106, -114, -78, 112, -108, -7, -87, -21, 90, -50, 49, -11, 72, -9, 51, -117, 79, -91, 83, 84, -57, -118, 79, 28, 123, -76, 123, 99, 57, -51, 2, -89, -107, 74, -2, 49, 109, -93, -22, -45, 0, 6, -88, 52, 104, 59, 48, 14, 12, 2, 17, -80, -4, -78, -73, -115, -68, 99, -28, 5, -75, 43, -67, 3, -91, 65, 27, -123, 9, 20, 38, 84, 104, -83, 81, -70, -14, 15, 34, -78, -18, -19, -67, -11, 88, 87, -127, -73, -123, -93, 44, -41, -30, -4, 106, -23, -52, -38, 54, 125, -22, -81, -25, 127, 99, -10, -31, -34, -102, -57, 95, 1, 102, -5, -67, 0, 90, 64, -5, 92, 17, 16, -11, 9, -48, 84, -38, 80, -21, -9, 8, 72, -128, 108, -17, 85, -115, 43, 71, 95, -104, -68, -47, -60, -22, 96, -44, 48, 90, -108, -12, -117, 29, -78, -74, -22, 85, 106, -65, 118, -12, -89, -65, -52, -83, 31, -124, 2, 108, -31, 41, 58, -18, -15, -42, 116, -17, -10, -61, 55, 45, 127, -78, -1, -2, 18, -56, -128, 20, -24, 2, 57, -107, 54, -84, 2, -67, 115, 69, 0, 84, -34, 87, -11, -5, 26, 17, 113, -65, 71, -3, 103, 74, 32, 27, -71, 68, 93, -71, -21, 101, 67, -105, -121, 117, 115, -79, 14, 100, -113, 40, -39, -82, 3, 9, 69, 87, -61, 59, -21, 113, -123, -57, 90, -33, -62, -6, -87, -78, -16, -33, 74, 23, -117, 7, -98, -8, -53, -27, -101, -6, 64, -125, -2, 24, 89, 31, 100, -113, 106, -42, -35, 105, 61, -37, 8, -120, -83, -38, 24, 17, 42, 115, -48, -3, -21, -23, 93, 83, -51, -48, -102, -16, -89, 39, 46, -93, -3, -21, -62, 25, -17, 75, 56, -91, 81, -114, -118, -56, 51, -5, -103, -89, 7, 55, 44, -8, 86, 54, 57, -83, -85, -65, -25, -2, -12, 49, -3, 105, -65, 59, -3, -69, -45, -113, 93, -1, 93, -9, 103, -11, -49, -108, -1, 95, -75, -65, 5, 55, 125, 123, -73, -23, 24, 41, 35, 0, 0, 0, 0, 73, 69, 78, 68, -82, 66, 96, -126};;
	protected final byte[] byteArray_update = {-34, 0, 0, 0, 4, 115, 66, 73, 84, 8, 8, 8, 8, 124, 8, 100};
	protected final Buffer TEST_BINARYDATE_POST = new Buffer(byteArray_post);
	protected final Buffer TEST_BINARYDATE_UPDATE = new Buffer(byteArray_update);
	
	/* Constants of Headers */
	protected final String APIKEY_K = "APIKEY";
	protected final String APIKEY_V = "1234567890abcdef";
	protected final String APIKEY_INVALID_V = "1234567890abcdeg";
	protected final String ACCEPT_K = "Accept";
	protected final String ACCEPT_BINARY_DATA_V = "binary/octet-stream";
	protected final String ACCEPT_JSON_V = "application/json";
	protected final String ACCEPT_BINARY_DATA_INVALID_V = "binary/octet-streaa";
	protected final String ACCEPT_JSON_INVALID_V = "application/jsoa";
	protected final String CONTENT_TYPE_K = "Content-Type";
	protected final String CONTENT_TYPE_BINARY_DATA_V = "binary/octet-stream";
	protected final String CONTENT_TYPE_JSON_V = "application/json";
	protected final String CONTENT_TYPE_BINARY_DATA_INVALID_V = "binary/octet-streaa";
	protected final String CONTENT_TYPE_JSON_INVALID_V = "application/jsoa";
	/* Constrants for status codes */
	protected final static String OK = EnumOfAPIStatus.ok.getStatusCode();
	protected final static String UNKNOWN_ERROR = EnumOfAPIStatus.unknownError.getStatusCode();
	protected final static String MISSING_API_KEY = EnumOfAPIStatus.apiKeyMissing.getStatusCode();
	protected final static String INVALID_API_KEY = EnumOfAPIStatus.apiKeyInvalid.getStatusCode();
	protected final static String MISSING_ACCEPT_HEADER = EnumOfAPIStatus.missingAcceptHeader.getStatusCode();
	protected final static String INVALID_ACCEPT_HEADER = EnumOfAPIStatus.invalidAcceptHeader.getStatusCode();
	protected final static String MISSING_SESSION_KEY = EnumOfAPIStatus.missingSessionKey.getStatusCode();
	protected final static String INVALID_SESSION_KEY = EnumOfAPIStatus.invalidSessionKey.getStatusCode();
	protected final static String MISSING_CONTENT_TYPE_HEADER = EnumOfAPIStatus.missingContentTypeHeader.getStatusCode();
	protected final static String INVALID_CONTENT_TYPE_HEADER = EnumOfAPIStatus.invalidContentTypeHeader.getStatusCode();
	protected final static String MISSING_HTTP_REQUEST_BODY = EnumOfAPIStatus.missingHttpRequestBody.getStatusCode();
	protected final static String INVALID_OR_INCORRECT_HTTP_REQUEST_BODY = EnumOfAPIStatus.invalidOrIncorrectHttpRequestBody.getStatusCode();
	protected final static String MISSING_USER_UUID_STATUS_CODE = EnumOfAPIStatus.missingUserUuid_inside_http_headers.getStatusCode();
	protected final static String DUPLICATED_EMAIL_CODE = EnumOfAPIStatus.duplicateEmail.getStatusCode();
	/* Constrants for status codes */
	protected final static String OK_DESC = EnumOfAPIStatus.ok.getDesc();
	protected final static String UNKNOWN_ERROR_DESC = EnumOfAPIStatus.unknownError.getDesc();
	protected final static String API_KEY_MISSING_DESC = EnumOfAPIStatus.apiKeyMissing.getDesc();
	protected final static String API_KEY_INVALID_DESC = EnumOfAPIStatus.apiKeyInvalid.getDesc();
	protected final static String MISSING_ACCEPT_HEADER_DESC = EnumOfAPIStatus.missingAcceptHeader.getDesc();
	protected final static String INVALID_ACCEPT_HEADER_DESC = EnumOfAPIStatus.invalidAcceptHeader.getDesc();
	protected final static String MISSING_SESSION_KEY_DESC = EnumOfAPIStatus.missingSessionKey.getDesc();
	protected final static String INVALID_SESSION_KEY_DESC = EnumOfAPIStatus.invalidSessionKey.getDesc();
	protected final static String MISSING_CONTENT_TYPE_HEADER_DESC = EnumOfAPIStatus.missingContentTypeHeader.getDesc();
	protected final static String INVALID_CONTENT_TYPE_HEADER_DESC = EnumOfAPIStatus.invalidContentTypeHeader.getDesc();
	protected final static String MISSING_HTTP_REQUEST_BODY_DESC = EnumOfAPIStatus.missingHttpRequestBody.getDesc();
	protected final static String INVALID_OR_INCORRECT_HTTP_REQUEST_BODY_DESC = EnumOfAPIStatus.invalidOrIncorrectHttpRequestBody.getDesc();
	protected final static String MISSING_USER_UUID_STATUS_DESC = EnumOfAPIStatus.missingUserUuid_inside_http_headers.getDesc();
	protected final static String DUPLICATED_EMAIL_DESC = EnumOfAPIStatus.duplicateEmail.getDesc();

	/* Constants for reading database returned message from database to Server in Json format */
	protected final static String DB_STATUS = "status";
	protected final static String DB_ROWS = "rows";
	protected final static String DB_FIELD = "fields";
	protected final static String DB_RESULT = "results";
	protected final static String DB_ERROR = "error";
	protected final static String DB_MESSAGE = "message";
	
	/* Constants and variables for writing output data from Server to Client */
	protected final static String NABI_CLIENT_DATA_BACKUP_APIVersion_K = "NabiClientDataBackupAPIVersion";
	protected final static String NABI_CLIENT_DATA_BACKUP_APIVersion_V = "1.0";
	protected final static String STATUS = "status";
	protected final static String STATUS_DESCRIPTION = "statusDescription";
	protected final static String LAST_TIME_MODIFIED = "lastTimeModified";
}
