package edu.iastate.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "Admin")
public class Admin extends Member {
	
    public Admin() {
        super();
    }

    public Admin(String name, String username, String password) {
        super(name, username, password, UserType.ADMIN);
    }

    public enum View {ADMIN, PLAYER, OFFICIAL}; 
    
    /**
     * 
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "current_view")
    private View curView;
    
    public View getCurView() {
        return curView;
    }
    
    public void setCurView(View newCurView) {
        this.curView = newCurView;
    }
	
}
