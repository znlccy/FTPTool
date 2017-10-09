package org.znlccy.window;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.znlccy.action.CutLinkAction;
import org.znlccy.action.LinkToAction;
import org.znlccy.extClass.FTPClient;
import org.znlccy.panel.ftp.FTPPanel;
import org.znlccy.panel.local.LocalPanel;
import org.znlccy.panel.queue.DownloadPanel;
import org.znlccy.panel.queue.QueuePanel;
import org.znlccy.panel.queue.UploadPanel;

/**
 * @author ZNLCCY
 * @version 0.0.1
 * @date 2017-10-9
 * @introduce 这是FTP的主窗体
 */
public class FTP_Client_Frame extends JFrame {

	/**
	 * 创建FTP客户端类
	 */
	private FTPClient ftpClient;
	
	/**
	 * 创建FTP的面板类
	 */
	private FTPPanel ftpPanel;
	
	/**
	 * 创建本地面板类
	 */
	private LocalPanel localPanel; 
	
	/**
	 * 创建队列面板
	 */
	private QueuePanel queuePanel;
	
	/**
	 * 创建上传面板
	 */
	private UploadPanel uploadPanel;
	
	/**
	 * 创建下载面板
	 */
	private DownloadPanel downloadPanel;
	
	/**
	 * 创建分割面板
	 */
	private JSplitPane jSplitPane1;
	
	/**
	 * 创建连接按钮
	 */
	private JButton linkButton;
	
	/**
	 * 创建复选框按钮
	 */
	private JCheckBox checkBoxes;
	
	/**
	 * 创建连接动作按钮
	 */
	private final LinkToAction LINK_TO_ACTION;
	
	/**
	 * 创建断开连接动作按钮
	 */
	private final CutLinkAction CUT_LINK_ACTION;
	
	/**
	 * 创建密码文本框
	 */
	private JPasswordField passField;
	
	/**
	 * 创建剪切按钮
	 */
	private JButton cutLinkButton;	
	
	/**
	 * 创建系统托盘
	 */
	private SystemTray systemTray;
	
	/**
	 * 创建端口输入文本框
	 */
	private JTextField portTextField;
	
	/**
	 * 创建服务器输入文本框
	 */
	private JTextField serverTextField;
	
	/**
	 * 创建用户输入文本框
	 */
	private JTextField userTextField;
	
	/**
	 * 创建用户接受的字符串 
	 */
	private static String userStr;
	
	/**
	 * 创建密码接受的字符串
	 */
	private static String passStr;
	
	/**
	 * 创建关闭按钮
	 */
	private JToggleButton shutdownButton;
	
	/**
	 * 创建应用的图标
	 */
	private final ImageIcon icon = new ImageIcon(getClass().getResource("/org/znlccy/ftp/images/trayIcon.png"));
	
	/**
	 * 创建获得关闭按钮的get方法
	 * @return
	 */
	public JToggleButton getShutdownButton() {
		return shutdownButton;
	}

	/**
	 * 启动这个应用
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FTP_Client_Frame frame = new FTP_Client_Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 创建窗体，默认构造函数
	 */
	public FTP_Client_Frame() {
		/*创建连接动作按钮*/
		LINK_TO_ACTION = new LinkToAction(this, "连接到", null);
		/*创建断开连接动作按钮*/
		CUT_LINK_ACTION = new CutLinkAction(this, "断开", null);
		/*初始化组件*/
		initComponent();
		/*初始化系统托盘*/
		initSystemTray();
	}

	/**
	 * 初始化系统托盘
	 */
	private void initSystemTray() {
		// TODO Auto-generated method stub
		/*如果支持系统托盘，显示系统托盘*/
		if(SystemTray.isSupported())
		{
			systemTray = SystemTray.getSystemTray();
		}
		/*获得系统托盘的图标*/
		TrayIcon trayIcon = new TrayIcon(icon.getImage());
		/*创建右击菜单选项*/
		PopupMenu popupMenu = new PopupMenu("TrayMenu");
		
		/*创建显示菜单选项*/
		MenuItem showMenuItem = new MenuItem("Show");
		/*创建显示菜单选项的事件监听机制*/
		showMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				/*设置窗体的扩展状态*/
				FTP_Client_Frame.this.setExtendedState(JFrame.NORMAL);
				/*设置窗体的可见性*/
				FTP_Client_Frame.this.setVisible(true);
			}
		});
		
		/*创建退出菜单选项*/
		MenuItem exitMenuItem = new MenuItem("Exit");
		/*创建退出菜单选项的事件监听机制*/
		exitMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/*设置退出系统*/
				System.exit(0);
			}
		});
		
		/*向右击菜单选项中添加菜单选项*/
		/*添加显示菜单选项*/
		popupMenu.add(showMenuItem);
		/*添加分割线*/
		popupMenu.addSeparator();
		/*添加退出菜单选项*/
		popupMenu.add(exitMenuItem);
		
		/*设置托盘的右击菜单的内容*/
		trayIcon.setPopupMenu(popupMenu);
		
		try {
			systemTray.add(trayIcon);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * 初始化系统组件
	 */
	private void initComponent() {
		// TODO Auto-generated method stub
		/*设置应用程序图标*/
		setIconImage(icon.getImage());
		/*设置默认关闭方式*/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*创建网格包限制组件*/
		GridBagConstraints gridBagConstraints;
		
		/*创建面板*/
		JPanel jPanel1 = new JPanel();
		/*创建工具条*/
		JToolBar jToolBar1 = new JToolBar();
		/*创建连接按钮*/
		JButton linkTo = new JButton();
		/*初始化剪切按钮*/
		cutLinkButton = new JButton();
		/*创建面板4*/
		JPanel jPanel4 = new JPanel();
		/*创建标签1*/
		JLabel jLabel1 = new JLabel();
		/*初始化服务器输入文本框*/
		serverTextField = new JTextField();
		/*创建标签2*/
		JLabel jLabel2 = new JLabel();
		/*创建用户输入文本框*/
		userTextField = new JTextField();
		/*创建标签3*/
		JLabel jLabel3 = new JLabel();
		/*创建匿名标签*/
		JLabel jLabelniming = new JLabel();
		/*初始化密码输入文本框*/
		passField = new JPasswordField();
		/*创建标签6*/
		JLabel jLabel6 = new JLabel();
		/*初始化端口输入文本框*/
		portTextField = new JTextField();
		/*初始化复选框*/
		checkBoxes = new JCheckBox();
		/*初始化连接按钮*/
		linkButton = new JButton();
		/*创建分割面板2*/
		JSplitPane jSplitPane2 = new JSplitPane();
		/*初始化各种面板*/
		ftpPanel = new FTPPanel(this);
		localPanel = new LocalPanel(this);
		uploadPanel = new UploadPanel();
		downloadPanel = new DownloadPanel();
		queuePanel = new QueuePanel(this);
		
		/*创建选项卡面板*/
		JTabbedPane jTabbedPane1 = new JTabbedPane();
		/*创建菜单栏的工具条*/
		JMenuBar MenuBar = new JMenuBar();
		
		/*创建文件菜单*/
		JMenu fileMenu = new JMenu();
		/*创建管理菜单选项*/
		JMenuItem ftpManageMenuItem = new JMenuItem();
		/*创建分割器*/
		JSeparator jSeparator1 = new JSeparator();
		/*创建连接菜单选项*/
		JMenuItem linkToMenuItem = new JMenuItem();
		/*创建断开连接菜单选项*/
		JMenuItem cutMenuItem = new JMenuItem();
		/*创建分割器*/
		JSeparator jSeparator2 = new JSeparator();
		/*创建退出菜单选项*/
		JMenuItem exitMenuItem = new JMenuItem();
		/*创建上传菜单选项*/
		JMenuItem uploadMenuItem = new JMenuItem();
		/*创建分割器*/
		JSeparator jSeparator3 = new JSeparator();
		/*创建文件夹菜单选项*/
		JMenuItem createFolderMenuItem = new JMenuItem();
		/*创建重命名菜单选项*/
		JMenuItem renameMenuItem = new JMenuItem();
		/*创建删除菜单选项*/
		JMenuItem deleteMenuItem = new JMenuItem();
		
		/*创建FTP菜单*/
		JMenu ftpMenu = new JMenu();
		/*创建下载菜单选项*/
		JMenuItem downloadMenuItem = new JMenuItem();
		/*创建分割器*/
		JSeparator jSeparator6 = new JSeparator();
		/*创建FTP删除菜单选项*/
		JMenuItem ftpDeleteMenuItem = new JMenuItem();
		/*创建FTP重命名菜单选项*/
		JMenuItem ftpRenameMenuItem = new JMenuItem();
		/*创建新文件夹菜单选项*/
		JMenuItem newFolderMenuItem = new JMenuItem();
		
		/*创建帮助菜单*/
		JMenu helpMenu = new JMenu();
		/*创建关于菜单选项*/
		JMenuItem aboutMenuItem = new JMenuItem();
		/*创建调试的菜单选项*/
		JMenuItem debugMenuItem = new JMenuItem();
		
		/*设置窗体标题*/
		setTitle("FTP上传下载");
		
		/*添加窗口事件监听*/
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowOpened(e);
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowIconified(e);
			}
		});
		
		/*创建组件事件监听机制*/
		addComponentListener(new ComponentAdapter() {
			
		});
		
		/*设置窗体布局*/
		getContentPane().setLayout(new GridBagLayout());
		/*面板1设置网格布局*/
		jPanel1.setLayout(new GridLayout(0 ,1));
		/*设置鼠标经过的时候是否显示*/
		jToolBar1.setRollover(true);
		/*设置是否可以拖动*/
		jToolBar1.setFloatable(false);
		
		/*设置连接的标题*/
		linkTo.setText("连接到");
		/*设置是否聚焦*/
		linkTo.setFocusable(false);
		/*设置连接的事件动作*/
		linkTo.setAction(LINK_TO_ACTION);
		/*把连接按钮添加到工具条上*/
		jToolBar1.add(linkTo);
		
		/*设置连接的标题*/
		cutLinkButton.setText("断开");
		/*设置开始时是否可用*/
		cutLinkButton.setEnabled(false);
		/*设置是否聚焦*/
		cutLinkButton.setFocusable(false);
		/*设置连接的事件动作*/
		cutLinkButton.setAction(LINK_TO_ACTION);
		/*把连接按钮添加到工具条上*/
		jToolBar1.add(cutLinkButton);
		
		/*把工具条添加到面板上*/
		jPanel1.add(jToolBar1);
		
		/*初始化自动关机按钮*/
		shutdownButton = new JToggleButton();
		shutdownButton.setText("自动关机");
		shutdownButton.setToolTipText("队列完成后，自动关闭计算机");
		shutdownButton.setFocusable(false);
		jToolBar1.add(shutdownButton);
		
		/*设置面板4相关属性*/
		jPanel4.setBorder(BorderFactory.createEtchedBorder());
		jPanel4.setLayout(new BoxLayout(jPanel4, BoxLayout.LINE_AXIS));
		
		/*设置标签1的相关属性*/
		jLabel1.setText("主机地址:");
		jPanel4.add(jLabel1);
		
		/*设置服务器文本框的属性*/
		serverTextField.setText("192.168.0.166");
		serverTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				// TODO Auto-generated method stub
				LinkFTPKeyPressed(evt);
			}
		});
		jPanel4.add(serverTextField);
		
		/*设置标签2的相关属性*/
		jLabel2.setText("用户名:");
		jPanel4.add(jLabel2);
		
		userTextField.setText("admins");
		userTextField.setMaximumSize(new Dimension(200,2147483647));
		userTextField.setPreferredSize(new Dimension(100,21));
		userTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				// TODO Auto-generated method stub
				LinkFTPKeyPressed(evt);
			}
		});
		jPanel4.add(userTextField);
		
		/*设置标签3的相关属性*/
		jLabel3.setText("密码:");
		jPanel4.add(jLabel3);
		
		passField.setText("admins");
		passField.setMaximumSize(new Dimension(200, 2147483647));
		passField.setPreferredSize(new Dimension(100, 21));
		passField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				// TODO Auto-generated method stub
				LinkFTPKeyPressed(evt);
			}
		});
		jPanel4.add(passField);
		
		/*设置标签6的相关属性*/
		jLabel6.setText("端口:");
		jPanel4.add(jLabel6);
		
		portTextField.setText("21");
		portTextField.setMaximumSize(new Dimension(200, 2147483647));
		portTextField.setPreferredSize(new Dimension(50 ,21));
		portTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				// TODO Auto-generated method stub
				LinkFTPKeyPressed(evt);
			}
		});
		jPanel4.add(portTextField);
		
		/*添加匿名按钮*/
		checkBoxes.setBorderPainted(true);
		jPanel4.add(checkBoxes);
		jLabelniming.setText("匿名");
		jPanel4.add(jLabelniming);
		checkBoxes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				checkboxsActionPerformed(evt);
			}
		});
		
		linkButton.setText("连接");
		linkButton.setFocusable(false);
		linkButton.setHorizontalTextPosition(SwingConstants.CENTER);
		linkButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		linkButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				linkButtonActionPerformed(evt);
			}
		});
	}

	private void linkButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		try {
			String server = serverTextField.getText();
			if(server == null)
			{
				return;
			}
			String portStr = portTextField.getText();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 创建复选框事件机制
	 * @param evt
	 */
	private void checkboxsActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if(checkBoxes.isSelected())
		{
			userTextField.setEnabled(false);
			passField.setEnabled(false);
			userTextField.setText("");
			passField.setText("");
			userStr = "Anonymous";
			passStr = "331";
		}
		else
		{
			userTextField.setEnabled(true);
			passField.setEnabled(true);
		}
	}

	/**
	 * 连接FTP键按压回车事件
	 * @param e
	 */
	private void LinkFTPKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		if(evt.getKeyChar() == '\n')
		{
			linkButton.doClick();
		}
	}
	
}
