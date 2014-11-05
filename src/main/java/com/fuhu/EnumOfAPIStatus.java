package com.fuhu;

import java.util.HashMap;
import java.util.Map;


// Reference: https://confluence.fuhu.org/display/FOA/Create+User+API+3.0?src=contextnavpagetreemode#CreateUserAPI3.0-POST/usermanagement/v3/user.1
// Reference: https://confluence.fuhu.org/display/FOA/Fuhu+API+Error+Codes?src=contextnavpagetreemode
public enum EnumOfAPIStatus {

	ok														("0", 		"Success"),
	unknownError											("1", 		"Unknown Error"),
	duplicateEmail											("101", 	"Duplicate email"),
	emailFormatInvalid										("102",		"Email format invalid"),
	internalServerError										("103", 	"Internal server error"),
	apiKeyInvalid											("104", 	"API key invalid"),
	lastNameMissing											("105", 	"Lastname missing"),
	firstNameMissing										("106", 	"Firstname missing"),
	passwordMissing											("107", 	"Password missing"), 
	apiKeyMissing											("108", 	"API key missing"),
	deviceTypeMissingButDeviceIdFound						("109", 	"DeviceType missing but DeviceId found"),
	deviceIdMissingButDeviceTypeFound						("110", 	"DeviceId missing but DeviceType found"),
	oldPasswordInvalid										("111",		"Old password invalid"),
	emailMissing											("113", 	"Email missing"),
	emailInvalid											("115", 	"Email Invalid"),
	loginError												("116",		"Login error"), 
	apiKeyNotAuthorized										("118",		"API key not authorized"),
	missingAuthKey											("119",		"Missing auth key"),
	invalidSessionKey										("120",		"Invalid Session Key"), 
	missingSessionKey										("128",		"Missing session key"),
	invalidCountryKey										("143", 	"Invalid Country Key"),
	missingLicenseVersion									("155", 	"Missing License Version"),
	invalidLicenseVersion									("156", 	"Invalid License Version"),
	notLatestLicenseVersion									("157", 	"Not Latest License Version"),
	invalidLanguage											("159", 	"Invalid Language"),
	deviceTypeInvalid										("173", 	"Device Type Invalid"),

	userNameAlreadyUsed										("208",		"The Username is already in use by another account"), // add-child api	
	
	duplicatedUserName										("244", 	"Duplicated User Name"),
	invalidPassword											("319",		"Invalid password"),
	
	// 9xx: New User Errors
	noOneDollarPayment										("900",		"No One Dollar Payment"), // ???
	failToCreateSubuser										("901",		"Fail To Create Subuser"),
	notPassEmailVerification								("903", 	"Not pass email verification"),
	missingBirthday											("904", 	"MISSING_BIRTHDAY"),
	missingGender											("905",		"Missing gender"),
	missingKidId											("906",		"Missing kid id"), 
	invalidKidId											("909",		"Invalid kid id"), 
	missingDisplayName										("911",		"Missing display name"), 
	
	umInvalidBirthday										("20102",	"UM_INVALID_BIRTHDAY"), 
	
	invalidRequestParam										("200001", 	"Invalid request parameter"), // new status codes for nabiGo
	missingHttpRequestBody									("200002", 	"Missing HTTP request body"), 
	invalidOrIncorrectHttpRequestBody						("200003", 	"Invalid or incorrect HTTP request body"),
	emptyJsonArrayInHttpRequestBody							("200004", 	"Empty JSON array in HTTP request body"),
	apiNotImplemented										("200005",	"API not implemented"),
	databaseError											("200006", 	"Database error"), 
	databaseErrorUnsupportedTable							("200007",	"Database error: unsupported table"),
	unsupportedTableType									("200008",	"Unsupported table type"),
	emailVerificationCodeGenerationError					("200010",	"Email verification code generation error"),
	osgUserCreatedButUnableToCreateUserRecordInNabiGoDb		("200011",	"OSG user created but unable to create user record in NabiGo DB"),
	missingUserUuid											("200012", 	"Missing user UUID"),
	missingUserKey											("200013",	"Missing user key"), 
	missingAuthorizationKey									("200014",	"Missing authorization key"),
	unableToExtractUserUUID									("200015",	"Unable to extract User UUID"),
	unableToExtractProfileId								("200016",	"Unable to extract Profile Id"),
	invalidUserUUID											("200017",	"Invalid user UUID"),
	multipleUsersFoundForTheSameUUID						("200018", 	"Multiple users found for the same UUID"),
	incorrectRequestBody									("200019",	"Incorrect request body"),
	missingName												("200020",	"Missing name"),
	missingUsername											("200021",	"Missing username"),
	profileNotFound											("200022",	"Profile not found"),
	foundMultipleProfiles									("200023",	"Found multiple profiles"),
	kidIdNotFound											("200024",	"Kid Id not found"),
	encounteredErrorWhenFindingUserInDb						("200025",	"Encountered error when finding user in database"),
	encounteredErrorWhenFindingProfileInDb					("200026",	"Encountered error when finding profile in database"),
	missingField_fullname									("200027",	"Missing field: fullname"),
	missingField_height										("200028",	"Missing field: height"),
	missingField_weight										("200029", 	"Missing field: weight"),
	missingField_stride										("200030",	"Missing field: stride"),
	missingField_current_energy_goal						("200031",	"Missing field: current_energy_goal"),
	missingField_sleep_monitor_on							("200032",	"Missing field: sleep_monitor_on"),
	missingEmail											("200033",	"Missing email"),
	missingEmailVerificationCode							("200034",	"Missing email verification code"),
	errorEncounteredWhenCheckingEmailVerificationCode		("200035",	"Error encountered when checking email verification code.  Possible causes: incorrect email, incorrect code, or the code has already been verified."),
	missingContentTypeHeader								("200036",	"Missing Content type Header"),
	invalidContentTypeHeader								("200037",	"Invalid Content type Header"),
	missingAcceptHeader										("200038",	"Missing Accept Header"),
	invalidAcceptHeader										("200039",	"Invalid Accept type"),	
	missingIPAddress                    					("200040",  "Missing IP address"),
	errorOccurredBecauseEmailInvalidOrIpAddressInvalid    	("200041",  "Error occurred because email address is invalid or ipaddress is invalid"),
	daoLayerError_unsupportedColumnDataType					("200042",	"DAO layer error: unsupported column datatype"),
	daoLayerError_unsupportedKeyDataType					("200043",	"DAO layer error: unsupported key datatype"),
	containsBothEmailandUseruuidInHeader					("200044",	"Contains both useruuid and email in header"),
	missingBothEmailandUseruuidInHeader						("200045",	"Missing both useruuid and email in header"),
	missingPhotoUrl											("200046",	"Missing field: photo URL"),
	missingField_unit										("200047",	"Missing field: unit"),
	missingField_sleep_monitor_begin						("200048",	"Missing field: sleep_monitor_begin"),
	missingField_sleep_monitor_end							("200049",	"Missing field: sleep_monitor_end"),
	missingField_alarm_on									("200050",	"Missing field: alarm_on"),
	missingField_alarm_time									("200051",	"Missing field: alarm_time"),
	missingField_alarm_snooze_on							("200052",	"Missing field: alarm_snooze_on"),
	missingField_alarm_snooze_interval						("200053", 	"Missing field: alarm_snooze_interval"),
	missingField_current_ec_mac								("200054",	"Missing field: current_ec_mac"),
	invalidOrBirthdayConversionError						("200055",	"Invalid or birthday conversion error"),	
	missing_input_osg_kid_id								("200056",	"Missing input: osg_kid_id"),
	missingUserUuid_inside_http_headers						("200057", 	"Missing user UUID inside HTTP headers"),
	
	param_error_osg_user_key								("200100",	"parameter error: OSG user key"),
	param_error_osg_auth_key								("200101",	"parameter error: OSG auth key"),
	param_error_session_key									("200102",	"parameter error: session key"),
	param_error_osg_kid_id									("200103",	"parameter error: OSG kid id"),
	param_error_nabigo_user_uuid							("200104",	"parameter error: nabiGo user UUID"),
	param_error_nabigo_profile_uuid							("200105",	"parameter error: nabiGo profile UUID"),
	param_error_fooz_kids_email_address_not_found			("200106",	"parameter error: email address not found"), 
	
	appErrorUserRecordNotFoundInDatabase					("200200",	"application error: user record not found in database"), 
	appErrorRetrievedMoreThanOneUserRecordFromDatabase		("200201",	"application error: retrieved more than one user record from database"),
	appErrorRetrievedIncorrectTypeOfRecordFromDatabase		("200202",  "application error: retrieved incorrect type of record from database"),
	appErrorUnableToGetUserInfoFromOSG						("200203", 	"application error: unable to get user info from OSG");
	
	
	
	protected static Map<String, EnumOfAPIStatus> statusToEnumMap = null;
	
	protected static void initMapping() {
		if (statusToEnumMap == null) {
			statusToEnumMap = new HashMap<String, EnumOfAPIStatus>();
			
			for (EnumOfAPIStatus status : EnumOfAPIStatus.values()) {
				statusToEnumMap.put(status.getStatusCode(), status);
			}
		}
	}
	
	public static EnumOfAPIStatus statusCodeToEnum(String statusCode) {
		
		try {
			initMapping();
			
			EnumOfAPIStatus statusEnum = statusToEnumMap.get(statusCode); 
					
			if (statusEnum != null)
				return statusEnum;
			else {
				//Utility.logger.info("Unknown status code = " + statusCode);
				return unknownError;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return unknownError;
	}
	
	public static EnumOfAPIStatus translateFoozKidsErrorCodeToNabiGoStatusCode(Number error_code) {

		try {
			switch (error_code.intValue()) {
			case 101:
				return apiKeyInvalid;
				
			case 210:
				return param_error_fooz_kids_email_address_not_found;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return unknownError;
	}
	
	
	String statusCode;
	public String getStatusCode() {
		return statusCode;
	}
	
	String desc;
	public String getDesc() {
		return desc;
	}
	
	
	
//	
//	String statusMsg = "";
//	public String getStatusMsg() {
//		return statusMsg;
//	}
//	public APIStatusEnum setStatusMsg(String statusMsg) {
//		this.statusMsg = statusMsg;
//		return this;
//	}
//	
//	String errorClass;
//	public String getErrorClass() {
//		return this.errorClass;
//	}
//	/*
//	public APIStatusEnum setErrorClass(String errorClass) {
//		this.errorClass = errorClass;
//		return this;
//	}
//	*/
//	public APIStatusEnum setErrorObject(Object errorObject) {
//		if (errorObject != null)
//			this.errorClass = errorObject.getClass().getCanonicalName();
//		return this;
//	}
//	
	
	/*
	String errorUUID;
	public String getErrorUUID() {
		return this.errorUUID;
	}
	public NabiGoApiStatusCode setErrorUUID(String errorUUID) {
		this.errorUUID = errorUUID;
		return this;
	}
	*/
	
	EnumOfAPIStatus(String statusCode, String desc) {
		this.statusCode = statusCode;
		this.desc = desc;
//		this.errorClass = "";
		//this.errorUUID = "";
	}
}
