
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class StoragePanel extends JPanel {
    private final Warehouse warehouse;

    public StoragePanel(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void updateDisplay() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("宋体", Font.BOLD, 16));
        g.drawString("仓库", getWidth()/2 - 30, 30);
        // 绘制仓库轨道
        g.setColor(Color.GRAY);
        g.drawRect(50, 50, 700, 100);
        
        // 显示数量
        g.setColor(Color.BLACK);
        g.drawString("当前苹果数量: " + warehouse.size(), 50, 40);

        // 绘制苹果
        int x = 100;
        synchronized (warehouse) {
            for (int i = 0; i < warehouse.size(); i++) {
                g.setColor(Color.RED);
                g.fillOval(x, 80, 30, 30);
                x += 50;
            }
        }
    }
}