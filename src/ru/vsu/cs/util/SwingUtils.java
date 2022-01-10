package util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;


/**
 * ����� ������� ��� ������ � �������� ������������ � ������������� Swing
 *
 * @author ������� ��������� (������� ���� ��� ���)
 */
public class SwingUtils {
    /**
     * �������� ���������� ���� � �������������� ����������
     *
     * @param message ���������
     * @param title   ��������� ����
     */
    public static void showInfoMessageBox(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * �������� ���������� ���� � �������������� ����������
     *
     * @param message ���������
     */
    public static void showInfoMessageBox(String message) {
        showInfoMessageBox(message, "���������");
    }

    /**
     * �������� ���������� ���� � ���������� �� ������
     *
     * @param message ���������
     * @param title   ��������� ����
     * @param ex      �c��������
     */
    public static void showErrorMessageBox(String message, String title, Throwable ex) {
        StringBuilder sb = new StringBuilder(ex.toString());
        if (message != null) {
            sb.append(message);
        }
        if (ex != null) {
            sb.append("\n");
            for (StackTraceElement ste : ex.getStackTrace()) {
                sb.append("\n\tat ");
                sb.append(ste);
            }
        }
        JOptionPane.showMessageDialog(null, sb.toString(), title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * �������� ���������� ���� � ���������� �� ������
     *
     * @param message ���������
     * @param ex      �c��������
     */
    public static void showErrorMessageBox(String message, Throwable ex) {
        showErrorMessageBox(message, "������", ex);
    }

    /**
     * �������� ���������� ���� � ���������� �� ������
     *
     * @param ex �c��������
     */
    public static void showErrorMessageBox(Throwable ex) {
        showErrorMessageBox(null, ex);
    }

    /**
     * ��������� ����������� �� ��������� ��� ������, ������� �� ������������
     * (���������� ��������������� ������ ��� ������� ����, ������� ����
     * ��������, ��������, � ������������ �����, � �� � ������ main(...)
     */
    public static void setShowMessageDefaultErrorHandler() {
        JComponent comp;
        Thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable ex) -> {
            showErrorMessageBox(ex);
        });
    }

    /**
     * ������� �������������� ������� ��� ���������� Swing
     *
     * @param <T>    ��� ����������
     * @param comp   ���������
     * @param width  ������ ����������
     * @param height ������ ����������
     * @return
     */
    public static <T extends Component> T setFixedSize(T comp, int width, int height) {
        Dimension d = new Dimension(width, height);
        comp.setMaximumSize(d);
        comp.setMinimumSize(d);
        comp.setPreferredSize(d);
        comp.setSize(d);
        return comp;
    }

    /**
     * ��������� ���� ����������� (LookAndFeel) ��� ��������� ����������
     *
     * @param name �������� ����
     */
    public static void setLookAndFeelByName(String name) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (name.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    for (Window window : Window.getWindows()) {
                        Dimension size = window.getSize();
                        SwingUtilities.updateComponentTreeUI(window);
                        window.pack();
                        window.setSize(size);
                    }
                    break;
                }
            }
        } catch (Exception ex) {
            Class inner = new Object() {
            }.getClass();
            Logger.getGlobal().logp(Level.SEVERE, inner.getEnclosingClass().getName(), inner.getEnclosingMethod().getName(), null, ex);
        }
    }

    /**
     * ���������� ���� JMenu ���������� ��� ������ ��� ����������� (LookAndFeel)
     *
     * @param menu ���� ��� ���������� ������� ����
     */
    public static void initLookAndFeelMenu(JMenu menu) {
        ActionListener actionListener = (ActionEvent e) -> {
            JMenuItem menuItem = (JMenuItem) e.getSource();
            SwingUtils.setLookAndFeelByName(menuItem.getText());
        };
        ButtonGroup group = new ButtonGroup();
        LookAndFeel currentLookAndFeel = UIManager.getLookAndFeel();
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                continue;
            }
            JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem();
            menuItem.setText(info.getName());
            menuItem.setSelected(info.getName().equals(currentLookAndFeel.getName()));
            menuItem.addActionListener(actionListener);
            group.add(menuItem);
            menu.add(menuItem);
        }
    }

    /**
     * ��������� ������ �� ��������� ��� ��������� ����������
     *
     * @param fontName �������� ������ (null - �� ������)
     * @param size     ������ ������ (������ ��� ����� 0 - �� ������)
     */
    public static void setDefaultFont(String fontName, int size) {
        UIManager.getDefaults().entrySet().forEach((entry) -> {
            Object value = javax.swing.UIManager.get(entry.getKey());
            if (value != null && value instanceof FontUIResource) {
                FontUIResource fr = (FontUIResource) value;
                fr = new FontUIResource(
                        (fontName != null) ? fontName : fr.getFontName(),
                        fr.getStyle(),
                        (size > 0) ? size : fr.getSize()
                );
                UIManager.put(entry.getKey(), fr);
            }
        });
    }

    /**
     * ��������� ����� ������ �� ��������� ��� ��������� ����������
     *
     * @param fontName �������� ������ (null - �� ������)
     */
    public static void setDefaultFont(String fontName) {
        setDefaultFont(fontName, -1);
    }

    /**
     * ��������� ������� ������ �� ��������� ��� ��������� ����������
     *
     * @param size ������ ������ (������ ��� ����� 0 - �� ������)
     */
    public static void setDefaultFont(int size) {
        setDefaultFont(null, size);
    }

    public static String readStrFromFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName), "windows-1251");
        String str = scanner.nextLine();
        return str;
    }

    public static List<String> readLinesFromFileToList(String fileName) throws FileNotFoundException {
        List<String> lines;
        try (Scanner scanner = new Scanner(new File(fileName), "windows-1251")) {
            lines = new ArrayList<>();
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
            // �����������, ����� ������� �������� ����
        }
        return lines;
    }

    public static void printListInConsole(List<String> list) {
        int k = 0;
        for (int i = 0; i < list.size(); i++) {
            k++;
            System.out.print(list.get(i));
            if (k == 3) {
                System.out.print(";");
                System.out.println();
                k = 0;
            } else {
                System.out.print(" ");
            }
        }
    }
}
