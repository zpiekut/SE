
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.DefaultListModel;




public class TrainModelUI extends javax.swing.JFrame
{

	private TrainModel TM;

	/**
	 * Creates new form TrainModelUI
	 */
	public TrainModelUI()
	{
		//initComponents();
		TM = new TrainModel();
		//updateAttributeList();
	}
	
	private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        attributeWindows = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        lightButton = new javax.swing.JButton();
        doorButton = new javax.swing.JButton();
        brakeButton = new javax.swing.JButton();
        emergencybrakeButton1 = new javax.swing.JButton();
        setTempButton = new javax.swing.JButton();
        speedButton = new javax.swing.JButton();
        speedTextField = new javax.swing.JTextField();
        tempTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        logWindows = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Train Model");

        attributeWindows.setModel(new DefaultListModel());
        attributeWindows.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(attributeWindows);

        jLabel2.setText("Overall Attribute");

        lightButton.setText("light");
        lightButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                lightButtonActionPerformed(evt);
            }
        });

        doorButton.setText("door");
        doorButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                doorButtonActionPerformed(evt);
            }
        });

        brakeButton.setText("brake");
        brakeButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                brakeButtonActionPerformed(evt);
            }
        });

        emergencybrakeButton1.setText("emergency brake");
        emergencybrakeButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                emergencybrakeButton1ActionPerformed(evt);
            }
        });

        setTempButton.setText("set temperature ('f)");
        setTempButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                setTempButtonActionPerformed(evt);
            }
        });

        speedButton.setText("set speed (mile/hr)");
        speedButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                speedButtonActionPerformed(evt);
            }
        });

        speedTextField.setText("0");

        tempTextField.setText("70");

        logWindows.setModel(new DefaultListModel());
        logWindows.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(logWindows);

        jLabel1.setText("Log");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(49, 49, 49)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel2)
                    .add(layout.createSequentialGroup()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 294, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(setTempButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 153, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(tempTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 188, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(lightButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 153, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(doorButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 153, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(brakeButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 153, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(emergencybrakeButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 153, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(layout.createSequentialGroup()
                                .add(speedButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 153, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(speedTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 188, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 650, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(34, 34, 34)
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(lightButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(doorButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(brakeButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(emergencybrakeButton1)
                        .add(11, 11, 11)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(speedButton)
                            .add(speedTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(setTempButton)
                            .add(tempTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 228, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(22, 22, 22)
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 175, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void lightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lightButtonActionPerformed
			if (TM.isLightCondition())
			{
				getDefaultListModel(logWindows).addElement("turn off lights");
			} else
			{
				getDefaultListModel(logWindows).addElement("turn on lights");
			}
        }//GEN-LAST:event_lightButtonActionPerformed

        private void doorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doorButtonActionPerformed

			if (TM.isDoorCondition())
			{
				getDefaultListModel(logWindows).addElement("close doors");
			} else
			{
				getDefaultListModel(logWindows).addElement("open doors");
			}
        }//GEN-LAST:event_doorButtonActionPerformed

        private void brakeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brakeButtonActionPerformed
			// TODO add your handling code here:
			getDefaultListModel(logWindows).addElement("brake");
        }//GEN-LAST:event_brakeButtonActionPerformed

        private void emergencybrakeButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emergencybrakeButton1ActionPerformed
			// TODO add your handling code here:
			getDefaultListModel(logWindows).addElement("emergency brake");
        }//GEN-LAST:event_emergencybrakeButton1ActionPerformed

        private void setTempButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setTempButtonActionPerformed
			// TODO add your handling code here:
			String temp = tempTextField.getText();

			getDefaultListModel(logWindows).addElement("set temparature to " + temp + " 'f");
        }//GEN-LAST:event_setTempButtonActionPerformed

        private void speedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speedButtonActionPerformed
			// TODO add your handling code here:
			String spd = speedTextField.getText();
			getDefaultListModel(logWindows).addElement("set speed to " + spd + " mile/hr");
        }//GEN-LAST:event_speedButtonActionPerformed


	public static void main(String args[])
	{

		try
		{
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
			{
				if ("Nimbus".equals(info.getName()))
				{
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex)
		{
			java.util.logging.Logger.getLogger(TrainModelUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex)
		{
			java.util.logging.Logger.getLogger(TrainModelUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex)
		{
			java.util.logging.Logger.getLogger(TrainModelUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex)
		{
			java.util.logging.Logger.getLogger(TrainModelUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				new TrainModelUI().setVisible(true);
			}
		});
	}

	private javax.swing.JList attributeWindows;
    private javax.swing.JButton brakeButton;
    private javax.swing.JButton doorButton;
    private javax.swing.JButton emergencybrakeButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton lightButton;
    private javax.swing.JList logWindows;
    private javax.swing.JButton setTempButton;
    private javax.swing.JButton speedButton;
    private javax.swing.JTextField speedTextField;
    private javax.swing.JTextField tempTextField;

}