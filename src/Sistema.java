import java.io.IOException;

public abstract class Sistema {
	

	public static void main(String[] args) throws IOException {

		Usuario.lectorDeUsuario();
		System.out.println(Usuario.User());
	}

}
