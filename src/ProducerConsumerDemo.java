import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
// 新增导入


public class ProducerConsumerDemo {
    private static final int MAX_CAPACITY = 12;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Warehouse warehouse = new Warehouse(MAX_CAPACITY);
            
            // 初始化各组件
            ProducePanel producePanel = new ProducePanel();
            StoragePanel storagePanel = new StoragePanel(warehouse);
            ConsumePanel consumePanel = new ConsumePanel();
            
            // 创建控制器
            ProducerController producerCtrl = new ProducerController(warehouse);
            ConsumerController consumerCtrl = new ConsumerController(warehouse);

            // 创建主窗口
            JFrame frame = new JFrame("姓名：张绍杰  学号：632307110102    模拟生产者与消费者问题") {
                @Override
                public void paint(Graphics g) {
                    super.paint(g);
                    g.drawString("窗口尺寸：800x600 像素 | 包含3个800x200面板", 250, 30);
                }
            };

            // 创建按钮
            JButton produceBtn = new JButton("开始生产");
            JButton consumeBtn = new JButton("开始消费");
            
            // 绑定控制器
            producerCtrl.bindButton(produceBtn, producePanel, storagePanel);
            consumerCtrl.bindButton(consumeBtn, consumePanel, storagePanel);

            // 组装界面
            JPanel mainPanel = new JPanel(new GridLayout(3, 1));
            producePanel.add(produceBtn);
            consumePanel.add(consumeBtn);
            mainPanel.add(producePanel);
            mainPanel.add(storagePanel);
            mainPanel.add(consumePanel);
            
            frame.add(mainPanel);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}