
public class RouteEntry {
	String Destination;
	String NextHopRouter;
	int NumberOfHops;
	
	public RouteEntry(String dest, String next, int hops){
		Destination=dest;
		NextHopRouter=next;
		if (hops >= 16){
			NumberOfHops=16;
		}
		else{
			NumberOfHops=hops;
		}
	}
	
	public RouteEntry compareEntries(RouteEntry re){
		if (re.Destination.equalsIgnoreCase(Destination)){
			if (re.NumberOfHops+1 < this.NumberOfHops){
				System.out.println("inc hops");
				re.NumberOfHops++;
				return re;
			}
			else{
				return this;
			}
		}
		return this;
	}
	
	public String getDestination() {
		return Destination;
	}
	public void setDestination(String destination) {
		Destination = destination;
	}
	public String getNextHopRouter() {
		return NextHopRouter;
	}
	public void setNextHopRouter(String nextHopRouter) {
		NextHopRouter = nextHopRouter;
	}
	public int getNumberOfHops() {
		return NumberOfHops;
	}
	public void setNumberOfHops(int numberOfHops) {
		if (numberOfHops > 15){
			NumberOfHops = -1;
		}
		else{
			NumberOfHops = numberOfHops;
		}
	}
}
