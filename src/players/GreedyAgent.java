package players;

import Statistics.Propability;
import maps.Nodes;

import java.util.ArrayList;
import java.util.Collections;

public class GreedyAgent extends Player {

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

    public GreedyAgent(){
        this.playerID = 5;
        myCountries = new ArrayList<>();
        borderCountries = new ArrayList<>();
        BSRList = new ArrayList<>();
        NBSRList = new ArrayList<>();
        enemyCountries = new ArrayList<>();
        enemyNBSRList = new ArrayList<>();
        enemyBSRList = new ArrayList<>();
        enemyBorderCountries = new ArrayList<>();
    }

    @Override
    public void reinforce() {
        enforcementSoldiers();
        myCountries.clear();
        enemyCountries.clear();
        borderCountries.clear();
        NBSRList.clear();
        for (Nodes currentNode: map.GraphNodes()){
        	if(currentNode.getPlayerNo() == playerID){
                myCountries.add(currentNode);
            }
            else{
                enemyCountries.add(currentNode);
//                System.out.println("enemy node = " +currentNode.getId());
            }
        }

//        for(Node node : myCountries){
//            System.out.println("my ID = "+ node.getId());
//        }

        calculateAllNBSR(forMe);
        Collections.sort(borderCountries,Nodes.NBSRDescendingComparator);
//        for(int i =0 ; i<borderCountries.size() ; i++){
//            System.out.println("NBSR = "+ borderCountries.get(i).getNBSR() + "  node ID  =  "+borderCountries.get(i).getId()+"\n");
//        }
//        System.out.println("======================================================================================");
        int numberOfSolidersWillBeAdded ;
        for(Nodes node : borderCountries){
            if(additionalSoliders >0){
                numberOfSolidersWillBeAdded = (int)Math.ceil(additionalSoliders*node.getNBSR());
//                System.out.println("NBSR = "+ node.getNBSR() + " node ID  =  "+node.getId()+"  numberOfSolidersWillBeAdded "+numberOfSolidersWillBeAdded+"\n");
                if( additionalSoliders > numberOfSolidersWillBeAdded ){
                    System.out.println("in if -> "+node.getId()+" no of soliders was "+node.getSoliderNumb()+"  and now is "+(node.getSoliderNumb() + numberOfSolidersWillBeAdded)+"\n\n");
                   node.setSoliderNumb(node.getSoliderNumb() + numberOfSolidersWillBeAdded);
                  //gui
                   countryEditiedByReinfrocment.add(node);
                   countryEditiedByReinfrocment.get(countryEditiedByReinfrocment.size()-1).setSoliderNumb(node.getSoliderNumb()+ numberOfSolidersWillBeAdded);
                  
                   additionalSoliders -= numberOfSolidersWillBeAdded ;
                }
               else{
                    System.out.println("in else -> "+node.getId()+"  no of soliders was "+node.getSoliderNumb()+"  and now is "+(node.getSoliderNumb() + additionalSoliders)+"\n\n");
                    node.setSoliderNumb(node.getSoliderNumb() + additionalSoliders);
                    //gui
                    countryEditiedByReinfrocment.add(node);
                    countryEditiedByReinfrocment.get(countryEditiedByReinfrocment.size()-1).setSoliderNumb(node.getSoliderNumb()+ additionalSoliders);
                    additionalSoliders = 0;
               }
            }
        }
    }

    @Override
    public void attack() {
//        enemyCountries.clear();
        enemyNBSRList.clear();
        enemyBorderCountries.clear();
        System.out.println("Enemy");
        calculateAllNBSR(forMe);
        Collections.sort(borderCountries,Nodes.NBSRComparator);
        for(int i =0 ; i<borderCountries.size() ; i++){
            System.out.println("NBSR = "+ borderCountries.get(i).getNBSR() + "  node ID  =  "+borderCountries.get(i).getId()+"\n");
        }
        System.out.println("======================================================================================");

        calculateAllNBSR(forEnemy);
        Collections.sort(enemyBorderCountries,Nodes.NBSRDescendingComparator);
        for(int i =0 ; i<enemyBorderCountries.size() ; i++){
            System.out.println("Enemy NBSR = "+ enemyBorderCountries.get(i).getNBSR() + "  node ID  =  "+enemyBorderCountries.get(i).getId()+"\n");
        }
        System.out.println("======================================================================================");

        double smallestNBSR = Double.MAX_VALUE ;
        boolean attacked = false ;
        Nodes attackingNode = null;
	        for(Nodes node : enemyBorderCountries){
	//            System.out.println("current enemy node id = " +node.getId());
	            smallestNBSR = Double.MAX_VALUE;
	            for(Nodes neighbour : map.GetNodeNeighbors(node.getId())){
	//                System.out.println("current my node id = " +neighbour.getId());
	                if(smallestNBSR > neighbour.getNBSR() && neighbour.getPlayerNo() == playerID){
	                    smallestNBSR = neighbour.getNBSR();
	                    attackingNode = neighbour;
	                }
	            }
	            
	            //hahgem bekam
	            int numberOfAttackingSoliders = 0 ;
	            if(attackingNode != null){
	                numberOfAttackingSoliders = attackingNode.getSoliderNumb()-1;  //-1 so that minimum one soilder will be lefted to defend
	            }
	            if(node.getSoliderNumb() < 10){
	                int testnumberOfAttackingSoliders=numberOfAttackingSoliders;
	                attacked = false;
	                while((testnumberOfAttackingSoliders-1)>=0&& (node.getSoliderNumb()-1)>=0 && Propability.getInstance().getProb(testnumberOfAttackingSoliders,node.getSoliderNumb()) >0.7){
	                    System.out.println("winprop = "+Propability.getInstance().getProb(testnumberOfAttackingSoliders,node.getSoliderNumb()));
	                    numberOfAttackingSoliders =testnumberOfAttackingSoliders;
	                    testnumberOfAttackingSoliders--;
	                    attacked = true;
	                }
	                if(attacked == true){
	                    System.out.println("attacked from "+attackingNode.getId()+ " to "+node.getId()+" using "+numberOfAttackingSoliders+"win prop = "+Propability.getInstance().getProb(numberOfAttackingSoliders,node.getSoliderNumb()));
		                ownedTerritoryToAttackGUI=attackingNode;
		                adjacentTerritoryToAttackGUI=node;
		                attackingSolidersGUI=numberOfAttackingSoliders;
	                    attackFrom.add(attackingNode);
	                    attackTo.add(node);
	                    solidersAttacking.add(numberOfAttackingSoliders);
	                    attackWithDice(attackingNode,node,numberOfAttackingSoliders);
	                }
	            }
	            if(attacked == false && attackingNode.getSoliderNumb() > (2*node.getSoliderNumb()+1)){
	                System.out.println("else attacked from "+attackingNode.getId()+ " to "+node.getId()+" using "+numberOfAttackingSoliders+"win prop = "+Propability.getInstance().getProb(attackingNode.getSoliderNumb()-1,node.getSoliderNumb()));
	                ownedTerritoryToAttackGUI=attackingNode;
	                adjacentTerritoryToAttackGUI=node;
	                attackingSolidersGUI=attackingNode.getSoliderNumb()-1;
	                attackFrom.add(attackingNode);
                    attackTo.add(node);
                    solidersAttacking.add(attackingNode.getSoliderNumb()-1);
	                attackWithDice(attackingNode,node,attackingNode.getSoliderNumb()-1);
	            }
	        }
	    
	  }
	    private void calculateAllNBSR(boolean forMe){
	        double totalBSRSum = 0.0;
	        if(forMe){
	            NBSRList.clear();
	            getMyBorders();
	//            System.out.println("======================================================================================");
	//            for(Node node : borderCountries){
	//                System.out.println("Border countrie ID = "+ node.getId());
	//            }
	//            System.out.println("======================================================================================");
	            totalBSRSum = calculateAllBSRs(forMe);
	//            System.out.println("======================================================================================");
	//            System.out.println("BSRSUM = " +totalBSRSum+"\n");
	//            System.out.println("======================================================================================");
	//            System.out.println("BSR sss");
	            for(int i =0 ; i<borderCountries.size() ; i++){
	//                System.out.println("Node id =  "+borderCountries.get(i).getId() +" BSR = "+BSRList.get(i)+"\n");
	                borderCountries.get(i).setNBSR((BSRList.get(i)/totalBSRSum));
	            }
	            System.out.println("======================================================================================");
	        }else{
	            enemyNBSRList.clear();
	            getEnemyBorders();
	            System.out.println("======================================================================================");
	            for(Nodes node : enemyBorderCountries){
	                System.out.println("Enemy Border countrie ID = "+ node.getId());
	            }
	            System.out.println("======================================================================================");
	            totalBSRSum = calculateAllBSRs(forMe);
	            System.out.println("======================================================================================");
	            System.out.println("Enemy BSRSUM = " +totalBSRSum+"\n");
	            System.out.println("======================================================================================");
	            System.out.println("Enemy BSR sss");
	            for(int i =0 ; i<enemyBorderCountries.size() ; i++){
	                System.out.println("Enemy Node id =  "+enemyBorderCountries.get(i).getId() +" BSR = "+enemyBSRList.get(i)+"\n");
	                enemyBorderCountries.get(i).setNBSR((enemyBSRList.get(i)/totalBSRSum));
	            }
	        }
    }

    private double calculateAllBSRs(boolean forMe){
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

    private double calculateBSR(Nodes node ,boolean forWho){
        return ((double)calculateBST(node,forWho)/node.getSoliderNumb());
    }

    private int calculateBST(Nodes node,boolean forMe){
        int BST=0;
        if(forMe){
            for (Nodes neighbours :  map.GetNodeNeighbors(node.getId())){
                if(neighbours.getPlayerNo() != this.playerID){
                    BST +=neighbours.getSoliderNumb();
                }
            }
//            System.out.println("BST of NodeID "+node.getId()+" = "+BST);
        }else {
            for (Nodes neighbours : map.GetNodeNeighbors(node.getId())) {
                if (neighbours.getPlayerNo() == this.playerID) {
                    BST += neighbours.getSoliderNumb();
                }

            }
            System.out.println("BST of EnemyNodeID "+node.getId()+" = "+BST);
        }
        return BST;
    }


    private void getMyBorders(){
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

    private void getEnemyBorders(){
        for(Nodes currentNode : enemyCountries){
            for(Nodes adjToCurrentNode : map.GetNodeNeighbors(currentNode.getId())){
                if(adjToCurrentNode.getPlayerNo() == this.playerID){
                    enemyBorderCountries.add(currentNode);
                    break;
                }
            }
        }
    }
}
