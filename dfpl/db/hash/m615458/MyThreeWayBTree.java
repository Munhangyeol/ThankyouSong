package org.dfpl.db.hash.m615458;
//package 이름은 org.dfpl.db.hash.m학번 입니다. 

import java.util.ArrayList;

//지키지 않을 시 반려합니다. 

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.SortedSet;

@SuppressWarnings("unused")
public class MyThreeWayBTree implements NavigableSet<Integer> {

	// Data Abstraction은 예시일 뿐 자유롭게 B-Tree의 범주 안에서 어느정도 수정가능

	private MyThreeWayBTreeNode root;
	private MyThreeWayBTreeNode currentNode;
	private MyThreeWayBTreeNode parentNode;
	private MyThreeWayBTreeNode newNode,newNode1,newNode2;
	private MyThreeWayBTreeNode leftChildNode,rightChildeNode,middleNode;
	
	private List<Integer> values;
	private int removeF=0;
	private int travelPos=0;
	private int travelParentPos=0;
	int siz=0;
	public MyThreeWayBTreeNode findTNode(Integer e,MyThreeWayBTreeNode node) {
		/*System.out.println(e);
		System.out.println(node.getKeyList());
		*/
	
		if(node.getChildrenSize()==0) {
		  
			return node;
		}
		else {
			if(node.getKeyPos(node.getKeyList().size()-1)<e) {
				return findTNode(e,node.getChildren(node.getChildrenSize()-1));
			}
			else if(node.getKeyPos(0)>e) {
				
				return findTNode(e,node.getChildren(0));
			}
			else {
				if(node.getChildrenSize()>1)
				return findTNode(e,node.getChildren(1));
				else return findTNode(e,node.getChildren(0));
			}
			
		}
		
		}
		
	
    public MyThreeWayBTreeNode getRoot() {
    	return root;
    }
	@Override
	public Comparator<? super Integer> comparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer first() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer last() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		
	
		return siz;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		
		if(root==null)
			return true;
		else
			return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		MyThreeWayBTreeNode node=root;
		Integer e=(Integer)o;
	
		/*if(node!=null)
		System.out.println(node.getKeyList());*/
		
		while(true) {
		
			if(node==null)return false;
			if(node.contains(e)==true)return true;
			if(node.getChildrenSize()==0)return false;
		
		
			if(node.getKeyList().size()==1) {
				if(node.getKeyPos(0)>e) {
					node=node.getChildren(0);
				}
				else {
					node=node.getChildren(1);
				}
			}
			else {
				
				
				if(node.getKeyPos(0)>e) {
					node=node.getChildren(0);
				}
				else if(node.getKeyPos(1)<e){
					node=node.getChildren(2);
				}
				else {
					node=node.getChildren(1);
				}
			}
			
			
		}
			
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
		
		

		
		/*if(root!=null) {
			System.out.println("root: "+ root.getKeyList());
			for(int i=0;i<root.getChildrenSize();i++) {
				
				System.out.print("i: "+i+root.getChildren(i).getKeyList()+" " );
			}
			System.out.println("");
			for(int i=0;i<root.getChildrenSize();i++) {
				for(int j=0;j<root.getChildren(i).getChildrenSize();j++) {
				
					System.out.print("j: "+i+root.getChildren(i).getChildren(j).getKeyList()+" ");
				}
			}
			System.out.println("");
			for(int i=0;i<root.getChildrenSize();i++) {
				for(int j=0;j<root.getChildren(i).getChildrenSize();j++) {
					for(int k=0;k<root.getChildren(i).getChildren(j).getChildrenSize();k++)
				
					System.out.print("k: "+k+root.getChildren(i).getChildren(j).getChildren(k).getKeyList()+" ");
				}
			}
			System.out.println("");
					
			
			
		}*/
		if(this.contains(e)==true)return false;
		siz++;
		if(root==null) {                  //맨처음에 이노드가 비어있으면.
		    currentNode=new MyThreeWayBTreeNode(e);
			
			root=currentNode;
			return true;
		}
		this.currentNode=this.findTNode(e, root);    //안 비어 있는데 넣어야 하는 리프노드를 찾아야 하는경우.
	
		if(currentNode==null) {                      // 찾은 노드가 비어있을 때
			
			currentNode=new MyThreeWayBTreeNode(e);
			
			return true;
	
		}
		else {                                       // 찾은 노드가 안 비어있을 때
			if(currentNode.getKeyList().size()<2) {                    //찾은 노드에 값을 넣었을 때 maxium 속성은 안 어길때
				
				currentNode.addKey(e);
				return true;
			}
	    else {
				
	    	    currentNode.addKey(e);
	    	    
	    	    removeMaxium(currentNode,e);
				
				
				
			}
		
			
		}
		return true;
	}
	public void removeMaxium(MyThreeWayBTreeNode node,int e) {
		int childPos=0;
		
		    if(node.getParent()==null) {
		   
		    		newNode=new MyThreeWayBTreeNode(node.getKeyPos(1));
		    		newNode1=new MyThreeWayBTreeNode(node.getKeyPos(0));
			    	for(int i=0;i<node.getChildrenSize();i++) {
			    		if(node.getChildren(i).getKeyPos(node.getChildren(i).getKeyList().size()-1)<node.getKeyPos(1))
			    		newNode1.setChilderen(node.getChildren(i));
			    	}
		    		newNode2=new MyThreeWayBTreeNode(node.getKeyPos(2));
		    		for(int i=0;i<node.getChildrenSize();i++) {
		    			if(node.getChildren(i).getKeyPos(0)>=node.getKeyPos(1))
			    		newNode2.setChilderen(node.getChildren(i));
			    	}
		    		newNode.setChilderen(newNode1);
		    		newNode.setChilderen(newNode2);
		    	
		    		root=newNode;
		    	
		    		
		    	
		    }
		    else {
		    
		    	for(int i=0;i<node.getParent().getChildrenSize();i++) {
		    		if(node==node.getParent().getChildren(i)) {
		    			childPos=i;
		    			break;
		    		}
		    	}
		    	node.getParent().addKey(node.getKeyPos(1));
		    	removeF=node.getKeyPos(1);
		    	
		    	node.getKeyList().remove(1);
		    	newNode1=new MyThreeWayBTreeNode(node.getKeyPos(0));
		    	for(int i=0;i<node.getChildrenSize();i++) {
		    		if(node.getChildren(i).getKeyPos(node.getChildren(i).getKeyList().size()-1)<removeF)
		    			newNode1.setChilderen(node.getChildren(i));
		    	}
	    		newNode2=new MyThreeWayBTreeNode(node.getKeyPos(1));
	    		for(int i=0;i<node.getChildrenSize();i++) {
		    		if(node.getChildren(i).getKeyPos(0)>removeF)
		    			newNode2.setChilderen(node.getChildren(i));
		    	}
	    	node.getParent().removeChild(childPos);
	    	node.getParent().setChilderen(newNode1);
    		
    		node.getParent().setChilderen(newNode2);
	    	/*
	    		if(childPos==0) {
	    			node.getParent().removeChild(childPos);
	    		node.getParent().setChilderen(newNode1,0);
	    		
	    		node.getParent().setChilderen(newNode2,1);
	    		}
	    		else if(childPos==node.getParent().getChildrenSize()-1) {
	    			
	    			node.getParent().removeChild(childPos);
		    		node.getParent().setChilderen(newNode1,node.getParent().getChildrenSize());
		    	
		    		node.getParent().setChilderen(newNode2,node.getParent().getChildrenSize());
		    	
		    		}
	    	
	    		else {
	    			System.out.println("middle!");
	    			node.getParent().removeChild(childPos);
	    			node.getParent().setChilderen(newNode1,1);
	    			node.getParent().setChilderen(newNode2,2);
	    		}*/
	    		/*if(node.getParent().getKeyList().size()+1<node.getParent().getChildrenSize()) {
	    			System.out.println("miss child Pos: "+childPos);
	    			System.out.println("nodeParent: "+node.getParent().getKeyList());
	    			for(int i=0;i<node.getParent().getChildrenSize();i++)
	    			System.out.println("node: "+node.getParent().getChildren(i).getKeyList());
	    		}*/
	    		
		    	if(node.getParent().getKeyList().size()>=3) {
		    		
		    		removeMaxium(node.getParent(),removeF);
		    	}
		    }
	       
	        
	}
	public MyThreeWayBTreeNode findRemoveNode(int e) {
		MyThreeWayBTreeNode node=root;
	
	if(node.contains(e)==true)return root;
		
		while(true) {
		   
			
			if(node.contains(e)==true) return node;
			if(node.getChildrenSize()==0)return node;
		
			if(node.getKeyList().size()==1) {
				if(node.getKeyPos(0)>e) {
					node=node.getChildren(0);
				}
				else {
					node=node.getChildren(1);
				}
			}
			else {
				
				if(node.getKeyPos(0)>e) {
					node=node.getChildren(0);
				}
				else if(node.getKeyPos(1)<e){
					node=node.getChildren(2);
				}
				else {
					node=node.getChildren(1);
				}
			}
			
			
		}
			
	}
		

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		int childPos=0;
		int keyPos=0;
		int e=(int)o;
		
		
		MyThreeWayBTreeNode T=findRemoveNode(e);
	
	/*	if(root!=null) {
			System.out.println("root: "+ root.getKeyList());
			for(int i=0;i<root.getChildrenSize();i++) {
				
				System.out.print("i: "+i+root.getChildren(i).getKeyList()+" " );
			}
			System.out.println("");
			for(int i=0;i<root.getChildrenSize();i++) {
				for(int j=0;j<root.getChildren(i).getChildrenSize();j++) {
				
					System.out.print("j: "+i+root.getChildren(i).getChildren(j).getKeyList()+" ");
				}
			}
			System.out.println("");
			for(int i=0;i<root.getChildrenSize();i++) {
				for(int j=0;j<root.getChildren(i).getChildrenSize();j++) {
					for(int k=0;k<root.getChildren(i).getChildren(j).getChildrenSize();k++)
				
					System.out.print("k: "+k+root.getChildren(i).getChildren(j).getChildren(k).getKeyList()+" ");
				}
			}
			System.out.println("");
					
			
			
		}*/
		
		if(T.getParent()!=null) {
		for(int i=0;i<T.getParent().getChildrenSize();i++) {
    		if(T==T.getParent().getChildren(i)) {
    			childPos=i;
    			break;
    		}
    	}
		}
		for(int i=0;i<T.getKeyList().size();i++) {
			if(T.getKeyPos(i)==e) {
				keyPos=i;
			}
		}
	
		if(T.getKeyList().size()>=2) {
			T.getKeyList().remove(keyPos);
			root.setNullParent();
			return true;
		}
		//System.out.println("TT"+T.getKeyList());
		if(T.getChildrenSize()==0) {
			if(T==root) {
				T.getKeyList().remove(keyPos);
				return true;
			}
			T.getKeyList().remove(keyPos);
			leafRemove(T,childPos);
			root.setNullParent();
		return true;	
		}
		else {
			
			T.getKeyList().remove(keyPos);
			internalRemove(T,keyPos);
			root.setNullParent();
			return true;	
			
		}
	
	
		
		
		
	}
	public void leafRemove(MyThreeWayBTreeNode t,int childPos) {
		if(t.getKeyList().size()>=1) {
			return ;
		}
		else {
			if(t.getChildrenSize()!=0) {
				System.out.println("Miss t: "+t.getKeyList());
				for(int i=0;i<t.getChildrenSize();i++) {
					System.out.println("Miss t child: "+t.getChildren(i).getKeyList());
				}
			}
			
			if(t.getParent().getKeyList().size()==2) {
			if(childPos==1) {
				if(t.getParent().getChildren(0).getKeyList().size()>=2) {
					t.getParent().addKey(t.getParent().getChildren(0).getKeyPos(1));
					t.getParent().getChildren(0).getKeyList().remove(1);
					t.addKey(t.getParent().getKeyPos(1));
					t.getParent().getKeyList().remove(1);
				
					return;
				}
				else if(t.getParent().getChildren(2).getKeyList().size()>=2) {
					t.getParent().addKey(t.getParent().getChildren(2).getKeyPos(0));
					t.getParent().getChildren(2).getKeyList().remove(0);
					t.addKey(t.getParent().getKeyPos(1));
					t.getParent().getKeyList().remove(1);
					return;
					
				}
				else {
					t.getParent().getChildren(1).addKey(t.getParent().getKeyPos(0));
					t.getParent().getKeyList().remove(0);
					t.getParent().getChildren(1).addKey(t.getParent().getChildren(0).getKeyPos(0));
					

				
					parentNode=t.getParent();
					if(parentNode.getParent()==null) {
						t.getParent().removeChild(0);
							return ;
					
					}
					for(int i=0;i<parentNode.getParent().getChildrenSize();i++) {
			    		if(parentNode==parentNode.getParent().getChildren(i)) {
			    			childPos=i;
			    			break;
			    		}
			    	}
					t.getParent().removeChild(0);
				    
					leafRemove(parentNode,childPos);
					
					return;
				}
			}
			else if(childPos==0) {
				if(t.getParent().getChildren(1).getKeyList().size()>=2) {
					t.getParent().addKey(t.getParent().getChildren(1).getKeyPos(0));
					t.getParent().getChildren(1).getKeyList().remove(0);
					t.addKey(t.getParent().getKeyPos(0));
					t.getParent().getKeyList().remove(0);
					return;
					
				}
				else {
					t.getParent().getChildren(1).addKey(t.getParent().getKeyPos(0));
					t.getParent().getKeyList().remove(0);
					parentNode=t.getParent();
					
					if(parentNode.getParent()==null) {
						t.getParent().removeChild(0); 
						return ;
					
					}
					for(int i=0;i<parentNode.getParent().getChildrenSize();i++) {
			    		if(parentNode==parentNode.getParent().getChildren(i)) {
			    			childPos=i;
			    			break;
			    		}
			    	}
					t.getParent().removeChild(0);
					leafRemove(parentNode,childPos);
					
					return;
				}
				
			}
			else {
				if(t.getParent().getChildren(1).getKeyList().size()>=2) {
					t.getParent().addKey(t.getParent().getChildren(1).getKeyPos(1));
					t.getParent().getChildren(1).getKeyList().remove(1);
					t.addKey(t.getParent().getKeyPos(2));
					t.getParent().getKeyList().remove(2);
					return;
					
				}
				else {
					t.getParent().getChildren(1).addKey(t.getParent().getKeyPos(1));
					t.getParent().getKeyList().remove(1);
					parentNode=t.getParent();
					
					if(parentNode.getParent()==null) {
						t.getParent().removeChild(2);
							return ;
					
					}
					for(int i=0;i<parentNode.getParent().getChildrenSize();i++) {
			    		if(parentNode==parentNode.getParent().getChildren(i)) {
			    			childPos=i;
			    			break;
			    		}
			    	}
					t.getParent().removeChild(2);
					leafRemove(parentNode,childPos);
					
					return;
				}
				
			}
			
		}
			//keysize=1일때
			else {
				if(childPos==0) {
				
					if(t.getParent().getChildrenSize()==1) {
						System.out.println("RMiss t: "+t.getKeyList());
						for(int i=0;i<t.getChildrenSize();i++) {
							System.out.println("RMiss t child: "+t.getChildren(i).getKeyList());
						}
					}
					if(t.getParent().getChildren(1).getKeyList().size()>=2) {
						t.getParent().addKey(t.getParent().getChildren(1).getKeyPos(0));
						t.getParent().getChildren(1).getKeyList().remove(0);
						t.addKey(t.getParent().getKeyPos(0));
						t.getParent().getKeyList().remove(0);
						if(t.getParent().getChildren(1).getKeyList().size()+1!=t.getParent().getChildren(1).getChildrenSize()&&t.getParent().getChildren(1).getChildrenSize()>0) {
							t.setChilderen(t.getParent().getChildren(1).getChildren(0));
							t.getParent().getChildren(1).removeChild(0);
						}
						return;
						
					}
					else {
					
				
						t.getParent().getChildren(1).addKey(t.getParent().getKeyPos(0));
						t.getParent().getKeyList().remove(0);
						if(t.getChildrenSize()!=0) {
							t.getParent().getChildren(1).setChilderen(t.getChildren(0));
						}
						parentNode=t.getParent();
						
						
						if(parentNode.getParent()==null) {
							
							 t.getParent().removeChild(0);
							 root=t.getParent().getChildren(0);
							
							
							return ;
						
						}
						for(int i=0;i<parentNode.getParent().getChildrenSize();i++) {
				    		if(parentNode==parentNode.getParent().getChildren(i)) {
				    			childPos=i;
				    			break;
				    		}
				    	}
						
						 t.getParent().removeChild(0);
						leafRemove(parentNode,childPos);
						
						
					}
					
					
				}
				else {
					if(t.getParent().getChildren(0).getKeyList().size()>=2) {
						t.getParent().addKey(t.getParent().getChildren(1).getKeyPos(1));
						t.getParent().getChildren(1).getKeyList().remove(1);
						t.addKey(t.getParent().getKeyPos(2));
						t.getParent().getKeyList().remove(2);
						if(t.getParent().getChildren(0).getKeyList().size()+1!=t.getParent().getChildren(0).getChildrenSize()&&t.getParent().getChildren(0).getChildrenSize()>0) {
							t.setChilderen(t.getParent().getChildren(0).getChildren(2));
							t.getParent().getChildren(0).removeChild(2);
						}
						return;
						
					}
					else {
						//System.out.println("Root"+root.getKeyList());
						//System.out.println("Root"+t.getKeyList());
						if(t==root) {
							
							return;
						
						}
						t.getParent().getChildren(0).addKey(t.getParent().getKeyPos(0));
						t.getParent().getKeyList().remove(0);
						parentNode=t.getParent();
						//System.out.println("parent"+t.getParent().getKeyList());
						if(t.getChildrenSize()!=0) {
							
							t.getParent().getChildren(0).setChilderen(t.getChildren(0));
						}
						
						if(parentNode.getParent()==null) {
							
							
							t.getParent().removeChild(1);
							root=t.getParent().getChildren(0);
								return ;
						
						}
						for(int i=0;i<parentNode.getParent().getChildrenSize();i++) {
				    		if(parentNode==parentNode.getParent().getChildren(i)) {
				    			childPos=i;
				    			break;
				    		}
				    	}
						t.getParent().removeChild(1);
						leafRemove(parentNode,childPos);
						
						
					}
					
				}
				
			}
		}
	}
	public void internalRemove(MyThreeWayBTreeNode t,int keyPos) {
		
		
		if(keyPos==0) {
		
			if(t.getChildren(0).getKeyList().size()>=2) {
				t.addKey(t.getChildren(0).getKeyPos(1));
				t.getChildren(0).getKeyList().remove(1);
				return;
				}
			else if(t.getChildren(1).getKeyList().size()>=2) {
			t.addKey(t.getChildren(1).getKeyPos(0));
			t.getChildren(1).getKeyList().remove(0);
			return;
			}
			else {
				
				t.addKey(t.getChildren(1).getKeyPos(0));
				t.getChildren(1).getKeyList().remove(0);
				this.leafRemove(t.getChildren(1), 1);
				return ;
			}
		}
		else if(keyPos==1) {
			if(t.getChildren(1).getKeyList().size()>=2) {
				t.addKey(t.getChildren(1).getKeyPos(1));
				t.getChildren(1).getKeyList().remove(1);
				return;
				}
			else if(t.getChildren(2).getKeyList().size()>=2) {
			t.addKey(t.getChildren(2).getKeyPos(0));
			t.getChildren(2).getKeyList().remove(0);
			return;
			}
			else {
				t.addKey(t.getChildren(2).getKeyPos(0));
				t.getChildren(2).getKeyList().remove(0);
				this.leafRemove(t.getChildren(2), 2);
				return;
			}
		}
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

	@Override
	public Integer lower(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer floor(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer ceiling(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer higher(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer pollFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer pollLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		values=new ArrayList<Integer>();
		inOrder(root);
		
		return values.iterator();
	}
	
	public void inOrder(MyThreeWayBTreeNode node) {
		if(node.getChildrenSize()==0) {
			for(int i=0;i<node.getKeyList().size();i++) {
			values.add(node.getKeyPos(i));
		
			
			}
			
		
			return;
		}
		
		else if(node.getChildrenSize()==2) {
				inOrder(node.getChildren(0));
				values.add(node.getKeyPos(0));
				inOrder(node.getChildren(1));
			}
		else if(node.getChildrenSize()==3) {
				
				inOrder(node.getChildren(0));
				values.add(node.getKeyPos(0));
			
				inOrder(node.getChildren(1));
				values.add(node.getKeyPos(1));
				
				inOrder(node.getChildren(2));
			}
		
			
		return;
		
		
		
		
	}

	@Override
	public NavigableSet<Integer> descendingSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> descendingIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> subSet(Integer fromElement, boolean fromInclusive, Integer toElement,
			boolean toInclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> headSet(Integer toElement, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> tailSet(Integer fromElement, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> subSet(Integer fromElement, Integer toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> headSet(Integer toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> tailSet(Integer fromElement) {
		// TODO Auto-generated method stub
		return null;
	}
	


}

