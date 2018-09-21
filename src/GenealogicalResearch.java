import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Scanner;

public class GenealogicalResearch {
	
	class person implements Comparable<Object>{
		String name;
		String birthDate;
		String deathDate;
		person mother;
		person father; 
		ArrayList<person> children;
		
		public person(String name) {
			this.name = name;
			children = new ArrayList<person>();
		}
		public boolean compare(person p) {
			return (this.name.equals(p.name));
		}
		@Override
		public int compareTo(Object o) {
			return this.name.compareTo(((person) o).name);
		}
		
	}
	
	public static void main(String[] args) {
		GenealogicalResearch run = new GenealogicalResearch();
		run.run();
	}
	
	public void run() {
//		Kattio katt = new Kattio(System.in);
		Scanner scan = new Scanner(System.in);
		Hashtable<String, person> people = new Hashtable<String, person>();
		int count = 0;
		
		while (true) {
			String s = scan.next();
			if (s.equals("QUIT")) {
//				katt.close();
				scan.close();
				return;
			}
			String[] words = scan.nextLine().split(":");
			
			if (s.equals("BIRTH")) {
				String child = words[0].trim();
				String date = words[1].trim();
				String mother = words[2].trim();
				String father = words[3].trim();
				person c = new person("");
				person m;
				person f;
//				person p;
//				String[] loop = {words[0].trim(), words[2].trim(), words[3].trim()};
//				for (int i = 0; i < 3; i++) {
//					 if (people.containsKey(loop[i])){
//							if (i == 0) {
//								c = people.get(loop[i]);
//								c.birthDate = words[1];
//							} else {
//								p = people.get(loop[i]);
//								p.children.add(c);
//							}
//						} else {
//							if (i == 0) {
//								c = new person(loop[i]);
//								c.birthDate = words[1];
//								people.put(loop[i], c);
//							} else {
//								p = new person(loop[i]);
//								p.children.add(c);
//								people.put(loop[i], p);
//							}
//						}
//				}
				if (people.containsKey(child)){
					c = people.get(child);
					c.birthDate = date;
				} else {
					c = new person(child);
					c.birthDate = date;
					people.put(child, c);
				}
				if (people.containsKey(mother)){
					m = people.get(mother);
					m.children.add(c);
				} else {
					m = new person(mother);
					m.children.add(c);
					people.put(mother, m);
				}
				if (people.containsKey(father)){
					f = people.get(father);
					f.children.add(c);
				} else {
					f = new person(father);
					f.children.add(c);
					people.put(father, f);
				}
				c.mother = m;
				c.father = f;
			}
			else if (s.equals("DEATH")) {
				people.get(words[0].trim()).deathDate = words[1].trim();
			}
			
			if (s.equals("ANCESTORS")) {
				count++; 
				if(count > 1) {
					System.out.println();
				}
				String person = words[0].trim();
				person p = people.get(person);
				System.out.println("ANCESTORS of " + person);
				printAncestors(p, 1);
			}
			if (s.equals("DESCENDANTS")) {
				count++; 
				if(count > 1) {
					System.out.println();
				}
				String person = words[0].trim();
				person p = people.get(person);
				System.out.println("DESCENDANTS of " + person);
				printDescendants(p, 1);
			}
		}
	}

	public void printAncestors(person p, int gen) {
		if (p.mother == null) {
			return;
		}
		for (int i = 0; i < gen; i++) {
			System.out.print("  ");
		}
		person first; 
		person last;
		if (p.mother.compareTo(p.father) < 0) {
			first = p.mother;
			last = p.father;
		} else {
			first = p.father;
			last = p.mother;
		}
		System.out.print(first.name);
		if (first.birthDate != null) {
			System.out.print(" " + first.birthDate + " -");
			if (first.deathDate != null) {
				System.out.print(" " + first.deathDate);
			}
		}
		System.out.println();
		printAncestors(first, gen+1);
		
		for (int i = 0; i < gen; i++) {
			System.out.print("  ");
		}
		System.out.print(last.name);
		if (last.birthDate != null) {
			System.out.print(" " + last.birthDate + " -");
			if (last.deathDate != null) {
				System.out.print(" " + last.deathDate);
			}
		}
		System.out.println();
		printAncestors(last, gen+1);
	}
	
	public void printDescendants(person p, int gen) {
//		if (p.children == null) {
//			return;
//		}
		Collections.sort(p.children);
		for (int i = 0; i < p.children.size(); i++) {
			for (int j = 0; j < gen; j++) {
				System.out.print("  ");
			}
			person p2 = p.children.get(i);
			System.out.print(p2.name);
			if (p2.birthDate != null) {
				System.out.print(" " + p2.birthDate + " -");
				if (p2.deathDate != null) {
					System.out.print(" " + p2.deathDate);
				}
			}
			System.out.println();
			printDescendants(p2, gen+1);
		}
	}
}






//	class Person implements Comparable<Object>{
//		String name;
//		String birth;
//		String death;
//		Person mother;
//		Person father;
//		ArrayList<Person> children;
//
//		public Person(String name) {
//			this.name = name;
//			children = new ArrayList<Person>();
//		}
//
//		@Override
//		public boolean equals(Object o){
//			return this.name.equals(((Person) o).name);
//		}
//		@Override
//		public int compareTo(Object o) {
//			return this.name.compareTo(((Person) o).name);
//		}
//		@Override
//		public String toString(){
//			return name;
//		}
//	}
//
//	public static void main(String[] args) {
//		GenealogicalResearch runner = new GenealogicalResearch();
//		runner.run();
//	}
//
//	public void run() {
//		Scanner scan = new Scanner(System.in);
//
//		Hashtable<String, Person> people = new Hashtable<String, Person>();
//		int count = 0;
//		while(true) {
//			String s = scan.next();
//			if(s.equals("QUIT")) return;
//			String l = scan.nextLine();
//
//			String[] tokens = l.split(":");
//
//			if(s.equals("BIRTH")) {
//				String child = tokens[0].trim();
//				String date = tokens[1].trim();
//				String mother = tokens[2].trim();
//				String father = tokens[3].trim();
//
//				Person c;
//				Person m;
//				Person f;
//
//				
//				if(people.containsKey(child)){
//					c = people.get(child);
//					c.birth = date;
//				}
//				else{
//					c = new Person(child);
//					c.birth = date;
//					people.put(child, c);
//				}
//				if(people.containsKey(mother)){
//					m = people.get(mother);
//					m.children.add(c);
//				}
//				else{
//					m = new Person(mother);
//					m.children.add(c);
//					people.put(mother, m);
//				}
//
//				if(people.containsKey(father)){
//					f = people.get(father);
//					f.children.add(c);
//				}
//				else{
//					f = new Person(father);
//					f.children.add(c);
//					people.put(father, f);
//				}
//
//				c.mother = m;
//				c.father = f;
//
//			}
//
//			else if(s.equals("DEATH")) {
//				String person = tokens[0].trim();
//				String date = tokens[1].trim();
//				Person p = people.get(person);
//				p.death = date;
//			}
//
//			if(s.equals("ANCESTORS")) {
//				count++;
//				if(count > 1) System.out.println();
//				String person = tokens[0].trim();
//				Person p = people.get(person);
//				System.out.println("ANCESTORS of " + person);
//				printAncestors(p, 1);
//			}
//
//			if(s.equals("DESCENDANTS")) {
//				count++;
//				if(count > 1) System.out.println();
//				String person = tokens[0].trim();
//				Person p = people.get(person);
//				System.out.println("DESCENDANTS of " + person);
//				printDecendants(p, 1);
//			}
//			
//			//System.out.println(s);
//			//System.out.println(l);
//		}
//	}
//
//	public void printAncestors(Person p, int level) {
//		if(p.mother == null) return;
//		Person first;
//		Person second;
//
//		if(p.mother.name.compareTo(p.father.name) < 0){
//			first = p.mother;
//			second = p.father;
//		}
//		else {
//			second = p.mother;
//			first = p.father;
//		}
//
//		for(int i = 0; i < level; i++) {
//			System.out.print("  ");
//		}
//
//		System.out.print(first.name);
//		if(first.birth != null) System.out.print(" " +first.birth + " -");
//		if(first.death != null) System.out.print(" " + first.death);
//		System.out.println();
//		printAncestors(first, level+1);
//
//		for(int i = 0; i < level; i++) {
//			System.out.print("  ");
//		}
//
//		System.out.print(second.name);
//		if(second.birth != null) System.out.print(" " +second.birth + " -");
//		if(second.death != null) System.out.print(" " +second.death);
//		System.out.println();
//		printAncestors(second, level+1);
//
//	}
//
//	public void printDecendants(Person p, int level) {
//		Collections.sort(p.children);
//		for(Person c : p.children) {
//			for(int i = 0; i < level; i++) {
//			System.out.print("  ");
//			}
//
//			System.out.print(c.name);
//			if(c.birth != null) System.out.print(" " +c.birth + " -");
//			if(c.death != null) System.out.print(" " + c.death);
//			System.out.println();
//			printDecendants(c, level+1);
//		}
//	}
//}