package Krishna.models;

public class LocationStats {

	private String State;
	private String country;
	private int latesttotalcases;
	private int difffromprevday;
	public int getDifffromprevday() {
		return difffromprevday;
	}
	public void setDifffromprevday(int difffromprevday) {
		this.difffromprevday = difffromprevday;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatesttotalcases() {
		return latesttotalcases;
	}
	public void setLatesttotalcases(int latesttotalcases) {
		this.latesttotalcases = latesttotalcases;
	}
	@Override
	public String toString() {
		return "LocationStats [State=" + State + ", country=" + country + ", latesttotalcases=" + latesttotalcases
				+ "]";
	}
	
   
	
}
