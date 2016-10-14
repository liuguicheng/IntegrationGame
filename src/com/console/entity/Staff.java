package com.console.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.console.ConsoleHelper;

public class Staff implements Serializable {
    /**
     * ����id
     */
    private static final long serialVersionUID = -8024987555656772049L;

    /**
     * ���ֵ��ʶ
     */
    public static final String SIMPLE_DIC_IDENTIFICATION = "dicStaff";

    /** Ա�����. */
    private String id;
    /** ����. */
    private String name;
    /** ��¼����. */
    private String loginName;
    /** ����. */
    private String password;
    /** �绰. */
    private String phone;
    /** ����. */
    private String fax;
    /** �ֻ�. */
    private String mobile;
    /** ���Ӻ���. */
    private String email;
    /** ע������. */
    private Date regDate;
    /** �Ƿ���Ч. */
    private String valid;
    /** ��ע. */
    private String description;
    /** �ֻ���. */
    private String extensionNumber;
    /** ϵͳģ��. */
    private String sysTemplate;
    /** ��������. */
    private Department department;
    /**��Ա����*/
    private String staffType;
    
    /**
     * ��ְʱ��
     */
    private Date inDate;
    
    /**
     * ��ְʱ��
     */
    private Date  exitDate;
    
    private String idNum;
    
    /**
     * �Ա�
     */
    private String sex;
    
    /**
     * ����
     */
    private String roomNum;
    
    public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	//    /** ְ�� */
   private String duty;

    /** ��Ա�������е�ϵͳȨ�ޱ�ż�. */
    private Set powerIdSet;

    /** �������ϵͳ��ɫ��. */
    private Set systemRole;

    /**˳���*/
    private Integer orderNumber;
//
//    private Set<String> workQueue;
   private String isLogin;//�������
   private String memberId;
   /**2016/1/25*/
   /**������ò*/
   private String politics;
   /**�Ļ��̶�*/
   private String education;
   /**��������*/
   private Date birth;
   /**��������*/
   private String treatmentLevel;
   /**�뵳ʱ��*/
   private Date partyDate;
   /**���ʱ��*/
   private Date editDate;
   /**����ʱ��*/
   private Date registerDate;
   /**������ʱ��*/
   private String tryDate;
   /**ת������ʱ��*/
   private Date levelDate;
   /**ת��/����ʱ��*/
   private Date transferDate;
   /**����ְʱ��*/
   private Date currentDated;
   /**����ְ�ĺ�*/
   private String currentNum;
   /**��ȿ�����Ϣ*/
   private String yearGrade;
   /**����ʱ��*/
   private Date retireDate;
   /**����ʱְ�񼶱�*/
   private String retireDutyLevel;
   /**��ͬ����*/
   private String contractPeriod;
   /**�α���� */
   private String insured;
   /**Ƹ�ø�λ����������/���ڣ�*/
   private String hireDuty;
   /**¼��ʱ��*/
   private Date hireDate;
   
	public String getPolitics() {
    return politics;
}

public void setPolitics(String politics) {
    this.politics = politics;
}

public String getEducation() {
    return education;
}

public void setEducation(String education) {
    this.education = education;
}

public Date getBirth() {
    return birth;
}

public void setBirth(Date birth) {
    this.birth = birth;
}

public String getTreatmentLevel() {
    return treatmentLevel;
}

public void setTreatmentLevel(String treatmentLevel) {
    this.treatmentLevel = treatmentLevel;
}

public Date getPartyDate() {
    return partyDate;
}

public void setPartyDate(Date partyDate) {
    this.partyDate = partyDate;
}

public Date getEditDate() {
    return editDate;
}

public void setEditDate(Date editDate) {
    this.editDate = editDate;
}

public Date getRegisterDate() {
    return registerDate;
}

public void setRegisterDate(Date registerDate) {
    this.registerDate = registerDate;
}

public String getTryDate() {
    return tryDate;
}

public void setTryDate(String tryDate) {
    this.tryDate = tryDate;
}

public Date getLevelDate() {
    return levelDate;
}

public void setLevelDate(Date levelDate) {
    this.levelDate = levelDate;
}

public Date getTransferDate() {
    return transferDate;
}

public void setTransferDate(Date transferDate) {
    this.transferDate = transferDate;
}

public Date getCurrentDated() {
    return currentDated;
}

public void setCurrentDated(Date currentDated) {
    this.currentDated = currentDated;
}

public String getCurrentNum() {
    return currentNum;
}

public void setCurrentNum(String currentNum) {
    this.currentNum = currentNum;
}

public String getYearGrade() {
    return yearGrade;
}

public void setYearGrade(String yearGrade) {
    this.yearGrade = yearGrade;
}

public Date getRetireDate() {
    return retireDate;
}

public void setRetireDate(Date retireDate) {
    this.retireDate = retireDate;
}

public String getRetireDutyLevel() {
    return retireDutyLevel;
}

public void setRetireDutyLevel(String retireDutyLevel) {
    this.retireDutyLevel = retireDutyLevel;
}

public String getContractPeriod() {
    return contractPeriod;
}

public void setContractPeriod(String contractPeriod) {
    this.contractPeriod = contractPeriod;
}

public String getInsured() {
    return insured;
}

public void setInsured(String insured) {
    this.insured = insured;
}

public String getHireDuty() {
    return hireDuty;
}

public void setHireDuty(String hireDuty) {
    this.hireDuty = hireDuty;
}

public Date getHireDate() {
    return hireDate;
}

public void setHireDate(Date hireDate) {
    this.hireDate = hireDate;
}

    public String getMemberId() {
	return memberId;
}

public void setMemberId(String memberId) {
	this.memberId = memberId;
}

	/**
     * �չ��캯��
     */
    public Staff() {
    }

	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	public String getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Date getExitDate() {
		return exitDate;
	}

	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	/**
     * ���캯��
     * @param id ��Ա���
     */
    public Staff(String id) {
        this.id = id;
    }

    /**
     * @return �����ĵ�λ�ڵ㣬ͨ��Department.Code������������������
     */
    public Department getBelong() {
        Department dep = getDepartment();
        while (dep != null) {
            if (dep.getCode() != null && dep.getCode().trim().length() > 0) {
                return dep;
            } else {
                dep = ConsoleHelper.getDepartment(dep.getParentId());
            }
        }
        return null;
    }
    
    /**
     * @return ��������������
     */
    public String getBelongCode() {
        Department dep =  getBelong();
        if (dep != null) {
            return cleanCode(dep.getCode());
        }
        return "";
    }
    /**
     * ���������������е���Ч�ַ�
     * @param divisionCode
     * @return ��������������
     */
    private final String cleanCode(String divisionCode) {
        if (divisionCode == null || divisionCode.trim().length() == 0) {
            return "";
        }
        divisionCode = divisionCode.trim();
        int length = divisionCode.length();
        if (length == 6) { //ֻ����λ����ģ����в������
            for (int i = length - 1; i >=0; i--) {
                if (divisionCode.charAt(i) == '0') {
                    length--;
                } else {
                    break;
                }
            }
        }
        return divisionCode.substring(0, length);
    }
    /**
     * @hibernate.many-to-one column = "dep_id" not-null = "true"
     * @return Returns the department.
     */
    public Department getDepartment() {
        return this.department;
    }

    /**
     * @param department The department to set.
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

  

    public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	/**
     * @hibernate.property column = "description" length = "100" not-null = "false"
     * @return Returns the description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * @hibernate.property column = "email" length = "60" not-null = "false"
     * @return Returns the email.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @hibernate.property column = "extension_number" length = "10" not-null = "false"
     * @return Returns the extensionNumber.
     */
    public String getExtensionNumber() {
        return this.extensionNumber;
    }

    /**
     * @param extensionNumber The extensionNumber to set.
     */
    public void setExtensionNumber(String extensionNumber) {
        this.extensionNumber = extensionNumber;
    }

    /**
     * @hibernate.property column = "fax" length = "40" not-null = "false"
     * @return Returns the fax.
     */
    public String getFax() {
        return this.fax;
    }

    /**
     * @param fax The fax to set.
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @hibernate.id column = "staff_id" generator-class = "native"
     * @return Returns the id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @hibernate.property column = "login_name" length = "40" not-null = "true" unique = "true"
     * @return Returns the loginName.
     */
    public String getLoginName() {
        return this.loginName;
    }

    /**
     * @param loginName The loginName to set.
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * @hibernate.property column = "mobile_phone" length = "40" not-null = "false"
     * @return Returns the mobile.
     */
    public String getMobile() {
        return this.mobile;
    }

    /**
     * @param mobile The mobile to set.
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @hibernate.property column = "staff_name" length = "20" not-null = "true"
     * @return Returns the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @hibernate.property column = "password" length = "40" not-null = "true"
     * @return Returns the password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @hibernate.property column = "phone" length = "40" not-null = "false"
     * @return Returns the phone.
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * @param phone The phone to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @hibernate.property column = "reg_date" not-null = "false"
     * @return Returns the regDate.
     */
    public Date getRegDate() {
        return this.regDate;
    }



    /**
     * @param regDate The regDate to set.
     */
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    /**
     * @hibernate.property column = "valid" length = "1" not-null = "true"
     * @return Returns the valid.
     */
    public String getValid() {
        return this.valid;
    }

    /**
     * @param valid The valid to set.
     */
    public void setValid(String valid) {
        this.valid = valid;
    }

//    /**
//     * @hibernate.set table = "cg_sys_staff_flow_role_rel" cascade = "none"
//     *                inverse = "false" lazy = "true" order-by = "flow_role_name asc"
//     * @hibernate.collection-key column = "staff_id"
//     * @hibernate.collection-many-to-many class = "com.spower.basesystem.manage.valueobject.FlowRole"
//     *                column = "flow_role_name"
//     * @return Returns the flowRole.
//     */
//    public Set getFlowRole() {
//        return this.flowRole;
//    }
//
//    /**
//     * @param flowRole The flowRole to set.
//     */
//    public void setFlowRole(Set flowRole) {
//        this.flowRole = flowRole;
//    }

    /**
     * @hibernate.set table = "cg_sys_staff_role_rel" cascade = "none"
     *                inverse = "false" lazy = "true" order-by = "role_id asc"
     * @hibernate.collection-key column = "staff_id"
     * @hibernate.collection-many-to-many class = "com.spower.basesystem.manage.valueobject.Role"
     *                column = "role_id"
     * @return Returns the systemRole.
     */
    public Set getSystemRole() {
        return this.systemRole;
    }

    /**
     * @param systemRole The systemRole to set.
     */
    public void setSystemRole(Set systemRole) {
        this.systemRole = systemRole;
    }

    /**
     * ����Ա�����е�Ȩ��.
     * @param powerId   Ȩ�ޱ��
     */
    public void addPowerId(String powerId) {
        if (this.powerIdSet == null) {
            this.powerIdSet = new HashSet();
        }
        if (this.powerIdSet.add(powerId)) {
            String temp = powerId;
            while (temp.length() > Power.LEVEL_LENGTH) {
                temp = temp.substring(0, temp.length() - Power.LEVEL_LENGTH);
                if (!this.powerIdSet.add(temp)) {
                    break;
                }
            }
        }
    }

    /**
     * �ж�Ա���Ƿ����Ȩ�ޱ����ʾ��Ȩ��.
     * @param powerId   Ȩ�ޱ��
     * @return  ����Ȩ�޷���true�����򷵻�false
     */
    public boolean hasPower(String powerId) {
        return this.powerIdSet != null && this.powerIdSet.contains(powerId);
    }

    /** �ж�Ա���Ƿ���� ҳ�����Ȩ�ޱ��� ��Ӧ��Ȩ��.
     * @param opCode
     * @return  ����Ȩ�޷���true�����򷵻�false
     */
    public boolean hasOperate(String opCode) {
        Set operateCodes = new HashSet();
        Iterator iter = getSystemRole().iterator();
        while(iter.hasNext()){
            Role role = (Role) iter.next();
            Iterator opIterator = role.getOperates().iterator();
            while(opIterator.hasNext()){
                Operate op = (Operate) opIterator.next();
                operateCodes.add(op.getOperateCode());
            }
        }

        return operateCodes.contains(opCode);
    }
    /**
     * �ж��Ƿ�������Ա������Ȩ��
     * @return  true-->������Ȩ�ޣ�false-->û���κ���Ȩ
     */
    public boolean isAnyPower() {
        return this.powerIdSet != null && this.powerIdSet.size() > 0;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Staff) {
            if (this.id != null) {
                return this.id.equals(((Staff) obj).getId());
            }
        }
        return false;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return this.id.hashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "[��ţ�" + this.id + " ������" + this.name + "]";
    }

    /**
     * @return ���� orderNumber��
     */
    public Integer getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber Ҫ���õ� orderNumber��
     */
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return ���� sysTemplate��
     */
    public String getSysTemplate() {
        return sysTemplate;
    }

    /**
     * @param sysTemplate Ҫ���õ� sysTemplate��
     */
    public void setSysTemplate(String sysTemplate) {
        this.sysTemplate = sysTemplate;
    }


    /** �ж��û��Ƿ�ӵ��ĳ����ɫ
     * @param roleName
     * @return
     */
    public boolean hasRole(String roleName) {
        if (this.systemRole != null) {
            for (Iterator it = this.systemRole.iterator(); it.hasNext();) {
                Role role = (Role) it.next();
                if (role.getName().equals(roleName)) {
                    return true;
                }
            }
        }
        return false;
    }

	

//    /**
//     * @return the workQueue
//     */
//    public Set<String> getWorkQueue() {
//        return workQueue;
//    }
//
//    /**
//     * @param workQueue the workQueue to set
//     */
//    public void setWorkQueue(Set<String> workQueue) {
//        this.workQueue = workQueue;
//    }

}
