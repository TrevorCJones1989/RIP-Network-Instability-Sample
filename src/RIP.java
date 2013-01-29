import java.util.ArrayList;


public class RIP {
	ArrayList<Router> routerList;
	
	public boolean finished(){
		for (int i=0; i<routerList.size(); i++){
			if (routerList.get(i).getRouterEntries().size()<7){
				return false;
			}
		}
		return true;
	}
	
	//Checks if Finished converging
	public boolean reachedConvergance(){
		ArrayList<RouteEntry> resLast = routerList.get(routerList.size()-1).getRouterEntries();
		ArrayList<RouteEntry> resFirst = routerList.get(0).getRouterEntries();
		int truth=0;
		for (int i=0; i<resLast.size(); i++){
			if (resLast.get(i).getDestination().equals("N1") && resLast.get(i).getNumberOfHops()>=16){
				truth++;
			}
		}
		for (int i=0; i<resFirst.size(); i++){
			if (resFirst.get(i).getDestination().equals("N1") && resFirst.get(i).getNumberOfHops()>=16){
				truth++;
			}
		}
		if (truth==2){
			return true;
		}
		return false;
	}
	
	//Initiates all routers with the relevant networks
	public void init(){
		routerList = new ArrayList<Router>();
		for (int i=1; i<7; i++){
			RouteEntry re = new RouteEntry("N"+i, "", 0);
			RouteEntry re2 = new RouteEntry("N"+(i+1), "", 0);
			Router r = new Router("R"+i, re, re2);
			routerList.add(r);
		}
	}
	
	//Updates router entries according to their neighbours.
	public void update(){
		for (int i=0; i<routerList.size(); i++){
			if (i==0){
				routerList.get(i).updateRouterEntries(routerList.get(i+1));
			}
			else if (i==routerList.size()-1){
				routerList.get(i).updateRouterEntries(routerList.get(i-1));
			}
			else{
				routerList.get(i).updateRouterEntries(routerList.get(i+1));
				routerList.get(i).updateRouterEntries(routerList.get(i-1));
			}
		}
	}
	
	
	public ArrayList<Router> getRouterList(){
		return routerList;
	}
	
	public void printAll(){
		for (Router router : routerList){
			router.printEntries();
		}
	}
}
