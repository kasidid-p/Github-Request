package com.kamontat.controller.menu;

import com.kamontat.constant.HotKey;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;

/**
 * @author kamontat
 * @version 1.0
 * @since 2/8/2017 AD - 10:40 PM
 */
public class MenuBarController extends JMenuBar {
	public MenuBarController(Menu[] lefts, Menu[] rights) {
		if (lefts != null) for (Menu left : lefts) {
			addLeft(left);
		}
		
		if (rights != null) for (Menu right : rights) {
			addRight(right);
		}
	}
	
	private Menu addRight(Menu m) {
		add(Box.createHorizontalGlue());
		return (Menu) super.add(m);
	}
	
	private Menu addLeft(Menu m) {
		return (Menu) super.add(m);
	}
	
	public static class Menu extends JMenu {
		HashMap<String, MenuItem> items;
		
		public Menu(String title) {
			super(title);
			items = new HashMap<String, MenuItem>();
		}
		
		public void addItem(MenuItem menuItem) {
			items.put(menuItem.getText(), menuItem);
			add(menuItem);
		}
		
		public MenuItem getItem(String text) {
			return items.get(text);
		}
		
		public boolean isContain(String text) {
			return items.containsKey(text);
		}
		
		@Override
		public void addSeparator() {
			super.addSeparator();
		}
		
		public static Menu[] toArray(Menu... menus) {
			return menus;
		}
		
		public void addMenuUpdateListener(MenuUpdateListener l) {
			super.addMenuListener(l);
		}
		
		/**
		 * how to use custom menu item
		 * <pre><code>
		 * Menu.addItem(new MenuBarController.Menu.MenuItem(new HotKey("test"), new AbstractAction() {
		 *      public void actionPerformed(ActionEvent e) {
		 *          // some code
		 *      }
		 * }));
		 * </code></pre>
		 */
		public static class MenuItem extends JMenuItem {
			public MenuItem(HotKey key, Action a) {
				super(key.getName());
				addActionListener(a);
				setAccelerator(key.getKeyStroke());
				setToolTipText(key.getDescription());
			}
			
			public static MenuItem getExitMenu() {
				return new MenuItem(HotKey.EXIT, new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
			}
		}
	}
}
