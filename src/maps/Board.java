package maps;

import java.util.ArrayList;

public class Board {
	
	public static Graph AmericaBorad(){
		
		ArrayList<Nodes> n = new ArrayList<Nodes>();
		
		Nodes t;	
		
		t = new Nodes(0,"none");	
		n.add(t);
		
		t = new Nodes(1,"WA");	
		n.add(t);
		
		t = new Nodes(2,"OR");	
		n.add(t);
		
		t = new Nodes(3,"CA");	
		n.add(t);
		
		t = new Nodes(4,"NV");	
		n.add(t);
		
		t = new Nodes(5,"ID");	
		n.add(t);
		
		t = new Nodes(6,"MT");	
		n.add(t);
		
		t = new Nodes(7,"WY");	
		n.add(t);
		
		t = new Nodes(8,"UT");	
		n.add(t);
		
		t = new Nodes(9,"AZ");	
		n.add(t);
		
		t = new Nodes(10,"CO");	
		n.add(t);
		
		t = new Nodes(11,"NM");	
		n.add(t);
		
		t = new Nodes(12,"TX");	
		n.add(t);
		
		t = new Nodes(13,"OK");	
		n.add(t);
		
		t = new Nodes(14,"KS");	
		n.add(t);
		
		t = new Nodes(15,"NE");	
		n.add(t);
		
		t = new Nodes(16,"SD");	
		n.add(t);
	
		t = new Nodes(17,"ND");	
		n.add(t);
		
		
		t = new Nodes(18,"MN");	
		n.add(t);
		
		t = new Nodes(19,"IA");	
		n.add(t);
		
		t = new Nodes(20,"MO");	
		n.add(t);
		
		t = new Nodes(21,"AR");	
		n.add(t);
		
		t = new Nodes(22,"LA");	
		n.add(t);
		
		t = new Nodes(23,"MS");	
		n.add(t);
		
		t = new Nodes(24,"AL");	
		n.add(t);
		
		t = new Nodes(25,"FL");	
		n.add(t);
		
		t = new Nodes(26,"GA");	
		n.add(t);
		
		t = new Nodes(27,"SC");	
		n.add(t);
		
		t = new Nodes(28,"NC");	
		n.add(t);
		
		t = new Nodes(29,"VA");	
		n.add(t);
		
		t = new Nodes(30,"WV");	
		n.add(t);
		
		t = new Nodes(31,"TN");	
		n.add(t);
		
		t = new Nodes(32,"KY");	
		n.add(t);
		
		t = new Nodes(33,"IL");	
		n.add(t);
		
		t = new Nodes(34,"WI");	
		n.add(t);
		
		t = new Nodes(35,"IN");	
		n.add(t);
		
		t = new Nodes(36,"MI");	
		n.add(t);
		
		t = new Nodes(37,"OH");	
		n.add(t);
		
		t = new Nodes(38,"PA");	
		n.add(t);
		
		t = new Nodes(39,"NY");	
		n.add(t);
		
		t = new Nodes(40,"VT");	
		n.add(t);
		
		t = new Nodes(41,"NH");	
		n.add(t);
		
		t = new Nodes(42,"ME");	
		n.add(t);
		
		t = new Nodes(43,"MA");	
		n.add(t);
		
		t = new Nodes(44,"RI");	
		n.add(t);
		
		t = new Nodes(45,"CT");	
		n.add(t);
		
		t = new Nodes(46,"NJ");	
		n.add(t);
		
		t = new Nodes(47,"DE");	
		n.add(t);
		
		t = new Nodes(48,"MD");	
		n.add(t);
		
		t = new Nodes(49,"HI");	
		n.add(t);
		
		t = new Nodes(50,"AK");	
		n.add(t);
		

		Graph g = new Graph(n);
		
		g.addEdge(1, 2);
		g.addEdge(1, 5);
		
		g.addEdge(2, 5);
		g.addEdge(2, 4);
		g.addEdge(2, 3);
		
		g.addEdge(3, 4);
		g.addEdge(3, 9);
		g.addEdge(3, 50);
		
		g.addEdge(4, 5);
		g.addEdge(4, 8);
		g.addEdge(4, 9);
		
		g.addEdge(5, 8);
		g.addEdge(5, 7);
		g.addEdge(5, 6);
		
		g.addEdge(6, 7);
		g.addEdge(6, 16);
		g.addEdge(6, 17);
		
		g.addEdge(7, 8);
		g.addEdge(7, 10);
		g.addEdge(7, 15);
		g.addEdge(7, 16);
		
		g.addEdge(8, 9);
		g.addEdge(8, 11);
		g.addEdge(8, 10);
		
		g.addEdge(9,10);
		g.addEdge(9, 40);
		g.addEdge(9, 50);
		g.addEdge(9, 11);
		
		g.addEdge(10, 11);
		g.addEdge(10, 13);
		g.addEdge(10, 14);
		g.addEdge(10, 15);
		
		g.addEdge(11, 13);
		g.addEdge(11, 12);
		g.addEdge(11, 49);
		g.addEdge(11, 50);
		
		g.addEdge(12, 13);
		g.addEdge(12, 22);
		g.addEdge(12, 21);
		g.addEdge(12, 49);
		
		g.addEdge(13, 14);
		g.addEdge(13, 20);
		g.addEdge(13, 21);
		g.addEdge(13, 22);
		
		g.addEdge(14, 15);
		g.addEdge(14, 19);
		g.addEdge(14, 20);
		
		g.addEdge(15, 16);
		g.addEdge(15, 19);
		g.addEdge(15, 20);
		
		g.addEdge(16, 17);
		g.addEdge(16, 18);
		g.addEdge(16, 19);
		
		g.addEdge(17, 18);
		
		g.addEdge(18, 19);
		g.addEdge(18, 34);
		
		g.addEdge(19, 20);
		g.addEdge(19, 33);
		g.addEdge(19, 34);
		
		g.addEdge(20, 21);
		g.addEdge(20, 31);
		g.addEdge(20, 32);
		g.addEdge(20, 33);
		
		g.addEdge(21, 22);
		
		g.addEdge(22, 23);
		
		g.addEdge(23, 24);
		g.addEdge(23, 31);
		
		g.addEdge(24, 25);
		g.addEdge(24, 26);
		g.addEdge(24, 31);
		
		g.addEdge(25, 26);
		
		g.addEdge(26, 27);
		g.addEdge(26, 28);
		g.addEdge(26, 31);
		
		g.addEdge(27, 28);
		
		g.addEdge(28, 29);
		g.addEdge(28, 31);
		
		g.addEdge(29, 30);
		g.addEdge(29, 31);
		g.addEdge(29, 32);
		g.addEdge(29, 48);
		
		g.addEdge(30, 32);
		g.addEdge(30, 37);
		g.addEdge(30, 38);
		g.addEdge(30, 39);
		g.addEdge(30, 48);
		
		g.addEdge(31, 32);
		
		g.addEdge(32, 33);
		g.addEdge(32, 35);
		g.addEdge(32, 37);
		
		g.addEdge(33, 34);
		g.addEdge(33, 35);
		
		g.addEdge(34, 36);
		
		g.addEdge(35, 36);
		g.addEdge(35, 37);
		
		g.addEdge(36, 37);
		
		g.addEdge(37, 38);
		
		g.addEdge(38, 39);
		g.addEdge(38, 46);
		g.addEdge(38, 47);
		g.addEdge(38, 48);
		
		g.addEdge(39, 43);
		g.addEdge(39, 45);
		g.addEdge(39, 46);
		
		g.addEdge(40, 41);
		g.addEdge(40, 43);
		
		g.addEdge(41, 42);
		
		//42
		
		g.addEdge(43, 44);
		g.addEdge(43, 45);
		
		g.addEdge(44, 45);
		
		//45
		
		g.addEdge(46, 47);
		
		g.addEdge(47, 48);
		
		//48
		
		g.addEdge(49,50);
		
		//50
		
		
		
		return g;
	}
	
	
	public static Graph EygptBoard(){
		ArrayList<Nodes> n = new ArrayList<Nodes>();

		Nodes t;

		t = new Nodes(0,"none");
		n.add(t);

		t = new Nodes(1,"MT");
		n.add(t);

		t = new Nodes(2,"AL");
		n.add(t);

		t = new Nodes(3,"BH");
		n.add(t);

		t = new Nodes(4,"KF");
		n.add(t);

		t = new Nodes(5,"DK");
		n.add(t);

		t = new Nodes(6,"DT");
		n.add(t);

		t = new Nodes(7,"PT");
		n.add(t);

		t = new Nodes(8,"SI");
		n.add(t);

		t = new Nodes(9,"GH");
		n.add(t);

		t = new Nodes(10,"MF");
		n.add(t);

		t = new Nodes(11,"KB");
		n.add(t);

		t = new Nodes(12,"SH");
		n.add(t);

		t = new Nodes(13,"IS");
		n.add(t);

		t = new Nodes(14,"GZ");
		n.add(t);

		t = new Nodes(15,"FY");
		n.add(t);

		t = new Nodes(16,"CI");
		n.add(t);

		t = new Nodes(17,"SZ");
		n.add(t);

		t = new Nodes(18,"JS");
		n.add(t);

		t = new Nodes(19,"BS");
		n.add(t);

		t = new Nodes(20,"MN");
		n.add(t);

		t = new Nodes(21,"WA");
		n.add(t);

		t = new Nodes(22,"AT");
		n.add(t);

		t = new Nodes(23,"BA");
		n.add(t);

		t = new Nodes(24,"SG");
		n.add(t);

		t = new Nodes(25,"KN");
		n.add(t);

		t = new Nodes(26,"LX");
		n.add(t);

		t = new Nodes(27,"AS");
		n.add(t);

		t = new Nodes(28,"HT");
		n.add(t);
		
		t = new Nodes(29,"TE");
		n.add(t);

		Graph g = new Graph(n);

        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 14);
        g.addEdge(1, 21);

        g.addEdge(2, 3);

        g.addEdge(3, 4);
        g.addEdge(3, 9);
        g.addEdge(3, 10);
        g.addEdge(3, 4);

        g.addEdge(4, 5);
        g.addEdge(4, 9);

        g.addEdge(5, 6);
        g.addEdge(5, 9);
        g.addEdge(5, 12);
        g.addEdge(5, 11);

        g.addEdge(6, 7);

        g.addEdge(7, 13);
        g.addEdge(7, 8);

        g.addEdge(8, 13);
        g.addEdge(8, 17);
        g.addEdge(8, 18);

        g.addEdge(9, 10);

        g.addEdge(10, 11);
        g.addEdge(10, 14);

        g.addEdge(11, 12);
        g.addEdge(11, 14);
        g.addEdge(11, 16);

        g.addEdge(12, 13);
        g.addEdge(12, 16);

        g.addEdge(13, 17);

        g.addEdge(14, 15);
        g.addEdge(14, 16);
        g.addEdge(14, 19);
        g.addEdge(14, 20);
        g.addEdge(14, 21);

        g.addEdge(15, 19);

        g.addEdge(16, 17);
        g.addEdge(16, 19);
        g.addEdge(16, 23);

        g.addEdge(17, 23);
        g.addEdge(17, 18);

        //18

        g.addEdge(19, 20);
        g.addEdge(19, 23);

        g.addEdge(20, 21);
        g.addEdge(20, 22);
        g.addEdge(20, 23);

        g.addEdge(21, 22);
        g.addEdge(21, 24);
        g.addEdge(21, 26);
        g.addEdge(21, 27);

        g.addEdge(22, 23);
        g.addEdge(22, 24);

        g.addEdge(23, 24);
        g.addEdge(23, 25);
        g.addEdge(23, 26);
        g.addEdge(23, 27);
        g.addEdge(23, 28);
        g.addEdge(23, 29);

        g.addEdge(24, 25);

        g.addEdge(25, 26);
        g.addEdge(25, 27);

        g.addEdge(28, 29);
        return g;
	}

}
