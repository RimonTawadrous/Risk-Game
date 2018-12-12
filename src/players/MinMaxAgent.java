package players;

import java.util.HashMap;

import Statistics.Attack;
import mapGUI.MapController;
import maps.State;

public class MinMaxAgent extends Player {

    private State lastMove;
//    private State bestForNow;
	
	
	// Initial values of  
	// Alpha and Beta 
	static Double MAX = 1000.0; 
	static Double MIN = -1000.0; 
	double temp;
	private  HashMap<Double,State> goalNodes = new HashMap<Double, State>();
	
	public MinMaxAgent(){
		this.playerID = 8;
	}
	
	
	private void solve(){
		
		Double optimalValue = minimax(new State(map,this.playerID,0,null),0,true,MIN,MAX);
		
		System.out.println("Hashmap Values");
		for (int i=0; i<goalNodes.size(); i++ )
			System.out.println(goalNodes.toString());
		
//		lastMove = goalNodes.get(optimalValue);
		
		System.out.println("Optimal value of heuristic"+optimalValue);
	}
	
	// Returns optimal value for 
	// current player (Initially called 
	// for root and maximizer) 
	 Double minimax(State state, int depth,  Boolean maximizingPlayer, Double alpha, Double beta) 
	{ 
	    // Terminating condition. i.e  
	    // leaf node is reached 
	    if (state.isGoal()) {
	    	Double t = state.computeUtility();
//	    	if(!goalNodes.containsKey(t))
	    		goalNodes.put(t, state);
	        return t; 
	    }
	    
	    if (maximizingPlayer) 
	    { 
	        Double best = MIN; 
	  
	        // Recur for left and 
	        // right children 
	        
	        for (State s :state.getNextStates()) 
	        { 
	        	System.out.println("Da5al maxxxx");
	            Double val = minimax(s, depth + 1,false, alpha, beta); 
	            
//	            temp = best;
	  
	            best = Math.max(best, val);
	            
//	            if(temp != best) lastMove = state;
	            
	            alpha = Math.max(alpha, best); 
	  
	            // Alpha Beta Pruning 
	            if (beta <= alpha) 
	                break; 
	        } 
	        
	        lastMove = goalNodes.get(best);
//	        if(lastMove.getPreviousState() == null)
//	        	System.out.println("Sandra");
	        System.out.println("BEst max: "+best);
	        return best; 
	    } 
	    else
	    { 
	    	Double best = MAX; 
	  
	        // Recur for left and 
	        // right children 
	    	for (State s :state.getNextStates()) 
	        { 
	              
	    		Double val = minimax(s, depth + 1,true, alpha, beta); 
	            
//	    		temp = best;
	    		
	    		best = Math.min(best, val);
	    		
//	    		if(temp != best) lastMove = state;
	    		
	            beta = Math.min(beta, best); 
	  
	            // Alpha Beta Pruning 
	            if (beta <= alpha) 
	                break; 
	        } 
	    	lastMove = goalNodes.get(best);
	    	 System.out.println("BEst min: "+best);
	        return best; 
	    } 
	   }
	
	@Override
	public void reinforce() {
		
		 State firstState =  new State(map,this.playerID,0,null);
		  firstState.reinforceOnly(countryEditiedByReinfrocment);
		
	}

	@Override
	public void attack() {
				
		//retrieve next move to be done from current given state
		State s = getnextState();
//        if(bestForNow != lastMove){
		if(s!= null){
			System.out.println("s msh be null");
		
//		else
//			System.out.println("s  be null");
		
//		if(s.getAttackArrayList() != null){
			System.out.println("Da5aal Ataaack");
            for(Attack a : s.getAttackArrayList()){
                System.out.println("this is first attack"+a.toString());
                ownedTerritoryToAttackGUI=a.getAttacker();
                adjacentTerritoryToAttackGUI=a.getDefender();
                attackingSolidersGUI=a.getNumberOfAttackingSoliders();
                attackFrom.add(a.getAttacker());
                attackTo.add(a.getDefender());
                solidersAttacking.add(a.getNumberOfAttackingSoliders());
               // attackWithDice(a.getAttacker().getId(),a.getDefender().getId(),a.getNumberOfAttackingSoliders());
            }
            attackWithDice(ownedTerritoryToAttackGUI.getId(),adjacentTerritoryToAttackGUI.getId(),attackingSolidersGUI);
//        }
		}    
	}
	
	
	private State getnextState(){
        //betraga3 el goal state fel last move
    	solve();
        State nextState = null;
        State temp = lastMove;
        State current = lastMove;
        
        if(lastMove!= null)
        	System.out.println("LAst move msh be null");
        else
        	return null;
        
        current = current.getPreviousState();
        if(current == null)
        	System.out.println("current state be null ");
        
        int i=0;
        while (current != null) {
            nextState = temp;
            temp = temp.getPreviousState();
            System.out.println("this is me " + i++);
            current = current.getPreviousState();
        }
        return nextState;
    }

}
