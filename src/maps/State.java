package maps;

import Statistics.Attack;
import Statistics.Propability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class State implements Comparable<State> {

    private Graph map;
    private boolean forMe = true;
    private boolean forEnemy = false;
    private ArrayList<Nodes> myCountries;
    private ArrayList<Nodes> enemyCountries;
    private ArrayList<Nodes> borderCountries;
    private ArrayList<Nodes> enemyBorderCountries;
    private ArrayList<Double> BSRList;
    private ArrayList<Double> enemyBSRList;
    private ArrayList<Double> NBSRList;
    private ArrayList<Double> enemyNBSRList;
    private ArrayList<Attack> attackArrayList;
    private int playerID;
    private State previousState=null;
    private int depth = 0;
    private Double costPlusHiurestics = 0.0;

    public ArrayList<Attack> getAttackArrayList() {
        return attackArrayList;
    }

    public State getPreviousState() {
        return previousState;
    }

    public State(Graph map,int playerID,int depth,State previousState){
        intialize(map,playerID,depth,previousState);
        getMyCountries();
        getEnemyCountries();
    }

    public State(Graph map,int playerID,int depth,State previousState,ArrayList<Attack> attacks){
        intialize(map,playerID,depth,previousState);
        if(attacks != null){
            for(int i = 0 ; i<attacks.size() ; i++){
                attackArrayList.add(attacks.get(i));
//                doAttack(map,attacks.get(i).getAttacker(),attacks.get(i).getDefender(),attacks.get(i).getNumberOfAttackingSoliders());
            }
        }
        reinforce();
    }

    public State(Graph map,int playerID,int depth,State previousState,Attack attack){
        intialize(map,playerID,depth,previousState);
        System.out.println("state depth = "+depth+" ===============================================================================");
        System.out.println("original map before attack = ");
        myprintBoard();
        attackArrayList.add(attack);
        System.out.println(attack.toString());
        doAttack(attack.getAttackerID(),attack.getDefenderID(),attack.getNumberOfAttackingSoliders());
        System.out.println("after attack ");
        myprintBoard();
        System.out.println("before reinforce ");
        reinforce();
        System.out.println("After reinforce ");
        System.out.println("============================================================================================================");
    }

    private void intialize(Graph map,int playerID,int depth,State previousState){
        myCountries = new ArrayList<>();
        borderCountries = new ArrayList<>();
        BSRList = new ArrayList<>();
        NBSRList = new ArrayList<>();
        enemyCountries = new ArrayList<>();
        enemyNBSRList = new ArrayList<>();
        enemyBSRList = new ArrayList<>();
        enemyBorderCountries = new ArrayList<>();
        attackArrayList = new ArrayList<>();
        System.out.println("map size = "+map.V());
        this.map = new Graph(map);
        this.playerID = playerID;
        this.previousState = previousState;
        this.depth = depth;
    }

    public void reinforce() {
        int additionalSoliders = enforcementSoldiers();
        getMyCountries();
        getEnemyCountries();
        calculateAllNBSR(forMe);
        Collections.sort(borderCountries,Nodes.NBSRDescendingComparator);
        int numberOfSolidersWillBeAdded ;
        for(Nodes node : borderCountries){
            if(additionalSoliders >0){
                numberOfSolidersWillBeAdded = (int)Math.ceil(additionalSoliders*node.getNBSR());
                if( additionalSoliders > numberOfSolidersWillBeAdded ){
                    System.out.println("in if -> "+node.getId()+" no of soliders was "+node.getSoliderNumb()+"  and now is "+(node.getSoliderNumb() + numberOfSolidersWillBeAdded)+"\n\n");
                    node.setSoliderNumb(node.getSoliderNumb() + numberOfSolidersWillBeAdded);
                    additionalSoliders -= numberOfSolidersWillBeAdded ;
                }
                else{
                    System.out.println("in else -> "+node.getId()+"  no of soliders was "+node.getSoliderNumb()+"  and now is "+(node.getSoliderNumb() + additionalSoliders)+"\n\n");
                    node.setSoliderNumb(node.getSoliderNumb() + additionalSoliders);
                    additionalSoliders = 0;
                }
            }
        }
    }
    public void reinforceOnly(ArrayList <Nodes> countryEditiedByReinfrocment) {
        int additionalSoliders = enforcementSoldiers();
        getMyCountries();
        getEnemyCountries();
        calculateAllNBSR(forMe);
        Collections.sort(borderCountries,Nodes.NBSRDescendingComparator);
        int numberOfSolidersWillBeAdded ;
        for(Nodes node : borderCountries){
            if(additionalSoliders >0){
                numberOfSolidersWillBeAdded = (int)Math.ceil(additionalSoliders*node.getNBSR());
                if( additionalSoliders > numberOfSolidersWillBeAdded ){
                    System.out.println("in if -> "+node.getCountryName()+" no of soliders was "+node.getSoliderNumb()+"  and now is "+(node.getSoliderNumb() + numberOfSolidersWillBeAdded)+"\n\n");               
                    countryEditiedByReinfrocment.add(node);
                    node.setSoliderNumb(node.getSoliderNumb() + numberOfSolidersWillBeAdded);
                    additionalSoliders -= numberOfSolidersWillBeAdded ;
                }
                else{
                    System.out.println("in else -> "+node.getId()+"  no of soliders was "+node.getSoliderNumb()+"  and now is "+(node.getSoliderNumb() + additionalSoliders)+"\n\n");
                    countryEditiedByReinfrocment.add(node);
                    node.setSoliderNumb(node.getSoliderNumb() + additionalSoliders);
                    additionalSoliders = 0;
                }
            }
        }
    }

    public Iterable<State> getNextStates(){
        ArrayList<State> nextStates = new ArrayList<>();
        int limit =0;
        calculateAllNBSR(forMe);
        Collections.sort(borderCountries,Nodes.NBSRComparator);
        calculateAllNBSR(forEnemy);
        Collections.sort(enemyBorderCountries,Nodes.NBSRDescendingComparator);
        if(enemyBorderCountries.size()>3){
            limit=3;
        }
        else
        {
            limit=enemyBorderCountries.size();
        }
        for(int i = 0 ;i<limit ;i++){
                for(Nodes neighbour : map.GetNodeNeighbors(enemyBorderCountries.get(i).getId())){
                    boolean attacked = false ;
                    Nodes attackingNode = null;
                    int numberOfAttackingSoliders = 0 ;
                    if( neighbour.getPlayerNo() == playerID){
                        attackingNode = neighbour;
                        if(attackingNode != null){
                            numberOfAttackingSoliders = attackingNode.getSoliderNumb()-1;  //-1 so that minimum one soilder will be lefted to defend
                        }
                        if(enemyBorderCountries.get(i).getSoliderNumb() < 20){
                            int testnumberOfAttackingSoliders=numberOfAttackingSoliders;
                            attacked =false;
                            while((testnumberOfAttackingSoliders-1)>=0&& (enemyBorderCountries.get(i).getSoliderNumb()-1)>=0&& Propability.getInstance().getProb(testnumberOfAttackingSoliders,enemyBorderCountries.get(i).getSoliderNumb()) >0.75){
                                numberOfAttackingSoliders = testnumberOfAttackingSoliders;
                                nextStates.add(new State(map,playerID,depth+1,this,new Attack(attackingNode,enemyBorderCountries.get(i),numberOfAttackingSoliders)));
//                                attackArrayList.add(new Attack(attackingNode,enemyBorderCountries.get(i),numberOfAttackingSoliders));
//                                System.out.println(("attacker ID = " + attackingNode.getId() +"defender ID = "+enemyBorderCountries.get(i).getId() +"numberOfAttackingSoliders = "+numberOfAttackingSoliders));
                                attacked = true;
                                testnumberOfAttackingSoliders--;
                            }
                        }
                        if(attacked == false && attackingNode.getSoliderNumb() > (2*enemyBorderCountries.get(i).getSoliderNumb()+1)){
                            attacked = true;
                            nextStates.add(new State(map,playerID,depth+1,this,new Attack(attackingNode,enemyBorderCountries.get(i),attackingNode.getSoliderNumb()-1)));
//                            attackArrayList.add(new Attack(attackingNode,enemyBorderCountries.get(i),attackingNode.getSoliderNumb()-1));
//                            System.out.println(("attacker ID = " + attackingNode.getId() +"defender ID = "+enemyBorderCountries.get(i).getId() +"numberOfAttackingSoliders = "+(attackingNode.getSoliderNumb()-1)));
                        }
                    }
                }
            }
        return nextStates;
    }

    public boolean isGoal() {
        if(myCountries.size()== map.V()-1 || depth >= 3){
            return true;
        }
       return false;
    }

    public boolean isRealTimeGoal() {
        if(myCountries.size()== map.V()-1 || depth >= 5){
            return true;
        }
        return false;
    }

    public Double computeUtility(){
    	
    	costPlusHiurestics = calculateAllBSRs(forMe)+ Double.valueOf(depth);
    	return 1.0/(costPlusHiurestics*1.0);
    	
    }
    
    @Override
    public int compareTo(State o) {
        costPlusHiurestics = calculateAllBSRs(forMe)+ Double.valueOf(depth);
        return this.costPlusHiurestics.compareTo(o.costPlusHiurestics);
    }

    protected void calculateAllNBSR(boolean forMe){
        double totalBSRSum = 0.0;
        if(forMe){
            NBSRList.clear();
            getMyBorders();
            totalBSRSum = calculateAllBSRs(forMe);
            for(int i =0 ; i<borderCountries.size() ; i++){
                borderCountries.get(i).setNBSR((BSRList.get(i)/totalBSRSum));
            }
//            costPlusHiurestics = totalBSRSum+ Double.valueOf(depth);
        }else{
            enemyNBSRList.clear();
            getEnemyBorders();
            totalBSRSum = calculateAllBSRs(forMe);
            for(int i =0 ; i<enemyBorderCountries.size() ; i++){
                enemyBorderCountries.get(i).setNBSR((enemyBSRList.get(i)/totalBSRSum));
            }
        }
    }

    protected double calculateAllBSRs(boolean forMe){
        double BSRSum = 0.0;
        double BSR = 0.0;
        if(forMe){
            BSRList.clear();
            for(Nodes node : borderCountries){
                BSR = calculateBSR(node , forMe);
                BSRList.add(BSR);
                BSRSum+= BSR;
            }
        }else{
            enemyBSRList.clear();
            for(Nodes node :enemyCountries){
                if(node.getId()!=0) {
                    BSR = calculateBSR(node, forMe);
                    enemyBSRList.add(BSR);
                    BSRSum += BSR;
                }
            }
        }
        return BSRSum;
    }

    protected double calculateBSR(Nodes node ,boolean forWho){
        return ((double)calculateBST(node,forWho)/node.getSoliderNumb());
    }

    protected int calculateBST(Nodes node,boolean forMe){
        int BST=0;
        if(forMe){
            for (Nodes neighbours :  map.GetNodeNeighbors(node.getId())){
                if(neighbours.getPlayerNo() != this.playerID){
                    BST +=neighbours.getSoliderNumb();
                }
            }
        }else {
            for (Nodes neighbours : map.GetNodeNeighbors(node.getId())) {
                if (neighbours.getPlayerNo() == this.playerID) {
                    BST += neighbours.getSoliderNumb();
                }
            }
        }
        return BST;
    }

    private void getMyCountries(){
        myCountries.clear();
        int i =0;
        for (Nodes currentNode: map.GraphNodes()){
            if(currentNode.getPlayerNo() == playerID){
                myCountries.add(currentNode);
            }
        }
    }

    private void getEnemyCountries(){
        enemyCountries.clear();
        for (Nodes currentNode: map.GraphNodes()){
            if(currentNode.getPlayerNo() != playerID){
                enemyCountries.add(currentNode);
            }
        }
    }

    protected void getMyBorders(){
        this.borderCountries.clear();
        for(Nodes currentNode :myCountries){
            for(Nodes adjToCurrentNode : map.GetNodeNeighbors(currentNode.getId())){
                if(adjToCurrentNode.getPlayerNo() != this.playerID){
                    borderCountries.add(currentNode);
                    break;
                }
            }
        }
    }

    protected void getEnemyBorders(){
        for(Nodes currentNode : enemyCountries){
            for(Nodes adjToCurrentNode : map.GetNodeNeighbors(currentNode.getId())){
                if(adjToCurrentNode.getPlayerNo() == this.playerID){
                    enemyBorderCountries.add(currentNode);
                    break;
                }
            }
        }
    }

    public int enforcementSoldiers(){
        int countriesOwned=0;
        for(Nodes adjToCurrentNode : map.GraphNodes()){
            if(adjToCurrentNode.getPlayerNo() == this.playerID){
                countriesOwned++;
            }
        }
        int t = countriesOwned /3;
        return Math.max(3,(t));
    }


    public void doAttack(int attackSourceID,int attackDestinationID,int soldNumb){
        System.out.println("number of soldier to attack with"+soldNumb);
        map.getNodeDetails(attackSourceID).setSoliderNumb(map.getNodeDetails(attackSourceID).getSoliderNumb()- soldNumb);
        int nmbSoldiersToAttackWith = soldNumb;

        int curentSoldiersToAttackWith = 0;
        int curentSoldiersToDefendWith = 0;
        Integer []  attckerResult;
        Integer [] defenderResult;


        while(nmbSoldiersToAttackWith!= 0 && map.getNodeDetails(attackDestinationID).getSoliderNumb() !=0){

            //Attack soldiers
            if(nmbSoldiersToAttackWith >= 3){
                curentSoldiersToAttackWith = 3;
            }else{
                curentSoldiersToAttackWith = nmbSoldiersToAttackWith;
            }

            //defend soldiers
            if( map.getNodeDetails(attackDestinationID).getSoliderNumb() >= 2){
                curentSoldiersToDefendWith = 2;
            }else{
                curentSoldiersToDefendWith = 1;
            }

            //roll dice
            attckerResult = rollDice(curentSoldiersToAttackWith);
            defenderResult = rollDice(curentSoldiersToDefendWith);

            //print dice values
            System.out.println("Turn");
            System.out.println("Attacker Dice: ");
            for (Integer e :attckerResult){
                System.out.println(e);
            }
            System.out.println("Defender Dice: ");
            for (Integer e :defenderResult){
                System.out.println(e);
            }

            int min=0 ;
            if(defenderResult.length <= attckerResult.length ){
                min = defenderResult.length ;
            }
            else if(attckerResult.length < defenderResult.length ){
                min = attckerResult.length;
            }
            int flag = 0;
            for(int i=0; i< min ; i++){

                if(attckerResult[i] == defenderResult[i] ){
                    nmbSoldiersToAttackWith--;
                    if(i==0){
                        flag = 1;
                        break;
                    }
                }
                else if(attckerResult[i] < defenderResult[i]){
                    nmbSoldiersToAttackWith--;
                }
                else if(attckerResult[i] > defenderResult[i]){
                    map.getNodeDetails(attackDestinationID).setSoliderNumb( map.getNodeDetails(attackDestinationID).getSoliderNumb() -1);
                }
            }
            if(flag == 1){
                break;
            }
        }
        if( map.getNodeDetails(attackDestinationID).getSoliderNumb()==0){
            //ksb el balad
            map.getNodeDetails(attackDestinationID).setSoliderNumb(nmbSoldiersToAttackWith);
            map.getNodeDetails(attackDestinationID).setPlayerNo(map.getNodeDetails(attackSourceID).getPlayerNo());
            int n =  map.getNodeDetails(attackDestinationID).getPlayer1OR2();
            if(n==1)
                map.getNodeDetails(attackDestinationID).setPlayer1OR2(2);
            else
                map.getNodeDetails(attackDestinationID).setPlayer1OR2(1);
//            this.countries.add( map.getNodeDetails(attackDestinationID));
        }
        else if(nmbSoldiersToAttackWith==0){
            //5ssr el balad
        }
        else if( map.getNodeDetails(attackDestinationID).getSoliderNumb()!=0 && nmbSoldiersToAttackWith!=0 ){
            // ta3adol fl awel
            map.getNodeDetails(attackSourceID).setSoliderNumb(map.getNodeDetails(attackSourceID).getSoliderNumb()+ nmbSoldiersToAttackWith);
        }
    }

    private Integer [] rollDice(int count){

        Integer [] temp = new Integer [count];

        for(int i=0; i<count; i++){

            temp[i] = ThreadLocalRandom.current().nextInt(1,6+1);

        }

        Arrays.sort(temp,Collections.reverseOrder());

        return temp;
    }

    public void myprintBoard(){

        System.out.println("ID\tName\tplayer\tsoldiers");
        for(Nodes n: map.GraphNodes()){
            System.out.println(n.getId()+"\t"+n.getCountryName()+"\t\t"+n.getPlayerNo()+"\t\t"+n.getSoliderNumb());
//            System.out.println(map.V());
        }

    }
}
