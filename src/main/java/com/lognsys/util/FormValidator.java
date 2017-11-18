package com.lognsys.util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.lognsys.dao.dto.AssignTaskDTO;
import com.lognsys.dao.dto.DailyLogDTO;
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
		}else if (target instanceof DailyLogDTO) {
//			System.out.println("Form Validation target Users "+target);
			
			DailyLogDTO dailyLog = (DailyLogDTO) target;
			
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(dt);

//			validateShift(dailyLog, errors);
//			validateStatus(dailyLog, errors);
//			validateJobType(dailyLog, errors);
//			validateRecordType(dailyLog, errors);
				
//			validateTargetDate( errors);
//			validateMachine(dailyLog, errors);
//			validateDescription(dailyLog, errors);
//			validateSpareParts(dailyLog, errors);
//			validateAttendby(dailyLog, errors);
		
		}else if (target instanceof AssignTaskDTO) {
//			System.out.println("Form Validation target Users "+target);
			
			AssignTaskDTO assignTaskDTO = (AssignTaskDTO) target;
			
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(dt);

			validateTitle(assignTaskDTO, errors);
			validateAssignTo(assignTaskDTO, errors);
			validatePriority(assignTaskDTO, errors);
//			validateTargetDate(errors);
			validateDonePercentage(assignTaskDTO, errors);
		
		}
		
	}

	private void validateRecordType(DailyLogDTO dailyLog, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "recordtype", "required.recordtype", "recordtype is required.");

		// input string conatains characters only
		if (!(dailyLog.getRecordtype() != null && dailyLog.getRecordtype().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((dailyLog.getRecordtype()));

			if (!matcher.matches()) {
				errors.rejectValue("recordtype", "recordtype.containNonChar", "Enter a valid recordtype");
			}
		}	
	}

	private void validateJobType(DailyLogDTO dailyLog, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "jobtype", "required.jobtype", "jobtype is required.");

		// input string conatains characters only
		if (!(dailyLog.getJobtype() != null && dailyLog.getJobtype().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((dailyLog.getJobtype()));

			if (!matcher.matches()) {
				errors.rejectValue("jobtype", "jobtype.containNonChar", "Enter a valid jobtype");
			}
		}
	}

	private void validateStatus(DailyLogDTO dailyLog, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "status", "required.status", "status is required.");

		// input string conatains characters only
		if (!(dailyLog.getStatus() != null && dailyLog.getStatus().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((dailyLog.getStatus()));

			if (!matcher.matches()) {
				errors.rejectValue("status", "status.containNonChar", "Enter a valid status");
			}
		}
	}

	private void validateShift(DailyLogDTO dailyLog, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "shift", "required.shift", "shift is required.");

		// input string conatains characters only
		if (!(dailyLog.getShift() != null && dailyLog.getShift    ().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((dailyLog.getShift()));

			if (!matcher.matches()) {
				errors.rejectValue("shift", "shift.containNonChar", "Enter a valid shift");
			}
		}	
	}

	private void validateDonePercentage(AssignTaskDTO assignTaskDTO, Errors errors) {

			ValidationUtils.rejectIfEmpty(errors, "done_percentage", "required.done_percentage", "done_percentage is required.");

	}

	private void validateTargetDate( Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "target_date", "required.target_date", "target_date is required.");

	}

	private void validatePriority(AssignTaskDTO assignTaskDTO, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "priority", "required.priority", "priority is required.");

		// input string conatains characters only
		if (!(assignTaskDTO.getPriority() != null && assignTaskDTO.getPriority().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((assignTaskDTO.getPriority()));

			if (!matcher.matches()) {
				errors.rejectValue("priority", "priority.containNonChar", "Enter a valid priority");
			}
		}
	}

	private void validateAssignTo(AssignTaskDTO assignTaskDTO, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "assigned_to", "required.assigned_to", "assigned_to is required.");

		// input string conatains characters only
		if (!(assignTaskDTO.getAssigned_to() != null && assignTaskDTO.getAssigned_to().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((assignTaskDTO.getAssigned_to()));

			if (!matcher.matches()) {
				errors.rejectValue("assigned_to", "assigned_to.containNonChar", "Enter a valid assigned_to");
			}
		}
	}

	private void validateTitle(AssignTaskDTO assignTaskDTO, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "title", "required.title", "title is required.");

		// input string conatains characters only
		if (!(assignTaskDTO.getTitle() != null && assignTaskDTO.getTitle().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((assignTaskDTO.getTitle()));

			if (!matcher.matches()) {
				errors.rejectValue("title", "title.containNonChar", "Enter a valid title");
			}
		}
	}

	private void validateAttendby(DailyLogDTO dailyLog, Errors errors) {
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

	private void validateSpareParts(DailyLogDTO dailyLog, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "spareparts", "required.spareparts", "sparepart is required.");

		// input string conatains characters only
		if (!(dailyLog.getSpare_parts() != null && dailyLog.getSpare_parts().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((dailyLog.getSpare_parts()));

			if (!matcher.matches()) {
				errors.rejectValue("spareparts", "spareparts.containNonChar", "Enter a valid sparepartsS");
			}
		}

	}

	private void validateDescription(DailyLogDTO dailyLog, Errors errors) {
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

	private void validateMachine(DailyLogDTO dailyLog, Errors errors) {
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
