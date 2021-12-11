package model;

import java.util.ArrayList;

//public class MatchingProperties implements Observer
//{
//	private ArrayList<Property> interestedSearch;
//	private ArrayList<RRCriteria> properties;
//	
//	 public MatchingProperties(RegisteredRenter rr) 
//	 {
//		 properties = Database.getUserSearchCriteria(rr.getInstance().getEmailAddress());
//		 interestedSearch = new ArrayList<Property>();
//	  }
//	
//	// updates the user when a 
//    @Override
//    public void update(Property p) {
//        for(RRCriteria search : properties )
//        {
//            if(search.matches(p) && !matchingContains(p)) 
//            {
//            	interestedSearch.add(p);
//            }
//        }
//    }
//    
//    public boolean matchingContains(Property pp) 
//    {
//    	for(Property p : interestedSearch) {
//    		if(p.getAddress().equals(pp.getAddress())) return true;
//    	}
//    	return false;
//    }
//
//    public ArrayList<Property> getMatchingProperties() {
//        return interestedSearch;
//    }
//    
//    public Object[][] getSearchModel() {
//    	LinkedList<Object[]> temp = new LinkedList<Object[]>();
//    	for(SearchCriteria search : searchCriteria) {
//        	temp.add(new Object[] {search.getType(), search.getBedrooms(), search.getBathrooms(), search.getFurnished(), search.getQuadrant()});
//            
//        }
//        return temp.toArray(new Object[temp.size()][]);
//    }
//    
//    public void addSearchCriteria(String type, int beds, int baths, boolean furnished, String quadrant) {
//
//        if(DatabaseConnectivity.addUserSearchCriteria(user.getUsername(), type, beds, baths, furnished, quadrant)) {
//	        searchCriteria.add(new SearchCriteria(type, beds, baths, furnished, quadrant));
//        }
//
//    }
//    
//    public void removeSearchCriteria(String type, int beds, int baths, boolean furnished, String quadrant) {
//
//        if(DatabaseConnectivity.removeUserSearchCriteria(user.getUsername(), type, beds, baths, furnished, quadrant)) {
//	        searchCriteria.clear();
//	        searchCriteria = DatabaseConnectivity.getUserSearchCriteria(user.getUsername());
//	        removeMatchingProperty(type, beds, baths, furnished, quadrant);
//        }
//
//    }
//    
//    public void removeMatchingProperty(String type, int beds, int baths, boolean furnished, String quadrant) {
//    	Iterator<Property> i = matchingProperties.iterator();
//        
//        while (i.hasNext()) {
//           Property prop = i.next();
//           if (prop.getType().equalsIgnoreCase(type) && prop.getNumBath() == baths && prop.getNumBed() == beds &&
//        		   prop.getFurnished() == furnished && prop.getQuadrant().equalsIgnoreCase(quadrant)) {
//              i.remove();
//           }
//        }
//    }

//}
