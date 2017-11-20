package security;

public class Diffhelman {
	// ------------ diffhelman
		// find key
		public static int find_key(int q, int alpha, int xa, int xb) {

			int ya = Math.floorMod((int) Math.pow(alpha, xa), q);
			int yb = Math.floorMod((int) Math.pow(alpha, xb), q);

			System.out.println(ya + "    " + yb);
			int k_sender = Math.floorMod((int) Math.pow(yb, xa), q);
			int k_reciever = Math.floorMod((int) Math.pow(ya, xb), q);
			if (k_reciever == k_sender) {

				return k_reciever;
			} else {
				System.out.println("Error Key sender not eqal key recieveer ");
				return 0;
			}

		}
}
