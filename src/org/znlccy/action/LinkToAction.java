package org.znlccy.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import org.znlccy.panel.manager.FTPLinkDialog;
import org.znlccy.window.FTP_Client_Frame;

public class LinkToAction extends AbstractAction {
	
	/**
	 * 创建FTP主界面类
	 */
	private FTP_Client_Frame client_Frame;
	
	/**
	 * 创建带有参数的构造函数
	 * @param frame
	 * @param string
	 * @param icon
	 */
	public LinkToAction(FTP_Client_Frame frame, String string, Icon icon) {
		// TODO Auto-generated constructor stub
		super(string,icon);
		/*初始化主界面*/
		client_Frame = frame;
	}
	
	/* (non-Javadoc)
	 * 创建连接动作的事件监听机制
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		FTPLinkDialog dialog = new FTPLinkDialog(this.client_Frame);
	}
}
