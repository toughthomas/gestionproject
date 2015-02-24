package wpws;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import wpws.WPMaturity.State;
import livre.Chapter;
import livre.Volume;

@Entity
public class WorkPackage {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private WPMaturity.State status;

	@ManyToOne
	@JoinColumn(name="WS_ID")
	private WorkSpace assignedTo;
	
	@OneToMany(mappedBy="wp")
	private Set<Volume> vols = new HashSet<>();
	
	@OneToMany(mappedBy="wp")
	private Set<Chapter> chaps = new HashSet<Chapter>();
	
	public WorkPackage(){
		
	}
	
	public WorkPackage(WorkSpace assignedTo,String name) {
		this.status = State.Start;
		this.assignedTo = assignedTo;
		this.vols = new HashSet<Volume>();
		this.chaps = new HashSet<Chapter>();
		this.name = name;
	}


	//Attribue un volume à un workPackage
	public boolean addVolume(Volume volume){
		Objects.requireNonNull(volume);
		return vols.add(volume);
	}
	
	//Attribue un chapitre au workpackage
	public boolean addChapter(Chapter chapterToAdd){
		Objects.requireNonNull(chapterToAdd);
		return chaps.add(chapterToAdd);
	}
	
	public boolean removeChapter(Chapter chapterToAdd) {
		Objects.requireNonNull(chapterToAdd);
		return chaps.remove(chapterToAdd);
	}
	
	public boolean removeVolume(Volume v) {
		Objects.requireNonNull(v);
		return vols.remove(v);
	}
	
	/**
	 * 
	 * @return la liste des chapites contenus dans ce WP
	 */
	
	public List<Chapter> getAllChapters(){
		return new ArrayList<>(chaps);
	}
	/**
	 * 
	 * @return la liste des volumes contenus dans ce WP
	 */
	public List<Volume> getAllVolumes(){
		return new ArrayList<>(vols);
	}
	//Recupère le statut courant du workpackage
	public WPMaturity.State getStatus(){
		return this.status;
	}

	//Ajouter un WorkPackage
	public void setStatus(WPMaturity.State status){
		this.status = status;
	}

	public void setAssignedTo(WorkSpace assignedTo) {
		this.assignedTo = Objects.requireNonNull(assignedTo);
	}

	public WorkSpace getAssignedTo() {
		return assignedTo;
	}

	public String getName() {
		return name;
	}
	
public void setName(String name) {
	this.name = name;
}
}
