package com.lognsys.util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.lognsys.model.DailyLog;
import com.lognsys.model.Users;

/**
 * 
 * @author pdoshi
 * 
 *         TODO : Use this class for validation of forms attributes
 */

public class FormValidator implements Validator {

	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	String ID_PATTERN = "[0-9]+";
	String STRING_PATTERN = "[a-z \\s A-Z]+";
	String MOBILE_PATTERN = "[0-9]{10}";
	String RATING_PATTERN = "[0.1-5.0]{2}";
	String ZIPCODE_PATTERN = "[0-9]{6}";
	String STRING_NUMERIC_PATTERN = "[a-z \\s A-Z] [0-9]";
	String DOUBLE_PATTERN = "[0-9]+(\\.){0,1}[0-9]*";
	private static final String TIME12HOURS_PATTERN =
            "(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)";

	@Override
	public boolean supports(Class clazz) {
		   return Users.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
//		System.out.println("Form Validation target "+target);
		if (target instanceof Users) {
//			System.out.println("Form Validation target Users "+target);
			
			Users users = (Users) target;
			
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(dt);

			users.setBirthdate(currentTime);

			if (users.getFirstname() != null && users.getLastname() != null) {
				users.setRealname(users.getFirstname() + " " + users.getLastname());

			}

			validateFirstName(users, errors);
			validateLastName(users, errors);
			validateAddress(users, errors);
			validateCity(users, errors);
			validateState(users, errors);
			validateZipcode(users, errors);
			validatePhonenumber(users, errors);
			validateEmail(users, errors);
		}else if (target instanceof DailyLog) {
//			System.out.println("Form Validation target Users "+target);
			
			DailyLog dailyLog = (DailyLog) target;
			
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(dt);

			validateRealname(dailyLog, errors);
			validateRemark(dailyLog, errors);
			validateMachine(dailyLog, errors);
			validateDescription(dailyLog, errors);
			validateSpareParts(dailyLog, errors);
			validateAttendby(dailyLog, errors);
			
			validateDoubleNmber(dailyLog, errors);
		
		}
		
	}

	private void validateAttendby(DailyLog dailyLog, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "attendby", "required.attendby", "attendby is required.");

		// input string conatains characters only
		if (!(dailyLog.getAttendby() != null && dailyLog.getAttendby().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((dailyLog.getAttendby()));

			if (!matcher.matches()) {
				errors.rejectValue("attendby", "attendby.containNonChar", "Enter a valid attendby");
			}
		}
		
	}

	private void validateSpareParts(DailyLog dailyLog, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "spareparts", "required.spareparts", "sparepart is required.");

		// input string conatains characters only
		if (!(dailyLog.getSpareparts() != null && dailyLog.getSpareparts().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((dailyLog.getSpareparts()));

			if (!matcher.matches()) {
				errors.rejectValue("spareparts", "spareparts.containNonChar", "Enter a valid sparepartsS");
			}
		}

	}

	private void validateDescription(DailyLog dailyLog, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "description", "required.description", "description is required.");

		// input string conatains characters only
		if (!(dailyLog.getDescription() != null && dailyLog.getDescription().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((dailyLog.getDescription()));

			if (!matcher.matches()) {
				errors.rejectValue("description", "description.containNonChar", "Enter a valid description");
			}
		}
		
	}

	private void validateMachine(DailyLog dailyLog, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "machine", "required.machine", "machine is required.");

		// input string conatains characters only
		if (!(dailyLog.getMachine() != null && dailyLog.getMachine().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((dailyLog.getMachine()));

			if (!matcher.matches()) {
				errors.rejectValue("machine", "machine.containNonChar", "Enter a valid machine");
			}
		}
		
	}

	private void validateRemark(DailyLog dailyLog, Errors errors) {
				ValidationUtils.rejectIfEmpty(errors, "remark", "required.remark", "remark is required.");

				// input string conatains characters only
				if (!(dailyLog.getRemark() != null && dailyLog.getRemark().isEmpty())) {
					pattern = Pattern.compile(STRING_PATTERN);
					matcher = pattern.matcher((dailyLog.getRemark()));

					if (!matcher.matches()) {
						errors.rejectValue("remark", "remark.containNonChar", "Enter a valid remark");
					}
				}
	}

	private void validateRealname(DailyLog dailyLog, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmpty(errors, "realname", "required.realname", "name is required.");

		// input string conatains characters only
		if (!(dailyLog.getRealname() != null && dailyLog.getRealname().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((dailyLog.getRealname()));

			if (!matcher.matches()) {
				errors.rejectValue("realname", "realname.containNonChar", "Enter a valid Name");
			}
		}
	}

	private void validateDoubleNmber(DailyLog dailyLog, Errors errors) {
//		loadmax loadmin voltmax voltmin frequencymax frequencymin pfmax pfmin powerdip;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loadmax", "required.loadmax", "loadmax is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loadmin", "required.loadmin", "loadmin is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "voltmax", "required.voltmax", "voltmax is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "voltmin", "required.voltmin", "voltmin is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "frequencymax", "required.frequencymax", "frequencymax is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "frequencymin", "required.frequencymin", "frequencymin is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pfmax", "required.pfmax", "pfmax is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pfmin", "required.pfmin", "pfmin is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "powerdip", "required.powerdip", "powerdip is required.");

			if (dailyLog.getLoadmax()<=0.0) {
				errors.rejectValue("loadmax", "loadmax.incorrect", "Enter a correct loadmax");
			}else if (dailyLog.getLoadmax()<=0.0) {
				errors.rejectValue("loadmin", "loadmin.incorrect", "Enter a correct loadmin");
			}else if (dailyLog.getLoadmax()<=0.0) {
				errors.rejectValue("voltmax", "voltmax.incorrect", "Enter a correct voltmax");
			}else if (dailyLog.getLoadmax()<=0.0) {
				errors.rejectValue("voltmin", "voltmin.incorrect", "Enter a correct voltmin");
			}else if (dailyLog.getLoadmax()<=0.0) {
				errors.rejectValue("frequencymax", "frequencymax.incorrect", "Enter a correct frequencymax");
			}else if (dailyLog.getLoadmax()<=0.0) {
				errors.rejectValue("frequencymin", "frequencymin.incorrect", "Enter a correct frequencymin");
			}else if (dailyLog.getLoadmax()<=0.0) {
				errors.rejectValue("pfmax", "pfmax.incorrect", "Enter a correct pfmax");
			}else if (dailyLog.getLoadmax()<=0.0) {
				errors.rejectValue("pfmin", "pfmin.incorrect", "Enter a correct pfmin");
			}else if (dailyLog.getLoadmax()<=0.0) {
				errors.rejectValue("powerdip", "powerdip.incorrect", "Enter a correct powerdip");
			}
	}

	/**
	 * 
	 * @param users
	 * @param errors
	 */
	private void validateEmail(Users users, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required.username", "Email is required.");

		// email validation in spring
		if (!(users.getUsername() != null && users.getUsername().isEmpty())) {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(users.getUsername());
			if (!matcher.matches()) {
				errors.rejectValue("username", "username.incorrect", "Enter a correct Email");
			}
		}
	}

	private void validatePhonenumber(Users users, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "required.phone", "Phone is required.");

		// phone number validation
		if (!(users.getPhone() != null && users.getPhone().isEmpty())) {
			pattern = Pattern.compile(MOBILE_PATTERN);
			matcher = pattern.matcher(users.getPhone());
			if (!matcher.matches()) {
				errors.rejectValue("phone", "phone.incorrect", "Enter a correct phone number");
			}
		}
	}

	private void validateZipcode(Users users, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zipcode", "required.zipcode", "Zipcode is required.");

		// phone number validation
		if (!(users.getZipcode() != null && users.getZipcode().isEmpty())) {
			pattern = Pattern.compile(ZIPCODE_PATTERN);
			matcher = pattern.matcher(users.getZipcode());
			if (!matcher.matches()) {
				errors.rejectValue("zipcode", "zipcode.incorrect", "Enter a correct Zipcode");
			}
		}

	}

	private void validateState(Users users, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "state", "required.state", "State is required.");

		// input string conatains characters only
		if (!(users.getState() != null && users.getState().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((users.getState()));

			if (!matcher.matches()) {
				errors.rejectValue("state", "state.containNonChar", "Enter a valid State");
			}
		}

	}

	private void validateCity(Users users, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmpty(errors, "city", "required.city", "City is required.");

		// input string conatains characters only
		if (!(users.getCity() != null && users.getCity().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((users.getCity()));

			if (!matcher.matches()) {
				errors.rejectValue("city", "city.containNonChar", "Enter a valid City");
			}
		}

	}

	private void validateAddress(Users users, Errors errors) {
		// text area validation
		ValidationUtils.rejectIfEmpty(errors, "address", "required.address", "Address is required.");

	}

	private void validateLastName(Users users, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "lastname", "required.lastname", "Last Name is required.");

		// input string conatains characters only
		if (!(users.getLastname() != null && users.getLastname().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((users.getLastname()));

			if (!matcher.matches()) {
				errors.rejectValue("lastname", "lastname.containNonChar", "Enter a valid Last name");
			}
		}

	}

	private void validateFirstName(Users users, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "firstname", "required.firstname", "First Name is required.");

		// input string conatains characters only
		if (!(users.getFirstname() != null && users.getFirstname().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((users.getFirstname()));

			if (!matcher.matches()) {
				errors.rejectValue("firstname", "firstname.containNonChar", "Enter a valid First name");
			}
		}

	}

}
