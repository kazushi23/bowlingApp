package sg.bowling.bowlingapp.model;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Table;

@Entity
@Table(appliesTo = "bowlingball")
public class Bowlingball {
	// price, sold, meetup, condition, description, type(solid, reactive, urethane, plastic, polyester, hybrid)
	// brand
	
	@Id
	@SequenceGenerator(
			name= "bowlingball_sequence",
			sequenceName="bowlingball_sequence",
			allocationSize=1
	)
	@GeneratedValue(
			strategy= GenerationType.SEQUENCE,
			generator= "bowlingball_sequence"
	)
	private int id;
	
	@Column(length = 500)
	private String name; //ball name
	
	private LocalDate release; // release date
	private String coverstock; // solid reactive, urethane, pearl reactive, plastic, polyester, hybrid
	private String type; // heavy oil, med-high oil, med oil, med-light oil, light oil, spareBall
	private int grit; // grit finish
	private boolean symmetrical; //asym or sym
	private Double weight; // lbs
	private Double rg; // 2.52
	private Double diff; // 0.052
	private String ballImageLink; // s3
	private Double price; // $
	private boolean sold; // true or false
	private String gamesUsed; // 0,5, <10, <15, <20, <25, <30 ... >100
	private String description; // info about ball and location to meet
	private String brand; // storm, motiv ...
	private int drilled; // 1st, 2nd, 3rd, >4th
	private String drilledHand; // right 1 haneded, left 1 handed, 2 handed right, 2 handed left
	private boolean cracked; // true or false
	private Long userid; // who post
	
	public Bowlingball() {
		
	}
	
	public Bowlingball(String name, LocalDate release, String coverstock, String type, int grit, boolean symmetrical,
			Double weight, Double rg, Double diff, String ballImageLink, Double price, boolean sold,
			String gamesUsed, String description, String brand, int drilled, String drilledHand,
			boolean cracked, Long userid) {
		this.name = name;
		this.release = release;
		this.coverstock = coverstock;
		this.type = type;
		this.grit = grit;
		this.symmetrical = symmetrical;
		this.weight = weight;
		this.rg = rg;
		this.diff = diff;
		this.ballImageLink = ballImageLink;
		this.price = price;
		this.sold = sold;
		this.gamesUsed = gamesUsed;
		this.description = description;
		this.brand = brand;
		this.drilled = drilled;
		this.drilledHand = drilledHand;
		this.cracked = cracked;
		this.userid = userid;
	}
	
	public Bowlingball(int id, String name, LocalDate release, String coverstock, String type, int grit, boolean symmetrical,
			Double weight, Double rg, Double diff, String ballImageLink, Double price, boolean sold,
			String gamesUsed, String description, String brand, int drilled, String drilledHand,
			boolean cracked, Long userid) {
		this.id = id;
		this.name = name;
		this.release = release;
		this.coverstock = coverstock;
		this.type = type;
		this.grit = grit;
		this.symmetrical = symmetrical;
		this.weight = weight;
		this.rg = rg;
		this.diff = diff;
		this.ballImageLink = ballImageLink;
		this.price = price;
		this.sold = sold;
		this.gamesUsed = gamesUsed;
		this.description = description;
		this.brand = brand;
		this.drilled = drilled;
		this.drilledHand = drilledHand;
		this.cracked = cracked;
		this.userid = userid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getRelease() {
		return release;
	}

	public void setRelease(LocalDate release) {
		this.release = release;
	}

	public String getCoverstock() {
		return coverstock;
	}

	public void setCoverstock(String coverstock) {
		this.coverstock = coverstock;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getGrit() {
		return grit;
	}

	public void setGrit(int grit) {
		this.grit = grit;
	}

	public boolean isSymmetrical() {
		return symmetrical;
	}

	public void setSymmetrical(boolean symmetrical) {
		this.symmetrical = symmetrical;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getRg() {
		return rg;
	}

	public void setRg(Double rg) {
		this.rg = rg;
	}

	public Double getDiff() {
		return diff;
	}

	public void setDiff(Double diff) {
		this.diff = diff;
	}

	public String getBallImageLink() {
		return ballImageLink;
	}

	public void setBallImageLink(String ballImageLink) {
		this.ballImageLink = ballImageLink;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public String getGamesUsed() {
		return gamesUsed;
	}

	public void setGamesUsed(String gamesUsed) {
		this.gamesUsed = gamesUsed;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getDrilled() {
		return drilled;
	}

	public void setDrilled(int drilled) {
		this.drilled = drilled;
	}

	public String getDrilledHand() {
		return drilledHand;
	}

	public void setDrilledHand(String drilledHand) {
		this.drilledHand = drilledHand;
	}

	public boolean isCracked() {
		return cracked;
	}

	public void setCracked(boolean cracked) {
		this.cracked = cracked;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "Bowlingball [id=" + id + ", name=" + name + ", release=" + release + ", coverstock=" + coverstock
				+ ", type=" + type + ", grit=" + grit + ", symmetrical=" + symmetrical + ", weight=" + weight + ", rg="
				+ rg + ", diff=" + diff + ", ballImageLink=" + ballImageLink + ", price=" + price + ", sold=" + sold
				+ ", gamesUsed=" + gamesUsed + ", description=" + description + ", brand=" + brand + ", drilled="
				+ drilled + ", drilledHand=" + drilledHand + ", cracked=" + cracked + ", userid=" + userid + "]";
	}
		
}
