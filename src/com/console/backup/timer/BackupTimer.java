package com.console.backup.timer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.console.ConsoleHelper;
import com.console.backup.command.DBBackupEdit;
import com.console.unit.DBBackupUnit;


public class BackupTimer {

	public void backup() {
		try {

			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(sdf.format(new Date()) + "*********每日凌晨自动备份");
			String dburl = DBBackupUnit.dbbackup();
			if (!dburl.equals("")) {
				String[] dburlstr = dburl.split(",");
				String filename = dburlstr[1];
				String fileurl = dburlstr[0] + "/" + dburlstr[1];
				DBBackupEdit bedit=new DBBackupEdit();
				bedit.setDb_time(new Date());
				bedit.setDb_bskcupName(filename);
				bedit.setDb_bskcpuUrl(fileurl);
				ConsoleHelper.getInstance().getBackupService().save(bedit);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
