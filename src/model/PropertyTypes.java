package model;
//Stand Alone Enum for all Property Types
//has toString abstract function
public enum PropertyTypes {
	APARTMENT {
		public String toString() {
            return "Apartment";
        }
	},
	CONDO {
		public String toString() {
            return "Condo";
        }
	},
	ATTACHED_HOUSE {
		public String toString() {
            return "Attached House";
        }
	},
	DETACHED_HOUSE {
		public String toString() {
            return "Detached House";
        }
	},
	TOWN_HOUSE {
		public String toString() {
            return "Town House";
        }
	},
	SINGLE_FAMILY_HOME {
		public String toString() {
            return "Single Family Home";
        }
	},
	MULTI_FAMILY_HOME {
		public String toString() {
            return "Multi Family Home";
        }
	};
	
    public abstract String toString();
}