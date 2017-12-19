package com.lognsys.util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.lognsys.dao.dto.AssignTaskDTO;
import com.lognsys.dao.dto.AssignTaskDailylogDTO;
import com.lognsys.dao.dto.DailyLogDTO;
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
	String MULTIPLE_STRING_PATTERN = "[a-z \\s A-Z \\,]+";
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
		}else if (target instanceof AssignTaskDailylogDTO) {	
			System.out.println("\n Rest saveForm target \n \n " +target.toString());

			
			AssignTaskDailylogDTO assignTaskdailylogDTODTO = (AssignTaskDailylogDTO) target;
			validateTitle(assignTaskdailylogDTODTO.getAssignTaskDTO(), errors);
			validateAssignTo(assignTaskdailylogDTODTO.getAssignTaskDTO(), errors);
			validatePriority(assignTaskdailylogDTODTO.getAssignTaskDTO(), errors);
			validateTargetDate(assignTaskdailylogDTODTO.getAssignTaskDTO(),errors);
			validateDonePercentage(assignTaskdailylogDTODTO.getAssignTaskDTO(), errors);
			

			validateShift(assignTaskdailylogDTODTO.getDailylogDTO(), errors);
			validateStatus(assignTaskdailylogDTODTO.getDailylogDTO(), errors);
			validateJobType(assignTaskdailylogDTODTO.getDailylogDTO(), errors);
//			validateBu(assignTaskdailylogDTODTO.getDailylogDTO(), errors);
			validateRecordType(assignTaskdailylogDTODTO.getDailylogDTO(), errors);
				
			validateMachine(assignTaskdailylogDTODTO.getDailylogDTO(), errors);
			validateDescription(assignTaskdailylogDTODTO.getDailylogDTO(), errors);
			validateSpareParts(assignTaskdailylogDTODTO.getDailylogDTO(), errors);
			validateAttendby(assignTaskdailylogDTODTO.getDailylogDTO(), errors);
		
		}
		
	}

	private void validateBu(DailyLogDTO dailylogDTO, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dailylogDTO.bu", "required.dailylogDTO.bu", "bu is required.");

		// input string conatains characters only
		if (!(dailylogDTO.getBu() != null && dailylogDTO.getBu().isEmpty() && dailylogDTO.getBu().equalsIgnoreCase("Select") && dailylogDTO.getBu().equalsIgnoreCase("None") )) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((dailylogDTO.getBu()));

			if (!matcher.matches()) {
				errors.rejectValue("dailylogDTO.bu", "dailylogDTO.bu.containNonChar", "Enter a valid bu");
			}
		}
	}

	private void validateRecordType(DailyLogDTO dailylogDTO, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dailylogDTO.recordtype", "required.dailylogDTO.recordtype", "Record type is required.");

		// input string conatains characters only
		if (!(dailylogDTO.getRecordtype() != null && dailylogDTO.getRecordtype().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((dailylogDTO.getRecordtype()));

			if (!matcher.matches()) {
				errors.rejectValue("dailylogDTO.recordtype", "dailylogDTO.recordtype.containNonChar", "Enter a valid recordtype");
			}
		}	
	}

	private void validateJobType(DailyLogDTO dailylogDTO, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dailylogDTO.jobtype", "required.dailylogDTO.jobtype", "Jobtype is required.");

		// input string conatains characters only
		if (!(dailylogDTO.getJobtype() != null && dailylogDTO.getJobtype().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((dailylogDTO.getJobtype()));

			if (!matcher.matches()) {
				errors.rejectValue("dailylogDTO.jobtype", "dailylogDTO.jobtype.containNonChar", "Enter a valid jobtype");
			}
		}
	}

	private void validateStatus(DailyLogDTO dailylogDTO, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dailylogDTO.status", "required.dailylogDTO.status", "Status is required.");

		// input string conatains characters only
		if (!(dailylogDTO.getStatus() != null && dailylogDTO.getStatus().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((dailylogDTO.getStatus()));

			if (!matcher.matches()) {
				errors.rejectValue("dailylogDTO.status", "dailylogDTO.status.containNonChar", "Enter a valid status");
			}
		}
	}

	private void validateShift(DailyLogDTO dailylogDTO, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dailylogDTO.shift", "required.dailylogDTO.shift", "Shift is required.");

		// input string conatains characters only
		if (!(dailylogDTO.getShift() != null && dailylogDTO.getShift().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((dailylogDTO.getShift()));

			if (!matcher.matches()) {
				errors.rejectValue("dailylogDTO.shift", "dailylogDTO.shift.containNonChar", "Enter a valid shift");
			}
		}	
	}

	private void validateDonePercentage(AssignTaskDTO assignTaskDTO, Errors errors) {

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "assignTaskDTO.done_percentage", "required.assignTaskDTO.done_percentage", "Done percentage is required.");

	}

	private void validateTargetDate(AssignTaskDTO assignTaskDTO, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "assignTaskDTO.target_date", "required.assignTaskDTO.target_date", "Target date is required.");

	}

	private void validatePriority(AssignTaskDTO assignTaskDTO, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "assignTaskDTO.priority", "required.assignTaskDTO.priority", "Priority is required.");

		// input string conatains characters only
		if (!(assignTaskDTO.getPriority() != null && assignTaskDTO.getPriority().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((assignTaskDTO.getPriority()));

			if (!matcher.matches()) {
				errors.rejectValue("assignTaskDTO.priority", "assignTaskDTO.priority.containNonChar", "Enter a valid priority");
			}
		}
	}

	private void validateAssignTo(AssignTaskDTO assignTaskDTO, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "assignTaskDTO.assigned_to", "required.assignTaskDTO.assigned_to", "Assigned to is required.");

		// input string conatains characters only
		if (!(assignTaskDTO.getAssigned_to() != null && assignTaskDTO.getAssigned_to().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((assignTaskDTO.getAssigned_to()));

			if (!matcher.matches()) {
				errors.rejectValue("assignTaskDTO.assigned_to", "assignTaskDTO.assigned_to.containNonChar", "Enter a valid Assigned to");
			}
		}
	}

	private void validateTitle(AssignTaskDTO assignTaskDTO, Errors errors) {
	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "assignTaskDTO.title", "required.assignTaskDTO.title", "Title is required.");

		// input string conatains characters only
		if (!(assignTaskDTO.getTitle() != null && assignTaskDTO.getTitle().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((assignTaskDTO.getTitle()));

			if (!matcher.matches()) {
				errors.rejectValue("assignTaskDTO.title", "assignTaskDTO.title.containNonChar", "Enter a valid title");
			}
		}
		System.out.println("\n Rest saveForm errors \n \n " +errors.toString());

	}

	private void validateAttendby(DailyLogDTO dailylogDTO, Errors errors) {
	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dailylogDTO.attendby", "required.dailylogDTO.attendby", "Attendby is required.");

	/*	// input string conatains characters only
		if (!(dailylogDTO.getAttendby() != null && dailylogDTO.getAttendby().isEmpty())) {
			pattern = Pattern.compile(MULTIPLE_STRING_PATTERN);
			matcher = pattern.matcher((dailylogDTO.getAttendby()));

			if (!matcher.matches()) {
				errors.rejectValue("dailylogDTO.attendby", "dailylogDTO.attendby.containNonChar", "Enter a valid attendby");
			}
		}*/
		
	}

	private void validateSpareParts(DailyLogDTO dailylogDTO, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dailylogDTO.spare_parts", "required.dailylogDTO.spare_parts", "Spare parts is required.");

		// input string conatains characters only
		if (!(dailylogDTO.getSpare_parts() != null && dailylogDTO.getSpare_parts().isEmpty())) {
			pattern = Pattern.compile(MULTIPLE_STRING_PATTERN);
			matcher = pattern.matcher((dailylogDTO.getSpare_parts()));

			if (!matcher.matches()) {
				errors.rejectValue("dailylogDTO.spare_parts", "dailylogDTO.spare_parts.containNonChar", "Enter a valid spare parts");
			}
		}

	}

	private void validateDescription(DailyLogDTO dailylogDTO, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dailylogDTO.description", "required.dailylogDTO.description", "Description is required.");

		// input string conatains characters only
		if (!(dailylogDTO.getDescription() != null && dailylogDTO.getDescription().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((dailylogDTO.getDescription()));

			if (!matcher.matches()) {
				errors.rejectValue("dailylogDTO.description", "dailylogDTO.description.containNonChar", "Enter a valid description");
			}
		}
		
	}

	private void validateMachine(DailyLogDTO dailylogDTO, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dailylogDTO.machine", "required.dailylogDTO.machine", "Machine is required.");

		// input string conatains characters only
		if (!(dailylogDTO.getMachine() != null && dailylogDTO.getMachine().isEmpty())) {
			pattern = Pattern.compile(MULTIPLE_STRING_PATTERN);
			matcher = pattern.matcher((dailylogDTO.getMachine()));

			if (!matcher.matches()) {
				errors.rejectValue("dailylogDTO.machine", "dailylogDTO.machine.containNonChar", "Enter a valid machine");
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "required.state", "State is required.");

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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "required.city", "City is required.");

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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "required.address", "Address is required.");

	}

	private void validateLastName(Users users, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "required.lastname", "Last Name is required.");

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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "required.firstname", "First Name is required.");

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
