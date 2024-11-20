package modelo;

public enum StatusConta {
	ATIVA("ativa"),
	SUSPENSA("suspensa"), 
	EXCLUIDA("excluia");
	
	private final String status;
	
	private StatusConta(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return status;
	}
}
