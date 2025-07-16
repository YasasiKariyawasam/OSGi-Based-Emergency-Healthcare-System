package com.mtit.hostpital.bedmanagementpublisher;

public interface BedManagementService {

	
	 int getAvailableBeds();
	    int getAvailableIcuUnits();
	    void updateBedStatus(int beds, int icuUnits);
}
