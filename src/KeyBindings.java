import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

public class KeyBindings 
{
	private final String PILE_A_PICK = "pile a pick";
	private final String PILE_B_PICK = "pile b pick";
	private final String PILE_C_PICK = "pile c pick";
	
	public KeyBindings() 
	{
		JLabel label = new JLabel();
		label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), PILE_A_PICK);
		label.getActionMap().put(PILE_A_PICK, new PilePickAction("A"));
		
		label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("B"), PILE_B_PICK);
		label.getActionMap().put(PILE_B_PICK, new PilePickAction("B"));
		
		label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("C"), PILE_C_PICK);
		label.getActionMap().put(PILE_C_PICK, new PilePickAction("C"));
	}
	
	private class PilePickAction extends AbstractAction
	{
		String pile;
		
		public PilePickAction(String str) 
		{
			pile = str;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (pile) {
			case "A":
				SimpleNimGame.ballsA.remove(0);
				break;
			case "B":
				SimpleNimGame.ballsB.remove(0);
				break;
			case "C":
				SimpleNimGame.ballsC.remove(0);
				break;
			}
		}
	}
}