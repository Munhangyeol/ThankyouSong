package org.dfpl.db.hash.m615458;
import java.util.ArrayList;
// package 이름은 org.dfpl.db.hash.m학번 입니다. 
// 지키지 않을 시 반려합니다. 
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet implements Set<Integer> {

	// 너무 어려운 학생은 LinkedList 등으로 작성하셔도 됩니다.
	// 최대 3점을 받을 수 있습니다.
	// 멤버 변수는 hashTable 이외의 것을 사용하지 않습니다. (예: size)
	private MyThreeWayBTree[] hashTable;
	// 예: private LinkedList<Integer>[] hashTable;

	public MyHashSet() {
		// 해시테이블 배열 크기는 3로 고정합니다.
		// hash function은 key를 3로 나눈 값이며,
		// 충돌시 3-way B-Tree에 저장됩니다.
		
		
		hashTable = new MyThreeWayBTree[3];
		for(int i=0;i<3;i++)
			hashTable[i]=new MyThreeWayBTree();
		
	}

	@Override
	public int size() {
		// 예제 코드로 수정해도 됩니다. 
	
		int size = 0;
		for (MyThreeWayBTree t : hashTable) {
			if(t==null)return 0;
			size += t.size();
			
		}
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(this.hashTable[0].isEmpty()==true&&this.hashTable[1].isEmpty()==true&&this.hashTable[2].isEmpty()==true) {
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		int e= (int)o;
		if(hashTable[e%3].contains(e)==true)
			return true;
		else 
			return false;
	
	}

	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		MyIterator iter=new MyIterator(hashTable,this.size());
		
		
		return iter.getIter();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Integer e) {
		// TODO Auto-generated method stub
		if(hashTable[e%3].contains(e)==false)
		hashTable[e%3].add(e);
		
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		int e=(Integer)o;
		
		if(hashTable[e%3].contains(e)==true) {
			hashTable[e%3].remove(o);
			return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Integer> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}
	
	public void printRoot(int i) {
		
	}
	class MyIterator implements Iterator<Integer>{
		
		private int currentIndex = 0;
		private int size=0;
		ArrayList<Integer> list;
		public  MyIterator(MyThreeWayBTree hashTable1[],int size) {
			list=new ArrayList();
			
			
			for(int i=0;i<3;i++) {
				Iterator<Integer> iter=hashTable[i].iterator();
			while(iter.hasNext()) {
			
				list.add(iter.next());
				
			}
			}
			
		}
		public Iterator getIter() {
			return list.iterator();
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			 return currentIndex < (this.size+3)&& list.get(currentIndex) != null;
        }

		@Override
		public Integer next() {
			// TODO Auto-generated method stub
			return list.get(currentIndex++);
		}
		
	}
	
}
