package utilisateur;

import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import comportement.Ability;
import entreprise.Entreprise;

@Entity(name = User.USER)
// Entity_name
@Access(AccessType.PROPERTY)
// modif by getters/setters
public class User {
	public static final String USER = "User";
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Size(min = 2, message = "User name too short")
	private String userName;
	@NotNull
	@Size(min = 4, message = "passWord must contain more than 4 characters")
	private String passWord;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "COMPANY_NAME")
	private Entreprise entreprise;

	@NotNull
	private Ability ability;

	public User() {
	}

	public User(String userName, String passWord, Ability ability) {
		this.userName = userName;
		this.passWord = passWord;
		this.ability = Objects.requireNonNull(ability);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Ability getAbility() {
		return ability;
	}

	public void setAbility(Ability ability) {
		Objects.requireNonNull(ability);
		this.ability = ability;
	}
	
	public Entreprise getEntreprise() {
		return entreprise;
	}
	
	public void setEntreprise(Entreprise entreprise) {
		Objects.requireNonNull(entreprise);
		this.entreprise = entreprise;
	}
}
