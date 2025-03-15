

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class ProducePanel extends JPanel {
    private int progress = -1;

    public void updateProgress(int progress) {
        this.progress = progress;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 新增标题绘制
        g.setColor(Color.BLACK);
        g.setFont(new Font("宋体", Font.BOLD, 16));
        g.drawString("生产轨道", 20, 30);
        // 绘制生产轨道
        g.setColor(Color.GRAY);
        g.drawRect(100, 75, 630, 40);
        // 绘制移动中的苹果
        if (progress >= 0) {
            g.setColor(Color.RED);
            g.fillOval(100 + progress, 80, 30, 30);
        }
    }
}