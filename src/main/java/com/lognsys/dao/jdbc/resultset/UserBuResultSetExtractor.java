package com.lognsys.dao.jdbc.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.lognsys.dao.dto.BuDTO;
import com.lognsys.dao.dto.UsersBuDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.util.Constants;

/**
 * <a>http://stackoverflow.com/questions/13295600/multiple-one-to-many-relations
 * -resultsetextractor
 * 
 * @author pdoshi
 *
 */
public class UserBuResultSetExtractor implements ResultSetExtractor<List<UsersBuDTO>> {

	@Override
	public List<UsersBuDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {

		List<UsersBuDTO> listOfUsersDTO = new ArrayList<>();
		while (rs.next()) {
			UsersBuDTO ug = new UsersBuDTO();

			// Union of UsersDTO and Groups object
			UsersDTO u = new UsersDTO(rs.getInt(Constants.USER_FIELD_NAMES.usersId.name()),
					rs.getString(Constants.USER_FIELD_NAMES.realname.name()),
					rs.getString(Constants.USER_FIELD_NAMES.username.name()),
					rs.getString(Constants.USER_FIELD_NAMES.phone.name()),
					rs.getString(Constants.USER_FIELD_NAMES.address.name()),
					rs.getString(Constants.USER_FIELD_NAMES.birthdate.name()),
					rs.getBoolean(Constants.USER_FIELD_NAMES.enabled.name()),
					rs.getBoolean(Constants.USER_FIELD_NAMES.notification.name()),
					rs.getString(Constants.USER_FIELD_NAMES.city.name()),
					rs.getString(Constants.USER_FIELD_NAMES.state.name()),
					rs.getString(Constants.USER_FIELD_NAMES.zipcode.name()));

			BuDTO g = new BuDTO(rs.getInt(Constants.BU_FIELDNAME.buId.name()),
					rs.getString(Constants.BU_FIELDNAME.bu_name.name()));

			// users & groups
			ug.setUser(u);
			ug.setBuDTO(g);

			listOfUsersDTO.add(ug);
		}

		return listOfUsersDTO;
	}

}
