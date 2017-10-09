package org.znlccy.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import org.znlccy.window.FTP_Client_Frame;

public class CutLinkAction extends AbstractAction {
	
	/**
	 * 创建FTP传输的主面板
	 */
	private FTP_Client_Frame frame;
	
	/**
	 * 创建带有参数的构造函数
	 * @param frame
	 * @param string
	 * @param icon
	 */
	public CutLinkAction(FTP_Client_Frame client_Frame, String string, Icon icon) {
		// TODO Auto-generated constructor stub
		super(string, icon);
		/*初始化窗体*/
		frame = client_Frame;
		/*设置是否可用*/
		setEnabled(false);
	}
	
	/* (non-Javadoc)
	 * 创建断开连接的事件监听机制
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
