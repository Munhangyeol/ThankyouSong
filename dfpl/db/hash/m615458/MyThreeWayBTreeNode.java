package org.dfpl.db.hash.m615458;

import java.util.ArrayList;

//package 이름은 org.dfpl.db.hash.m학번 입니다. 
//지키지 않을 시 반려합니다. 
import java.util.List;
@SuppressWarnings("unused")
public class MyThreeWayBTreeNode {

	// Data Abstraction은 예시일 뿐 자유롭게 B-Tree의 범주 안에서 어느정도 수정가능
	private MyThreeWayBTreeNode parent;
	private List<Integer> keyList;
	private List<MyThreeWayBTreeNode> children;
	public MyThreeWayBTreeNode(Integer e) {
		keyList=new ArrayList<Integer>();
		this.children=new ArrayList<MyThreeWayBTreeNode>();
		this.keyList.add(e);
		
	}
	
	public void addKey(int e) {
		this.keyList.add(e);
		keyList.sort(null);
		
		
		
	}
	public void setNullParent() {
		this.parent=null;
	}
	public boolean contains(int e) {
		return this.getKeyList().contains(e);
	}
	public List getKeyList() {
		
		return keyList;
		
	}
	
	public void setParent(MyThreeWayBTreeNode newParent) {
		parent=newParent;
	}
	public void setChilderen(MyThreeWayBTreeNode newNode) {
	
		MyThreeWayBTreeNode tmp;
	
		this.children.add(newNode);
		for (int i=children.size()-1;i>0;i--) {
			if(children.get(i).getKeyPos(0)<children.get(i-1).getKeyPos(children.get(i-1).getKeyList().size()-1)) {
				tmp=children.get(i);
			    children.remove(i);
			    children.add(i-1, tmp);
				
				
			}
		}
	   
		
		
		
		newNode.parent=this;
		
	}
	public void setStaticChilderen(MyThreeWayBTreeNode newNode) {
		
		
		
		this.children.add(newNode);
		
		
		
		newNode.parent=this;
		
	}
	public void removeChild(int i) {
		children.remove(i);
		
	}
	public MyThreeWayBTreeNode getChildren(int i) {
		
		if(children==null)return null;
		if(children.get(i)==null)return null;
		return children.get(i);
		
		
	}

	public MyThreeWayBTreeNode getTreeNode() {
		return this;
	}
public int getChildrenSize() {
		
		return this.children.size();
		
		
	}
public MyThreeWayBTreeNode getParent() {
	if(parent==null)return null;
		return parent;
		
		
	}

	
	
	public Integer getKeyPos(Integer i) {
		return keyList.get(i);
	}

}
