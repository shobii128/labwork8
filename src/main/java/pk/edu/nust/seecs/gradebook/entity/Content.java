package pk.edu.nust.seecs.gradebook.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;


@Entity
public class Content implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "content_id")
    private Integer contentId;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date starttime;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endtime;
    
    /*
    * This relationship contains the students associated with this content.
    * The reverse relationship is present in the Student entity class
    */
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "content_students", joinColumns = {
        @JoinColumn(name = "content_id")}, inverseJoinColumns = {
        @JoinColumn(name = "student_id")})
    private Set<Student> students = new HashSet<>(0);
    
    /*
    * This relationship contains the course this content belongs to.
    * The reverse relationship is present in the Course entity class
    */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="course_id",nullable=false)
    private Course course;
    
    /*
    * This relationship contains the grades marked against this content.
    * The reverse relationship is present in the Grade entity class
    */
    @OneToMany
    private Set<Grade> grades = new HashSet<>(0);
    
    /*
    * This relationship contains the clo mapped to this content.
    * This should be converted to a 1-1 mapping, since only 1 clo can map to a content.
    * The reverse relationship is present in the Clo entity class
    */
    @OneToMany
    private List<Clo> clo = new ArrayList<>();
    
    /*
    * This method will return the id of this content.
    * Note; we have not defined any setter method for content id, since it is 
    * to be generated by the dbms.
    */
    public Integer getContentId() {
        return contentId;
    }
    /*
    * Class constructor.
    */
    public Content() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Clo> getClo() {
        return clo;
    }

    public void setClo(List<Clo> clo) {
        this.clo = clo;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Content{" + "contentId=" + contentId + ", title=" + title + ", description=" + description + ", starttime=" + starttime + ", endtime=" + endtime + ", students=" + students + ", course=" + course + ", grades=" + grades + ", clo=" + clo + '}';
    }
    

}
