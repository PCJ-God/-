
import javax.swing.*;

public class ConsumerController {
    private volatile boolean isConsuming = false;
    private final Warehouse warehouse;

    public ConsumerController(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void bindButton(JButton consumeBtn, ConsumePanel consumePanel, JPanel storagePanel) {
        consumeBtn.addActionListener(e -> handleConsumption(consumeBtn, consumePanel, storagePanel));
    }

    private void handleConsumption(JButton button, ConsumePanel panel, JPanel storagePanel) {
        synchronized (warehouse) {
            if (warehouse.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "仓库为空!");
                return;
            }
        }
        
        if (button.getText().equals("开始消费")) {
            startConsumption(button, panel, storagePanel);
        } else {
            stopConsumption(button);
        }
    }

    private void startConsumption(JButton button, ConsumePanel panel, JPanel storagePanel) {
        button.setText("暂停消费");
        isConsuming = true;
        new Thread(() -> {
            while (isConsuming) {
                animateConsumption(panel);
                removeFromWarehouse(storagePanel);
            }
        }).start();
    }

    private void animateConsumption(ConsumePanel panel) {
        for (int i = 0; i <= 600; i += 5) {
            if (!isConsuming) break;
            panel.updateProgress(i);
            try { Thread.sleep(20); } catch (InterruptedException ignored) {}
        }
        panel.updateProgress(-1);
    }

    private void removeFromWarehouse(JPanel storagePanel) {
        synchronized (warehouse) {
            if (warehouse.removeApple()) {
                storagePanel.repaint();
            } else {
                SwingUtilities.invokeLater(() -> 
                    JOptionPane.showMessageDialog(storagePanel, "仓库为空!"));
                isConsuming = false;
            }
        }
    }

    private void stopConsumption(JButton button) {
        button.setText("开始消费");
        isConsuming = false;
    }
}