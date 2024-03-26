package com.shiru.model.Entity;


public class User {


    public static final String COLUMNLABEL1 = "email";
    public static final String COLUMNLABEL2 = "nome";
    public static final String COLUMNLABEL3 = "cognome";

    private int id;
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private String telefono;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
    public String toString() {
        return "Utente{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
