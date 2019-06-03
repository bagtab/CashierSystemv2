package view;

import dto.UpdateDTO;

public interface Observer {
	/**
	 * updates display based on the updateInfo
	 * @param updateInfo  object containing all relevant information for update
	 */
	public void update(UpdateDTO updateInfo);
}
