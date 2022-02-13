package tech.getarrays.banco.Model;

import tech.getarrays.banco.entity.UserEntity;

public class UserDto extends UserEntity{
	
	private UserEntity user;
	
	private String newPassword;

	public UserDto() {
	}

	public UserDto(UserEntity user, String newPassword) {
		super();
		this.user = user;
		this.newPassword = newPassword;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
	
}
