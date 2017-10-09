package org.znlccy.panel.local;

import javax.swing.JPanel;

import org.znlccy.window.FTP_Client_Frame;

public class LocalPanel extends JPanel {

	/**
	 * 创建FTP传输主界面
	 */
	private FTP_Client_Frame frame;
	
	/**
	 * 创建默认无参构造函数
	 */
	public LocalPanel() {

	}

	/**
	 * 创建带有参数的构造函数
	 * @param client_Frame
	 */
	public LocalPanel(FTP_Client_Frame client_Frame) {
		super();
		this.frame = client_Frame;
	}
	
}
