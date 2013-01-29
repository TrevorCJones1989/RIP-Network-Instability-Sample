import java.util.ArrayList;



public class MainClass {

    public static void main(String args[]) {
    	//Input arg should be an int, 1,2,3,4 depending on what you wish to run
    	//Client = 1
    	//Server = 2
    	//RIP normal = 3
    	//RIP instability = 4
    	
    	int choice = new Integer(args[0]).intValue();
    	
    	switch(choice){
    	case 1:
    		System.out.println("------Running Client------");
    		TCPClient tcpclient = new TCPClient();
    		try {
				tcpclient.runClient(args[1], new Integer(args[2]).intValue());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
    		break;
    	case 2:
    		System.out.println("------Running Server------");
    		TCPServer tcpserver = new TCPServer();
    		try {
				tcpserver.runServer(new Integer(args[1]).intValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
    		break;
    	case 3:
    		System.out.println("------RIP Normal------");
    		RIP rip = new RIP();
        	rip.init();
        	int j=1;
        	while (!rip.finished()){
        		System.out.println("=====Round "+j+"=====");
        		rip.printAll();
        		rip.update();
        		j++;
        	}
        	System.out.println("=====Round "+j+"=====");
        	rip.printAll();
        	break;
    	case 4:
        	System.out.println("------RIP Instability------");
        	RIP rip2 = new RIP();
        	rip2.init();
        	rip2.printAll();
        	rip2.update();
        	//Set R1's N1 entry to 16.
        	ArrayList<RouteEntry> routeEntryList = rip2.getRouterList().get(0).getRouterEntries();
        	for (int i=0; i<routeEntryList.size(); i++){
        		if (routeEntryList.get(i).getDestination().equals("N1")){
        			RouteEntry temp = rip2.getRouterList().get(0).getRouterEntries().get(i);
        			RouteEntry temp2 = new RouteEntry(temp.getDestination(), temp.getNextHopRouter(), 16);
        			rip2.getRouterList().get(0).getRouterEntries().remove(i);
        			rip2.getRouterList().get(0).getRouterEntries().add(temp2);
        		}
        	}
        	
        	int k=1;
        	
        	//Run until finished
        	while (!rip2.reachedConvergance()){
        		System.out.println("=====Round "+k+"=====");
        		rip2.printAll();
        		rip2.update();
        		k++;
        	}
        	System.out.println("=====Round "+k+"=====");
        	rip2.printAll();
        	break;
    	}
    	
    	
    }
}
