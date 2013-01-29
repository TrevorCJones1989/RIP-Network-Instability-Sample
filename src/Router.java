import java.util.ArrayList;

public class Router {
	ArrayList<RouteEntry> RouterEntries;
	String RouterName;
	boolean debug = false;
	
	public Router(String routerName){
		RouterName=routerName;
		RouterEntries = new ArrayList<RouteEntry>();
	}
	
	public Router(String routerName, RouteEntry re1, RouteEntry re2){
		RouterName = routerName;
		RouterEntries = new ArrayList<RouteEntry>();
		RouterEntries.add(re1);
		RouterEntries.add(re2);
	}
	
	
	public ArrayList<RouteEntry> getRouterEntries(){
		return RouterEntries;
	}
	
	
	public void updateRouterEntries(Router router){
		ArrayList<RouteEntry> otherRouterEntries = router.getRouterEntries();
		for (int j=0; j<otherRouterEntries.size(); j++){
			
			//See if router contains destination
			RouteEntry re = routerContainsDestination(otherRouterEntries.get(j));
			
			//update router entry
			if (re != null){
				compareRouterEntries(re, otherRouterEntries.get(j), router);
				
			}
			//insert router entry
			else{
				if (debug){System.out.println("Router "+RouterName+" insert: "+otherRouterEntries.get(j).getDestination());}
				RouteEntry newRe = new RouteEntry(otherRouterEntries.get(j).getDestination(), router.RouterName,otherRouterEntries.get(j).getNumberOfHops()+1);
				RouterEntries.add(newRe);
			}
			
		}
	}
	
	//Compare and possibly update.
	public void compareRouterEntries(RouteEntry re1, RouteEntry re2, Router r){
			if (r.RouterName.equals(re1.getNextHopRouter())){
				//Router has new information from a next hop router's link
				RouterEntries.remove(re1);
				RouterEntries.add(new RouteEntry(re2.getDestination(), r.RouterName, (re2.getNumberOfHops()+1)));
				if (debug){System.out.println("Update Route-Information with: "+re2.getDestination()+" "+r.RouterName+" "+(re2.getNumberOfHops()+1));}
			}
			if (re1.NumberOfHops > (re2.getNumberOfHops()+1)){
				//An old link has reduced its hops
				System.out.println("Update Link-Hops-Value with: "+re2.Destination+" "+re2.NextHopRouter+" "+(re2.getNumberOfHops()+1));
				RouterEntries.remove(re1);
				if (debug){RouterEntries.add(new RouteEntry(re2.Destination,r.RouterName, (re2.getNumberOfHops()+1)));}
			}
	}
	
	public void printEntries(){
		System.out.println("RouterName: "+RouterName);
		for (int i=0; i<RouterEntries.size(); i++){
			System.out.println("Dest: " + RouterEntries.get(i).getDestination() + " Next: " + RouterEntries.get(i).getNextHopRouter() + " Hops: " + RouterEntries.get(i).getNumberOfHops());
		}
	}
	
	public RouteEntry routerContainsDestination(RouteEntry re){
		for (int i=0; i<RouterEntries.size(); i++){
			if (RouterEntries.get(i).getDestination().equalsIgnoreCase(re.getDestination())){
				return RouterEntries.get(i);
			}
		}
		return null;
	}
	
}
