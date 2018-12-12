package maps;

import java.util.Comparator;

public  class Nodes implements Comparable<Nodes> {
	
	//unique for each node
	private int id;
	
	private String CountryName;
	
	private Double NBSR = 0.0;
	
	
	private int playerNo = 0;
	private int soliderNumb = 0;
	
	private int player1OR2 = 0;
	
	public int getPlayer1OR2(){
		return this.player1OR2;
	}
	
	
	public void setPlayer1OR2(int n){
		this.player1OR2 = n;
	}
	public Nodes(int id,String name){
		
		this.id = id;
		this.CountryName = name;
	}
	
	public Nodes(int id){
		
		this.id = id;
		
	}
	
	public Nodes(Nodes n){
		
//		System.out.println(n.getPlayerNo());
		this.id = n.id;
		this.CountryName = n.CountryName;
		this.NBSR = n.NBSR;		
		this.playerNo = n.playerNo;
		this.soliderNumb = n.soliderNumb;
		this.player1OR2 = n.player1OR2;
			
	}
	
	public int getId(){
		return id;
	}
	
	public void setPlayerNo(int n){
		this.playerNo = n;
	}
	
	public void IncrementSoliders(){
		this.soliderNumb ++;
	}
	
	public int getPlayerNo(){
		return playerNo;
	}
	
	public String getCountryName(){
		return CountryName;
	}
	public int getSoliderNumb(){
		return soliderNumb;
	}
	
	public void setSoliderNumb(int value){
		soliderNumb = value;
	}
	
	
	public void setNBSR(double BSR){
        this.NBSR = BSR ;
	}

    public Double getNBSR(){
        return NBSR ;
    }

	@Override
	public int compareTo(Nodes o) {
        return this.getNBSR().compareTo(o.getNBSR());
	}

    public static Comparator<Nodes> NBSRComparator = new Comparator<Nodes>() {
        @Override
        public int compare(Nodes o1, Nodes o2) {
            return (o1.getNBSR().compareTo(o2.getNBSR()));
        }
    };

	public static Comparator<Nodes> NBSRDescendingComparator = new Comparator<Nodes>() {
        @Override
        public int compare(Nodes o1, Nodes o2) {
            return ((o1.getNBSR().compareTo(o2.getNBSR()))*(-1));
        }
	};
	
	public static Comparator<Nodes> SoldierNumbComparator = new Comparator<Nodes>() {
        @Override
        public int compare(Nodes o1, Nodes o2) {
            return (new Integer(o1.getSoliderNumb()).compareTo(new Integer(o2.getSoliderNumb())));
        }
	};
}
