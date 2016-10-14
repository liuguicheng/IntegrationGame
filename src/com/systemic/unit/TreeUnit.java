package com.systemic.unit;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class TreeUnit {

	
	private static List<TreeModel> returnList = new ArrayList<TreeModel>();
    
    /**
	 * @return the returnList
	 */
	public static List<TreeModel> getReturnList() {
		return returnList;
	}

	/**
     * ���ݸ��ڵ��ID��ȡ�����ӽڵ�
     * @param list �����
     * @param typeId ����ĸ��ڵ�ID
     * @return String
     */ 
    public static List<TreeModel> getChildNodes(List<TreeModel> list, String typeId) {
        if(list == null || typeId == null) return null;
        for (Iterator<TreeModel> iterator = list.iterator(); iterator.hasNext();) {
        	TreeModel node = (TreeModel) iterator.next();
            // һ�����ݴ����ĳ�����ڵ�ID,�����ø��ڵ�������ӽڵ�
            if (typeId.equals(node.getId())) {
                recursionFn(list, node);
            }
            // �����������еĸ��ڵ��µ������ӽڵ�
            /*if (node.getParentId()==0) {
                recursionFn(list, node);
            }*/
        }
        return returnList;
    }
     
    private static void recursionFn(List<TreeModel> list, TreeModel node) {
        List<TreeModel> childList = getChildList(list, node);// �õ��ӽڵ��б�
        if (hasChild(list, node)) {// �ж��Ƿ����ӽڵ�
            returnList.add(node);
            Iterator<TreeModel> it = childList.iterator();
            while (it.hasNext()) {
            	TreeModel n = (TreeModel) it.next();
                recursionFn(list, n);
            }
        } else {
            returnList.add(node);
        }
    }
     
    // �õ��ӽڵ��б�
    private static List<TreeModel> getChildList(List<TreeModel> list, TreeModel node) {
        List<TreeModel> nodeList = new ArrayList<TreeModel>();
        Iterator<TreeModel> it = list.iterator();
        while (it.hasNext()) {
        	TreeModel n = (TreeModel) it.next();
        	if(n.getPid()!=null&&!"".equals(n.getPid())){
        		if(node.getId()!=null&&!"".equals(node.getId())){
        			if (n.getPid().equals(node.getId())) {
                        nodeList.add(n);
                    }
        		}
        		
        	}
            
        }
        return nodeList;
    }
 
    // �ж��Ƿ����ӽڵ�
    private static boolean hasChild(List<TreeModel> list, TreeModel node) {
        return getChildList(list, node).size() > 0 ? true : false;
    }
    
 // ����ģ�����ݲ���
    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
//        List<TreeModel> nodeList = new ArrayList<TreeModel>();
//        TreeModel node1 = new TreeModel(String.valueOf(1), "�߲�", String.valueOf(0));
//        TreeModel node2 = new TreeModel(String.valueOf(2), "ˮ��", String.valueOf(0));
//        TreeModel node3 = new TreeModel(String.valueOf(3), "����", String.valueOf(0));
//        TreeModel node4 = new TreeModel(String.valueOf(4), "����", String.valueOf(1));
//        TreeModel node5 = new TreeModel(String.valueOf(5), "Ҷ��", String.valueOf(1));
//        TreeModel node6 = new TreeModel(String.valueOf(6), "˿��", String.valueOf(4));
//        TreeModel node7 = new TreeModel(String.valueOf(7), "�ƹ�", String.valueOf(4));
//        TreeModel node8 = new TreeModel(String.valueOf(8), "�ײ�", String.valueOf(1));
//        TreeModel node9 = new TreeModel(String.valueOf(9), "Ϻ", String.valueOf(10));
//        TreeModel node10 = new TreeModel(String.valueOf(10), "��", String.valueOf(2));
//        TreeModel node11 = new TreeModel(String.valueOf(11), "ţ", String.valueOf(3));
//         
//        nodeList.add(node1);
//        nodeList.add(node2);
//        nodeList.add(node3);
//        nodeList.add(node4);
//        nodeList.add(node5);
//        nodeList.add(node6);
//        nodeList.add(node7);
//        nodeList.add(node8);
//        nodeList.add(node9);
//        nodeList.add(node10);
//        nodeList.add(node11);
//         
//        List<TreeModel> reemodellist= getChildNodes(nodeList,"1");
//        for (TreeModel treeModel : reemodellist) {
//			System.out.println("id:"+treeModel.getId()+"-"+"��id:"+treeModel.getPid()+"-"+"name:"+treeModel.getName());
//		}
//        long end = System.currentTimeMillis();
//        System.out.println("��ʱ:" + (end - start) + "ms");
    	
    	
    }

}
