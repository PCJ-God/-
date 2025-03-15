

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class ConsumePanel extends JPanel {
    private int progress = -1;

    public void updateProgress(int progress) {
        this.progress = progress;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("宋体", Font.BOLD, 16));
        g.drawString("消费轨道", 20, 30); 
        // 绘制消费轨道
        g.setColor(Color.GRAY);
        g.drawRect(100, 75, 630, 40);
        // 绘制移动中的苹果
        if (progress >= 0) {
            g.setColor(Color.RED);
            g.fillOval(100 + progress, 80, 30, 30);
        }
    }
}