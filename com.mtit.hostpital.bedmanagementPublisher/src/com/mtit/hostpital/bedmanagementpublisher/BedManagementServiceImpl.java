package com.mtit.hostpital.bedmanagementpublisher;

public class BedManagementServiceImpl implements BedManagementService {

	
	private int availableBeds = 100; // Initial number of beds
    private int availableIcuUnits = 20; // Initial number of ICU units
    
    
    
    @Override
    public int getAvailableBeds() {
        return availableBeds;
    }

    @Override
    public int getAvailableIcuUnits() {
        return availableIcuUnits;
    }

    @Override
    public void updateBedStatus(int beds, int icuUnits) {
        this.availableBeds = beds;
        this.availableIcuUnits = icuUnits;
        System.out.println("Updated Bed Status: Beds=" + beds + ", ICU Units=" + icuUnits);
        
    }
    
   
}
