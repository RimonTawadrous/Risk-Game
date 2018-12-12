package players;
import Statistics.Attack;
import edu.princeton.cs.algs4.MinPQ;
import mapGUI.MapController;
import maps.State;

public class AStar extends Player{

    private State lastMove;
    private State bestForNow;

    public AStar(){
        this.playerID = 6 ;
    }

    public void solve(){
        MinPQ<State> moves = new MinPQ<State>();
        moves.insert(new State(map,this.playerID,0,null));
        while (lastMove == null) {
            lastMove = expand(moves);
        }
        System.out.println(moves.size());
    }

    private State expand(MinPQ<State> moves) {
        if (moves.isEmpty()){
            return bestForNow;
        }else{
            State bestMove = moves.delMin();
            if (bestMove.isGoal()) {
                return bestMove;
            }
            for (State neighbor : bestMove.getNextStates()) {
                moves.insert(neighbor);
            }
            bestForNow = bestMove;
        }
        return null;
    }

    @Override
    public void reinforce() {
    	State firstState =  new State(map,this.playerID,0,null);
    	firstState.reinforceOnly(countryEditiedByReinfrocment);
    }

    @Override
    public void attack() {
        State s = getnextState();
        if(bestForNow != lastMove){
            for(Attack a : s.getAttackArrayList()){
                System.out.println("this is first attack"+a.toString());
                ownedTerritoryToAttackGUI=a.getAttacker();
                adjacentTerritoryToAttackGUI=a.getDefender();
                attackingSolidersGUI=a.getNumberOfAttackingSoliders();
                attackFrom.add(a.getAttacker());
                attackTo.add(a.getDefender());
                solidersAttacking.add(a.getNumberOfAttackingSoliders());
              //  attackWithDice(a.getAttacker().getId(),a.getDefender().getId(),a.getNumberOfAttackingSoliders());
            }
            attackWithDice(ownedTerritoryToAttackGUI.getId(),adjacentTerritoryToAttackGUI.getId(),attackingSolidersGUI);
        }
    }

    private State getnextState(){
        solve();
        State nextState = null;
        State temp = lastMove;
        State current = lastMove;
        current = current.getPreviousState();
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
