
package com.console.support;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springline.beans.tree.ITreeNode;
import org.springline.beans.tree.support.TreeNodeImpl;

import com.console.entity.Department;
import com.console.entity.Power;
import com.console.entity.Staff;


/**
 * @description
 */
public final class MenuManager {


    /**
     * ȱʡ���췽��.
     */
    private MenuManager() {
        //
    }


    /**
     * �����˵���.
     * @param root          �˵����������
     * @param currentStaff  ��ǰ��¼��Ա��
     * @param dept          ������λ
     * @return  MenuTreeʵ��
     */
    public static ITreeNode buildMenuTree(ITreeNode root, Staff currentStaff, Department dept) {
        ITreeNode result = new TreeNodeImpl(root.getNodeKey(), root.getNodeName(), root.getData());
        result.getChildren().addAll(buildMenuTree(root.getChildren(), currentStaff, dept));

        return result;
    }

    private static List buildMenuTree(List children, Staff currentStaff, Department dept) {
        List list = new ArrayList();
        if (children == null || children.size() == 0) {
            return list;
        }
        for (Iterator it = children.iterator(); it.hasNext();) {
            ITreeNode node = (ITreeNode) it.next();
            Power power = (Power) node.getData();
            ITreeNode tmp = new TreeNodeImpl(node.getNodeKey(), node.getNodeName(), node.getData());
            if (hasPower(power, currentStaff, dept)) {
                list.add(tmp);
            }
            tmp.getChildren().addAll(buildMenuTree(node.getChildren(), currentStaff, dept));
        }
        return list;

    }
    /**�жϵ�ǰ�û��Ƿ�ӵ����ӦȨ��
     * @param power Map of power
     * @param currentUser ��ǰ�û�
     * @param dept �û����ڵ�λ
     * @return true or false
     */
    public static boolean hasPower(Power power, Staff currentUser, Department dept) {
        if (power != null) {
            if (Power.POWER_TYPE_COMMON.equals(power.getTypeFlag())) { //������Ȩ
                return true;
            } else if (Power.POWER_TYPE_ROLE.equals(power.getTypeFlag())) { //��ɫ��Ȩ
                return currentUser.hasPower(power.getCode());
            } else if (Power.POWER_TYPE_DEPT.equals(power.getTypeFlag())) { //������Ȩ
                return  dept.hasPower(power.getCode());
            } else if (Power.POWER_TYPE_ITERATE.equals(power.getTypeFlag())) { //������Ȩ
                return currentUser.hasPower(power.getCode()) && dept.hasPower(power.getCode());
            }
        }
        return false;
    }
    
    /**
     * //��֤����ҳ���Ƿ���Ҫ����
     * @param advancedPassword 
     * @param threePassword
     * @param power
     * @return
     */
    public static boolean isAdvancedPassword(String advancedPassword, String threePassword, Power power) {
		boolean fa=false;
		   if(power.getPasswordLevel()==2){
			 //��֤����ҳ���Ƿ���Ҫ����
			   if(advancedPassword==null||"".equals(advancedPassword)){
				   fa=true;
		    }
		    }
		   if(power.getPasswordLevel()==3){
			   if(threePassword==null||"".equals(threePassword)){
				   fa=true;
			   }
		   }
		   return fa;
	}
}
