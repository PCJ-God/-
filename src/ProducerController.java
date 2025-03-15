
import javax.swing.*;

public class ProducerController {
    private volatile boolean isProducing = false;
    private final Warehouse warehouse;

    public ProducerController(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void bindButton(JButton produceBtn, ProducePanel producePanel, JPanel storagePanel) {
        produceBtn.addActionListener(e -> handleProduction(produceBtn, producePanel, storagePanel));
    }

    private void handleProduction(JButton button, ProducePanel panel, JPanel storagePanel) {
        synchronized (warehouse) {
            if (warehouse.isFull()) {
                JOptionPane.showMessageDialog(panel, "仓库已满!");
                return;
            }
        }
        
        if (button.getText().equals("开始生产")) {
            startProduction(button, panel, storagePanel);
        } else {
            stopProduction(button);
        }
    }

    private void startProduction(JButton button, ProducePanel panel, JPanel storagePanel) {
        button.setText("暂停生产");
        isProducing = true;
        MutableInt progress = new MutableInt(0);
        new Thread(() -> {
            while (isProducing) {
                animateProduction(panel , progress);
                
                if(progress.get() >= 600){
                    addToWarehouse(button, storagePanel);
                    progress.set(0);
                }
            }
        }).start();
    }

    private void animateProduction(ProducePanel panel , MutableInt progress) {
        for (int i = 0; i <= 600; i += 5) {
            if (!isProducing) break;
            progress.set(i);
            panel.updateProgress(i);
            try { Thread.sleep(20); } catch (InterruptedException ignored) {}
        }
        panel.updateProgress(-1);
    }

    private void addToWarehouse(JButton button, JPanel storagePanel) {
        synchronized (warehouse) {
            if (warehouse.addApple()) {
                storagePanel.repaint();
            } else {
                SwingUtilities.invokeLater(() -> {
                    button.setText("开始生产");
                    JOptionPane.showMessageDialog(button.getParent(), "仓库已满!");
                });
                isProducing = false;
            }
        }
    }

    private void stopProduction(JButton button) {
        button.setText("开始生产");
        isProducing = false;
    }
}