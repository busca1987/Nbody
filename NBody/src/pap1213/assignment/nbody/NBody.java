package pap1213.assignment.nbody;

public class NBody {
	public static void main(String[] args) {
		
		Universe universe = new Universe();
		Context ctx = new Context(universe);
		
		universe.setContext(ctx);
		ControlPanel control = new ControlPanel(ctx);
		control.setVisible(true);
	}

}
